package tanven.uav.map;

import android.os.Bundle;


import com.amap.api.maps.MapView;
import com.lz.amap.base.BaseActivity;
import com.lz.amap.map.AMapManager;

import tanven.uav.R;

public class MapActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_map;
    }
    @Override
    protected void initView(Bundle savedInstanceState) {
        MapView mMapview = findViewById(R.id.map);

        AMapManager.getMapInstance(this).onCreate(savedInstanceState,mMapview);

    }


}
