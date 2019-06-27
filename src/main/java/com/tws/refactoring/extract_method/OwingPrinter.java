package com.tws.refactoring.extract_method;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class OwingPrinter {
    void printOwing(String name, List<Order> orders) {
        Iterator<Order> elements = orders.iterator();
        double outstanding = 0.0;

        printBanner();

        outstanding = getOutstanding(elements, outstanding);

        printDetails(name, outstanding);
    }

    private double getOutstanding(Iterator<Order> elements, double outstanding) {
        while (elements.hasNext()) {
            Order each = (Order) elements.next();
            outstanding += each.getAmount();
        }
        return outstanding;
    }

    private void printDetails(String name, double outstanding) {
        System.out.println("name: " + name);
        System.out.println("amount: " + outstanding);
    }

    private void printBanner() {
        System.out.println ("*****************************");
        System.out.println ("****** Customer totals ******");
        System.out.println ("*****************************");
    }
}

class Order {
    private final double amount;

    public Order(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}
