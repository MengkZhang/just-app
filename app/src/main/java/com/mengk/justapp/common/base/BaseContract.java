package com.mengk.justapp.common.base;

/**
 * base协议类
 */
public interface BaseContract {
    interface BasePresenter<T> {
        void attachView(T view);

        void detachView();
    }

    interface BaseView<T> {
    }
}
