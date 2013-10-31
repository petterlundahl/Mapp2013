package com.example.mapp;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database name
	private static final String DATABASE_NAME = " Mapp";

	// Table names
	private static final String MISSIONS = "Missions";
	private static final String QUESTIONS = "Questions";

	// Missions
	private static String object_Name = "object_name";

	// Questions
	private static String ID = "id"; // auto
	private static String question = "question"; // frågan
	private static String correctAnswer = "correct_answer"; // rätt svar
	private static String wrongAnswer1 = "wrong_answer1"; // fel svar1
	private static String wrongAnswer2 = "wrong_answer2"; // fel svar2

	// table create statements
	private static final String CREATE_TABLE_MISSONS = "CREATE TABLE "
			+ MISSIONS + "(" + object_Name + " TEXT PRIMARY KEY" + ")";

	// table create stmt
	private static final String CREATE_TABLE_QUESTIONS = "CREATE TABLE "
			+ QUESTIONS + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ object_Name + " TEXT,"
			+ question + " TEXT," + correctAnswer + " TEXT, " + wrongAnswer1
			+ " TEXT," + wrongAnswer2 + " TEXT, FOREIGN_KEY (" + object_Name
			+ ") REFERENCES " + MISSIONS + " (" + object_Name + "))";

	// konstruktor
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		// creating required tables
		db.execSQL(CREATE_TABLE_MISSONS); // skapa tabellen missions
		//db.execSQL(CREATE_TABLE_QUESTIONS); // skapa tabellen questions
		createMissions(db);
		//createQuestions(db);
	}

	private void createMissions(SQLiteDatabase db) {
		ContentValues values = new ContentValues();

		// insert row, stoppa in i tabellen Missions och tömmer den varje gång
		values.put(object_Name, "Ek");
		db.insert(MISSIONS, null, values);
		values.clear();
		
		values.put(object_Name, "Björk");
		db.insert(MISSIONS, null, values);
		values.clear();

		values.put(object_Name, "Tall");
		db.insert(MISSIONS, null, values);
		values.clear();

		values.put(object_Name, "Gran");
		db.insert(MISSIONS, null, values);
		values.clear();

		values.put(object_Name, "Sälg");
		db.insert(MISSIONS, null, values);
		values.clear();
		
	}

	private void createQuestions(SQLiteDatabase db) {
		ContentValues values = new ContentValues();
		
		// insert row, stoppa in i tabellen QUESTIONS
		values.put(null, "Hur hög kan en ek bli?");
		question = String.valueOf(db.insert(QUESTIONS, null, values));
		values.clear();

		values.put(null, "Hur hög kan en Björk bli?");
		question = String.valueOf(db.insert(QUESTIONS, null, values));
		values.clear();

		values.put(null, "Hur hög kan en Gran bli?");
		question = String.valueOf(db.insert(QUESTIONS, null, values));
		values.clear();

		values.put(null, "Hur hög kan en Sälg bli?");
		question = String.valueOf(db.insert(QUESTIONS, null, values));
		values.clear();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		// on Upgrade drop older tables
		db.execSQL("DROP TABLE IF EXISTS " + MISSIONS);
		db.execSQL("DROP TABLE IF EXISTS " + QUESTIONS);

		// Create new tables
		onCreate(db);
	}

	// Returnerar en lista med namn på alla uppdragsobjekt i databasen
	public List<String> getMissions() {
		// efter Sprint1
		// String->object
		List<String> missions = new ArrayList<String>();

		String selectQuery = "SELECT  * FROM " + MISSIONS;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				missions.add(c.getString(c.getColumnIndex(object_Name)));
			} while (c.moveToNext());
		}

		return missions;
	}

	// Returnerar en lista med frågor från databasen för vald mission
	public List<Question> getQuestions(String objectName) {
		// efter Sprint1, String->object
		List<Question> questions = new ArrayList<Question>();
		

		String selectQuery = "SELECT  * FROM " + QUESTIONS
				+ "WHERE objectName = '" + objectName + "'";
		// tex select * from question where objectName = 'Ek';

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				// questions.add(new Question(question, correctAnswer,
				// wrongAnswer1, wrongAnswer2));
				Question q = new Question(c.getString(c.getColumnIndex(question)),
						c.getString(c.getColumnIndex(correctAnswer)),
						c.getString(c.getColumnIndex(wrongAnswer1)),
						c.getString(c.getColumnIndex(wrongAnswer2)));
				
				questions.add(q);

			} while (c.moveToNext());
		}

		return questions;
	}

}

