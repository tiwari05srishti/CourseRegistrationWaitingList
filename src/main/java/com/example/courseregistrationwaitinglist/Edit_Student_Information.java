package com.example.courseregistrationwaitinglist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Edit_Student_Information extends AppCompatActivity {

    // Declare variables;
    EditText id, name, course, priority;
    TextView errorText;
    Button editStudentInfoBtn, backBtn;
    Student_Waiting_List_DataBase DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Action bar custom text display;
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        // Action bar back icon functionality;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_edit_student_information);

        // Initialized variables;
        id = findViewById(R.id.studentID);
        name = findViewById(R.id.studentName);
        course = findViewById(R.id.courseName);
        priority = findViewById(R.id.studentPriority);
        DB = new Student_Waiting_List_DataBase(this);

        // Error textview initialization;
        errorText = findViewById(R.id.addStudentValidationMsg);

        // Edit student button initialization and functionality;
        editStudentInfoBtn = findViewById(R.id.editStudentBtn);
        editStudentInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve entered student data;
                String studentID = id.getText().toString();
                String studentName = name.getText().toString();
                String assignCourse = course.getText().toString();
                String assignPriority = priority.getText().toString();

                // Empty fields validation;
                if(studentID.equals(""))
                {
                    errorText.setText("(Enter an existing Student ID!)");

                }else{
                    errorText.setText("");
                    // Called update method for updating student information;
                    Boolean checkupdatedata = DB.updateStudentInfo(studentID, studentName, assignCourse, assignPriority);
                    if(checkupdatedata==true){
                        // Show toast message for a successful student information update;
                        Toast.makeText(Edit_Student_Information.this, "Student info updated!", Toast.LENGTH_SHORT).show();
                    }else{
                        // Show toast message for an un-successful student information update;
                        Toast.makeText(Edit_Student_Information.this, "Update info failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Back button initialization and functionality;
        backBtn =  findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to naviate to another activity;
                Intent backButtonIntent =  new Intent(Edit_Student_Information.this, Student_Registration_Waiting_List.class);
                startActivity(backButtonIntent);
            }
        });
    }
}