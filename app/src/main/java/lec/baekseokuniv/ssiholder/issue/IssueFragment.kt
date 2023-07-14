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
import lec.baekseokuniv.ssiholder.data.argument.IssueArg
import lec.baekseokuniv.ssiholder.data.payload.IssuePayload
import lec.baekseokuniv.ssiholder.data.payload.OfferPayload
import lec.baekseokuniv.ssiholder.databinding.FragmentIssueBinding
import lec.baekseokuniv.ssiholder.repository.IssuingRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


const val ARG_OFFER = "argument_issuable_data"

class IssueFragment : Fragment() {
    private lateinit var binder: FragmentIssueBinding
    private var offerSecret: String? = null

    private val issueRepository = IssuingRepository()

    companion object {
        fun newInstance(issuableData: String? = null) =
            IssueFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_OFFER, issuableData)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            offerSecret = it.getString(ARG_OFFER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binder = DataBindingUtil.inflate(inflater, R.layout.fragment_issue, container, false)
        binder.btnIssuing.setOnClickListener {
            val app = requireActivity().application as App
            binder.txtOfferData.text = offerSecret ?: "start issue from qr"
            offerSecret ?: return@setOnClickListener

            val credentialInfo = CredentialInfo("", "", "")
            var offerPayload: OfferPayload
            issueRepository.postIssueOffer(
                credentialInfo.testCredDefId,
                object : Callback<OfferPayload> {
                    override fun onResponse(
                        call: Call<OfferPayload>,
                        response: Response<OfferPayload>
                    ) {
                        offerPayload = response.body() ?: return
                        binder.txtOfferData.text = offerPayload.credOfferJson
                        issueRepository.requestCredential(
                            CredentialRequestArg(
                                app.wallet,
                                app.getDid() ?: return,
                                app.getMasterSecret() ?: return,
                                offerPayload.credOfferJson,
                                offerPayload.credDefJson
                            )
                        ).thenApplyAsync(
                            {
                                binder.txtIssuableCredDef.text = it.credDefJson
                                binder.txtIssuableCredReq.text = it.credReqJson
                                binder.txtIssuableCredMetaData.text = it.credReqMetadataJson
                                issueRepository.postIssue(
                                    IssueArg(
                                        offerSecret ?: "",
                                        offerPayload.credOfferJson,
                                        it.credReqJson
                                    ),
                                    object : Callback<IssuePayload> {
                                        override fun onResponse(
                                            call: Call<IssuePayload>,
                                            response: Response<IssuePayload>
                                        ) {
                                            val credential =
                                                response.body()?.credentialJson ?: return
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
                        }
                    }

                    override fun onFailure(call: Call<OfferPayload>, t: Throwable) {
                        t.printStackTrace()
                    }
                }
            )
        }
        return binder.root
    }

}