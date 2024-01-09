package com.vinz.nlpdemo;

import org.junit.jupiter.api.Test;

import com.vinz.nlpdemo.services.TextComplexityService;

import static org.junit.jupiter.api.Assertions.*;

class TextComplexityServiceTest {

    @Test
    void testTextComplexity() {
        TextComplexityService service = new TextComplexityService();
        double complexityScore = service.calculateComplexity("But more widespread perhaps than any belief, from its simplicity doubtless, is the idea that the body's shadow or reflexion is the soul.");

        assertTrue(complexityScore >= 0, "Complexity score should be non-negative.");
    }
}