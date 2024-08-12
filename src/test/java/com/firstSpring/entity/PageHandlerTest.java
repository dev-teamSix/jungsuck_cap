package com.firstSpring.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class PageHandlerTest {
    @Test
    public void test() {
        PageHandler ph = new PageHandler(250, 1, 5);
        ph.print();
        System.out.println("ph = " + ph);
        assertTrue(ph.getBeginPage() == 1);
        assertTrue(ph.getEndPage() == 10);
    }

    @Test
    public void test2() {
        PageHandler ph = new PageHandler(250, 11, 5);
        ph.print();
        System.out.println("ph = " + ph);
        assertTrue(ph.getBeginPage() == 11);
        assertTrue(ph.getEndPage() == 20);
    }

    @Test
    public void test3() {
        PageHandler ph = new PageHandler(244, 48, 5);
        ph.print();
        System.out.println("ph = " + ph);
        assertTrue(ph.getBeginPage() == 41);
        assertTrue(ph.getEndPage() == 49);
    }
}