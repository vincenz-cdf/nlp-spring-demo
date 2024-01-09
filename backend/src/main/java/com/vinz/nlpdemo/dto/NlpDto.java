package com.vinz.nlpdemo.dto;

public class NlpDto {

	private String sentiment;
	private double complexity;
	
	public NlpDto() {
		super();
	}
	
	public NlpDto(String sentiment, double complexity) {
		super();
		this.sentiment = sentiment;
		this.complexity = complexity;
	}
	
	public String getSentiment() {
		return sentiment;
	}
	
	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}
	
	public double getComplexity() {
		return complexity;
	}
	
	public void setComplexity(double complexity) {
		this.complexity = complexity;
	}
}
