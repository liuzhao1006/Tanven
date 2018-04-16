package com.lz.amap.permission;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.nfc.Tag;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.lz.amap.R;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * 刘朝
 * 时间: 2018/4/16 09:47
 * 描述:
 */
public class PermissionManager {

    private static PermissionManager permissionInstance;

    private PermissionManager(Context context) {
        this.context = context;
    }

    private Context context;

    public static synchronized PermissionManager getPermissionInstance(Context context) {
        if (permissionInstance == null) {
            synchronized (PermissionManager.class) {
                if (permissionInstance == null) {
                    permissionInstance = new PermissionManager(context);
                }
            }
        }
        return permissionInstance;
    }


    /**
     * 检查权限
     */
    public void checkPermission(){
        Log.i(TAG,"checkPermission");
        if (Build.VERSION.SDK_INT >= 23
                && context.getApplicationInfo().targetSdkVersion >= 23) {
            if (isNeedCheck) {
                checkPermissions(needPermissions);
            }
        }
    }

    /**
     * 需要进行检测的权限数组
     */
    private String[] needPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };

    public static final int PERMISSON_REQUESTCODE = 0;


    /**
     * 判断是否需要检测，防止不停的弹框
     */
    public boolean isNeedCheck = true;

    public boolean isNeedCheck() {
        return isNeedCheck;
    }

    public void setNeedCheck(boolean needCheck) {
        isNeedCheck = needCheck;
    }

    /**
     * @param permissions
     * @since 2.5.0
     */
    public void checkPermissions(String... permissions) {
        try {
            if (Build.VERSION.SDK_INT >= 23
                    && context.getApplicationInfo().targetSdkVersion >= 23) {
                Log.i(TAG,"checkPermission(...)");
                List<String> needRequestPermissonList = findDeniedPermissions(permissions);
                if (null != needRequestPermissonList
                        && needRequestPermissonList.size() > 0) {
                    String[] array = needRequestPermissonList.toArray(new String[needRequestPermissonList.size()]);
                    Method method = getClass().getMethod("requestPermissions", new Class[]{String[].class,
                            int.class});

                    method.invoke(this, array, PERMISSON_REQUESTCODE);
                }
            }
        } catch (Throwable e) {
        }
    }


    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     * @since 2.5.0
     */
    public List<String> findDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissonList = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= 23
                && context.getApplicationInfo().targetSdkVersion >= 23) {
            try {
                for (String perm : permissions) {
                    Method checkSelfMethod = getClass().getMethod("checkSelfPermission", String.class);
                    Method shouldShowRequestPermissionRationaleMethod = getClass().getMethod("shouldShowRequestPermissionRationale",
                            String.class);
                    if ((Integer) checkSelfMethod.invoke(this, perm) != PackageManager.PERMISSION_GRANTED
                            || (Boolean) shouldShowRequestPermissionRationaleMethod.invoke(this, perm)) {
                        needRequestPermissonList.add(perm);
                    }
                }
            } catch (Throwable e) {

            }
        }
        return needRequestPermissonList;
    }

    /**
     * 检测是否所有的权限都已经授权
     *
     * @param grantResults
     * @return
     * @since 2.5.0
     */
    public boolean verifyPermissions(int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }



    /**
     * 显示提示信息
     *
     * @since 2.5.0
     */
    public void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.notifyTitle);
        builder.setMessage(R.string.notifyMsg);

        // 拒绝, 退出应用
        builder.setNegativeButton(R.string.cancel,
                (dialog, which) -> {
                    if (failedPermission != null) {
                        failedPermission.onRefuse();
                    }
                });

        builder.setPositiveButton(R.string.setting,
                (dialog, which) -> {
                    if (successedPermission != null) {
                        successedPermission.onSuccessed();
                    }
                });

        builder.setCancelable(false);

        builder.show();
    }


    public interface IDialogFailedPermission {
        void onRefuse();
    }


    public interface IDialogSuccessedPermission {
        void onSuccessed();
    }

    private IDialogFailedPermission failedPermission;
    private IDialogSuccessedPermission successedPermission;

    /**
     * 拒绝权限
     */
    public void setDialogFailedPermission(IDialogFailedPermission failedPermission) {
        this.failedPermission = failedPermission;
    }

    /**
     * 成功申请权限
     */
    public void setDialogsuccessedPermission(IDialogSuccessedPermission successedPermission) {
        this.successedPermission = successedPermission;
    }





}
