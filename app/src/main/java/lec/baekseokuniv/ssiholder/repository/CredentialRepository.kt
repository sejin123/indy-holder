package lec.baekseokuniv.ssiholder.repository

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import lec.baekseokuniv.ssiholder.app.App
import lec.baekseokuniv.ssiholder.config.WalletConfig
import lec.baekseokuniv.ssiholder.data.Credential
import lec.baekseokuniv.ssiholder.data.CredentialFilter
import org.hyperledger.indy.sdk.anoncreds.Anoncreds
import org.hyperledger.indy.sdk.anoncreds.Anoncreds.generateNonce
import org.hyperledger.indy.sdk.anoncreds.CredentialsSearch
import org.hyperledger.indy.sdk.anoncreds.CredentialsSearchForProofReq
import org.hyperledger.indy.sdk.wallet.Wallet
import org.json.JSONArray
import org.json.JSONObject


class CredentialRepository() {
    fun getCredentialList(wallet: Wallet, filter: CredentialFilter?): List<Credential> {
        val raw: String = getRawCredentials(
            wallet,
            if (filter == null) "NULL" else Gson().toJson(filter).toString()
        )
        return Gson().fromJson(raw, object : TypeToken<List<Credential>>() {}.type)
    }

    fun getRawCredentials(wallet: Wallet, filter: String): String {
        return Anoncreds.proverGetCredentials(wallet, filter).get()
    }
}