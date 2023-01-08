package org.yuudai;

public class Main {
    public static void main(String[] args) {
        UpperLowerObfuscator upperLowerObfuscator = new UpperLowerObfuscator();
        SearchStringObfuscator searchStringObfuscator = new SearchStringObfuscator();
        System.out.println(upperLowerObfuscator.GenerateObfuscatedString());
        System.out.println(searchStringObfuscator.GenerateObfuscatedString());
    }
}