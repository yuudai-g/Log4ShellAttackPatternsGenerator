package org.yuudai;

import java.util.ArrayList;

public class Main {
    static ArrayList<String> obfuscations = new ArrayList<>();

    public static void main(String[] args) {
        obfuscations.add("${env:<A_>+:-#}");
        obfuscations.add("${lower:#}");
        obfuscations.add("${upper:#}");
        obfuscations.add("${<A0a>*:<Aa0>*:-#}");
        obfuscations.add("${sys:<A_>+:-#}");
        obfuscations.add("${:-#}");
        obfuscations.add("${date:'#'}");
        obfuscations.add("#");

        String target = "127.0.0.1/test.class";

        ArrayList<String> result = Obfuscator.Obfuscate(obfuscations, target, 5, false);
        for (String s : result) {
            System.out.println(s);
        }
    }
}