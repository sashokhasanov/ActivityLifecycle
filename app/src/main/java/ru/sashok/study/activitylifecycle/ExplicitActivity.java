package ru.sashok.study.activitylifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ExplicitActivity extends AppCompatActivity {

    public static final int OK = 0xff;
    public static final int CANCEL = 0xfff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicit);

        Button buttonOk = (Button) findViewById(R.id.buttonOk);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                setResult(OK);
                finish();
            }
        });

        Button buttonCancel = (Button) findViewById(R.id.buttonCancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                setResult(CANCEL);
                finish();
            }
        });

        FloatingActionButton faButton = (FloatingActionButton) findViewById(R.id.fab);
        faButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent explicitIntent = new Intent(ExplicitActivity.this, MainActivity.class);
                explicitIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(explicitIntent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        String text = "";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            text = extras.getString(MainActivity.TEXT_KEY);
        }

        TextView textView = findViewById(R.id.textView);
        textView.setText(text);
    }
}
