package com.example.mapp;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class QuestionActivity extends Activity implements OnClickListener {

	private int currentQuestionIndex;
	private ArrayList<Question> questions;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.activity_question);
		
		Intent intent = getIntent();
		String objectName = intent.getStringExtra(MissionsActivity.MISSION_NAME_KEY);
		
		TextView titleText = (TextView) findViewById(R.id.title);
		titleText.setText(objectName);
		
		Button btnA = (Button) findViewById(R.id.buttonA);
		Button btnB = (Button) findViewById(R.id.buttonB);
		Button btnC = (Button) findViewById(R.id.buttonC);
		
		btnA.setOnClickListener(this);
		btnB.setOnClickListener(this);
		btnC.setOnClickListener(this);
		
		currentQuestionIndex = 0;
		
		DatabaseHelper db = new DatabaseHelper(getApplicationContext());
		
		/* Kan avkommenteras när createQuestions() är helt klar
		questions = (ArrayList<Question>) db.getQuestions(objectName);
		Question q = questions.get(currentQuestionIndex);
		
		ArrayList<String> answers = (ArrayList<String>) q.getAnswers();
		
		btnA.setText(answers.get(0));
		btnB.setText(answers.get(1));
		btnC.setText(answers.get(2));
		*/
	}

	@Override
	public void onClick(View view) {
		Button btn = (Button) view;
		String answer = (String) btn.getText();
		
		//check answer with Question.isCorrect(answer)
	}

}
