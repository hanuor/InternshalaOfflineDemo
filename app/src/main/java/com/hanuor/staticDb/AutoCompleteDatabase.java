package com.hanuor.staticDb;
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

import static android.app.DownloadManager.COLUMN_STATUS;

public class AutoCompleteDatabase extends SQLiteOpenHelper {
    private static final int DB_Version = 5;

    public static final String DBNAME = "AutocompleteInternshala.db";

    public static final String TABLE_COLLEGES = "colleges";
    public static final String COLLEGES_ID  = "id";
    public static final String COLLEGES_IDSERVER = "id_server";
    public static final String COLLEGES_STATUS= "status";
    public static final String COLLEGES_NAME = "name";

    public static final String TABLE_SKILLS = "skills";
    public static final String SKILLS_ID = "id";
    public static final String SKILLS_IDSERVER = "id_server";
    public static final String SKILLS_STATUS = "status";
    public static final String SKILLS_NAME = "name";

    public static final String TABLE_DEGREES = "degrees";
    public static final String DEGREES_ID  = "id";
    public static final String DEGREES_IDSERVERDB = "id_server_db";
    public static final String DEGREES_STATUS= "status";
    public static final String DEGREES_NAME = "name";

    public static final String TABLE_STREAMS = "streams";
    public static final String STREAMS_ID  = "id";
    public static final String STREAMS_IDSERVERDB = "id_server_db";
    public static final String STREAMS_STATUS= "status";
    public static final String STREAMS_NAME = "name";

    public static final String TABLE_PROFILES = "profiles";
    public static final String PROFILES_ID  = "id";
    public static final String PROFILES_IDSERVERDB = "id_server_db";
    public static final String PROFILES_STATUS= "status";
    public static final String PROFILES_NAME = "name";

    public AutoCompleteDatabase(Context context) {
        super(context, DBNAME, null, DB_Version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String TABLE_CREATE_COLLEGES = "CREATE TABLE " + TABLE_COLLEGES + " (" +
                COLLEGES_ID + " INTEGER, " + COLLEGES_IDSERVER + " INTEGER, " +
                COLLEGES_STATUS + " STRING, " + COLLEGES_NAME + " STRING);";

        String TABLE_CREATE_SKILLS = "CREATE TABLE " + TABLE_SKILLS + " (" +
                SKILLS_ID + " INTEGER, " + SKILLS_IDSERVER + " INTEGER, " +
                SKILLS_STATUS + " STRING, " + SKILLS_NAME + " STRING);";

        String TABLE_CREATE_STREAMS = "CREATE TABLE " + TABLE_STREAMS + " (" +
                STREAMS_ID + " INTEGER, " + STREAMS_IDSERVERDB + " INTEGER, " +
                STREAMS_STATUS + " STRING, " + STREAMS_STATUS + " STRING);";

        String TABLE_CREATE_DEGREES = "CREATE TABLE " + TABLE_DEGREES + " (" +
                DEGREES_ID + " INTEGER, " + DEGREES_IDSERVERDB + " INTEGER, " +
                DEGREES_STATUS + " STRING, " + DEGREES_NAME + " STRING);";

        String TABLE_CREATE_PROFILES = "CREATE TABLE " + TABLE_PROFILES + " (" +
                PROFILES_ID + " INTEGER, " + PROFILES_IDSERVERDB + " INTEGER, " +
                PROFILES_STATUS + " STRING, " + PROFILES_NAME + " STRING);";

        sqLiteDatabase.execSQL(TABLE_CREATE_COLLEGES);
        sqLiteDatabase.execSQL(TABLE_CREATE_SKILLS);
        sqLiteDatabase.execSQL(TABLE_CREATE_STREAMS);
        sqLiteDatabase.execSQL(TABLE_CREATE_DEGREES);
        sqLiteDatabase.execSQL(TABLE_CREATE_PROFILES);
    }
    public void storeData(ArrayList<AutoCompleteModel> _autoList){
        Log.d("Calculation",":  ");
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            for (AutoCompleteModel city : _autoList) {
                values.put(COLUMN_ID, city.getId());
                values.put(COLUMN_IDSERVER, city.getId_server());
                values.put(COLUMN_NAME, city.getName());
                values.put(COLUMN_STATUS, city.getStatus());
                db.insert(TABLENAME, null, values);
            }
            db.setTransactionSuccessful();
            Log.d("Calculation","::::::");
        } finally {
            db.endTransaction();
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public int queryDB(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query_params = "SELECT " + "*" + " FROM " + TABLENAME;
        Cursor cSor = db.rawQuery(query_params, null);
        return cSor.getCount();
    }
}
