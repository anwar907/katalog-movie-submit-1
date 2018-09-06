package com.example.anwar.myasynctaskloader.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.anwar.myasynctaskloader.MovieDetail;
import com.example.anwar.myasynctaskloader.MovieItems;
import com.example.anwar.myasynctaskloader.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends BaseAdapter{

    private ArrayList<MovieItems> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;

    public void setData() {
    }


    private static class ViewHolder{
        TextView textViewNamaFilm;
        TextView textViewDeskripsi;
        TextView textViewTanggal;
        ImageView imgFilm;
        LinearLayout linearLayoutMovie;
    }

    public MovieAdapter(Context context){
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    public void setData(ArrayList<MovieItems> items){
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    public void addItem(final MovieItems item){
        mData.add(item);
        notifyDataSetChanged();
    }

    public void clearData(){
        mData.clear();
    }


    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getCount() {
        if (mData==null)return 0;

        return mData.size();
    }


    @Override
    public MovieItems getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.row_item_list_movie,null);
            holder.textViewNamaFilm = convertView.findViewById(R.id.txtNama);
            holder.textViewDeskripsi = convertView.findViewById(R.id.txtDeskripsi);
            holder.textViewTanggal = convertView.findViewById(R.id.txtTanggal);
            holder.imgFilm = convertView.findViewById(R.id.imgMovie);
            holder.linearLayoutMovie = convertView.findViewById(R.id.llItemList);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textViewNamaFilm.setText(mData.get(position).getName());
        holder.textViewDeskripsi.setText(mData.get(position).getDeskripsi());
        holder.textViewTanggal.setText(mData.get(position).getTanggal());
        Picasso.get()
                .load("http://image.tmdb.org/t/p/w342"+mData.get(position).getImage())
                .placeholder(R.drawable.ic_movie_black_24dp)
                .into(holder.imgFilm);



        holder.linearLayoutMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailMovieIntent = new Intent(context,MovieDetail.class);

                detailMovieIntent.putExtra("nama",mData.get(position).getName());
                detailMovieIntent.putExtra("desc",mData.get(position).getDeskripsi());
                detailMovieIntent.putExtra("image",mData.get(position).getImage());
                detailMovieIntent.putExtra("tanggal",mData.get(position).getTanggal());
                detailMovieIntent.putExtra("rating",mData.get(position).getRating());
                detailMovieIntent.putExtra("id",mData.get(position).getId());

                context.startActivity(detailMovieIntent);
            }
        });
        return convertView;
    }

}

