package com.vinz.nlpdemo.endpoints;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.vinz.nlpdemo.dto.NlpDto;
import com.vinz.nlpdemo.dto.TextDto;
import com.vinz.nlpdemo.services.SentimentAnalyzerService;
import com.vinz.nlpdemo.services.TextComplexityService;

@RestController
public class ComplexityController {
	
	@Autowired
	private TextComplexityService complexityService;
	
	@Autowired
	private SentimentAnalyzerService sentimentAnalyzerService;
	
	
	@PostMapping("/complexity")
	public ResponseEntity<Map<String, Object>> nlpCalculation(@RequestBody TextDto textDto) {
		try {
		    
		    NlpDto dto = new NlpDto();
		    dto.setSentiment(sentimentAnalyzerService.analyzeSentiment(textDto.getText()));
		    dto.setComplexity(complexityService.calculateComplexity(textDto.getText()));

			Map<String, Object> response = new HashMap<>();
			response.put("status", "success");
			response.put("message", "Complexity calculated successfully");
			response.put("data", dto);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Unable to retrieve complexity", e);
		}
	}
}
