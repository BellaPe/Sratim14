package com.example.android.sratim;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class InternetNew extends AppCompatActivity {
private SingleMovieReaderController controller;
private  ListView listViewMovies;
 public    ArrayList<Movie> movies;
 public Context con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet_new);
        controller = new SingleMovieReaderController(this);
        con = MyApp.getContext();

    }


    public void onClick_getItems(View V) {
            EditText search = (EditText) findViewById(R.id.search);
            String str = search.getText().toString();
            controller.readAllMovies(str);
        listViewMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                send(i);
            }
        });

        }
    private void send(int i) {
        // String selectedMovie = String.valueOf(adapterView.getItemAtPosition(i));
        Intent intent = new Intent(this, Edit.class);
        movies = controller.giveMovies();
        Movie movie = new Movie (movies.get(i).getSubject(), movies.get(i).getBody(), movies.get(i).getUrl());
        intent.putExtra("movie", movie);
                           /*intent.putExtra("Title", movies.get(i).getSubject());
                           intent.putExtra("Body", movies.get(i).getBody());
                           intent.putExtra("Url", movies.get(i).getUrl());*/
        this.startActivityForResult(intent, 1);
        this.finish();
    }

 /*   Intent intent = getIntent();
    Movie movie = (Movie) getIntent().getSerializableExtra("movie");
    onActivityResult(1, RESULT_OK, intent);  here is the try, the listview doesnt open and I don`t know why, and the movie is null. */

}

