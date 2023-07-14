package kr.co.bdgen.indywrapper

import android.content.Context
import android.content.SharedPreferences
import android.system.Os
import kr.co.bdgen.indywrapper.config.PoolConfig
import kr.co.bdgen.indywrapper.config.WalletConfig
import org.hyperledger.indy.sdk.pool.Pool
import org.hyperledger.indy.sdk.wallet.Wallet

object IndyWrapper {
    @JvmStatic
    fun init(context: Context) {
        //TODO
        // 1. type = null 인데, 이 부분 확인
        // 2. manifest 등에서 설정할 수 있는지 여부 확인
        //지갑 설정 과정
        //1. load indy library
        //dataDir.absolutePath = /data/user/0/lec.baekseokuniv.ssiholder
        Os.setenv("EXTERNAL_STORAGE", context.dataDir.absolutePath, true)
        System.loadLibrary("indy")
    }
}