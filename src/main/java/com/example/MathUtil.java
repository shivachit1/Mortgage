package com.example;

public class MathUtil {

    public static double power(double base,double power){
        float count = 1;
        double total=1;
        if(power<0) {
            base= 1/base;
            power = power * -1;
        }
        while(count<=power){
            total *=base;
            count++;
        }

        return total;
    }
}
