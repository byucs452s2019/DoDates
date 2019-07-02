package ntheurer.dodatesapp.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import model.UserClass;
import ntheurer.dodatesapp.R;
import ntheurer.dodatesapp.model.SingletonModel;
import ntheurer.dodatesapp.adapters.SpinnerAdapter;

public class AddClassActivity extends AppCompatActivity {

    private final String tag = "AddClassActivity";
    private String colorSelection;
    private String className;
    private boolean confirmDelete;
    private Context context;

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

        Button deleteButton = (Button) findViewById(R.id.delete_class_button);
        if (sModel.isEditingClass()) {
            UserClass currClass = (sModel.getUserClassMap()).get(sModel.getCurrClassID());
            if (currClass != null) {
                classNameEditText.setText(currClass.getClassName());
                colorSpinner.setSelection(colorList.indexOf(currClass.getColorString()));
                colorSelection = currClass.getColorString();
                deleteButton.setVisibility(View.VISIBLE);
            }
        }

        context = this;
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w(tag, "deleteButton clicked");
                className = classNameEditText.getText().toString();
                //FIXME: add confirm delete

                confirmDelete = false;
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(true);
                builder.setTitle("Are you sure you want to delete this class?");
                builder.setMessage("All assignments and class information will also be deleted.");
                builder.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                confirmDelete = true;
                                if (sModel.isEditingClass() && sModel.getCurrClassID() != null) {
                                    if (!sModel.deleteClass(sModel.getCurrClassID())) {
                                        //FIXME: error message
                                    } else {
                                        sModel.setEditingClass(false);
                                        Intent myIntent = new Intent(AddClassActivity.this, ClassListActivity.class);
                                        AddClassActivity.this.startActivity(myIntent);
                                    }
                                }
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        confirmDelete = false;
                        dialog.cancel();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

//                if (sModel.isEditingClass() && sModel.getCurrClassID() != null) {
//                    if (!sModel.deleteClass(sModel.getCurrClassID())) {
//                        //FIXME: error message
//                    }
//                    sModel.setEditingClass(false);
//                    Intent myIntent = new Intent(AddClassActivity.this, ClassListActivity.class);
//                    AddClassActivity.this.startActivity(myIntent);
//                }
            }
        });

        Button confirmButton = (Button) findViewById(R.id.createClassButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w(tag, "confirmButton clicked");
                className = classNameEditText.getText().toString();
                if (sModel.isEditingClass()) {
                    sModel.updateClass(className, colorSelection);
                    sModel.setEditingClass(false);
                } else {
                    sModel.addClass(new UserClass(className, colorSelection));
                }
                Intent myIntent = new Intent(AddClassActivity.this, ClassListActivity.class);
                AddClassActivity.this.startActivity(myIntent);
            }
        });

        Button cancelCreateClassButton = (Button) findViewById((R.id.cancelButton));
        cancelCreateClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w(tag, "cancelCreateButtion clocked");
                sModel.setEditingClass(false);
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
