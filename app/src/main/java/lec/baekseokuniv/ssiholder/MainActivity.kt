package lec.baekseokuniv.ssiholder

import android.os.Bundle
import android.system.Os
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import lec.baekseokuniv.ssiholder.config.PoolConfig
import lec.baekseokuniv.ssiholder.config.WalletConfig
import lec.baekseokuniv.ssiholder.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binder: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //TODO
        // 1. type = null 인데, 이 부분 확인
        // 2. manifest 등에서 설정할 수 있는지 여부 확인
        //지갑 설정 과정
        //1. load indy library
        Os.setenv("EXTERNAL_STORAGE", getExternalFilesDir(null)?.absolutePath, true)
        System.loadLibrary("indy")

        //2. create pool
        PoolConfig.createPool()

        //3. create and then open wallet
        val wallet = WalletConfig.openWallet()

        //4. create secret
        val masterSecretId = WalletConfig.createMasterSecretId(wallet)

        supportFragmentManager
            .beginTransaction()
            .add(binder.container.id, MainFragment.newInstance())
            .commit()

        setSupportActionBar(binder.appBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun navigateToFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(binder.container.id, fragment)
            .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}