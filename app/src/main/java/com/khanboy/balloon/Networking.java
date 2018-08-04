package com.khanboy.balloon;



import android.util.Log;

import java.io.IOException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.HttpClientBuilder;

public class Networking {

    // TODO: Declare Member variables here

    HttpClient client = HttpClientBuilder.create().build();
    HttpGet request = new HttpGet("http://www.github.com");

    HttpResponse response;


    // TODO: Method to get all the http response headers

    public void network() {

        // try-catch block to handle exceptions from server
        try {
            response = client.execute(request);

            Header[] headers = response.getAllHeaders();

            // Loop to print all the http response header in logcat one by one
            for (int i = 0; i < headers.length; i++) {

                // Log message to show all the http response headers in logcat
                Log.d("ABCD", "header : " + i + " - > " + headers[i]);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
