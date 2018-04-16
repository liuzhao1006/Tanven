package com.lz.amap.map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;

/**
 * 刘朝
 * 时间: 2018/4/15 19:47
 * 描述: 地图管理 单例工具
 */
public class AMapManager {

    public static AMapManager mapInstance;

    public static synchronized AMapManager getMapInstance(Activity activity) {
        if (mapInstance == null) {
            synchronized (AMapManager.class) {
                if (mapInstance == null) {
                    mapInstance = new AMapManager(activity);
                }
            }
        }
        return mapInstance;
    }

    private Activity activity;

    private AMapManager(Activity activity) {
        this.activity = activity;
    }

    MapView mMapView = null;
    AMap aMap = null;

    //创建地图
    public void onCreate(Bundle savedInstanceState, MapView mMapView){
        this.mMapView = mMapView;
        this.mMapView.onCreate(savedInstanceState);
        //初始化地图控制器对象
        if (aMap == null) {
            aMap = this.mMapView.getMap();
        }
    }

    public void onDestory(){
        mMapView.onDestroy();
    }


    public void onPause() {
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }
    public void onResume() {
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }


    /**
     * 是否启用离线地图
     * @param isOffLine true 启用离线地图.
     */
    public void offLineMap(boolean isOffLine){
        //在Activity页面调用startActvity启动离线地图组件
        if(isOffLine)
        activity.startActivity(new Intent(activity.getApplicationContext(),
                com.amap.api.maps.offlinemap.OfflineMapActivity.class));
    }




    


}
