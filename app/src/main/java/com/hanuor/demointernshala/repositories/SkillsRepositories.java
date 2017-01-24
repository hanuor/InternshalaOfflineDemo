package com.hanuor.demointernshala.repositories;
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
import android.util.Log;

import com.hanuor.staticDb.AutoCompleteDatabase;
import com.hanuor.staticDb.AutoCompleteFields;
import com.hanuor.staticDb.AutoCompleteModel;

import java.util.ArrayList;

public class SkillsRepositories extends AutoCompleteDatabase{
    private Context ctx;

    public SkillsRepositories(Context context) {
        super(context);
        this.ctx = context;
    }

    public void storeData(ArrayList<AutoCompleteModel> _autoList){
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("Calculation",":::");
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            for (AutoCompleteModel city : _autoList) {
                values.put(AutoCompleteFields.SKILLS_ID, city.getId());
                values.put(AutoCompleteFields.SKILLS_IDSERVER, city.getId_server());
                values.put(AutoCompleteFields.SKILLS_NAME, city.getName());
                values.put(AutoCompleteFields.SKILLS_STATUS, city.getStatus());
                db.insert(AutoCompleteFields.TABLE_SKILLS, null, values);
            }
            db.setTransactionSuccessful();
            Log.d("Calculation","::::::");
        } finally {
            db.endTransaction();
        }
    }
    public int queryDB(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query_params = "SELECT " + "*" + " FROM " + AutoCompleteFields.TABLE_SKILLS;
        Cursor cSor = db.rawQuery(query_params, null);
        return cSor.getCount();
    }
}
