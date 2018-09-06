package com.example.anwar.myasynctaskloader;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetail  extends AppCompatActivity  {
    private TextView nama,deskripsi,tanggal,rating;
    private RatingBar ratingBar;
    private ImageView imgMovie;
    private ProgressDialog mDialog;


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);
        nama = findViewById(R.id.movie_title);
        deskripsi = findViewById(R.id.movie_overview);
        tanggal = findViewById(R.id.movie_date);
        rating = findViewById(R.id.movie_rating);
        ratingBar = findViewById(R.id.ratingbar);
        imgMovie = findViewById(R.id.img_poster);
        mDialog = new ProgressDialog(this);
        mDialog.show();
        mDialog.setMessage("Please Wait..");
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mDialog.dismiss();
            }
        },1000);
        nama.setText(getIntent().getExtras().getString("nama"));
        deskripsi.setText(getIntent().getExtras().getString("desc"));
        tanggal.setText(getIntent().getExtras().getString("tanggal"));
        float rate = Float.parseFloat(getIntent().getExtras().getString("rating"));
        float hasil = rate/2;
        ratingBar.setRating(hasil);
        rating.setText(getIntent().getExtras().getString("rating"));

        Picasso.get()
                .load("http://image.tmdb.org/t/p/w342"+getIntent().getExtras().getString("image"))
                .placeholder(R.drawable.ic_movie_black_24dp)
                .into(imgMovie);


    }

}
