package com.isolate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @description: 计算两组关键词的相似度：jaccard相似度算法
 * @author: isolate
 * @email: 15071340963@163.com
 * @date: 2023/3/6 16:32
 */

public class TestJaccardSimilarity {

    public static void main(String[] args) {

        String[] keywords1 = {"apple", "banana", "orange"};
        String[] keywords2 = {"banana", "orange", "pear"};

        double similarity = jaccardSimilarity(keywords1, keywords2);
        System.out.println("Jaccard similarity: " + similarity);
    }

    public static double jaccardSimilarity(String[] keywords1, String[] keywords2) {
        Set<String> set1 = new HashSet<>(Arrays.asList(keywords1));
        Set<String> set2 = new HashSet<>(Arrays.asList(keywords2));

        Set<String> union = new HashSet<>(set1);
        union.addAll(set2);

        Set<String> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);

        return (double) intersection.size() / union.size();
    }
}
