package lec.baekseokuniv.ssiholder.issue

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import lec.baekseokuniv.ssiholder.R
import lec.baekseokuniv.ssiholder.databinding.FragmentIssueBinding

private const val ARG_ISSUING_DATA = "argument_issuing_data"

class IssueFragment : Fragment() {
    private lateinit var binder: FragmentIssueBinding
    private var issuingData: String? = null

    companion object {
        fun newInstance(issuingData: String? = null) =
            IssueFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_ISSUING_DATA, issuingData)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            issuingData = it.getString(ARG_ISSUING_DATA)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binder = DataBindingUtil.inflate(inflater, R.layout.fragment_issue, container, false)
        return binder.root
    }

}