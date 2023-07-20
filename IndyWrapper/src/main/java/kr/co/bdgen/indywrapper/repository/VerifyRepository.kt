package kr.co.bdgen.indywrapper.repository

import kr.co.bdgen.indywrapper.api.VerifyApi
import kr.co.bdgen.indywrapper.config.RetrofitConfig
import kr.co.bdgen.indywrapper.data.argument.CredentialInfo
import kr.co.bdgen.indywrapper.data.argument.CredentialVerifyPostVerifyProofArgs
import kr.co.bdgen.indywrapper.data.payload.ProofRequest
import kr.co.bdgen.indywrapper.data.payload.OfferPayload
import org.hyperledger.indy.sdk.anoncreds.Anoncreds
import org.hyperledger.indy.sdk.anoncreds.CredentialsSearchForProofReq
import org.hyperledger.indy.sdk.wallet.Wallet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class VerifyRepository {
    private val verifyApi: VerifyApi = RetrofitConfig.createApi(
        RetrofitConfig.issuerBaseUrl, VerifyApi::class.java
    )

    /**
     * 1. Credential을 생성하기 위해 issuer에 offer
     */
    fun createProof(
        schemaId: String,
        onSuccess: (ProofRequest) -> Void,
        onFail: (Throwable) -> Void,
    ) {
        verifyApi
            .postCreateProof(schemaId)
            .enqueue(object : Callback<ProofRequest> {
                override fun onResponse(
                    call: Call<ProofRequest>,
                    response: Response<ProofRequest>
                ) {
                    val payload = response.body()
                    if (payload == null) {
                        onFail(Exception("response error"))
                        return
                    }
                    onSuccess(payload)
                }

                override fun onFailure(
                    call: Call<ProofRequest>,
                    t: Throwable
                ) {
                    t.printStackTrace()
                    onFail(t)
                }
            })
    }

    /**
     * 2. credential을 생성하기 위한 metadata와 요청 정보를 담은 객체를 생성하는 메소드
     */
    fun verifyProof(
        proofRequestJson: String,
        proofJson: String,
        schemas: String,
        credDefs: String,
        revocRegDefs: String,
        revocRegs: String,
        onSuccess: (Boolean) -> Void,
        onFail: (Throwable) -> Void,
    ) {
        verifyApi.postVerifyProof(
            CredentialVerifyPostVerifyProofArgs(
                proofRequestJson,
                proofJson,
                schemas,
                credDefs,
                revocRegDefs,
                revocRegs,
            )
        )
            .enqueue(object : Callback<Boolean> {
                override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                    val payload = response.body()
                    if (payload == null) {
                        onFail(Exception("response error"))
                        return
                    }
                    onSuccess(payload)
                }

                override fun onFailure(call: Call<Boolean>, t: Throwable) {
                    onFail(t)
                }
            })
    }
}