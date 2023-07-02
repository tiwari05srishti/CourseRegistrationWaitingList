// This code is a Java class for adding a new student to a course registration waiting list app
// Importing necessary libraries and packages
package com.example.courseregistrationwaitinglist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

// The main class AddStudent extends AppCompatActivity
public class Add_Student_Information extends AppCompatActivity {

    // Declare variables;
    EditText id, name, course;
    TextView errorText;
    Button addStudentBtn, backBtn;
    Student_Waiting_List_DataBase DB;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set up the custom action bar with a title and back button
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Set the layout of the activity to be shown
        setContentView(R.layout.activity_add_student_information);

        // Initialize variables by finding their corresponding UI elements by their ids
        id = findViewById(R.id.studentID);
        name = findViewById(R.id.studentName);
        course = findViewById(R.id.courseName);
        DB = new Student_Waiting_List_DataBase(this);

        // Initialize error text view
        errorText = findViewById(R.id.addStudentValidationMsg);

        // Initialize add student button and set up its onClickListener
        addStudentBtn = findViewById(R.id.addStudentBtn);
        addStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get student information entered by the user
                String studentID = id.getText().toString();
                String studentName = name.getText().toString();
                String assignCourse = course.getText().toString();

                if(studentID.equals("") && studentName.equals("") && assignCourse.equals("")) {
                    errorText.setText("(Enter values in all fields!!!)");
                }
                else if (!TextUtils.isDigitsOnly(studentID)) {
                    errorText.setText("(Student ID must be a numeric value!!!)");
                }
                else if (studentName.length() < 3) {
                    errorText.setText("(Name must be at least 3 characters long!!!)");
                }
                else if (assignCourse.length() < 3) {
                    errorText.setText("(Enter a proper course title or code!!!)");
                }else{
                    errorText.setText("");
                    // Call the add student method to insert the student's information into the database
                    Boolean checkinsertdata = DB.addNewStudent(studentID, studentName, assignCourse);
                    if(checkinsertdata==true){
                        // Show a success message as a toast
                        Toast.makeText(Add_Student_Information.this, "Student Added Successfully!", Toast.LENGTH_SHORT).show();
                        // Clear the text fields
                        id.setText("");
                        name.setText("");
                        course.setText("");
                    }else{
                        // Show an error message as a toast
                        Toast.makeText(Add_Student_Information.this, "Unable to add student!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Initialize back button and set up its onClickListener
        backBtn =  findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to navigate back to the previous activity
                Intent backButtonIntent =  new Intent(Add_Student_Information.this, Student_Registration_Waiting_List.class);
                startActivity(backButtonIntent);
            }
        });
    }
}