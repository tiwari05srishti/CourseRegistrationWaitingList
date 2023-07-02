//Package declaration
package com.example.courseregistrationwaitinglist;

//Imported classes
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

// Class declaration
public class Delete_Student_Information extends AppCompatActivity {

    // Declare variables;
    EditText id;
    TextView errorText;
    Button deleteStudentBtn, backBtn;
    Student_Waiting_List_DataBase DB;

    // onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Action bar custom text display;
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        // Action bar back icon functionality;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_delete_student_informatiom);

        // Variables initialized;
        id = findViewById(R.id.deleteStudentId);
        DB = new Student_Waiting_List_DataBase(this);

        // Initialized error message textview;
        errorText = findViewById(R.id.addStudentValidationMsg);

        // Delete button initialization and functionality;
        deleteStudentBtn = findViewById(R.id.deleteStudentBtn);
        deleteStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentID = id.getText().toString();

                // Empty fields validation;
                if(studentID.equals(""))
                {
                    errorText.setText("(Enter an existing Student ID!)");

                }else{
                    errorText.setText("");
                    // Called delete student method to delete an existing information;
                    Boolean checkdeletedata = DB.deleteStudentInfo(studentID);
                    if(checkdeletedata==true){
                        // Show toast message for a successful deletion;
                        Toast.makeText(Delete_Student_Information.this, "Student Deleted From The List!", Toast.LENGTH_SHORT).show();
                        id.setText(""); // Clearing the student ID field after successful deletion
                    }else{
                        // Show toast message for an un-successful deletion;
                        Toast.makeText(Delete_Student_Information.this, "No Student Found With ID!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Back button initialization and functionality;
        backBtn =  findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backButtonIntent =  new Intent(Delete_Student_Information.this, Student_Registration_Waiting_List.class);
                startActivity(backButtonIntent);
                id.setText(""); // Clearing the student ID field after going back
            }
        });
    }
}