package com.lz.amap.map.location;

import com.amap.api.maps.AMap;

/**
 * 刘朝
 * 时间: 2018/4/16 11:53
 * 描述: 地图图层
 * <p>
 * Android 地图 SDK 提供了几种预置的地图图层，包括卫星图、白昼地图（即最常见的黄白色地图）、夜景地图、导航地图、路况图层。
 * 注意：路况图层是通过开关控制，不通过常量控制。
 * MAP_TYPE_NAVI 导航地图
 * MAP_TYPE_NIGHT 夜景地图
 * MAP_TYPE_NORMAL 白昼地图（即普通地图）
 * MAP_TYPE_SATELLITE 卫星图
 */
public class MapLayer {
    public MapLayer(AMap aMap, int type,boolean isEnable) {
        switch (type) {
            case AMap.MAP_TYPE_NAVI:
                aMap.setMapType(AMap.MAP_TYPE_NAVI);//导航图
                break;
            case AMap.MAP_TYPE_NIGHT:
                aMap.setMapType(AMap.MAP_TYPE_NIGHT);//夜景地图，aMap是地图控制器对象。
                break;
            case AMap.MAP_TYPE_NORMAL:
                aMap.setMapType(AMap.MAP_TYPE_NORMAL);//白昼地图（即普通地图）
                break;
            case AMap.MAP_TYPE_SATELLITE:
                aMap.setMapType(AMap.MAP_TYPE_SATELLITE);// 设置卫星地图模式，aMap是地图控制器对象。
                break;
        }
        //路况图依据实时路况数据渲染，与前两种设置方式不太相同，路况图实现的方法如下：
        aMap.setTrafficEnabled(isEnable);//显示实时路况图层，aMap是地图控制器对象。
    }


}
