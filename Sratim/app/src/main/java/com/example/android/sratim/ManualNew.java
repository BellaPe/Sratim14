package com.example.android.sratim;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ManualNew extends AppCompatActivity {
    private Button OK;
    private Button Cancel;
    private EditText subject;
    private EditText body;
    private EditText url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_new);

        subject = (EditText) findViewById(R.id.subject);
        body = (EditText) findViewById(R.id.body);
        url = (EditText) findViewById(R.id.url);
        OK = (Button) findViewById((R.id.Ok));
        OK.setText(R.string.OK);
        Cancel = (Button) findViewById((R.id.Cancel));
        Cancel.setText(R.string.Cancel);

        View.OnClickListener myOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Result(view);
            }
        };
        OK.setOnClickListener(myOnClickListener);
        Cancel.setOnClickListener(myOnClickListener);

        Intent intent = getIntent();
        subject.setText(intent.getStringExtra("Title"));
        body.setText(intent.getStringExtra("Body"));
        url.setText(intent.getStringExtra("Url"));
    }

    public void Result(View view) {
        String Click = (String) ((Button) view).getText();
        Movie addedMovie = new Movie(subject.getText().toString(), body.getText().toString(), url.getText().toString());
        switch (Click) {
            case "OK": {
                Intent returnIntent = getIntent();
                returnIntent.putExtra("movie", addedMovie);
                setResult(RESULT_OK, returnIntent);
                finish();

            } break;
            case "Cancel": {
                Intent returnIntent = getIntent();
                setResult(RESULT_CANCELED, returnIntent);
                finish();

            } break;
        }

    }

}
