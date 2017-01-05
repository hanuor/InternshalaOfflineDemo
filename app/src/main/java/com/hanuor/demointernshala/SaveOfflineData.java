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
import android.util.Log;

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
    private static final String MEDIATOR = "mediator";
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
                COLUMN_R_METHOD + " String, " + COLUMN_EXTRAS + " String, " +
                MEDIATOR + " INTEGER"
                + ")";
        sqLiteDatabase.execSQL(TABLE_IMG);

    }
    public SaveOfflineData(Context context) {
        super(context, DBNAME, null, DB_Version);
        //internal = new Internal(context);
    }

    public void storeData(String key, String data, String response, String url, String requestType, String status, String requestMethod, String extras, int mediator){
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
        cv.put(MEDIATOR, mediator);
        database.insert(GLOBALTABLENAME, null, cv);
    }
    public String queryDB(int _key){

        String returnString = null;
        SQLiteDatabase db = this.getReadableDatabase();

        String query_params = "SELECT " + COLUMN_ID +", " + COLUMN_DATA
                + " FROM " + GLOBALTABLENAME + " WHERE " + MEDIATOR + " = " +"" +_key +""+ ";";
        Cursor cSor = db.rawQuery(query_params, null);
        if(cSor.moveToFirst()){
            do{
                return  cSor.getString(cSor.getColumnIndex(SaveOfflineData.COLUMN_DATA));

            }while(cSor.moveToNext());

        }else{
            return null;
        }

    }
    public ArrayList<Caller> ForKey(){
        ArrayList<Caller> callerObj = new ArrayList<Caller>();
        if(getCount()!=0) {


            String returnString = null;
            SQLiteDatabase db = this.getReadableDatabase();
            String ans = null, url=null, method = null, status = null, id = null, reqType = null, extras = null, response = null;
            int mediator;

       /*     String query_params = "SELECT " + COLUMN_ID + ", " + COLUMN_DATA + ", " + COLUMN_URL + ", " +
                    COLUMN_R_METHOD + ", " + COLUMN_STATUS
                    + " FROM " + GLOBALTABLENAME + " WHERE " + COLUMN_ID + " = " + "'" + regenKey + "'" + ";";


*/

            String query_params = "Select * from " + GLOBALTABLENAME;
            Cursor cSor = db.rawQuery(query_params, null);
            if (cSor.moveToFirst()) {
                do {
                    ans = cSor.getString(cSor.getColumnIndex(SaveOfflineData.COLUMN_DATA));

                    url = cSor.getString(cSor.getColumnIndex(SaveOfflineData.COLUMN_URL));
                    method = cSor.getString(cSor.getColumnIndex(SaveOfflineData.COLUMN_R_METHOD));
                    response = cSor.getString(cSor.getColumnIndex(SaveOfflineData.COLUMN_RESP));
                    id = cSor.getString(cSor.getColumnIndex(SaveOfflineData.COLUMN_ID));
                    reqType = cSor.getString(cSor.getColumnIndex(SaveOfflineData.COLUMN_R_TYPE));
                    extras = cSor.getString(cSor.getColumnIndex(SaveOfflineData.COLUMN_EXTRAS));
                    status = cSor.getString(cSor.getColumnIndex(SaveOfflineData.COLUMN_STATUS));
                    mediator = cSor.getInt(cSor.getColumnIndex(SaveOfflineData.MEDIATOR));
                    if(status.equals("processed")){
                        continue;

                    }else{
                        callerObj.add(new Caller(url,method,ans,status,reqType,extras,id,response,mediator));
                    }
                } while (cSor.moveToNext());

            } else {
                //do nothing
            }
        }
        return callerObj;

    }

    public  int getCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query_params = "SELECT " + "*" + " FROM " + GLOBALTABLENAME;
        Cursor cSor = db.rawQuery(query_params, null);
        return cSor.getCount();
    }

    public void update(String id,String response, String status, int count){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_RESP, response);
        cv.put(COLUMN_STATUS, status);
        cv.put(MEDIATOR, count);

String qq = "UPDATE " + GLOBALTABLENAME + " SET " + COLUMN_STATUS + "=" + "'"+ status+"'" + ", " + COLUMN_RESP + "=" +"'"+ response+"', " +
        MEDIATOR +"=" + count +" "+ " WHERE "
        + COLUMN_ID + "=" + "'"+id+"'";
        database.execSQL(qq);

    }
    public void clearTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ GLOBALTABLENAME);
        db.close();
    }
    public void deleteUp(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(GLOBALTABLENAME, null, null, null, null, null, null);

        if(cursor.moveToFirst()) {
            String rowId = cursor.getString(cursor.getColumnIndex(SaveOfflineData.COLUMN_ID));

            //db.delete(GLOBALTABLENAME, SaveOfflineData.COLUMN_ID + "=?",  new String[]{rowId});
        }
        Log.d("upupup","deleted");
        Log.d("yapapap",getCount() + "");
        db.close();
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void insertArrayList(ArrayList<Caller> _caller){
        if(getCount()==0){
            SQLiteDatabase database = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            for(int i = 0; i < _caller.size(); i++){
                cv.put(COLUMN_ID, _caller.get(i).getId());
                cv.put(COLUMN_DATA, _caller.get(i).getAnswer());
                cv.put(COLUMN_RESP, _caller.get(i).getResponse());
                cv.put(COLUMN_URL, _caller.get(i).getReg_url());
                cv.put(COLUMN_R_TYPE, _caller.get(i).getRequestType());
                cv.put(COLUMN_STATUS, _caller.get(i).getStat());
                cv.put(COLUMN_R_METHOD, _caller.get(i).getReq_method());
                cv.put(COLUMN_EXTRAS, _caller.get(i).getExtras());
                cv.put(MEDIATOR, _caller.get(i).getMediator());
                database.insert(GLOBALTABLENAME, null, cv);
            }
        }else{
            ArrayList<Caller> getOld = ForKey();
            _caller.addAll(getOld);
            clearTable();
            insertArrayList(_caller);
           // return;
        }
        }

    public void delete(int Id)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        try {

            database.delete(SaveOfflineData.GLOBALTABLENAME,  SaveOfflineData.MEDIATOR + " = " +Id, null);
        }
        catch(Exception e) {e.printStackTrace(); }
    }
}
