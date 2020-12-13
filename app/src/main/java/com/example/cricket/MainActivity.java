 package com.example.cricket;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

 public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter madapter;
    private List<Model> modelList;


private  String url="https://cricapi.com/api/matches/?apikey=GnPFrdasROOGCMaL4l4qvOdzeTo1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
getSupportActionBar().setTitle("Match Score Finder");
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        modelList=new ArrayList<>();
        loadUrldata();

    }

     private void loadUrldata() {

         ProgressDialog pd=new ProgressDialog(this);
         pd.setMessage("Loading");
         pd.show();
         StringRequest stringRequest= new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
             @Override
             public void onResponse(String response) {

pd.dismiss();

try {

    JSONArray jsonArray=new JSONObject(response).getJSONArray("matches");
    for (int i=0;i<jsonArray.length();i++){

        try {

            String UniqueId =jsonArray.getJSONObject(i).getString("unique_id");
            String team1 =jsonArray.getJSONObject(i).getString("team-1");
            String team2=jsonArray.getJSONObject(i).getString("team-2");
           // String matchtype =jsonArray.getJSONObject(i).getString("type");
            String matchstatus =jsonArray.getJSONObject(i).getString("matchStarted");
            if (matchstatus.equals("true")){
matchstatus="Match Started";

            }else{
                matchstatus="Match Not  Started";
            }
            String dateTimeGMT=jsonArray.getJSONObject(i).getString("dateTimeGMT");
            SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            format1.setTimeZone(TimeZone.getTimeZone(dateTimeGMT));
            Date date=format1.parse(dateTimeGMT);


            SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy/ HH:mm");
            format2.setTimeZone(TimeZone.getTimeZone("GMT"));
            String dateTime=format2.format(date);


            Model model=new Model(UniqueId,team1,team2,matchstatus,dateTime);
            modelList.add(model);

        }catch(Exception e){
            Toast.makeText(MainActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }
    madapter =new MyAdapter(modelList,getApplicationContext());
recyclerView.setAdapter(madapter);


}catch (Exception exception){

    Toast.makeText(MainActivity.this, ""+ exception.getMessage(), Toast.LENGTH_SHORT).show();
}



             }
         }, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError error) {

                 Toast.makeText(MainActivity.this, "Error"+error.toString(), Toast.LENGTH_SHORT).show();

             }
         });
         RequestQueue requestQueue= Volley.newRequestQueue(this);
         requestQueue.add(stringRequest);
     }
 }