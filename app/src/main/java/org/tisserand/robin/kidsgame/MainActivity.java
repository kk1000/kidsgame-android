package org.tisserand.robin.kidsgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;

import com.firebase.client.Firebase;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    public final static String TASK_CHOOSER_NAME = "org.tisserand.robin.kidsgame.TASK_CHOOSER_NAME";
    public final static String TASK_CHOOSER_VALUE = "org.tisserand.robin.kidsgame.TASK_CHOOSER_VALUE";

    private static final String TAG = "RMain";
    private static final int TASK_CHOOSER_REQUEST = 1;

    private Firebase rootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        rootRef = new Firebase("https://torid-torch-6190.firebaseio.com/");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (TASK_CHOOSER_REQUEST == requestCode) {
            String msg;
            if (RESULT_OK == resultCode) {
                Log.i("RobinMain", "result is OK");
                String name = data.getStringExtra(TASK_CHOOSER_NAME);
                int value = data.getIntExtra(TASK_CHOOSER_VALUE, 0);
                msg = name + " : " + value;
                Log.d("RobinMain", msg);

                Firebase postRef = rootRef.child(name);
                Map<String, Object> postData = new HashMap<String, Object>();
                postData.put("date", "" + new Date());
                postData.put("value", value);
                postRef.push().setValue(postData);

                // user feedback
                Snackbar.make( findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
            if (RESULT_CANCELED == resultCode) {
                Log.i("RobinMain", "result is CANCELED");
            }
        }
    }

    public void onSelect(View view) {
        String message = "Selected: ";
        String name = "";
        switch (view.getId()) {
            case R.id.button_armand:
                message += "Armand";
                name = "armand";
                break;
            case R.id.button_hisae:
                message += "Hisae";
                name = "hisae";
                break;
            default:
                // should never happened
                break;
        }

        //Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        //        .setAction("Action", null).show();

        // Create new activity
        Intent intent = new Intent(this, TaskChooser.class);
        intent.putExtra(TASK_CHOOSER_NAME, name);
        startActivityForResult(intent, TASK_CHOOSER_REQUEST);
    }
}
