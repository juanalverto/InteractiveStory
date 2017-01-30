package com.interactivestory.wilder.interactivestory.UI;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.interactivestory.wilder.interactivestory.R;


public class MainActivity extends ActionBarActivity {

    private EditText mNameField;
    private Button mStartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNameField = (EditText)findViewById(R.id.nameEditText);
        mStartButton = (Button) findViewById(R.id.startbutton);
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mNameField.getText().toString();
                StartStory(name);
            }
        });
    }

    //ME lleva a la ventana nueva q creamos en new activity que se llama StoryActivity
private void StartStory(String name) {

    Intent intent = new Intent(this, StoryActivity.class);
    intent.putExtra(getString(R.string.key_name), name);
    startActivity(intent);


    }

    @Override
    protected void onResume() {
        super.onResume();
        mNameField.setText("");
    }
}
