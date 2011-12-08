package com.gatevo.aframe;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;


public class SQLiteConnector extends SQLiteOpenHelper{

	private String tableName;
	private String[] columns;
	
	public SQLiteConnector(Context context, String name, CursorFactory factory,
			int version, String[] columns, String tableName) {
		super(context, name, factory, version);
		
		this.tableName = tableName;
		this.columns = columns;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		int mapsize = this.columns.length;
		String columns = "";

        if(mapsize > 0){
            for (int i = 0; i < mapsize; i++)
            {
                if(i == 0){
                	columns = this.columns[i];
                }else {
                	columns = columns + ", " + this.columns[i];
                }
            }
        }

		db.execSQL("CREATE TABLE IF NOT EXISTS " + this.tableName + " (" + columns + ")");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
}