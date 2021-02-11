package javalamda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Comparator {

    public static void main(String[] args) {

        List<Product> productList = new ArrayList<Product>();

        productList.add(new Product(1, "pen", 10.00));
        productList.add(new Product(3, "ruler", 20.00));
        productList.add(new Product(2, "pencil", 15.00));

        System.out.println("Sorting based on name");


        Collections.sort(productList, java.util.Comparator.comparing(p -> p.price));

        for (Product p : productList) {
            System.out.println(p);
        }

        /**
         * filter collection data
         */
        System.out.println("filter collection data");
        Stream<Product> filer_products = productList.stream().filter(product -> product.price > 15);
        filer_products.forEach(
                (n) -> System.out.println(n)
        );

        Runnable runnable = () -> {
            System.out.println("ssdsd");
        };
        Thread thread = new Thread(runnable);
        thread.start();

        new Thread(() -> {
            System.out.println("123456");
        }).start();

    }

}

class Product {

    int id;
    String name;
    double price;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
