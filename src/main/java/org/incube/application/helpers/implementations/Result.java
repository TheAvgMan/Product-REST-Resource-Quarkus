package org.incube.application.helpers.implementations;

public class Result <TSuccess, TFailure> {
    private boolean isSuccess;
    private TSuccess successData;
    private TFailure failureData;

    private Result() {}

    public static <S, F> Result<S, F> success(S successData) {
        Result<S, F> result = new Result<>();
        result.setSuccess(true);
        result.setSuccessData(successData);
        return result;
    }

    public static <S, F> Result<S, F> failure(F failureData) {
        Result<S, F> result = new Result<>();
        result.setSuccess(false);
        result.setFailureData(failureData);
        return result;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public TSuccess getSuccessData() {
        return successData;
    }

    public TFailure getFailureData() {
        return failureData;
    }

    private void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    private void setSuccessData(TSuccess successData) {
        this.successData = successData;
    }

    private void setFailureData(TFailure failureData) {
        this.failureData = failureData;
    }
}
