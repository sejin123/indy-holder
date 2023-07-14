package lec.baekseokuni.indyholder.app;

import android.app.Application;

import kr.co.bdgen.indywrapper.IndyWrapper;
import kr.co.bdgen.indywrapper.config.PoolConfig;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        IndyWrapper.init(this);
        PoolConfig.getPoole(this);
    }
}
