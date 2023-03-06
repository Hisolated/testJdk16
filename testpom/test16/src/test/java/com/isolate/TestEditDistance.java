package com.isolate;

/**
 * @description: 计算两个数组的相似度，计算距离算法
 * @author: isolate
 * @email: 15071340963@163.com
 * @date: 2023/3/6 16:35
 */
public class TestEditDistance {

    public static void main(String[] args) {

        String[] keywords1 = {"apple", "banana", "orange"};
        String[] keywords2 = {"banana", "orange", "pear"};

        int distance = editDistance(keywords1, keywords2);
        System.out.println("Edit distance: " + distance);
    }

    public static int editDistance(String[] keywords1, String[] keywords2) {
        int m = keywords1.length;
        int n = keywords2.length;

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (keywords1[i - 1].equals(keywords2[j - 1])) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }

        return dp[m][n];
    }
}

