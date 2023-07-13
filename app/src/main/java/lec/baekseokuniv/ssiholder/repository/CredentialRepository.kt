package lec.baekseokuniv.ssiholder.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import lec.baekseokuniv.ssiholder.data.Credential
import lec.baekseokuniv.ssiholder.data.CredentialFilter
import org.hyperledger.indy.sdk.anoncreds.Anoncreds
import org.hyperledger.indy.sdk.wallet.Wallet


class CredentialRepository() {
    fun getCredentialList(wallet: Wallet, filter: CredentialFilter?): List<Credential> {
        val raw: String = getRawCredentials(
            wallet,
            if (filter == null) "NULL" else Gson().toJson(filter).toString()
        )
        val parsedList: List<Credential> =
            Gson().fromJson(raw, object : TypeToken<List<Credential>>() {}.type)
        for (parsed in parsedList) {
            parsed.raw = raw
        }
        return parsedList
    }

    fun getRawCredentials(wallet: Wallet, filter: String): String {
        return Anoncreds.proverGetCredentials(wallet, filter).get()
    }
}