package com.vinz.nlpdemo.services;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.*;

import java.util.*;

import org.springframework.stereotype.Service;

@Service
public class TextComplexityService {

    private StanfordCoreNLP pipeline;

    public TextComplexityService() {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit");
        pipeline = new StanfordCoreNLP(props);
    }

    public double calculateComplexity(String text) {
        CoreDocument doc = pipeline.processToCoreDocument(text);
        int totalSentences = doc.sentences().size();
        int totalWords = 0;
        int totalSyllables = 0;

        for (CoreSentence sentence : doc.sentences()) {
            for (CoreLabel token : sentence.tokens()) {
                String word = token.word();
                totalWords++;
                totalSyllables += countSyllables(word);
            }
        }

        // Flesch-Kincaid Grade Level calculation
        return 0.39 * (double) totalWords / totalSentences + 
               11.8 * (double) totalSyllables / totalWords - 
               15.59;
    }

    private int countSyllables(String word) {
        int count = 0;
        word = word.toLowerCase();
        boolean vowel = false;
        for (int i = 0; i < word.length(); i++) {
            if (isVowel(word.charAt(i))) {
                if (!vowel) {
                    count++;
                    vowel = true;
                }
            } else {
                vowel = false;
            }
        }
        return Math.max(count, 1); // Ensure at least one syllable
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}