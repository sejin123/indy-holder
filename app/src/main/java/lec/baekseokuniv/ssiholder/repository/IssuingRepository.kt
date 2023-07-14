package lec.baekseokuniv.ssiholder.repository

import lec.baekseokuniv.ssiholder.api.IssuerApi
import lec.baekseokuniv.ssiholder.config.RetrofitConfig
import lec.baekseokuniv.ssiholder.data.argument.CredentialInfo
import lec.baekseokuniv.ssiholder.data.argument.CredentialRequestArg
import lec.baekseokuniv.ssiholder.data.argument.IssueArg
import lec.baekseokuniv.ssiholder.data.argument.IssueOfferArg
import lec.baekseokuniv.ssiholder.data.payload.IssuePayload
import lec.baekseokuniv.ssiholder.data.payload.OfferPayload
import org.hyperledger.indy.sdk.anoncreds.Anoncreds.proverCreateCredentialReq
import org.hyperledger.indy.sdk.anoncreds.Anoncreds.proverStoreCredential
import org.hyperledger.indy.sdk.wallet.Wallet
import retrofit2.Callback
import java.util.concurrent.CompletableFuture


class IssuingRepository() {
    private val issuerApi: IssuerApi = RetrofitConfig.createApi(
        RetrofitConfig.issuerBaseUrl, IssuerApi::class.java
    )

    /**
     * 1. Credential을 생성하기 위해 issuer에 offer
     */
    fun postIssueOffer(offerSecret: String, callback: Callback<OfferPayload>) =
        issuerApi
            .postOffer(offerSecret)
            .enqueue(callback)

    /**
     * 2. credential을 생성하기 위한 metadata와 요청 정보를 담은 객체를 생성하는 메소드
     */
    fun requestCredential(
        arg: CredentialRequestArg
    ): CompletableFuture<CredentialInfo> {
        return proverCreateCredentialReq(
            arg.wallet,
            arg.holderDid,
            arg.credentialOffer,
            arg.credDefJson,
            arg.masterSecretId,
        ).thenApply {
            CredentialInfo(
                arg.credDefJson,
                it.credentialRequestMetadataJson,
                it.credentialRequestJson
            )
        }
    }

    /**
     * 3. 생성한 Credential을 저장함
     */
    fun storeCredential(
        wallet: Wallet,
        credReqArg: CredentialInfo,
        credJson: String
    ): CompletableFuture<String> {
        return proverStoreCredential(
            wallet,
            null,
            credReqArg.credReqMetadataJson,
            credJson,
            credReqArg.credDefJson,
            null
        )
    }

    /**
     * 4. Credential을 생성하기 위해 issuer에 credential을 issue해달라고 요청
     */
    fun postIssue(
        issueArg: IssueArg,
        callback: Callback<IssuePayload>
    ) =
        issuerApi
            .postIssue(issueArg)
            .enqueue(callback)
}