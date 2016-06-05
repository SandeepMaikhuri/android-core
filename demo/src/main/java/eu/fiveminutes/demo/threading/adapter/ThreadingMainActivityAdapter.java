package eu.fiveminutes.demo.threading.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

public final class ThreadingMainActivityAdapter extends ArrayAdapter<String> {

    @Inject
    public ThreadingMainActivityAdapter(final Context context, final int resource, final List<String> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        textView.setTextColor(Color.BLACK);
        return view;
    }
}
