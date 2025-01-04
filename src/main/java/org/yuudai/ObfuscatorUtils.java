package org.yuudai;

import java.util.ArrayList;

public class ObfuscatorUtils {
    /**
     * 与えられた配列の中からランダムに項目を選択するメソッド
     * @param list 選択に使用する配列
     * @return ランダムに選択された項目（文字列）
     */
    public static String GetRandomString(ArrayList<String> list) {
        int index = list.size();
        return list.get((int)(Math.random() * (double)index - 0.5));
    }

    public static Character GetRandomChar(ArrayList<Character> list) {
        int index = list.size();
        return list.get((int)(Math.random() * (double)index - 0.5));
    }
}