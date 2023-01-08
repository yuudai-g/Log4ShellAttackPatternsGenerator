package org.yuudai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        UpperLowerObfuscator upperLowerObfuscator = new UpperLowerObfuscator();
        System.out.println(upperLowerObfuscator.GenerateObfuscatedString());
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(ObfuscatorUtils.GenerateRandomString(12));
    }
}