package com.example.cricket;

public class Model {

String id,team1,team2,matchtype,matchstatus,date;


    public Model(String id, String team1, String team2, String matchstatus, String date) {
        this.id = id;
        this.team1 = team1;
        this.team2 = team2;
     //   this.matchtype = matchtype;
        this.matchstatus = matchstatus;
        this.date = date;
    }


    public String getId() {
        return id;
    }

    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }



    public String getMatchstatus() {
        return matchstatus;
    }

    public String getDate() {
        return date;
    }
}
