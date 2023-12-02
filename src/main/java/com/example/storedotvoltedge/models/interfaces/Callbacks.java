package com.example.storedotvoltedge.models.interfaces;

public interface Callbacks {

    interface SuccessCallback<TRes> {
        void onSuccess(TRes data);
    }

    interface ProgressCallback<TRes> {
        void onProgress(TRes progress);
    }

    interface FailureCallback {
        void onFailure(Throwable e);
    }
}
