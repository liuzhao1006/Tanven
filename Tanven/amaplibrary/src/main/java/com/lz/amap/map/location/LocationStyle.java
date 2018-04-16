package com.lz.amap.map.location;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.MyLocationStyle;

/**
 * 刘朝
 * 时间: 2018/4/16 11:19
 * 描述: 定位图标 主要功能有: 1. 初始化定位;定位模式,是否设置定位小蓝点.
 *
 *                        2. 小蓝点的属性: 自定义蓝点图标,自定义蓝点图标锚点
 *
 *                        3. 设置定位精确度,是否连续定位.
 */
public class LocationStyle {

    private AMap aMap;
    private MyLocationStyle myLocationStyle;

    public LocationStyle(AMap aMap) {
        this.aMap = aMap;
        //初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle = new MyLocationStyle();
        //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.interval(2000);
        //设置定位蓝点的Style
        this.aMap.setMyLocationStyle(myLocationStyle);
        //设置默认定位按钮是否显示，非必需设置。
        //aMap.getUiSettings().setMyLocationButtonEnabled(true);
        // 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.setMyLocationEnabled(true);

    }


    /**
     * 定位模式
     *
     * @param type 定位蓝点提供8种模式：
     */
    public void locationModel(int type) {
        switch (type) {
            case MyLocationStyle.LOCATION_TYPE_SHOW:
                //只定位一次。
                myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_SHOW);
                break;
            case MyLocationStyle.LOCATION_TYPE_LOCATE:
                //定位一次，且将视角移动到地图中心点。
                myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);
                break;
            case MyLocationStyle.LOCATION_TYPE_FOLLOW:
                //连续定位、且将视角移动到地图中心点，定位蓝点跟随设备移动。（1秒1次定位）
                myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW);
                break;
            case MyLocationStyle.LOCATION_TYPE_MAP_ROTATE:
                //连续定位、且将视角移动到地图中心点，地图依照设备方向旋转，定位点会跟随设备移动。（1秒1次定位）
                myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_MAP_ROTATE);
                break;
            case MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE:
                //连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）默认执行此种模式。
                myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);
                break;
            case MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER:
                //以下三种模式从5.1.0版本开始提供
                //连续定位、蓝点不会移动到地图中心点，定位点依照设备方向旋转，并且蓝点会跟随设备移动。
                myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER);
                break;
            case MyLocationStyle.LOCATION_TYPE_FOLLOW_NO_CENTER:
                //连续定位、蓝点不会移动到地图中心点，并且蓝点会跟随设备移动。
                myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW_NO_CENTER);
                break;
            case MyLocationStyle.LOCATION_TYPE_MAP_ROTATE_NO_CENTER:
                //连续定位、蓝点不会移动到地图中心点，地图依照设备方向旋转，并且蓝点会跟随设备移动。
                myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_MAP_ROTATE_NO_CENTER);
                break;

        }
    }


    /**
     * 是否显示定位蓝点
     * 控制是否显示定位蓝点
     *
     * @param visible 是否显示定位蓝点
     */
    public void showMyLocation(boolean visible) {
        //方法自5.1.0版本后支持
        //设置是否显示定位小蓝点，用于满足只想使用定位，不想使用定位小蓝点的场景，设置false以后图面上不再有定位蓝点的概念，但是会持续回调位置信息。
        myLocationStyle.showMyLocation(visible);
    }


    /**
     * 自定义定位蓝点图标：
     * 定位蓝点图标自定义：
     *
     * @param icon 自定义定位蓝点图标：
     */
    public void myLocationIcon(BitmapDescriptor icon) {
        myLocationStyle.myLocationIcon(icon);
    }

    /**
     * 自定义定位蓝点图标的锚点：
     * 锚点是指定位蓝点图标像素与定位蓝点坐标的关联点，例如需要将图标的左下方像素点与定位蓝点的经纬度关联在一起，通过如下方法传入（0.0,1.0）。图标左上点为像素原点。
     *
     * @param u
     * @param v
     */
    public void anchor(float u, float v) {
        //设置定位蓝点图标的锚点方法。
        myLocationStyle.anchor(u, v);
    }

    /**
     * 精度圆圈的自定义：
     * 精度圈颜色自定义方法如下
     *
     * @param width           设置定位蓝点精度圈的边框宽度的方法。
     * @param colorStorke     设置定位蓝点精度圆圈的边框颜色的方法。
     * @param colorRadiusFill 设置定位蓝点精度圆圈的边框颜色的方法。
     * @param interval        设置定位频次方法，单位：毫秒，默认值：1000毫秒，如果传小于1000的任何值将按照1000计算。该方法只会作用在会执行连续定位的工作模式上。
     *                        MyLocationStyle.LOCATION_TYPE_FOLLOW ;//连续定位、且将视角移动到地图中心点，定位蓝点跟随设备移动。（默认1秒1次定位）
     *                        MyLocationStyle.LOCATION_TYPE_MAP_ROTATE;//连续定位、且将视角移动到地图中心点，地图依照设备方向旋转，定位点会跟随设备移动。（默认1秒1次定位）
     *                        MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE;//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（默认1秒1次定位）默认执行此种模式。
     */
    public void strokeColor(float width, int colorStorke, int colorRadiusFill, long interval) {
        myLocationStyle.strokeWidth(width).strokeColor(colorStorke).radiusFillColor(colorRadiusFill).interval(interval);
    }




}
