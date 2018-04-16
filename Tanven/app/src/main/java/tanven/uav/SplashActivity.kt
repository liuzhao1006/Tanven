package tanven.uav

import android.content.Intent
import android.os.Bundle
import com.lz.amap.base.BaseActivity
import tanven.uav.login.LoginActivity

class SplashActivity : BaseActivity() {
    override fun initView(savedInstanceState: Bundle?) = startActivity(Intent(this,LoginActivity::class.java))
    override fun getLayoutId(): Int  = R.layout.activity_splash
}
