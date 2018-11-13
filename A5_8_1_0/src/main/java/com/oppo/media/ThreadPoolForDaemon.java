package com.oppo.media;

import android.util.Log;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;
import java.util.concurrent.TimeUnit;

public class ThreadPoolForDaemon {
    private static final String TAG = "ThreadPoolForDaemon";
    private static final long THREAD_POOL_ALIVE_TIME = 30;
    private static final int THREAD_POOL_CORE_SIZE = 3;
    private static final int THREAD_POOL_MAX_SIZE = 3;
    private static final int THREAD_POOL_QUEUE_MAX_COUNT = 10;
    private static ThreadPoolForDaemon sInstance = null;
    private boolean mBlocked = false;
    private final ExecutorService mExecutorService = new ThreadPoolExecutor(3, 3, THREAD_POOL_ALIVE_TIME, TimeUnit.SECONDS, new ArrayBlockingQueue(10), Executors.defaultThreadFactory(), new DiscardOldestPolicy());
    private final Object mLock = new Object();

    public static ThreadPoolForDaemon getInstance() {
        if (sInstance == null) {
            sInstance = new ThreadPoolForDaemon();
        }
        return sInstance;
    }

    private ThreadPoolForDaemon() {
    }

    public void block() {
        synchronized (this.mLock) {
            this.mBlocked = true;
        }
    }

    public void unBlock() {
        synchronized (this.mLock) {
            this.mBlocked = false;
            this.mLock.notifyAll();
        }
    }

    public boolean isBlocked() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mBlocked;
        }
        return z;
    }

    public void request(final Task task) {
        if (task != null) {
            this.mExecutorService.submit(new Runnable() {
                public void run() {
                    task.run();
                    synchronized (ThreadPoolForDaemon.this.mLock) {
                        if (ThreadPoolForDaemon.this.mBlocked) {
                            try {
                                ThreadPoolForDaemon.this.mLock.wait();
                            } catch (InterruptedException e) {
                                Log.d(ThreadPoolForDaemon.TAG, "request() has been interrupted! thread=" + Thread.currentThread().getName());
                            }
                        }
                    }
                    return;
                }
            });
            return;
        }
        Log.d(TAG, "request() parameters are invalid!");
    }
}
