package lec.baekseokuniv.ssiholder

import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import lec.baekseokuniv.ssiholder.databinding.ActivityMainBinding
import lec.baekseokuniv.ssiholder.issue.IssueFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binder: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = DataBindingUtil.setContentView(this, R.layout.activity_main);

        supportFragmentManager
            .beginTransaction()
            .add(binder.container.id, MainFragment.newInstance())
            .commit()

        setSupportActionBar(binder.appBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // deeplink 처리
        val data: Uri = intent?.data ?: return
        val param = data.getQueryParameter("secret") ?: return
        supportFragmentManager
            .beginTransaction()
            .add(binder.container.id, IssueFragment.newInstance(param))
            .commit()
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