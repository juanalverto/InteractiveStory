package com.interactivestory.wilder.interactivestory.UI;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.interactivestory.wilder.interactivestory.MODEL.Page;
import com.interactivestory.wilder.interactivestory.MODEL.Story;
import com.interactivestory.wilder.interactivestory.R;


public class StoryActivity extends ActionBarActivity {


    public static final String TAG = StoryActivity.class.getSimpleName();

    private Story mStory = new Story();
    private ImageView mImageView;
    private TextView mTextView;
    private Button mChoice1;
    private Button mChoice2;
    private String mName;
    private Page mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        Intent intent = getIntent();
        mName = intent.getStringExtra(getString(R.string.key_name));

        if (mName == null) {

            mName = "Friend";



        }
        Log.d(TAG, mName);

            mImageView = (ImageView)findViewById(R.id.StoryImageView);
            mTextView = (TextView)findViewById(R.id.StoryTextView);
            mChoice1 = (Button)findViewById(R.id.choiceButton1);
            mChoice2 = (Button)findViewById(R.id.choiceButton2);

            loadPage(0);

    }
// Aqui se cargan los contenidos de la pag.


    private void loadPage(int choice) {

         mCurrentPage = mStory.getPage(choice);


        Drawable drawable = getResources().getDrawable(mCurrentPage.getImageId());
            mImageView.setImageDrawable(drawable);

            String pageText = mCurrentPage.getText();
//Add the name if placeholder included .Wont add if no placeholder.
            pageText = String.format(pageText, mName);

            mTextView.setText(pageText);
//Este if de pedazo de codigo se encarga de al llegar al final de la historia si es la ultima screen, esconde el boton de arriba y te regresa al inicio de la historia.
        if (mCurrentPage.isFinal()) {

            mChoice1.setVisibility(View.INVISIBLE);
            mChoice2.setText("PLAY AGAIN");
            mChoice2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    finish();

                }
            });


        }
        else {

            mChoice1.setText(mCurrentPage.getChoice1().getText());
            mChoice2.setText(mCurrentPage.getChoice2().getText());

            //Se agrega oncklick boton para las dos opciones de los botones al apretar.

            mChoice1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int nextPage = mCurrentPage.getChoice1().getNextPage();
                    loadPage(nextPage);
                }
            });

            mChoice2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int nextPage = mCurrentPage.getChoice2().getNextPage();
                    loadPage(nextPage);
                }
            });
        }

    }




}
