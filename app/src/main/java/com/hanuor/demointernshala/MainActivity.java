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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView question;
    EditText catureInput;
    Button apply;
    SaveOfflineData offlineData;
    Internal internal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent serviceIntent = new Intent(MainActivity.this, Servicer.class);
        // Start service
        startService(serviceIntent);
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

//        Log.d("AnnaSunn",""+offlineData.ForKey("apply").toString());
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String getAnswer = catureInput.getText().toString();
                if(!internal.isNetworkAvailable()){
                    mmap.put("answer",getAnswer);
                    final String REGISTER_URL = "https://test.internshala.com/json/test/offline";

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.d("OfflineDD","V V " +offlineData.queryDB("apply"));
                                    //Toast.makeText(MainActivity.this,response,Toast.LENGTH_LONG).show();

                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    error.printStackTrace();

                                    offlineData.update("apply","network_error","unprocessed");
                                    Log.d("OfflineDD","V V E " +offlineData.queryDB("apply"));
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
                                offlineData.storeData("apply",json,"",REGISTER_URL,"POST","","StringRequest","");
                                Log.d("OfflineD",offlineData.queryDB("apply"));

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return null;
                        }

                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                    requestQueue.add(stringRequest);

                    Log.d("BeginAgain", "FF  "+ offlineData.getCount());


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
