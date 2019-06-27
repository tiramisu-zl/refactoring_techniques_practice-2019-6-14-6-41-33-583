package com.tws.refactoring.extract_method;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import static org.junit.Assert.*;

public class OwingPrinterTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void print() {
        System.out.print("hello");
        assertEquals("hello", outContent.toString());
    }

    @Test
    public void printOwing_none_order() {
        OwingPrinter owingPrinter = new OwingPrinter();
        List<Order> orders = new ArrayList<Order>();

        owingPrinter.printOwing("name", orders);

        String exceptStr = "*****************************\n" +
                "****** Customer totals ******\n" +
                "*****************************\n" +
                "name: name\n" +
                "amount: 0.0\n";
        assertEquals(exceptStr, outContent.toString());
    }

    @Test
    public void printOwing_one_order() {
        OwingPrinter owingPrinter = new OwingPrinter();
        Order order = new Order(1);
        List<Order> orders = new ArrayList<Order>();
        orders.add(order);

        owingPrinter.printOwing("name", orders);

        String exceptStr = "*****************************\n" +
                "****** Customer totals ******\n" +
                "*****************************\n" +
                "name: name\n" +
                "amount: 1.0\n";
        assertEquals(exceptStr, outContent.toString());
    }

    @Test
    public void printOwing_multi_order() {
        OwingPrinter owingPrinter = new OwingPrinter();
        Order orderOne = new Order(1);
        Order orderTwo = new Order(2);
        List<Order> orders = new ArrayList<Order>();
        orders.add(orderOne);
        orders.add(orderTwo);

        owingPrinter.printOwing("name", orders);

        String exceptStr = "*****************************\n" +
                "****** Customer totals ******\n" +
                "*****************************\n" +
                "name: name\n" +
                "amount: 3.0\n";
        assertEquals(exceptStr, outContent.toString());
    }
}