package org.yuudai;

import org.jetbrains.annotations.NotNull;

public class ObfuscatorUtils {
    /**
     * 与えられた配列の中からランダムに項目を選択するメソッド
     * @param array 選択に使用する配列
     * @return ランダムに選択された項目（文字列）
     */
    public static String SelectRandom(String @NotNull [] array){
        int index = array.length;
        return array[(int)(Math.random() * (double)index - 0.5)];
    }

    /**
     * ランダムな文字列を生成するメソッド
     * @param length 生成したい文字列の長さ
     * @return 生成されたランダムな文字列（大文字・小文字のアルファベットと「:」を含む）
     */
    public static @NotNull String GenerateRandomString(int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            switch ((int) (Math.random() * 2 + 0.5)) {
                case 0 -> builder.append((char) (Math.random() * 26 + 'a'));
                case 1 -> builder.append((char) (Math.random() * 26 + 'A'));
                case 2 -> builder.append(':');
            }
        }
        return builder.toString();
    }
}