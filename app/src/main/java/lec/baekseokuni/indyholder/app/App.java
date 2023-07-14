package lec.baekseokuni.indyholder.app;

import android.app.Application;

import org.hyperledger.indy.sdk.IndyException;
import org.hyperledger.indy.sdk.pool.Pool;

import java.util.concurrent.ExecutionException;

import kr.co.bdgen.indywrapper.IndyWrapper;
import kr.co.bdgen.indywrapper.config.PoolConfig;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        IndyWrapper.init(this);
        String poolName = PoolConfig.getPoole(this);
        try {
            Pool.openPoolLedger(poolName, "{}").get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
