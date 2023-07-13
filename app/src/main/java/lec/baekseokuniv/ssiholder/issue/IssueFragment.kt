package lec.baekseokuniv.ssiholder.issue

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import lec.baekseokuniv.ssiholder.R
import lec.baekseokuniv.ssiholder.app.App
import lec.baekseokuniv.ssiholder.data.argument.CredentialInfo
import lec.baekseokuniv.ssiholder.data.argument.CredentialRequestArg
import lec.baekseokuniv.ssiholder.data.payload.IssuePayload
import lec.baekseokuniv.ssiholder.databinding.FragmentIssueBinding
import lec.baekseokuniv.ssiholder.repository.IssuingRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


const val ARG_ISSUABLE_DATA = "argument_issuable_data"

class IssueFragment : Fragment() {
    private lateinit var binder: FragmentIssueBinding
    private var issuableData: String? = null

    private val issueRepository = IssuingRepository()

    companion object {
        fun newInstance(issuableData: String? = null) =
            IssueFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_ISSUABLE_DATA, issuableData)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            issuableData = it.getString(ARG_ISSUABLE_DATA)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binder = DataBindingUtil.inflate(inflater, R.layout.fragment_issue, container, false)
        binder.btnIssuing.setOnClickListener {
            val app = requireActivity().application as App
            val credentialInfo = CredentialInfo("", "", "")
            issueRepository.postIssueOffer(
                credentialInfo.testCredDefId,
                object : Callback<String?> {
                    override fun onResponse(call: Call<String?>, response: Response<String?>) {
                        val credOffer = response.body() ?: return
                        binder.txtOfferData.text = credOffer
                        issueRepository.requestCredential(
                            CredentialRequestArg(
                                app.wallet,
                                app.getDid() ?: return,
                                app.getMasterSecret() ?: return,
                                credOffer,
                                credentialInfo.testCredDefJson
                            )
                        ).thenAcceptAsync(
                            {
                                binder.txtIssuableCredDef.text = it.credDefJson
                                binder.txtIssuableCredReq.text = it.credReqJson
                                binder.txtIssuableCredMetaData.text = it.credReqMetadataJson
                                issueRepository.postIssue(
                                    credOffer,
                                    it.credReqJson,
                                    object : Callback<IssuePayload> {
                                        override fun onResponse(
                                            call: Call<IssuePayload>,
                                            response: Response<IssuePayload>
                                        ) {
                                            val credential = response.body()?.credentialJson ?: return
                                            binder.txtIssuableCred.text = credential
                                            issueRepository.storeCredential(
                                                app.wallet,
                                                it,
                                                credential
                                            )
                                                .exceptionally {
                                                    it.printStackTrace()
                                                    null
                                                }
                                        }

                                        override fun onFailure(
                                            call: Call<IssuePayload>,
                                            t: Throwable
                                        ) {
                                            t.printStackTrace()
                                        }
                                    }
                                )
                            },
                            app.mainExecutor
                        ).exceptionally {
                            it.printStackTrace()
                            null
                        }
                    }

                    override fun onFailure(call: Call<String?>, t: Throwable) {
                        t.printStackTrace()
                    }
                }
            )
        }
        return binder.root
    }

}