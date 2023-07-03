package lec.baekseokuniv.ssiholder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.system.Os
import androidx.databinding.DataBindingUtil
import lec.baekseokuniv.ssiholder.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binder: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(binder.appBar)

        //TODO
        // 1. type = null 인데, 이 부분 확인
        // 2. manifest 등에서 설정할 수 있는지 여부 확인
        Os.setenv("EXTERNAL_STORAGE", getExternalFilesDir(null)?.absolutePath, true)
        System.loadLibrary("indy")
    }
}