package ru.sashok.study.activitylifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int MY_REQUEST_CODE = 1;

    public static final String TEXT_KEY = "Text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Button implicitButton = (Button) findViewById(R.id.button2);
        implicitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String textToShare = "";
                EditText editText = findViewById(R.id.textInput);
                if (editText != null && editText.getText() != null) {
                    textToShare = editText.getText().toString();
                }

                Intent implicitIntent = new Intent();
                implicitIntent.setAction(Intent.ACTION_SEND);
                implicitIntent.putExtra(Intent.EXTRA_TEXT, textToShare);
                implicitIntent.setType("text/plain");

                if (implicitIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(implicitIntent);
                }

            }
        });

        Button explicitButton = (Button) findViewById(R.id.button1);
        explicitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String textToShare = "";
                EditText editText = findViewById(R.id.textInput);
                if (editText != null && editText.getText() != null) {
                    textToShare = editText.getText().toString();
                }

                Intent explicitIntent = new Intent(MainActivity.this, ExplicitActivity.class);
                explicitIntent.putExtra(TEXT_KEY, textToShare);

                startActivityForResult(explicitIntent, MY_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == MY_REQUEST_CODE) {
            if (resultCode == ExplicitActivity.OK) {
                Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
            } else if (resultCode == ExplicitActivity.CANCEL) {
                Toast.makeText(MainActivity.this, "CANCEL", Toast.LENGTH_SHORT).show();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
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
}
