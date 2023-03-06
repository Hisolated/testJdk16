package com.isolate;

import java.util.*;

/**
 * @description: 计算两组关键词的相似度：余弦相似度算法
 * @author: isolate
 * @email: 15071340963@163.com
 * @date: 2023/3/6 16:29
 */

public class TestCosineSimilarity {

    // 余弦相似度算法
    public static void main(String[] args) {

        String[] keywords1 = {"apple", "banana", "orange"};
        String[] keywords2 = {"banana", "orange", "pear"};

        double similarity = cosineSimilarity(keywords1, keywords2);
        System.out.println("Cosine similarity: " + similarity);
    }

    public static double cosineSimilarity(String[] keywords1, String[] keywords2) {
        Map<String, Integer> freq1 = getWordFrequency(keywords1);
        Map<String, Integer> freq2 = getWordFrequency(keywords2);

        Set<String> intersection = new HashSet<>(freq1.keySet());
        intersection.retainAll(freq2.keySet());

        double dotProduct = 0;
        for (String word : intersection) {
            dotProduct += freq1.get(word) * freq2.get(word);
        }

        double norm1 = getNorm(freq1);
        double norm2 = getNorm(freq2);

        return dotProduct / (norm1 * norm2);
    }

    private static Map<String, Integer> getWordFrequency(String[] keywords) {
        Map<String, Integer> freq = new HashMap<>();
        for (String word : keywords) {
            freq.put(word, freq.getOrDefault(word, 0) + 1);
        }
        return freq;
    }

    private static double getNorm(Map<String, Integer> freq) {
        double sumOfSquares = 0;
        for (int freqCount : freq.values()) {
            sumOfSquares += Math.pow(freqCount, 2);
        }
        return Math.sqrt(sumOfSquares);
    }


}
