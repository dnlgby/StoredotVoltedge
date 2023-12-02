package com.example.storedotvoltedge.models;


public class Action<TRes> {

    public static final int ACTION_SUCCESS = 0;
    public static final int ACTION_FAILURE = 1;
    private int mStatus;
    private String mErrorMessage;
    private TRes mActionResult;

    private Action(int status, String errorMessage, TRes result) {
        this.mStatus = status;
        this.mErrorMessage = errorMessage;
        this.mActionResult = result;
    }

    public static Action actionSuccessNoResult() {
        return new Action(ACTION_SUCCESS, null, null);
    }

    public static <TRes> Action actionSuccess(TRes result) {
        return new Action(ACTION_SUCCESS, null, result);
    }

    public static Action actionFailure(String errorMessage) {
        return new Action(ACTION_FAILURE, errorMessage, null);
    }

    public int getStatus() {
        return mStatus;
    }

    public String getErrorMessage() {
        return mErrorMessage;
    }

    public TRes getActionResult() {
        return mActionResult;
    }

}