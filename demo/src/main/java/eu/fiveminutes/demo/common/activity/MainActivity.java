package eu.fiveminutes.demo.common.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(buildListAdapter());
    }

    private ListAdapter buildListAdapter() {
        final List<String> items = new ArrayList<>(Activities.CLASSES.length);

        for (final Class<?> demo : Activities.CLASSES) {
            items.add(demo.getSimpleName().replaceFirst("Activity", ""));
        }

        return new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        startActivity(new Intent(this, Activities.CLASSES[position]));
    }
}
