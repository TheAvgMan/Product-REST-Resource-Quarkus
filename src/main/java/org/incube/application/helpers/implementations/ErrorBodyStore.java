package org.incube.application.helpers.implementations;

import java.util.ArrayList;
import java.util.List;

public class ErrorBodyStore {

    private static final List<ErrorBody> errorBodyStoreList = new ArrayList<>();

    public static ErrorBody getErrorBody() {
        return errorBodyStoreList.remove(0);
    }

    public static void addErrorBody(ErrorBody errorBody) {
        errorBodyStoreList.add(errorBody);
    }
}
