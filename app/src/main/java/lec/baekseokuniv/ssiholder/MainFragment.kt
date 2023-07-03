package lec.baekseokuniv.ssiholder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import lec.baekseokuniv.ssiholder.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binder: FragmentMainBinding

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binder = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        "Hello, SSI Holder!".also { binder.txtWelcoming.text = it }

        return binder.root
    }
}