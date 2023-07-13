package lec.baekseokuniv.ssiholder.credentiallist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import lec.baekseokuniv.ssiholder.R
import lec.baekseokuniv.ssiholder.app.App
import lec.baekseokuniv.ssiholder.data.CredentialFilter
import lec.baekseokuniv.ssiholder.databinding.FragmentCredentialListBinding
import lec.baekseokuniv.ssiholder.repository.CredentialRepository

class CredentialListFragment : Fragment() {
    private val credentialRepository = CredentialRepository()
    private lateinit var binder: FragmentCredentialListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binder = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_credential_list, container, false
        )
        binder.rvCredentialList.adapter = CredentialListRecyclerViewAdapter(
            credentialRepository.getCredentialList(
                (requireActivity().application as App).wallet,
                CredentialFilter(
                    "EtAGQxkwjMBgCkG4M6jXjP:2:FIANL-TEST:1.0",
                    schema_name = null,
                    schema_version = null,
                    cred_def_id = null
                ),
            )
        )
        return binder.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = CredentialListFragment()
    }
}