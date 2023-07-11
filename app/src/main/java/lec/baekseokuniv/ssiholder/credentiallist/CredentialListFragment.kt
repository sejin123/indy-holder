package lec.baekseokuniv.ssiholder.credentiallist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.gson.JsonArray
import lec.baekseokuniv.ssiholder.R
import lec.baekseokuniv.ssiholder.data.Credential
import lec.baekseokuniv.ssiholder.databinding.FragmentCredentialListBinding

class CredentialListFragment : Fragment() {
    private lateinit var binder: FragmentCredentialListBinding

    //FIXME: 실제 데이터를 붙일 때 해당 변수 수정 예정 (20230706 ksh)
    private val credentialList = listOf(
        Credential(
            "123456",
            "test credential",
            "schema_id",
            "credential_definition_id"
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binder = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_credential_list, container, false
        )
        binder.rvCredentialList.adapter = CredentialListRecyclerViewAdapter(credentialList)
        return binder.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = CredentialListFragment()
    }
}