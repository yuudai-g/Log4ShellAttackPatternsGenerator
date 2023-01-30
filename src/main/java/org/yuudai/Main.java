package org.yuudai;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[] num = {0, 0, 0};
        PlainPatternsGenerator generator = new PlainPatternsGenerator();
        UpperLowerObfuscator upperLowerObfuscator = new UpperLowerObfuscator();
        SearchStringObfuscator searchStringObfuscator = new SearchStringObfuscator();
        try {
            FileWriter file_no_obfuscation = new FileWriter("AttackPatterns_no_Obfuscation.txt");
            FileWriter file_upper_lower = new FileWriter("AttackPatterns_Upper_Lower.txt");
            FileWriter file_search = new FileWriter("AttackPatterns_Search.txt");
            PrintWriter writer_no_obfuscation = new PrintWriter(new BufferedWriter(file_no_obfuscation));
            PrintWriter writer_upper_lower = new PrintWriter(new BufferedWriter(file_upper_lower));
            PrintWriter writer_search = new PrintWriter(new BufferedWriter(file_search));
            for (int i = 0; i < 1000; i++) {
                Random rd = new SecureRandom();
                int r = rd.nextInt(0,3);
                switch (r) {
                    case 0 -> {
                        writer_no_obfuscation.println(generator.GenerateTargetString());
                        writer_no_obfuscation.flush();
                        num[0]++;
                    }
                    case 1 -> {
                        writer_upper_lower.println(upperLowerObfuscator.GenerateObfuscatedString());
                        writer_upper_lower.flush();
                        num[1]++;
                    }
                    case 2 -> {
                        writer_search.println(searchStringObfuscator.GenerateObfuscatedString());
                        writer_search.flush();
                        num[2]++;
                    }
                }
            }
            writer_no_obfuscation.close();
            writer_upper_lower.close();
            writer_search.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        for (int i:num) {
            System.out.println(i);
        }
    }
}