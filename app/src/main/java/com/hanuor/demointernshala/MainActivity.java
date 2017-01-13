package com.hanuor.demointernshala;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    TextView question;
    EditText catureInput;
    Button apply;
    SaveOfflineData offlineData;
    Internal internal;
    String getAnswer;
    Button test, layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // Intent serviceIntent = new Intent(MainActivity.this, Servicer.class);
        // Start service
       // startService(serviceIntent);
        test = (Button) findViewById(R.id.test);
        test = (Button) findViewById(R.id.layout);
        internal = new Internal(MainActivity.this);
        question = (TextView) findViewById(R.id.tV);
        catureInput = (EditText) findViewById(R.id.eT);
        apply = (Button) findViewById(R.id.apply);
        offlineData = new SaveOfflineData(MainActivity.this);
        question.setText("Tell us why you should be hired for this internship?");
        final HashMap<String, String> mmap = new HashMap<String, String>();
        mmap.put("status","clicked on apply button");
        mmap.put("prgoress", "demo");
        mmap.put("prgoress1", "demo2");
        mmap.put("prgoress2", "demo3");
        mmap.put("prgoress3", "dem4");

        Log.d("BeginAgian", offlineData.getCount()+"");
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent setT = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(setT);
            }


        });

//        Log.d("AnnaSunn",""+offlineData.ForKey("apply").toString());
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAnswer = catureInput.getText().toString();
                if(!internal.isNetworkAvailable()){
                    final String REGISTER_URL = "https://test.internshala.com/json/test/offline";

                    mmap.put("username","username");
                    mmap.put("pass","password");
                    mmap.put("dumtss", "email");
                    mmap.put("answer", getAnswer);
                    String json = null;
                    try {
                        Log.d("Hellooo","Hye " + offlineData.getCount());
                        json = new ObjectMapper().writeValueAsString(mmap);
                        int counter = offlineData.getCount()+1;
                        offlineData.storeData("apply"+counter, json, "",REGISTER_URL,"POST", "unprocessed", "StringRequest", "");
                        mmap.clear();


                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                /*

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                   // Log.d("OfflineDD","V V " +offlineData.queryDB());
                                    //Toast.makeText(MainActivity.this,response,Toast.LENGTH_LONG).show();

                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    error.printStackTrace();


                                    // Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG).show();


                                }
                            }){
                        @Override
                        protected Map<String,String> getParams(){
                            Map<String,String> params = new HashMap<String, String>();
                            params.put("username","username");
                            params.put("pass","password");
                            params.put("dumtss", "email");
                            params.put("answer", getAnswer);
                            String json = null;
                            try {
                                json = new ObjectMapper().writeValueAsString(params);
                                offlineData.storeData("apply",json,"",REGISTER_URL,"POST","","StringRequest","",0);
                                //Log.d("OfflineD",offlineData.queryDB("apply"));

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return null;
                        }

                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                    requestQueue.add(stringRequest);

                    Log.d("BeginAgain", "FF  "+ offlineData.getCount());
*/

                }

                if(getAnswer.equals("")){
                    Toast.makeText(MainActivity.this,"Type something",Toast.LENGTH_SHORT).show();
                }else{
                   // mmap.put("answer",getAnswer);

                    ///

                }
            }
        });
    }

}
