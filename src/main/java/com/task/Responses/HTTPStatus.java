package com.task.Responses;

public enum HTTPStatus {
    SUCCESS("200"),UNAUTHORIZED("404"),INTERNAL_ERROR("500");

    private String status;

    HTTPStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
