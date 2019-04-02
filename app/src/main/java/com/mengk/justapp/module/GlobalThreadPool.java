package com.mengk.justapp.module;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhang on 2017/8/29.
 * 全局的线程池 当改线程不使用的时候 杀死线程释放资源
 */
public class GlobalThreadPool {

    // 这种线程池 如果线程长时间不用 就被回收了
    private ExecutorService mExecutorService = Executors.newCachedThreadPool();

    private static GlobalThreadPool mGlobalThreadPool = new GlobalThreadPool();

    private GlobalThreadPool() {}

    public static GlobalThreadPool getInstance() {
        return mGlobalThreadPool;
    }

    /**
     * 获取全局的线程池
     * @return
     */
    public ExecutorService getGlobalExecutorService() {
        return mExecutorService;
    }
}

