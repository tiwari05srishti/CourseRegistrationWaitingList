package com.example.courseregistrationwaitinglist;
// Import necessary libraries
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Assign_Student_Priority extends AppCompatActivity {

    // Declare variables
    EditText id, priority; // Input fields to get student ID and priority
    TextView errorText; // TextView to display any errors
    Button assignPriorityBtn, backBtn; // Buttons to assign priority and navigate back
    Student_Waiting_List_DataBase DB; // Database Helper class instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Action bar custom text display
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        // Action bar back icon functionality
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_assign_student_priority);

        // Initialized variables
        id = findViewById(R.id.assignPriorityStudentId); // Get input field for student ID
        priority = findViewById(R.id.assignPriority); // Get input field for priority
        DB = new Student_Waiting_List_DataBase(this); // Create a new DBHelper object

        // Dropdown menu setup and its options
        Spinner dropdown=findViewById(R.id.spinner_languages); // Get the spinner element for priority selection
        // Create an ArrayAdapter using the priorityList array
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.priorityList, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item); // Specify the layout to use when the list of choices appears
        dropdown.setAdapter(adapter); // Apply the adapter to the spinner

        // Initialized error text field element
        errorText = findViewById(R.id.addStudentValidationMsg); // Get the element for displaying error messages

        // Assign a priority button initialization and functionality
        assignPriorityBtn = findViewById(R.id.assignPriorityBtn); // Get the assign priority button element
        assignPriorityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve entered student information
                String studentID = id.getText().toString(); // Get the entered student ID
                String assignPriority = dropdown.getSelectedItem().toString(); // Get the selected priority from the dropdown menu

                // Empty fields validation
                if(studentID.equals("") ||
                        assignPriority.equals(""))
                {
                    errorText.setText("(Enter an existing Student ID!)");

                }else{
                    errorText.setText("");
                    // Called assignPriority method to set a priority
                    Boolean checkupdatedata = DB.assignPriority(studentID,  assignPriority);
                    if(checkupdatedata==true){
                        // Show toast message for a successfully assigned priority
                        Toast.makeText(Assign_Student_Priority.this, "Priority Assigned Successfully", Toast.LENGTH_SHORT).show();
                    }else{
                        // Show toast message for an un-successfully assigned priority
                        Toast.makeText(Assign_Student_Priority.this, "Unable to set priority!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Back button initialization and functionality
        backBtn =  findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to navigate to another activity
                Intent backButtonIntent =  new Intent(Assign_Student_Priority.this, Student_Registration_Waiting_List.class);
                startActivity(backButtonIntent);
            }
        });
    }
}