package com.isolate;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 标签带权重相似度计算，加权余弦相似度算法
 * @author: isolate
 * @email: 15071340963@163.com
 * @date: 2023/3/6 16:27
 */
public class TestWeightedTagSimilarity {

    public static void main(String[] args) {

        Map<String, Double> tags1 = new HashMap<>();
        tags1.put("apple", 2.0);
        tags1.put("banana", 3.0);
        tags1.put("orange", 1.0);

        Map<String, Double> tags2 = new HashMap<>();
        tags2.put("banana", 2.0);
        tags2.put("orange", 3.0);
        tags2.put("pear", 1.0);

        double similarity = weightedTagSimilarity(tags1, tags2);
        System.out.println("Weighted tag similarity: " + similarity);
    }

    public static double weightedTagSimilarity(Map<String, Double> tags1, Map<String, Double> tags2) {
        Map<String, Double> commonTags = new HashMap<>(tags1);
        commonTags.keySet().retainAll(tags2.keySet());

        double dotProduct = 0;
        for (String tag : commonTags.keySet()) {
            dotProduct += tags1.get(tag) * tags2.get(tag);
        }

        double norm1 = getNorm(tags1);
        double norm2 = getNorm(tags2);

        return dotProduct / (norm1 * norm2);
    }

    private static double getNorm(Map<String, Double> tags) {
        double sumOfSquares = 0;
        for (double weight : tags.values()) {
            sumOfSquares += Math.pow(weight, 2);
        }
        return Math.sqrt(sumOfSquares);
    }

}
