package com.gatevo.aframe;

import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class DatabaseModel{
	
	private SQLiteConnector dbConnector;
	private SQLiteDatabase db;
	private String tableName;
	
	private DatabaseModel(Context context, Map<String,String> columns, String tableName){
		this.dbConnector = new SQLiteConnector(context, "datastore.db", null, 1, columns, tableName);
		
		this.db = this.dbConnector.getWritableDatabase();
		this.tableName = tableName;
	}
	
	@SuppressWarnings({ "unchecked", "null" })
	public Map smartSelect(String[] columns, String[] where, Object... addendum){
		Cursor ret = this.db.query(this.tableName, columns, null, where, null, null, null);
		
		int count = ret.getCount();
		Map res = null;
		for(int i = 0;i < count;i++){
			ret.move(i);
			res.put(i, ret.getString(i));
		}
		
		return res;
	}
	
}