package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Queue<Product> queue = new PriorityQueue<>();

        Thread t1 = new Thread(()->{
            File read = new File("guess.txt");
            Scanner input = null;

            try {
                input = new Scanner(read);

                while (input.hasNext()) {
                    String name = input.next();
                    String type = input.next();
                    double price = input.nextDouble();

                    Product p = new Product(name, type, price);
                    queue.add(p);
                }
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            } finally {
                assert input != null;
                input.close();
            }
        });

        Thread t2 = new Thread(()->{
            File read = new File("guess.txt");
            Scanner input = null;

            try {
                input = new Scanner(read);

                while (input.hasNext()) {
                    String name = input.next();
                    String type = input.next();
                    double price = input.nextDouble();

                    Product p = new Product(name, type, price);
                    queue.add(p);
                }
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            } finally {
                assert input != null;
                input.close();
            }
        });

        Thread t3 = new Thread(() -> {
            File read = new File("guess.txt");
            Scanner input = null;

            try {
                input = new Scanner(read);

                while (input.hasNext()) {
                    String name = input.next();
                    String type = input.next();
                    double price = input.nextDouble();

                    Product p = new Product(name, type, price);
                    queue.add(p);
                }
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            } finally {
                assert input != null;
                input.close();
            }
        });

        t1.start();
        t2.start();
        t3.start();

        for(int i = 0; i < 10; i++) {
            Product p = queue.remove();
            System.out.println(p.getName() + " " + p.getPrice());
        }
    }
}
