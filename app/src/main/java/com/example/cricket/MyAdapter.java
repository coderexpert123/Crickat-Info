package com.example.cricket;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.security.PrivateKey;
import java.util.List;

public class MyAdapter extends  RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private List<Model> models;
    private Context context;

    public MyAdapter(List<Model> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
Model model=models.get(position);
holder.team1.setText(model.getTeam1());
        holder.team1.setText(model.getTeam1());
        holder.team2.setText(model.getTeam2());
       // holder.matchtype.setText(model.getMatchtype());
        holder.matchstatus.setText(model.getMatchstatus());
        holder.date.setText(model.getDate());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mathid=model.getId();
                String date=model.getDate();



String[] option={"Match Details","Player List"};

                AlertDialog.Builder builder=new AlertDialog.Builder(view.getRootView().getContext());
                builder.setTitle("Choose Option");
                builder.setItems(option, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        if (which==0){
                            Intent intent =new Intent(context,matcdeatils.class);
                            intent.putExtra("match_id",mathid);
                            intent.putExtra("date",date);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        }
                        if (which==1){
                            Intent intent =new Intent(context,PlayearActivity.class);
                            intent.putExtra("match_id",mathid);

                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        }
                    }
                });
                builder.create().show();



            }

        });

    }

    @Override
    public int getItemCount() {
        return models.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

TextView team1,team2,matchtype,matchstatus,date;
CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

           team1= itemView.findViewById(R.id.team1tv);
           team2=  itemView.findViewById(R.id.team2tv);
          //matchtype=   itemView.findViewById(R.id.matchtypetv);
           matchstatus= itemView.findViewById(R.id.matchstatustv);
           date= itemView.findViewById(R.id.datetv);
cardView=itemView.findViewById(R.id.cardView);

        }
    }
}
