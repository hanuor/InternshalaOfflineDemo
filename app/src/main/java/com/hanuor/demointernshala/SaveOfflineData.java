package com.hanuor.demointernshala;
/*
 * Copyright (C) 2016 Hanuor Inc. by Shantanu Johri(https://hanuor.github.io/shanjohri/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class SaveOfflineData extends SQLiteOpenHelper {
    private static final int DB_Version = 3;

    private static final String DBNAME = "Internshala.db";
    private static final String GLOBALTABLENAME = "DataCollector";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DATA = "data";

    private static final String COLUMN_RESP = "response";
    private static final String COLUMN_URL = "url";
    private static final String COLUMN_R_TYPE = "type";
    private static final String COLUMN_STATUS = "status";
    private static final String COLUMN_R_METHOD = "requestmethod";
    private static final String COLUMN_EXTRAS = "extras";
    Internal internal;



    public SaveOfflineData(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String TABLE_IMG = "CREATE TABLE " + GLOBALTABLENAME + "(" +
                COLUMN_ID+ " STRING," + COLUMN_DATA + " String, " +
                COLUMN_RESP + " STRING, " + COLUMN_URL + " String, " +
                COLUMN_R_TYPE + " String, " + COLUMN_STATUS + " String, " +
                COLUMN_R_METHOD + " String, " + COLUMN_EXTRAS + " String"
                + ")";
        sqLiteDatabase.execSQL(TABLE_IMG);

    }
    public SaveOfflineData(Context context) {
        super(context, DBNAME, null, DB_Version);
        //internal = new Internal(context);
    }

    public void storeData(String key, String data, String response, String url, String requestType, String status, String requestMethod, String extras){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ID, key);
        cv.put(COLUMN_DATA, data);
        cv.put(COLUMN_RESP, response);
        cv.put(COLUMN_URL, url);
        cv.put(COLUMN_R_TYPE, requestType);
        cv.put(COLUMN_STATUS, status);
        cv.put(COLUMN_R_METHOD, requestMethod);
        cv.put(COLUMN_EXTRAS, extras);
        database.insert(GLOBALTABLENAME, null, cv);
    }
    public String queryDB(String _key){
        String regenKey = _key ;
        String returnString = null;
        SQLiteDatabase db = this.getReadableDatabase();

        String query_params = "SELECT " + COLUMN_ID +", " + COLUMN_DATA
                + " FROM " + GLOBALTABLENAME + " WHERE " + COLUMN_ID + " = " +"'" +regenKey +"'"+ ";";
        Cursor cSor = db.rawQuery(query_params, null);
        if(cSor.moveToFirst()){
            do{
                return  cSor.getString(cSor.getColumnIndex(SaveOfflineData.COLUMN_DATA));

            }while(cSor.moveToNext());

        }else{
            return null;
        }

    }
    public ArrayList<Caller> ForKey(String _key){

        if(getCount()!=0) {
        ArrayList<Caller> callerObj = new ArrayList<Caller>();
            String regenKey = _key;
            String returnString = null;
            SQLiteDatabase db = this.getReadableDatabase();
            String ans = null, url=null, method = null;

            String query_params = "SELECT " + COLUMN_ID + ", " + COLUMN_DATA + ", " + COLUMN_URL + ", " +
                    COLUMN_R_METHOD
                    + " FROM " + GLOBALTABLENAME + " WHERE " + COLUMN_ID + " = " + "'" + regenKey + "'" + ";";


            Cursor cSor = db.rawQuery(query_params, null);
            if (cSor.moveToFirst()) {
                do {
                    ans = cSor.getString(cSor.getColumnIndex(SaveOfflineData.COLUMN_DATA));

                    url = cSor.getString(cSor.getColumnIndex(SaveOfflineData.COLUMN_URL));
                    method = cSor.getString(cSor.getColumnIndex(SaveOfflineData.COLUMN_R_METHOD));
                    callerObj.add(new Caller(url,method,ans));



                } while (cSor.moveToNext());

            } else {
                //do nothing
            }
           return callerObj;

        }
return null;
    }

    public  int getCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query_params = "SELECT " + "*" + " FROM " + GLOBALTABLENAME;
        Cursor cSor = db.rawQuery(query_params, null);
        return cSor.getCount();
    }

    public void update(String id,String response, String status){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_RESP, response);
        cv.put(COLUMN_STATUS, status);
String qq = "UPDATE " + GLOBALTABLENAME + " SET " + COLUMN_STATUS + "=" + "'"+ status+"'" + ", " + COLUMN_RESP + "=" +"'"+ response+"'" + " WHERE "
        + COLUMN_ID + "=" + "'"+id+"'";
        database.execSQL(qq);

    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
