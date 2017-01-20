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
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class AutoCompleteDatabase extends SQLiteOpenHelper {
    private static final int DB_Version = 3;

    private static final String DBNAME = "AutocompleteInternshala.db";
    private static final String TABLENAME = "AutoComplete";
    private static final String COLUMN_ID  = "Id";
    private static final String COLUMN_IDSERVER = "IdServer";
    private static final String COLUMN_STATUS= "Status";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_CREATEDAT = "CreatedAt";
    private static final String COLUMN_UPDATEDAT = "UpdatedAt";
    public AutoCompleteDatabase(Context context) {
        super(context, DBNAME, null, DB_Version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String TABLE_CREATE = "CREATE TABLE " + TABLENAME + " (" +
                COLUMN_ID + " STRING, " + COLUMN_IDSERVER + " STRING, " +
                COLUMN_STATUS + " STRING, " + COLUMN_NAME + " STRING);";
        sqLiteDatabase.execSQL(TABLE_CREATE);
    }
    public void storeData(ArrayList<AutoCompleteModel> _autoList){
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
        } finally {
            db.endTransaction();
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
