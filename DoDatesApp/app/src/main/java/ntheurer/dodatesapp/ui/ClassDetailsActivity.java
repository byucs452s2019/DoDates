package ntheurer.dodatesapp.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import model.Assignment;
import ntheurer.dodatesapp.R;
import ntheurer.dodatesapp.model.SingletonModel;

public class ClassDetailsActivity extends AppCompatActivity {

    private final String tag = "ClassDetailsActivity";
    private Context context;

    protected void onCreate(Bundle savedInstanceState) {
        Log.w(tag, "onCreate: started");
        RecyclerView recyclerView;
        RecyclerView.Adapter adapter;
        RecyclerView.LayoutManager layoutManager;
        SingletonModel sModel = SingletonModel.getInstance();
        String currClassID = sModel.getCurrClassID();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_details);

        this.setTitle(((sModel.getUserClassMap()).get(currClassID)).getClassName());

        context = this;
        recyclerView = (RecyclerView) findViewById(R.id.assignment_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerAdapter(((sModel.getUserClassMap()).get(currClassID)).getAssignments());
        recyclerView.setAdapter(adapter);

        Button viewClassListButton = (Button) findViewById(R.id.back_to_class_list_button);
        viewClassListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w(tag, "viewClassListButton clicked");
                Intent myIntent = new Intent(ClassDetailsActivity.this, ClassListActivity.class);
                ClassDetailsActivity.this.startActivity(myIntent);
            }
        });

        ImageView addButton = (ImageView) findViewById(R.id.add_assignment_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w(tag, "addButtion clicked");
                Intent myIntent = new Intent(ClassDetailsActivity.this, AddAssignmentActivity.class);
                ClassDetailsActivity.this.startActivity(myIntent);
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

    private class AssignmentListHolder extends RecyclerView.ViewHolder {
        private TextView assignmentNameTextView;
        private TextView assignmentDueDateTextView;

        public AssignmentListHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.assignment_recycler_item, parent, false));
            Log.w(tag, "AssignmentListHolder contrustor entered");
            assignmentNameTextView = (TextView) itemView.findViewById(R.id.recycler_assignment_name);
            assignmentDueDateTextView = (TextView) itemView.findViewById(R.id.recycler_assignment_due_date);
        }

        public void bind(Assignment currAssignment) {
            SingletonModel sModel = SingletonModel.getInstance();
            String dueDate = "Due: ";

            if (assignmentNameTextView == null) {
                Log.e(tag, "assignmentNameTextView == null");
            }

            if (assignmentDueDateTextView == null) {
                Log.e(tag, "assignmentDueDateTextView == null");
            }

            try {
                assignmentNameTextView.setText(currAssignment.getAssignmentName());
                dueDate = dueDate + currAssignment.getDueDate();
                Log.w(tag, "dueDate = \"" + dueDate + "\"");
                assignmentDueDateTextView.setText(dueDate);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class RecyclerAdapter extends RecyclerView.Adapter<AssignmentListHolder> {
        private List<Assignment> assignmentList;

        public RecyclerAdapter(List<Assignment> assignments) {
            assignmentList = assignments;
        }

        @Override
        public AssignmentListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            return new AssignmentListHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(AssignmentListHolder holder, int position) {
            Assignment assignment = assignmentList.get(position);
            Log.i(tag, "assignment position = " + position);
            Log.i(tag, "assignment name = " + assignment.getAssignmentName());
            holder.bind(assignment);
        }

        @Override
        public int getItemCount() {
            if (assignmentList == null) {
                return 0;
            }
            return assignmentList.size();
        }
    }
}
