package com.gatevo.aframe;

import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class DatabaseModel{
	
	private SQLiteConnector dbConnector;
	public SQLiteDatabase db;
	private String tableName;
	
	protected DatabaseModel(Context context, String[] columns, String tableName){
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
	
	public long smartInsert(ContentValues insert){
		return this.db.insert(this.tableName, null, insert);
	}
	
	public int smartUpdate(ContentValues values, String[] where){
		return this.db.update(this.tableName, values, null, where);
	}
	
}