package lec.baekseokuniv.ssiholder.repository

import lec.baekseokuniv.ssiholder.data.argument.CredentialInfo
import lec.baekseokuniv.ssiholder.data.argument.CredentialRequestArg
import org.hyperledger.indy.sdk.anoncreds.Anoncreds.proverCreateCredentialReq
import org.hyperledger.indy.sdk.anoncreds.Anoncreds.proverStoreCredential
import org.hyperledger.indy.sdk.wallet.Wallet
import java.util.concurrent.CompletableFuture


class IssuingRepository {
    /**
     * credential을 생성하기 위한 metadata와 요청 정보를 담은 객체를 생성하는 메소드
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
     * 생성한 Credential을 저장함
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
}