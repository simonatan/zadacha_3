package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;

public class Main {

    public static void main(String[] args) {

        PriorityBlockingQueue<Product> products = new PriorityBlockingQueue<>(10, new ProductComparator());

        try {
            ProductThread t1 = new ProductThread("CalvinKleinThread", "calvin_klein.txt", products);
            ProductThread t2 = new ProductThread("GuessThread", "guess.txt", products);
            ProductThread t3 = new ProductThread("TrussardiThread", "trussardi.txt", products);

            t1.start();
            t2.start();
            t3.start();

            List<Thread> threads = new ArrayList<>();
            threads.add(t1);
            threads.add(t2);
            threads.add(t3);

            for(Thread thread : threads) {
                thread.join();
            }

        } catch (Exception exception) {
            System.out.println("There was an error while reading the data.");
            System.exit(1);
        }

        ArrayList<Product> polledElements = new ArrayList<>();
        products.drainTo(polledElements);

        File file = new File("result_products.txt");
        if (file.exists()) {
            System.out.println("File already exists.");
            System.exit(1);
        }
        PrintWriter output = null;

        try {
            output = new PrintWriter(file);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert output != null;
        for(int i = 0; i < 10; i++) {
            output.println(polledElements.get(i) + " ");
        }
        output.close();
    }
}
