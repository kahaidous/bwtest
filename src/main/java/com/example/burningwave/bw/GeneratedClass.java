package com.example.burningwave.bw;

import java.util.Arrays;
import java.util.List;

class GeneratedClass {

    List<String> words;

    GeneratedClass(String... words) {
        this.words = Arrays.asList(words);
    }

    public void print() {
        System.out.println("\n\t" + String.join(" ", words) + "\n");
    }

    public static void main(String[] args) {
        new GeneratedClass(args).print();
    }

}