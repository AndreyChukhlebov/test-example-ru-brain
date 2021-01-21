package com.example.testexamplerubrain.model;

import java.util.ArrayList;
import java.util.List;

//TODO doc
public class OperationState {
    private boolean success;
    private List<String> errorMessages = new ArrayList<>();


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }
}
