package LAIOFFER;

import java.util.*;

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
        for (int i = 0; i < distance[0].length; i++) {
            for (int j = 0; j < distance.length; j++) {
                System.out.print(distance[j][i] + " ");
            }
            System.out.println();
        }
        return distance[one.length()][two.length()];
    }

//这里跟编辑距离非常接近
    public static int largest(int[][] matrix) {
        int size = matrix.length;
        int[][] helper = new int[size][size];
        int max = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == 0 || j == 0) {
                    helper[i][j] = matrix[i][j];
                } else if (matrix[i][j] == 0) {
                    helper[i][j] = 0;
                } else {
                    helper[i][j] = 1 + Math.min(helper[i - 1][j - 1], Math.min(helper[i - 1][j], helper[i][j - 1]));
                }
                max = Math.max(helper[i][j], max);
            }
        }
        for(int i=0;i<helper.length;i++){
            for(int j=0;j<helper.length;j++){
                System.out.print(helper[i][j]+" ");
            }
            System.out.println();
        }
        return max;
    }
//求一个矩阵中和最大的子矩阵
    public static int largest2(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int result = Integer.MIN_VALUE;

        for (int i = 0; i < row; i++) {
            int[] cur = new int[col];
            for (int j = i; j < row; j++) {
                for (int k = 0; k < col; k++) {
                    cur[k] += matrix[j][k];
                }
                result = Math.max(result, max(cur));
            }
        }

        return result;
    }

    private static int max(int[] cur) {
        int result = cur[0];
        int lastMax = cur[0];
        for (int i = 1; i < cur.length; i++) {
            lastMax = Math.max(lastMax + cur[i], cur[i]);
            result = Math.max(result, lastMax);
        }
        return result;
    }

    public static int largest3(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        int N = matrix.length;
        int M = matrix[0].length;

        // 4 DP matrix with 4 directions
        int[][] leftUp = new int[N][M];
        int[][] leftDown = new int[N][M];
        int[][] rightUp = new int[N][M];
        int[][] rightDown = new int[N][M];

        // Fill these 4 matrix
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i == 0 || j == 0) {
                    leftUp[i][j] = matrix[i][j];
                } else if (matrix[i][j] == 0) {
                    leftUp[i][j] = 0;
                } else {
                    leftUp[i][j] = 1 + leftUp[i - 1][j - 1];
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = M - 1; j >= 0; j--) {
                if (i == 0 || j == M - 1) {
                    rightUp[i][j] = matrix[i][j];
                } else if (matrix[i][j] == 0) {
                    rightUp[i][j] = 0;
                } else {
                    rightUp[i][j] = 1 + rightUp[i - 1][j + 1];
                }
            }
        }
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < M; j++) {
                if (i == N - 1 || j == 0) {
                    leftDown[i][j] = matrix[i][j];
                } else if (matrix[i][j] == 0) {
                    leftDown[i][j] = 0;
                } else {
                    leftDown[i][j] = 1 + leftDown[i + 1][j - 1];
                }
            }
        }
        for (int i = N - 1; i >= 0; i--) {
            for (int j = M - 1; j >= 0; j--) {
                if (i == N - 1 || j == M - 1) {
                    rightDown[i][j] = matrix[i][j];
                } else if (matrix[i][j] == 0) {
                    rightDown[i][j] = 0;
                } else {
                    rightDown[i][j] = 1 + rightDown[i + 1][j + 1];
                }
            }
        }

        // Combine these 4 helper matrix into 1
        int globalMax = 0;
        int localMin;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int min1 = Math.min(leftUp[i][j], rightUp[i][j]);
                int min2 = Math.min(leftDown[i][j], rightDown[i][j]);
                localMin = Math.min(min1, min2);
                globalMax = Math.max(globalMax, localMin);
            }
        }

        return globalMax;
    }

    //Longest_Ascending_SubArray, return the longest length
    //ascending 不能是等于
    public static int longest(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }

        int size = array.length;
        int[] helper = new int[size];
        helper[0] = 1;
        int max = 1;

        for (int i = 1; i < size; i++) {
            if (array[i - 1] < array[i]) {
                helper[i] = helper[i - 1] + 1;
                max = Math.max(max, helper[i]);
            } else {
                helper[i] = 1;
            }
        }
        return max;
    }

    //回子形扫描一个数组
    List<Integer> sprialArrayResult=new ArrayList<>();
    //调用下面这个方法
    void sprial(int[][] nums){
        sprialArray(nums, 0, 0);
        printList(sprialArrayResult);
    }
    void sprialArray(int[][] nums, int startRow, int startCol){
        //看哪个先到达中间
        if(nums.length%2==1&&nums[0].length%2==1){
            if(startRow==nums.length/2&&startCol==nums.length/2) {
                sprialArrayResult.add(nums[startRow][startCol]);
                return;
            }
        }
//        else if(nums.length%2==1&&nums[0].length%2==0){
//            if(startCol==nums[0].length/2){
//                for(int i=startRow;i<nums.length-startRow;i++){
//                    sprialArrayResult.add(nums[i][startCol]);
//                }
//            }
//        }
//        else if(nums[0].length%2==1&&nums.length%2==0){
//            if(startRow==nums.length/2){
//                for(int i=startCol;i<nums[startRow].length-startCol;i++){
//                    sprialArrayResult.add(nums[startRow][i]);
//                }
//            }
//        }
        else if(nums.length%2==0|nums[0].length%2==0){
            if(startRow==nums.length/2|startCol==nums.length/2) {
                return;
            }
        }

        int m=nums.length;
        int n=nums[0].length;
        //left to right
        for(int i=startRow;i<nums[startRow].length-startCol;i++){
            sprialArrayResult.add(nums[startRow][i]);
        }
        //up to down
        for(int i=startRow+1; i<nums.length-startRow;i++){
            sprialArrayResult.add(nums[i][nums[startRow].length-startRow-1]);
        }
        //right to left
        for(int i=nums[startRow].length-startCol-1-1;i>=startCol;i--){
            sprialArrayResult.add(nums[nums.length-startRow-1][i]);
        }
        //down to up
        for(int i=nums.length-startRow-1-1;i>=startRow+1;i--){
            if(nums.length-startRow-1-1>startRow){
            sprialArrayResult.add(nums[i][startRow]);
            }
        }
        sprialArray(nums, startRow+1, startCol+1);
    }
    void printList(List<Integer> data){
        if(data==null||data.size()==0) System.out.print("NULL");
        for(int i=0;i<data.size();i++){
            System.out.print("["+data.get(i)+"]");
        }
    }

//Longest_Consecutive

    public static int longest5(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        int[] counter = new int[array.length];
        // Base case;
        counter[0] = array[0];
        int max = counter[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] == 0) {
                counter[i] = 0;
            } else {
                counter[i] = 1 + counter[i - 1];
                max = Math.max(max, counter[i]);
            }
        }
        return max;
    }
    //Longest_Ascending_SubArray
    public static int longest6(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }

        int size = array.length;
        int[] helper = new int[size];
        helper[0] = 1;
        int max = 1;

        for (int i = 1; i < size; i++) {
            if (array[i - 1] < array[i]) {
                helper[i] = helper[i - 1] + 1;
                max = Math.max(max, helper[i]);
            } else {
                helper[i] = 1;
            }
        }
        return max;
    }

    //Longest_Substring_No_Dup
    public static int longest7(String input) {
        if (input == null) {
            return 0;
        }
        Map<Character, Integer> freq = new HashMap<>();
        char[] chars = input.toCharArray();
        int max = 0;
        int start = 0;

        for (int end = 0; end < chars.length; end++) {
            if (!freq.containsKey(chars[end])) {
                freq.put(chars[end], 1);
                max = end - start + 1 > max ? end - start + 1 : max;
            } else if (freq.get(chars[end]) < 1) {
                freq.put(chars[end], freq.get(chars[end]) + 1);
                max = end - start + 1 > max ? end - start + 1 : max;
            } else {
                int i = start;
                while (chars[i] != chars[end]) {
                    freq.put(chars[i], freq.get(chars[i]) - 1);
                    i++;
                }
                start = i + 1;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        System.out.println(longest7("bcdfbd"));
    }
}
