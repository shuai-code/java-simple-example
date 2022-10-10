package org.shuai.function;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FunctionDemo {

    private static List<String> result = new ArrayList<>();

    public static void main(String[] args) {
        make("Yang", request -> request.prefix("Q").suffix("E").build());
        make("Lin", request -> request.prefix("C").suffix("D").build());
        make("Zhang", request -> request.prefix("A").suffix("D").build());
        make("Li", request -> request.prefix("F").suffix("D").build());
        make("Liu", request -> request.prefix("W").suffix("D").build());
        System.out.println(result);
    }

    public static void make(String name, Function<Request, Response> function) {
        Response response = function.apply(new Request(name));
        result.add(response.getResult());
    }
}
