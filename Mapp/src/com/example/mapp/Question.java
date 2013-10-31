package com.example.mapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Question {

	private String question;
	private String correctAnswer;
	private String wrongAnswer1;
	private String wrongAnswer2;
	
	public Question(String question, String correctAnswer, 
			String wrongAnswer1, String wrongAnswer2) {
		this.question = question;
		this.correctAnswer = correctAnswer;
		this.wrongAnswer1 = wrongAnswer1;
		this.wrongAnswer2 = wrongAnswer2;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public boolean isCorrect(String answer) {
		return answer.equals(correctAnswer);
	}
	
	public List<String> getAnswers() {
		ArrayList<String> orderedList = new ArrayList<String>();
		orderedList.add(correctAnswer);
		orderedList.add(wrongAnswer1);
		orderedList.add(wrongAnswer2);
		
		ArrayList<String> randomList = new ArrayList<String>();
		Random rand = new Random();
		
		for(int i = 2; i >= 0; i --) {
			int index = rand.nextInt(i + 1);
			randomList.add(orderedList.get(index));
			orderedList.remove(index);
		}
		
		return randomList;
	}
}
