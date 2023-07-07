package lec.baekseokuniv.ssiholder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.trace
import androidx.databinding.DataBindingUtil
import lec.baekseokuniv.ssiholder.credentiallist.CredentialListFragment
import lec.baekseokuniv.ssiholder.databinding.FragmentMainBinding
import lec.baekseokuniv.ssiholder.issue.IssueFragment
import lec.baekseokuniv.ssiholder.verification.VerificationFragment

class MainFragment : Fragment() {
    private lateinit var binder: FragmentMainBinding

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binder = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        "Hello, SSI Holder!".also { binder.txtWelcoming.text = it }

        val mainActivity: MainActivity = requireActivity() as MainActivity
        binder.btnNavigateToIssue.setOnClickListener {
            mainActivity.navigateToFragment(IssueFragment.newInstance())
        }
        binder.btnNavigateToVerification.setOnClickListener {
            mainActivity.navigateToFragment(VerificationFragment.newInstance())
        }
        binder.btnNavigateToCredentialList.setOnClickListener {
            mainActivity.navigateToFragment(CredentialListFragment.newInstance())
        }
        return binder.root
    }
}