package com.example.cricket;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class matcdeatils extends AppCompatActivity {
    TextView mtextv1,mtextv2,mstatustv,mscoretv,mdatetv;

private   String url="https://cricapi.com/api/cricketScore/?apikey=GnPFrdasROOGCMaL4l4qvOdzeTo1&unique_id=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matcdeatils);
        getSupportActionBar().setTitle("Details");
        Intent intent=getIntent();
        String id=intent.getStringExtra("match_id");
        String date=intent.getStringExtra("date");
        url=url+id;
mtextv1=findViewById(R.id.team1tv);

        mtextv2=findViewById(R.id.team2tv);
        mtextv1=findViewById(R.id.team1tv);

        mscoretv=findViewById(R.id.scoretv);
        mstatustv=findViewById(R.id.matchstatustv);
      //  mdescriptiontv=findViewById(R.id.Descriptiontv);
        mdatetv=findViewById(R.id.datetv);
        mdatetv.setText(date);
        loaddate();

    }

    private void loaddate() {
       final ProgressDialog pd=new ProgressDialog(this);
        pd.setMessage("Loading");
        pd.show();

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                pd.dismiss();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String team1=jsonObject.getString("team-1");
                    String team2=jsonObject.getString("team-2");
                    String matchstatus=jsonObject.getString("matchStarted");
                    if (matchstatus.equals(true)){
                                        matchstatus="Match Started";


                    }else{
                                         matchstatus="Match Not Started";

                    }
                    mtextv1.setText(team1);
                    mtextv2.setText(team2);
                    mstatustv.setText(matchstatus);
try {

    String score=jsonObject.getString("stat");

    mscoretv.setText(score);
    //mdescriptiontv.setText(description);
}catch (Exception e){
    Toast.makeText(matcdeatils.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

}



                }catch (Exception e){
                    Toast.makeText(matcdeatils.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(matcdeatils.this, "Error"+error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}