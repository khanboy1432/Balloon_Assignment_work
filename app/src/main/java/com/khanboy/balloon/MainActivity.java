package com.khanboy.balloon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    // TODO: Declare Member variables here

    private String url = "http://";
    private EditText urlText;
    private Button searchButton;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // TODO: Link layout elements to source code here

        urlText = (EditText) findViewById(R.id.url_Text);
        searchButton = (Button) findViewById(R.id.search_button);
        spinner = (ProgressBar) findViewById(R.id.progressbar);


       // TODO: Set Listeners here

       urlText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
           @Override
           public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

               if(actionId == 100 || actionId == EditorInfo.IME_NULL) {
                   attemptSearch();

               }
               return false;
           }
       });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showHeader();

                //attemptSearch();

            }
        });


        // TODO: Method to get http response headers

        // Uncomment to get http response headers
        //getHeaders();


    }


    // TODO: Method to load header from website and show it

    private void showHeader(){

        url = url + urlText.getText().toString();

        Intent showHeader = new Intent(MainActivity.this, LoadHeader.class);
        showHeader.putExtra("websiteURL", url);
        finish();
        startActivity(showHeader);

    }

    // TODO: Method to search the website and display in webview

    private void attemptSearch(){

        url = url + urlText.getText().toString();
        if(url.equals("http://")){
            Toast.makeText(MainActivity.this, "This field can't be empty", Toast.LENGTH_LONG).show();
        }
//        else if(!isURLvalid(url)) {
//
//            Toast.makeText(MainActivity.this, "Invalid URL Address", Toast.LENGTH_SHORT).show();
//        }
          else {
            Toast.makeText(MainActivity.this, "Opening Website", Toast.LENGTH_SHORT).show();
            spinner.setVisibility(View.VISIBLE);

            Log.d("ABCD", "onEditorAction: " + url);

            // Using Intent to navigate to second activity

            Intent myintent = new Intent(MainActivity.this, WebsiteActivity.class);
            myintent.putExtra("websiteurl", url);

            finish();
            startActivity(myintent);

        }
    }


    // TODO: Method to check url validation

    private boolean isURLvalid(String url){

        if (url.contains("www.") && url.contains(".com")){
            return true;
        } else {
            return false;
        }

    }


    // TODO: Method to get http response header from website server

    private void getHeaders(){

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {

                    Networking start = new Networking();
                    start.network();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

    }


}
