package com.khanboy.balloon;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class LoadHeader extends AppCompatActivity {

    // TODO: Declare Member variables here

    private TextView title;
    private TextView header;
    private String mURL;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header);


        title = (TextView) findViewById(R.id.title);
        header = (TextView) findViewById(R.id.header);
        mButton = (Button) findViewById(R.id.button);

        mURL = getIntent().getStringExtra("websiteURL");

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new GetHeader().execute();

            }
        });


    }

    public class GetHeader extends AsyncTask<Void,Void,Void>{

        // TODO: Declare member variables here

        String mTitle;
        String mHeader;


        @Override
        protected Void doInBackground(Void... voids) {


            try {
                Document doc = Jsoup.connect(mURL).get();

                Element header = doc.select("header").first();

                 mTitle = doc.title();

                mHeader = header.text();

            } catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            title.setText(mTitle);
            Log.d("ABCD", "onPostExecute: title " + mTitle);

            header.setText(mHeader);
            Log.d("ABCD", "onPostExecute: header " + mHeader);
        }

    }

    @Override
    public void onBackPressed() {


        Intent backIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(backIntent);
        finish();
        return;


    }

}
