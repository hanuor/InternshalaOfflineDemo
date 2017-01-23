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

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.hanuor.staticDb.AutoCompleteFields.COLLEGES_ID;
import static com.hanuor.staticDb.AutoCompleteFields.COLLEGES_IDSERVER;
import static com.hanuor.staticDb.AutoCompleteFields.COLLEGES_NAME;
import static com.hanuor.staticDb.AutoCompleteFields.COLLEGES_STATUS;
import static com.hanuor.staticDb.AutoCompleteFields.DBNAME;
import static com.hanuor.staticDb.AutoCompleteFields.DEGREES_ID;
import static com.hanuor.staticDb.AutoCompleteFields.DEGREES_IDSERVERDB;
import static com.hanuor.staticDb.AutoCompleteFields.DEGREES_NAME;
import static com.hanuor.staticDb.AutoCompleteFields.DEGREES_STATUS;
import static com.hanuor.staticDb.AutoCompleteFields.PROFILES_ID;
import static com.hanuor.staticDb.AutoCompleteFields.PROFILES_IDSERVERDB;
import static com.hanuor.staticDb.AutoCompleteFields.PROFILES_NAME;
import static com.hanuor.staticDb.AutoCompleteFields.PROFILES_STATUS;
import static com.hanuor.staticDb.AutoCompleteFields.SKILLS_ID;
import static com.hanuor.staticDb.AutoCompleteFields.SKILLS_IDSERVER;
import static com.hanuor.staticDb.AutoCompleteFields.SKILLS_NAME;
import static com.hanuor.staticDb.AutoCompleteFields.SKILLS_STATUS;
import static com.hanuor.staticDb.AutoCompleteFields.STREAMS_ID;
import static com.hanuor.staticDb.AutoCompleteFields.STREAMS_IDSERVERDB;
import static com.hanuor.staticDb.AutoCompleteFields.STREAMS_NAME;
import static com.hanuor.staticDb.AutoCompleteFields.STREAMS_STATUS;
import static com.hanuor.staticDb.AutoCompleteFields.TABLE_COLLEGES;
import static com.hanuor.staticDb.AutoCompleteFields.TABLE_DEGREES;
import static com.hanuor.staticDb.AutoCompleteFields.TABLE_PROFILES;
import static com.hanuor.staticDb.AutoCompleteFields.TABLE_SKILLS;
import static com.hanuor.staticDb.AutoCompleteFields.TABLE_STREAMS;

public class AutoCompleteDatabase extends SQLiteOpenHelper {
    private static final int DB_Version = 5;


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
                STREAMS_STATUS + " STRING, " + STREAMS_NAME + " STRING);";

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

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
