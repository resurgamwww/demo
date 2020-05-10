package com.wht.demo.leetCode.mock;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author wht
 * @date 2020/5/2 16:49
 */
public class MyCalendarTest {

    @Test
    public void book() {
        MyCalendar myCalendar = new MyCalendar();
        boolean b;
        b = myCalendar.book(10, 20);
        assert b == true;

        b = myCalendar.book(20, 20);
        assert b == true;

        b = myCalendar.book(30, 40);
        assert b == true;

        b = myCalendar.book(5, 10);
        assert b == true;

        b = myCalendar.book(40, 50);
        assert b == true;

        b = myCalendar.book(0, 100);
        assert b == false;
        b = myCalendar.book(0, 6);
        assert b == false;
        b = myCalendar.book(45, 55);
        assert b == false;
    }
}