package ntheurer.dodatesapp.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

import model.Assignment;
import ntheurer.dodatesapp.R;
import ntheurer.dodatesapp.model.SingletonModel;

public class AddAssignmentActivity extends AppCompatActivity {

    private final String tag = "AddAssignmentActivity";
    private String assignmentName;
    private String dueDate;
    private String doDate;
    private TextView displayDueDateTextView;
    private TextView displayDoDateTextView;
    private DatePickerDialog.OnDateSetListener dueDateSetListener;
    private DatePickerDialog.OnDateSetListener doDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.w(tag, "onCreate: started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignment);
        final SingletonModel sModel = SingletonModel.getInstance();

        final EditText assignmentNameEditText = (EditText) findViewById(R.id.assignment_name_edit);
        displayDueDateTextView = (TextView) findViewById(R.id.text_view_due_date);
        displayDoDateTextView = (TextView) findViewById(R.id.text_view_do_date);

        dueDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Log.w(tag, "dueDateSetListener");
                month = month + 1;
                dueDate = month + "/" + dayOfMonth + "/" + year;
                displayDueDateTextView.setText(dueDate);
            }
        };

        displayDueDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w(tag, "displayDueDateTextView clicked");
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddAssignmentActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dueDateSetListener,
                        year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        doDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Log.w(tag, "doDateSetListener");
                month = month + 1;
                doDate = month + "/" + dayOfMonth + "/" + year;
                displayDoDateTextView.setText(doDate);
            }
        };

        displayDoDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w(tag, "displayDoDateTextView clicked");
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddAssignmentActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        doDateSetListener,
                        year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        Button confirmButton = (Button) findViewById(R.id.create_assignment_button);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w(tag, "confirmButton clicked");
                String currClassID = sModel.getCurrClassID();
                assignmentName = assignmentNameEditText.getText().toString();
                Assignment assignment = new Assignment(assignmentName, dueDate, doDate, (sModel.getUserClassMap()).get(currClassID));
                ((sModel.getUserClassMap()).get(currClassID)).addSingleAssignment(assignment);
                Intent myIntent = new Intent(AddAssignmentActivity.this, ClassDetailsActivity.class);
                AddAssignmentActivity.this.startActivity(myIntent);
            }
        });

        Button cancelCreateButton = (Button) findViewById((R.id.cancel_assignment_button));
        cancelCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w(tag, "cancelCreateButtion clicked");
                Intent myIntent = new Intent(AddAssignmentActivity.this, ClassDetailsActivity.class);
                AddAssignmentActivity.this.startActivity(myIntent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
