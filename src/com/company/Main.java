package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Queue<Product> queue = new PriorityQueue<>();

        Thread t1 = new Thread(()->{
            File read = new File("calvin_klein.txt");
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
            File read = new File("trussardi.txt");
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

        File write = new File("output_Apartments.txt");
        if (write.exists()) {
            System.out.println("File already exists.");
            System.exit(1);
        }
        PrintWriter output = null;

        try {
            output = new PrintWriter(write);

            for(int i = 0; i < 10; i++) {
                Product p = queue.remove();
                output.println(p.getName() + " " + p.getPrice());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            assert output != null;
            output.close();
        }


    }
}
