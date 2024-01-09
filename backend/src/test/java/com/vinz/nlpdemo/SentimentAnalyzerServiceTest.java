package com.vinz.nlpdemo;

import org.junit.jupiter.api.Test;

import com.vinz.nlpdemo.services.SentimentAnalyzerService;

import static org.junit.jupiter.api.Assertions.*;

class SentimentAnalyzerServiceTest {

    @Test
    void testSentimentAnalysis() {
        SentimentAnalyzerService service = new SentimentAnalyzerService();
        String sentiment = service.analyzeSentiment("I love sunny days.");
        assertNotNull(sentiment, "Sentiment should not be null");
        assertEquals("Positive", sentiment, "Sentiment should be positive");
    }
}
