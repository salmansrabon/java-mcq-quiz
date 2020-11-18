package com.company;

public class GenerateRandom {
    public static int getRandomNumber(int min, int max){
        double number = Math.random() * (max - min + 1) + min;
        return (int)number;
    }
}
