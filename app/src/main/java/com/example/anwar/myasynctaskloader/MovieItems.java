package com.example.anwar.myasynctaskloader;

import org.json.JSONObject;

public class MovieItems {
    private int id;
    private String name;
    private String deskripsi;
    private String tanggal;
    private String image;
    private String rating;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getImage() {
        return image;
    }

    public String getRating() {
        return rating;
    }

    public MovieItems(JSONObject object){
        try{
            int id = object.getInt("id");
            String name = object.getString("title");
            String deskripsi = object.getString("overview");
            String tanggal = object.getString("release_date");
            String rating = object.getString("vote_average");
            String image = object.getString("poster_path");

            this.id = id;
            this.name = name;
            this.deskripsi = deskripsi;
            this.tanggal = tanggal;
            this.rating = rating;
            this.image = image;

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
