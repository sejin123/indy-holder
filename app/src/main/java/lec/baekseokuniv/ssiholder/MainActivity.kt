package lec.baekseokuniv.ssiholder

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import lec.baekseokuniv.ssiholder.databinding.ActivityMainBinding


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