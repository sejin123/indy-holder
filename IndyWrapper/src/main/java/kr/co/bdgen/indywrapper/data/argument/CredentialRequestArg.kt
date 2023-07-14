package kr.co.bdgen.indywrapper.data.argument

import org.hyperledger.indy.sdk.wallet.Wallet

data class CredentialRequestArg(
    val wallet: Wallet,
    val holderDid: String,
    val masterSecretId: String,
    val credentialOffer: String,
    val credDefJson: String
)