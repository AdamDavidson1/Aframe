package com.gatevo.example;

import android.content.Context;

import com.gatevo.aframe.DatabaseModel;

public class ExampleModel extends DatabaseModel{
	private static String[] columns = { 
										"id INTEGER PRIMARY KEY AUTOINCREMENT",
										"username TEXT",
										"added_datetime DATE"
									  };
	private static String tableName = "example";
	
	public ExampleModel(Context context){
		super(context, columns, tableName);
	}
	
}