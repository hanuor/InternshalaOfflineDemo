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
import java.util.HashMap;

public class CollegesRepository extends AutoCompleteDatabase{
    private Context ctx;

    public CollegesRepository(Context context) {
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
                values.put(AutoCompleteFields.COLLEGES_ID, city.getId());
                values.put(AutoCompleteFields.COLLEGES_IDSERVER, city.getId_server());
                values.put(AutoCompleteFields.COLLEGES_NAME, city.getName());
                values.put(AutoCompleteFields.COLLEGES_STATUS, city.getStatus());
                db.insert(AutoCompleteFields.TABLE_COLLEGES, null, values);
            }
            db.setTransactionSuccessful();
            Log.d("Calculation","::::::");
            //ArrayList<String> _get = getColleges();
//            JSONArray jsonArray = new JSONArray(_get);
//            HashMap<String, String> hMap = new HashMap<String, String>();
//            hMap.put("collegesData",jsonArray.toString());
//            JSONObject jsonObject = new JSONObject(hMap);

         //   Log.d("VamosHan","" + jsonObject.toString());
        } finally {
            db.endTransaction();
        }
    }
    public int queryDB(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query_params = "SELECT " + "*" + " FROM " + AutoCompleteFields.TABLE_COLLEGES;
        Cursor cSor = db.rawQuery(query_params, null);
        return cSor.getCount();
    }

    public ArrayList<String> getCollegeNamesForAutoComplete(){
        ArrayList<String> _storeData = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query_params = "SELECT " + "* FROM " + AutoCompleteFields.TABLE_COLLEGES +";";
        Cursor cSor = db.rawQuery(query_params, null);
        if(cSor.moveToFirst()){
            do{
              _storeData.add(cSor.getString(cSor.getColumnIndex(AutoCompleteFields.COLLEGES_NAME)));

            }while(cSor.moveToNext());

        }else{
            return null;
        }
        return _storeData;
    }


    public ArrayList<String> getSkillNamesForAutoComplete(){
        ArrayList<String> skillNameArray = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query_params = "SELECT " + AutoCompleteFields.SKILLS_NAME + " FROM " + AutoCompleteFields.TABLE_SKILLS +";";
        Cursor cursor = db.rawQuery(query_params, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return skillNameArray;
        }

        cursor.moveToFirst();

        do{
            skillNameArray.add(cursor.getString(cursor.getColumnIndexOrThrow(AutoCompleteFields.SKILLS_NAME)));
        }while(cursor.moveToNext());

        return skillNameArray;
    }

    public ArrayList<String> getStreamNamesForAutoComplete(){
        ArrayList<String> streamNameArray = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query_params = "SELECT " + AutoCompleteFields.STREAMS_NAME + " FROM " + AutoCompleteFields.TABLE_STREAMS +";";
        Cursor cursor = db.rawQuery(query_params, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return streamNameArray;
        }

        cursor.moveToFirst();

        do{
            streamNameArray.add(cursor.getString(cursor.getColumnIndexOrThrow(AutoCompleteFields.STREAMS_NAME)));
        }while(cursor.moveToNext());

        return streamNameArray;
    }
    public ArrayList<String> getProfileNamesForAutoComplete(){
        ArrayList<String> profileNameArray = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query_params = "SELECT " + AutoCompleteFields.PROFILES_NAME + " FROM " + AutoCompleteFields.TABLE_PROFILES +";";
        Cursor cursor = db.rawQuery(query_params, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return profileNameArray;
        }

        cursor.moveToFirst();

        do{
            profileNameArray.add(cursor.getString(cursor.getColumnIndexOrThrow(AutoCompleteFields.PROFILES_NAME)));
        }while(cursor.moveToNext());

        return profileNameArray;
    }
    public HashMap<String, String> getDegreesNamesForAutoComplete(){
        HashMap<String, String> degreeAutoCompleteMap = new HashMap<String, String>();
        String id = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String query_params = "SELECT " + AutoCompleteFields.DEGREES_IDSERVERDB +", " + AutoCompleteFields.DEGREES_NAME + " FROM " + AutoCompleteFields.TABLE_DEGREES +";";
        Cursor cursor = db.rawQuery(query_params, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return degreeAutoCompleteMap;
        }
        cursor.moveToFirst();
        do{
            id = cursor.getString(cursor.getColumnIndexOrThrow(AutoCompleteFields.DEGREES_ID));
            degreeAutoCompleteMap.put(id, cursor.getString(cursor.getColumnIndexOrThrow(AutoCompleteFields.DEGREES_NAME)));
            id = null;
        }while(cursor.moveToNext());

        return degreeAutoCompleteMap;
    }
}
