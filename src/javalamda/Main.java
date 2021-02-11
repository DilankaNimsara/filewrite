package javalamda;

interface Drawable {
    String draw(String name);
}

interface Add {
    int addInt(int a, int b);
}

interface Test {
    String name(String n1, String n2, String n3);
}

public class Main {
    public static void main(String[] args) {

        int width = 0;
        String name = "Dilanka";


        /**
         * method 1
         */

        Drawable d1 = (n) -> "name : " + n;
        System.out.println(d1.draw(name));

        /**
         * method 2
         */

        Drawable d2 = (n) -> "welcome to " + n;

        System.out.println(d2.draw("epic"));

        Add add1 = (a, b) -> a + b;

        System.out.println(add1.addInt(2, 4));

        Add add2 = (int a, int b) -> a + b;
        System.out.println(add2.addInt(2, 6));

        Test t1 = (n1, n2, n3) -> "my name is " + n1 + " " + n2 + " " + n3;
        System.out.println(t1.name("Dilanka", "Nimsara", "Amarasena"));



    }
}
