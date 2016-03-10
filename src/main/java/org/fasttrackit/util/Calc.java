package org.fasttrackit.util;


public class Calc {
    private int maxNumber;

    public Calc(int maxLenght) {
        maxNumber = (int) Math.pow(10, maxLenght);
    }

    public int add(int a, int b) {
        int s = a + b;

        if (s >= maxNumber) {
            System.out.println("sum was " + s);
            s = maxNumber - 1;
        }
        return s;
    }

}
