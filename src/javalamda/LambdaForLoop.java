package javalamda;

import java.util.ArrayList;
import java.util.List;

interface SayHi {
    String say(String hi);
}

public class LambdaForLoop {

    public static void main(String[] args) {
        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Mango");
        fruits.add("Grapes");
        fruits.add("Orange");

        fruits.forEach(
                (n) -> System.out.println(n)
        );

        SayHi sh = (name) -> {
            String s1 = name + "hello world ";
            String s2 = "*******";
            return s1 + s2;
        };
        System.out.println(sh.say("Hi "));
    }
}
