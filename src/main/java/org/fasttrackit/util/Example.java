package org.fasttrackit.util;


public class Example {
    public static void main(String[] args) {
        Calc calc = new Calc(3);

       int suma = calc.add(567,876);
        System.out.println(suma);

        suma = calc.add(3,50);
        System.out.println(suma);
    }

}
