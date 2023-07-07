package lec.baekseokuniv.ssiholder.verification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import lec.baekseokuniv.ssiholder.R
import lec.baekseokuniv.ssiholder.databinding.FragmentVerificationBinding

private const val ARG_VERIFIABLE_CREDENTIAL = "argument_verifiable_credential"

class VerificationFragment : Fragment() {
    private lateinit var binder: FragmentVerificationBinding
    private var vc: String? = null

    companion object {
        @JvmStatic
        fun newInstance(vc: String? = null) =
            VerificationFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_VERIFIABLE_CREDENTIAL, vc)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            vc = it.getString(ARG_VERIFIABLE_CREDENTIAL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binder = DataBindingUtil.inflate(inflater, R.layout.fragment_verification, container, false)
        return binder.root
    }
}