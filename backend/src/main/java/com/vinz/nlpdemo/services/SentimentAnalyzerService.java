package com.vinz.nlpdemo.services;

import edu.stanford.nlp.pipeline.*;

import java.util.Properties;

import org.springframework.stereotype.Service;

@Service
public class SentimentAnalyzerService {

    private StanfordCoreNLP pipeline;

    public SentimentAnalyzerService() {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
        pipeline = new StanfordCoreNLP(props);
    }

    public String analyzeSentiment(String text) {
        CoreDocument document = pipeline.processToCoreDocument(text);
        for (CoreSentence sentence : document.sentences()) {
            String sentiment = sentence.sentiment();
            return sentiment; // For simplicity, returning sentiment of the first sentence
        }
        return null;
    }
}
