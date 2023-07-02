// Package declaration
package com.example.courseregistrationwaitinglist;
// Import statements

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// DataBase class
public class Student_Waiting_List_DataBase extends SQLiteOpenHelper {

    // DataBase constructor;
    public Student_Waiting_List_DataBase(Context context) {
        // Create a database with version 1;
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a database table with four fields;
        db.execSQL("create Table StudentDetails(id TEXT primary key, name TEXT, course TEXT, priority TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop table;
        db.execSQL("drop Table if exists StudentDetails");
    }

    // Add a student information into database (insertion)
    public Boolean addNewStudent(String studentID, String studentName, String course){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", studentID);
        contentValues.put("name", studentName);
        contentValues.put("course", course);
        contentValues.put("priority", "None");
        long result = db.insert("StudentDetails", null, contentValues);
        return result != -1;
    }

    // Assign Priority for a student into database;
    public Boolean assignPriority(String studentID, String priority){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("priority", priority);
        Cursor cursor = db.rawQuery("Select * from StudentDetails where id = ?", new String[] {studentID});
        if(cursor.getCount()>0) {
            long result = db.update("StudentDetails", contentValues, "id=?", new String[]{studentID});
            return result != -1;
        }else{
            return false;
        }
    }

    // Edit student information into database (Update)
    public Boolean updateStudentInfo(String studentID, String studentName, String course, String priority){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", studentID);

        if (!studentName.equals("")) {
            contentValues.put("name", studentName);}

        if (!course.equals("")) {
            contentValues.put("course", course);}

        if (!priority.equals("")) {
            contentValues.put("priority", priority);
        }
        Cursor cursor = db.rawQuery("Select * from StudentDetails where id = ?", new String[] {studentID});
        if(cursor.getCount()>0) {
            long result = db.update("StudentDetails", contentValues, "id=?", new String[]{studentID});
            return result != -1;
        }else{
            return false;
        }

    }

    // Delete an existing student record method (Deletion)
    public Boolean deleteStudentInfo(String studentID){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from StudentDetails where id = ?", new String[] {studentID});
        if(cursor.getCount()>0) {
            long result = db.delete("StudentDetails","id=?", new String[]{studentID});
            return result != -1;
        }else{
            return false;
        }
    }

    // Retrieve student data (Get data)
    public Cursor getStudentInfo(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from StudentDetails", null);
        return cursor;
    }
}