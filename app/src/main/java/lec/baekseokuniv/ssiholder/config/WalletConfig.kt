package lec.baekseokuniv.ssiholder.config

import org.hyperledger.indy.sdk.anoncreds.Anoncreds
import org.hyperledger.indy.sdk.did.Did
import org.hyperledger.indy.sdk.wallet.Wallet
import org.json.JSONObject
import java.util.concurrent.CompletableFuture


object WalletConfig {
    private val walletConfig = JSONObject().put("id", "trusteeWallet").toString()
    private val walletCredentials = JSONObject().put("key", "prover_wallet_key").toString()

    fun openWallet(): Wallet {
        Wallet.createWallet(walletConfig, walletCredentials).get()
        return Wallet.openWallet(walletConfig, walletCredentials).get()
    }

    fun createMasterSecretId(wallet: Wallet, masterSecretId: String? = null): String =
        Anoncreds.proverCreateMasterSecret(wallet, masterSecretId).get()

    fun createDid(wallet: Wallet): CompletableFuture<Pair<String, String>> {
        return Did.createAndStoreMyDid(wallet, null)
            .thenApply {
                return@thenApply Pair(it.did, it.verkey)
            }
    }
}