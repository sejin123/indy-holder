package com.example.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import org.hyperledger.indy.sdk.IndyException;
import org.hyperledger.indy.sdk.pool.Pool;
import org.hyperledger.indy.sdk.wallet.Wallet;

import kotlin.Pair;
import kr.co.bdgen.indywrapper.IndyWrapper;
import kr.co.bdgen.indywrapper.config.PoolConfig;
import kr.co.bdgen.indywrapper.config.WalletConfig;

public class MyApplication extends Application {

    public static final String WALLET_PREFERENCE = "WALLET_PREFERENCE";
    public static final String PREF_KEY_DID = "PREF_KEY_DID";
    public static final String PREF_KEY_VER_KEY = "PREF_KEY_VER_KEY";
    public static final String PREF_KEY_MASTER_SECRET= "PREF_KEY_MASTER_SECRET";

    private static Wallet wallet;

    public static Wallet getWallet() {
        return wallet;
    }

    public static String getDid(Context context) {
        String did = null;
        SharedPreferences pref = context.getSharedPreferences(WALLET_PREFERENCE, MODE_PRIVATE);
        did = pref.getString(PREF_KEY_DID, "");
        return did;
    }

    public static String getMasterSecret(Context context) {
        String masterSecret = null;
        SharedPreferences pref = context.getSharedPreferences(WALLET_PREFERENCE, MODE_PRIVATE);
        masterSecret = pref.getString(PREF_KEY_MASTER_SECRET, "");
        return masterSecret;
    }


    @Override
    public void onCreate() { //처음 시작하는 곳 (대부분 초기화 구문 집어 넣음)
        super.onCreate();

        //indy library 초기화
        IndyWrapper.init(this);

        // ledger 사용을 위한 pool config 생성
        String poolName = PoolConfig.getPoole(this);
        try {
            Pool.openPoolLedger(poolName, "{}").get();

            //Wallet 생성 (주민등록증을 지갑에서 꺼내야하는데 없어서 생성)
            WalletConfig.createWallet(this).get();

            //Wallet 열기 (openWallet() 가 지갑을 여는 코드)
            wallet = WalletConfig.openWallet().get();

            //did, verKey 생성
            Pair<String, String> didAndVerKey = WalletConfig.createDid(wallet).get();

            //master secret 생성
            String masterSecret = WalletConfig.createMasterSecret(wallet, null);

            //생성한 did, verKey, masterSecretId 저장
            SharedPreferences prefs = getSharedPreferences(WALLET_PREFERENCE, MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(PREF_KEY_DID, didAndVerKey.getFirst());
            editor.putString(PREF_KEY_VER_KEY, didAndVerKey.getSecond());
            editor.putString(PREF_KEY_MASTER_SECRET, masterSecret);
            editor.apply();

            Toast.makeText(this, "wallet succes!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
