package com.example.anwar.myasynctaskloader;

import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.anwar.myasynctaskloader.Adapter.MovieAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<MovieItems>> {

    ListView listView;
    MovieAdapter adapter;
    EditText editMovie;
    Button btnCari;
    private ProgressDialog mDialog;
    static final String EXTRAS_MOVIE = "EXTRAS_MOVIE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new MovieAdapter(this);
        adapter.notifyDataSetChanged();
        listView = findViewById(R.id.lvFilm);
        listView.setAdapter(adapter);
        listView.setClickable(true);
        editMovie = findViewById(R.id.edtCari);
        btnCari = findViewById(R.id.btnCari);
        btnCari.setOnClickListener(myListener);
        mDialog = new ProgressDialog(this);
        String movie = editMovie.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRAS_MOVIE,movie);

        getLoaderManager().initLoader(0,bundle,  MainActivity.this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }


    @Override
    public Loader<ArrayList<MovieItems>> onCreateLoader(int id, Bundle args) {
        String movie = "";
        if (args !=null){
            movie = args.getString(EXTRAS_MOVIE);

        }
        return new MyAsyncTaskLoader(this,movie);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<MovieItems>> loader, ArrayList<MovieItems> data) {
        mDialog.dismiss();
        adapter.setData(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<MovieItems>> loader) {
        adapter.setData();
    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String cari = editMovie.getText().toString();

            mDialog.show();
            mDialog.setMessage("Tunggu Sebentar..");
            if (TextUtils.isEmpty(cari))return;

            Bundle bundle = new Bundle();
            bundle.putString(EXTRAS_MOVIE,cari);
            getLoaderManager().restartLoader(0,bundle,  MainActivity.this);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.option_languae:
                Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(mIntent);
                item.setTitle(getResources().getText(R.string.change_leanguae));


        }

        return super.onOptionsItemSelected( item );
    }


}