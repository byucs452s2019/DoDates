package ntheurer.dodatesapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ntheurer.dodatesapp.R;
import ntheurer.dodatesapp.model.SingletonModel;
import ntheurer.dodatesapp.adapters.SpinnerAdapter;
import ntheurer.dodatesapp.model.UserClass;

public class AddClassActivity extends AppCompatActivity {

    private final String tag = "AddClassActivity";
    private String colorSelection;
    private String className;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.w(tag, "onCreate: started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);
        final SingletonModel sModel = SingletonModel.getInstance();

        final EditText classNameEditText = (EditText) findViewById(R.id.name_edit);


        final TextView colorTextView = (TextView) findViewById(R.id.color_category);

        final List<String> colorList = sModel.getColorList();
        final Spinner colorSpinner;

        android.widget.SpinnerAdapter spinnerAdapter = new SpinnerAdapter(colorList, this);
        colorSpinner = (Spinner) findViewById(R.id.color_dropdown);
        colorSpinner.setAdapter(spinnerAdapter);
        colorSpinner.setSelection(colorList.indexOf("red"));
        colorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.w(tag, "item selected");
                colorSelection = colorList.get((int) parent.getItemAtPosition(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        Button confirmButton = (Button) findViewById(R.id.createClassButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w(tag, "confirmButton clicked");
                className = classNameEditText.getText().toString();
                sModel.addClass(new UserClass(className, colorSelection));
                Intent myIntent = new Intent(AddClassActivity.this, ClassListActivity.class);
                AddClassActivity.this.startActivity(myIntent);
            }
        });

        Button cancelCreateClassButton = (Button) findViewById((R.id.cancelButton));
        cancelCreateClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w(tag, "cancelCreateButtion clocked");
                Intent myIntent = new Intent(AddClassActivity.this, ClassListActivity.class);
                AddClassActivity.this.startActivity(myIntent);
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
