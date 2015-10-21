package org.tisserand.robin.kidsgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AListView extends AppCompatActivity {

    static final String[] nameArray = new String[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        nameArray[0] = "Armand";
        nameArray[1] = "Hisae";
        nameArray[2] = "Lucie";

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alist_view);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, nameArray);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        AdapterView.OnItemClickListener onClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("AListView", nameArray[position]);
            }
        };

        listView.setOnItemClickListener(onClickListener);
    }
}
