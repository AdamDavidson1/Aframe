package com.gatevo.aframe;

import java.util.Iterator;
import java.util.Map;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;


public class SQLiteConnector extends SQLiteOpenHelper{

	private String tableName;
	private Map<String, String> columns;
	
	public SQLiteConnector(Context context, String name, CursorFactory factory,
			int version, Map<String, String> columns, String tableName) {
		super(context, name, factory, version);
		
		this.tableName = tableName;
		this.columns = columns;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(SQLiteDatabase db) {
		int mapsize = this.columns.size();
		String columns = "";

        if(mapsize > 0){
        	Iterator keyValuePairs1 = this.columns.entrySet().iterator();
            for (int i = 0; i < mapsize; i++)
            {
            	Map.Entry entry = (Map.Entry) keyValuePairs1.next();
                if(i == 0){
                	columns = entry.getKey().toString() + " " + entry.getValue().toString();
                }else {
                	columns = columns + ", " + entry.getKey().toString() + " " + entry.getValue().toString();
                }
            }
        }

		db.execSQL("CREATE TABLE IF NOT EXISTS " + this.tableName + " (" + columns + ")");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
}