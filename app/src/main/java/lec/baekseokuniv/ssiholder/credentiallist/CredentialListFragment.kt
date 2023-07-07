package lec.baekseokuniv.ssiholder.credentiallist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import lec.baekseokuniv.ssiholder.R
import lec.baekseokuniv.ssiholder.data.Credential
import lec.baekseokuniv.ssiholder.databinding.FragmentCredentialListBinding

class CredentialListFragment : Fragment() {
    private lateinit var binder: FragmentCredentialListBinding
    private val credentialList = listOf<Credential>()   //FIXME: 실제 데이터를 붙일 때 해당 변수 수정 예정 (20230706 ksh)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binder = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_credential_list, container, false
        )
        binder.rvCredentialList.adapter = CredentialListRecyclerViewAdapter(credentialList)
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = CredentialListFragment()
    }
}