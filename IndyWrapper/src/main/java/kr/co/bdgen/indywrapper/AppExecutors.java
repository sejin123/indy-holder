package kr.co.bdgen.indywrapper;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Global executor pools for the whole application.
 * <p>
 * Grouping tasks like this avoids the effects of task starvation (e.g. disk reads don't wait behind
 * webservice requests).
 */
public class AppExecutors {
    private static AppExecutors INSTANCE;

    public static AppExecutors getInstance() {
        if (INSTANCE == null)
            synchronized (AppExecutors.class) {
                if (INSTANCE == null)
                    INSTANCE = new AppExecutors();
            }
        return INSTANCE;
    }

    @NonNull
    private final Executor mainThread = new MainThreadExecutor();
    @NonNull
    private final Executor computation = Executors.newSingleThreadExecutor();
    @NonNull
    private final Executor diskIO = Executors.newSingleThreadExecutor();
    @NonNull
    private final Executor networkIO = Executors.newFixedThreadPool(3);

    @NonNull
    public Executor mainThread() {
        return mainThread;
    }

    @NonNull
    public Executor computation() {
        return computation;
    }

    @NonNull
    public Executor diskIO() {
        return diskIO;
    }

    @NonNull
    public Executor networkIO() {
        return networkIO;
    }

    private static class MainThreadExecutor implements Executor {
        private final Handler mainThreadHandler;

        public MainThreadExecutor() {
            Looper looper = Looper.getMainLooper();
            mainThreadHandler = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P
                    ? Handler.createAsync(looper)
                    : new Handler(Looper.getMainLooper());
        }

        @Override
        public void execute(@NonNull Runnable command) {
            if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
                command.run();
                return;
            }
            mainThreadHandler.post(command);
        }
    }
}