package org.tisserand.robin.testfirebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class TaskChooser extends AppCompatActivity {

    private String name = "";
    private int value = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_chooser);

        Intent intent = getIntent();
        if (null != intent) {
            name = intent.getStringExtra(MainActivity.TASK_CHOOSER_NAME);
        }
        Log.i("TaskChooser", "name is " + name);
    }

    private void sendResult() {
        Intent result = new Intent();
        result.putExtra(MainActivity.TASK_CHOOSER_NAME, name);
        result.putExtra(MainActivity.TASK_CHOOSER_VALUE, value);
        Log.i("TaskChooser", name + " : " + value);
        setResult(RESULT_OK, result);
        super.finish();
    }

    public void onVote(View view) {
        switch (view.getId()) {
            case R.id.button_minus_5:
                value = -5;
                break;
            case R.id.button_plus_5:
                value = 5;
                break;
            default:
                break;
        }
        sendResult();
    }
}
