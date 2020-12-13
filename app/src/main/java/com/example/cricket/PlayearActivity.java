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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class PlayearActivity extends AppCompatActivity {

    TextView team1name,team2name,team1playername,team2Playername;


private String url="https://cricapi.com/api/fantasySquad/?apikey=GnPFrdasROOGCMaL4l4qvOdzeTo1&unique_id=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playear);
        getSupportActionBar().setTitle("Player Status");
        Intent intent=getIntent();
        String unique_id=intent.getStringExtra("match_id");
        url=url+unique_id;

team1name=findViewById(R.id.team1tv);
team2name=findViewById(R.id.team2tv);
team1playername=findViewById(R.id.team1playertv);
team2Playername=findViewById(R.id.team2playertv);
//calling load dtaa function
        loaddata();


     }

    private void loaddata() {
        final ProgressDialog pd=new ProgressDialog(this);
        pd.setMessage("Loading");
        pd.show();

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                pd.dismiss();

try {

    JSONArray squadArray=new JSONObject(response).getJSONArray("squad");
    JSONObject json0 = squadArray.getJSONObject(0);
    JSONObject json1 = squadArray.getJSONObject(1);

    String teamname1 = json0.getString("name");
    String teamname2 = json1.getString("name");

    JSONArray team1Array = json0.getJSONArray("players");
    JSONArray team2Array = json1.getJSONArray("players");


    team1name.setText(teamname1);

    team2name.setText(teamname2);

    for (int i=0;i<team1Array.length();i++){
        String team1=team1Array.getJSONObject(i).getString("name");
        team1playername.append((i+1)+" "+team1 +"\n");
    }
    for (int i=0;i<team2Array.length();i++){
        String team2=team2Array.getJSONObject(i).getString("name");
    team2Playername.append((i+1)+" ) "+team2+"\n");
    }

}catch (Exception e){

    Toast.makeText(PlayearActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
}

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PlayearActivity.this, "Error:"+error.toString(), Toast.LENGTH_SHORT).show();
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
