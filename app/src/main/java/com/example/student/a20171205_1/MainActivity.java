package com.example.student.a20171205_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.CharBuffer;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.textView);
        new Thread() {
            @Override
            public void run() {
                super.run();
                String str_url = "http://data.taipei/opendata/datalist/apiAccess?scope=datasetMetadataSearch&q=id:5bc82dc7-f2a2-4351-abc8-c09c8a8d7529";
                URL url = null;
                try {
                    url = new URL(str_url);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.connect();
                    InputStream inputStream = conn.getInputStream();
                    InputStreamReader reader = new InputStreamReader(inputStream);
                    BufferedReader br = new BufferedReader(reader);
                    str = br.readLine();
                    Log.d("CONN1", str);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv.setText(str.substring(0, 500));
                        }
                    });

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
