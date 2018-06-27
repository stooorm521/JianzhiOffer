package LAIOFFER;

import java.util.Arrays;
import java.util.HashSet;

public class Solutions {

        public static String decompress1(String input) {
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < input.length(); i += 2) {
                char letter = input.charAt(i);
                int repeat = Character.getNumericValue(input.charAt(i + 1));
                if (repeat != 0) {
                    for (int j = 0; j < repeat; j++) {
                        result.append(letter);
                    }
                }
            }

            return result.toString();
        }

        public static String decompress2(String input) {
            if (input == null || input.length() == 0) {
                return input;
            }
            char[] chars = input.toCharArray();
            // Pre calculate the length of the decompressed string
            int size = 0;
            for (int i = 1; i < input.length(); i += 2) {
                size += Character.getNumericValue(chars[i]);
            }

            int index = size - 1;
            for (int i = input.length() - 2; i >= 0; i -= 2) {
                char letter = chars[i];
                int repeat = Character.getNumericValue(chars[i + 1]);
                if (repeat != 0) {
                    for (int j = 0; j < repeat; j++) {
                        chars[index--] = letter;
                    }
                }
            }

            return chars.toString();
        }

    public static boolean canBreak(String input, String[] dict) {
        HashSet<String> myDict = new HashSet<>(Arrays.asList(dict));
        int size = input.length();
        int[] dp = new int[size + 1];
        dp[0] = 1;
        for (int i = 1; i <= size; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] == 1 && myDict.contains(input.substring(j, i))) {
                    dp[i] = 1;
                    break;
                }
            }
        }
        return dp[size] == 1;
    }

    public static int editDistance(String one, String two) {
        int[][] distance = new int[one.length() + 1][two.length() + 1]; // Including 0;
        for (int i = 0; i <= one.length(); i++) {
            for (int j = 0; j <= two.length(); j++) {
                if (i == 0) {
                    distance[i][j] = j;
                } else if (j == 0) {
                    distance[i][j] = i;
                } else if (one.charAt(i - 1) == two.charAt(j - 1)) {
                    distance[i][j] = distance[i - 1][j - 1];
                } else {
                    int case1 = 1 + distance[i - 1][j - 1];
                    int case2 = 1 + distance[i - 1][j];
                    int case3 = 1 + distance[i][j - 1];
                    distance[i][j] = Math.min(case1, Math.min(case2, case3));
                }
            }
        }
        for(int i=0;i<distance[0].length;i++) {
            for(int j=0;j<distance.length;j++){
                System.out.print(distance[j][i]+" ");
            }
            System.out.println();
        }
        return distance[one.length()][two.length()];
    }

    public static void main(String[] args) {
        System.out.print(editDistance("sigh", "asith"));
    }
}
