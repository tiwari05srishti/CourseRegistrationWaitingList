package com.example.courseregistrationwaitinglist;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Student_Registration_Waiting_List extends AppCompatActivity {
    // Declare variables;
    Button addStudentBtn, assignPriorityBtn, editStudentInfoBtn, deleteStudentBtn, viewWaitingList;
    Student_Waiting_List_DataBase DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Action bar custom text display;
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        // Action bar back icon functionality;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_student_registration_waiting_list);

        // Add a student button initialization and functionality;
        addStudentBtn = findViewById(R.id.addAStudentButton);
        addStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("AddStudentBtnClick", "Add Student Button Clicked");
                Intent addStudentBtnIntent = new Intent(Student_Registration_Waiting_List.this, Add_Student_Information.class);
                startActivity(addStudentBtnIntent);
            }
        });

        // Assign a priority button initialization and functionality;
        assignPriorityBtn = findViewById(R.id.setPriorityButton);
        assignPriorityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent assignPriorityBtnIntent = new Intent(Student_Registration_Waiting_List.this, Assign_Student_Priority.class);
                startActivity(assignPriorityBtnIntent);
            }
        });

        // Edit a student button initialization and functionality;
        editStudentInfoBtn = findViewById(R.id.editStudentInfoButton);
        editStudentInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editStudentInfoBtnIntent = new Intent(Student_Registration_Waiting_List.this, Edit_Student_Information.class);
                startActivity(editStudentInfoBtnIntent);
            }
        });

        // Delete a student button initialization and functionality;
        deleteStudentBtn = findViewById(R.id.deleteStudentButton);
        deleteStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent deleteStudentInfoBtnIntent = new Intent(Student_Registration_Waiting_List.this, Delete_Student_Information.class);
                startActivity(deleteStudentInfoBtnIntent);
            }
        });

        // View waiting list button initialization and functionality;
        viewWaitingList = findViewById(R.id.viewWaitingList);
        DB = new Student_Waiting_List_DataBase(this);
        viewWaitingList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Called get method to retrieve student information;
                Cursor res = DB.getStudentInfo();
                if(res.getCount()==0){
                    // Show toast message for the empty waiting list;
                    Toast.makeText(Student_Registration_Waiting_List.this, "No Student in Waiting List!", Toast.LENGTH_SHORT).show();
                }else{
                    // Show toast message for the current waiting list;
                    Toast.makeText(Student_Registration_Waiting_List.this, "Current Students in Waiting List", Toast.LENGTH_SHORT).show();

                    // Create a StringBuffer to store the retrieved data from the SQLite database
                    StringBuffer buffer = new StringBuffer();
                    // Loop through the query result using a cursor and append each row to the StringBuffer
                    while(res.moveToNext()){
                        buffer.append("Student ID: " + res.getString(0) + "\n");
                        buffer.append("Student Name: " + res.getString(1) + "\n");
                        buffer.append("Course Name: " + res.getString(2) + "\n");
                        buffer.append("Priority: " + res.getString(3) + "\n");
                        buffer.append("\n");
                    }

                    // Alert to show the current waiting list;
                    AlertDialog.Builder builder = new AlertDialog.Builder(Student_Registration_Waiting_List.this);
                    builder.setCancelable(true);
                    builder.setTitle("Course Registration Waiting List");
                    builder.setMessage(buffer.toString());
                    builder.show();
                }
            }
        });
    }
}