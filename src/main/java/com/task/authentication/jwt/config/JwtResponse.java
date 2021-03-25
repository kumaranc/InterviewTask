package com.task.authentication.jwt.config;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private String jwttoken;
    private String status;
    private String code;

    public JwtResponse(String jwttoken, String status, String code) {
        this.jwttoken = jwttoken;
        this.status = status;
        this.code = code;
    }

    public JwtResponse() {}

    public String getJwttoken() {
        return jwttoken;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

//    public JwtResponse(String jwttoken) {
//        this.jwttoken = jwttoken;
//    }


    @Override
    public String toString() {
        return "{" +
                "jwttoken:'" + jwttoken + '\'' +
                ", status:'" + status + '\'' +
                ", code:'" + code + '\'' +
                '}';
    }
}
