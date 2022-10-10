package org.shuai.function;

import lombok.Data;

@Data
public class Response {

    private String result;

    public Response(String result) {
        this.result = result;
    }
}
