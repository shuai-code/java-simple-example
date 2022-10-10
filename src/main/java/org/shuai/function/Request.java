package org.shuai.function;

import lombok.Data;

@Data
public class Request {

    private String name;

    public Request(String name) {
        this.name = name;
    }

    public Request prefix(String prefix) {
        this.name = prefix + "-" + this.name;
        return this;
    }

    public Request suffix(String suffix) {
        this.name = this.name + "-" + suffix;
        return this;
    }

    public Response build() {
        return new Response(this.name);
    }
}
