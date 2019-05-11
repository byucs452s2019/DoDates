package ntheurer.dodatesapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ntheurer.dodatesapp.R;

public class SpinnerAdapter extends BaseAdapter {
    private List<String> listData;
    private Activity activity;
    private LayoutInflater inflater;

    public SpinnerAdapter(List<String> listData, Activity activity) {
        this.listData = listData;
        this.activity = activity;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            view = inflater.inflate(R.layout.spinner_item, null);
        }
        final TextView textView = (TextView) view.findViewById(R.id.spinner_item_text);
        final String displayText = listData.get(position);
        textView.setText(displayText);
        return view;
    }
}
