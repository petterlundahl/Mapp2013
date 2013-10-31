package com.example.mapp;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MissionsActivity extends Activity implements OnItemClickListener {

	private ArrayList<String> missions;
	
	public static final String MISSION_NAME_KEY = "mission_name";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.activity_missions);
		
		ListView list = (ListView) findViewById(R.id.missions_list);
		list.setOnItemClickListener(this);
		
		DatabaseHelper db = new DatabaseHelper(getApplicationContext());
		
		//Den här raden kraschar appen:
		missions = (ArrayList<String>) db.getMissions();
		
		MissionsAdapter adapter = new MissionsAdapter(this, missions);
		list.setAdapter(adapter);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
		String mission = missions.get(position);
		
		Intent intent = new Intent(MissionsActivity.this, QuestionActivity.class);
		intent.putExtra(MISSION_NAME_KEY, mission);
		startActivity(intent);
	}
	
	private class MissionsAdapter extends BaseAdapter {

		private LayoutInflater inflater;
		private ArrayList<String> missions;
		
		public MissionsAdapter(Activity activity, ArrayList<String> missions) {
			inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			this.missions = missions;
		}
		
		@Override
		public int getCount() {
			return missions.size();
		}

		@Override
		public Object getItem(int position) {
			return missions.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			View view = inflater.inflate(R.layout.mission_item, null);
			TextView textView = (TextView) view.findViewById(R.id.object_name);
			textView.setText(missions.get(position));
			
			return view;
		}
		
	}

}
