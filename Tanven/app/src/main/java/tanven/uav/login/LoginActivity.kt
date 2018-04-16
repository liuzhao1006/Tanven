package tanven.uav.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.lz.amap.base.BaseActivity
import tanven.uav.R
import tanven.uav.map.MapActivity

class LoginActivity : BaseActivity() {
    override fun getLayoutId(): Int =R.layout.activity_login
    override fun initView(savedInstanceState: Bundle?) =startActivity(Intent(this,MapActivity::class.java))
}
