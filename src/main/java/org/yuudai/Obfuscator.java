package org.yuudai;

/**
 * 難読化された攻撃パターンを生成する抽象クラス
 */
public abstract class Obfuscator {
    static final String first = "${";
    static final String jndi = "jndi:";
    static final String[] proto = {"ldap", "ldaps", "rmi"};
    static final String address = "127.0.0.1:1389/test";

    abstract String GenerateTargetString();

    abstract String GenerateObfuscatedString();
}
