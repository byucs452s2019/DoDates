package ntheurer.dodatesapp.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ntheurer.dodatesapp.R;
import ntheurer.dodatesapp.model.SingletonModel;
import ntheurer.dodatesapp.model.UserClass;

public class ClassListActivity extends AppCompatActivity {

    private final String tag = "ClassListActivity";
    private Context context;

    protected void onCreate(Bundle savedInstanceState) {
        Log.w(tag, "onCreate: started");
        RecyclerView recyclerView;
        RecyclerView.Adapter adapter;
        RecyclerView.LayoutManager layoutManager;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_list);
        SingletonModel sModel = SingletonModel.getInstance();

        context = this;
        recyclerView = (RecyclerView) findViewById(R.id.class_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerAdapter(sModel.getUserClassList());
        recyclerView.setAdapter(adapter);

        Button viewCalendarButton = (Button) findViewById(R.id.viewCalendarButton);
        viewCalendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w(tag, "viewCalendarButton clicked");
                Intent myIntent = new Intent(ClassListActivity.this, CalendarActivity.class);
                ClassListActivity.this.startActivity(myIntent);
            }
        });

        ImageView addButton = (ImageView) findViewById(R.id.add_class_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w(tag, "addButton clicked");
                Intent myIntent = new Intent(ClassListActivity.this, AddClassActivity.class);
                ClassListActivity.this.startActivity(myIntent);
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

    private class ClassListHolder extends RecyclerView.ViewHolder {
        private TextView classNameTextView;

        public ClassListHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.class_recycler_item, parent, false));
            Log.w(tag, "ClassListHolder constructor entered");
            classNameTextView = (TextView) itemView.findViewById(R.id.recycler_class_name);
        }

        public void bind(final UserClass userClass) {
            Log.w(tag, "ClassListHolder bind entered");
            final SingletonModel sModel = SingletonModel.getInstance();
            final List<String> colorList = sModel.getColorList();

            classNameTextView.setText(userClass.getClassName());
            classNameTextView.setTextColor(Color.parseColor((sModel.getColorMap()).get(userClass.getColorString())));
            classNameTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sModel.setCurrClassID(userClass.getUniqueID());
                    Intent myIntent = new Intent(ClassListActivity.this, ClassDetailsActivity.class);
                    ClassListActivity.this.startActivity(myIntent);
                }
            });
        }
    }

    private class RecyclerAdapter extends RecyclerView.Adapter<ClassListHolder> {
        private List<UserClass> userClassList;

        public RecyclerAdapter(List<UserClass> userClasses) {
            Log.w(tag, "RecyclerAdapter constructor entered");
            userClassList = userClasses;
        }

        @Override
        public ClassListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Log.w(tag, "RecyclerAdapter onCreateViewHolder entered");
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            return new ClassListHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(ClassListHolder holder, int position) {
            Log.w(tag, "RecyclerAdapter onBindViewHolder entered");
            UserClass userClass = userClassList.get(position);
            holder.bind(userClass);
        }

        @Override
        public int getItemCount() {
            Log.w(tag, "RecyclerAdapter getItemCount entered");
            if (userClassList == null) {
                return 0;
            }
            return userClassList.size();
        }
    }
}
