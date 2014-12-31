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
        List<String> items = new ArrayList<String>();
        for (Class<?> demo : Activities.classes) {
            items.add(demo.getSimpleName().replaceFirst("Activity", ""));
        }
        return new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                items);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(this, Activities.classes[position]);
        startActivity(intent);
    }
}
