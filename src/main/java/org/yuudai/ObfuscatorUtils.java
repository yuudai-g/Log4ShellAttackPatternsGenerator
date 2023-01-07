package org.yuudai;

public class ObfuscatorUtils {
    /**
     * 与えられた配列の中からランダムに項目を選択する関数
     * @param array 選択に使用する配列
     * @return ランダムに選択された項目（文字列）
     */
    public static String SelectRandom(String[] array){
        int index = array.length;
        return array[(int)(Math.random() * (double)index - 0.5)];
    }
}