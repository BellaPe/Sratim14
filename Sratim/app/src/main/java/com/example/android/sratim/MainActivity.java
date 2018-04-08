package com.example.android.sratim;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private MenuItem Exit;
    private MenuItem DeleteAll;
    private MenuItem Manual;
    private MenuItem Internet;
    private MenuItem Group;
    private MenuItem Group2;
    private Database database;
    protected ArrayList<Movie> movies;
    private LinearLayout mMovieList;
    protected int i;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = new Database();
        movies = database.getAllMovies();
        mMovieList =(LinearLayout)findViewById(R.id.activity_main);
        loadMoviesDB();

      }


    protected void loadMoviesDB() {
        for(i=0;i<movies.size();i++){
            final int i_=i;
            int a = 1, b=2, c=3;
            RelativeLayout RL = new RelativeLayout(this);
            mMovieList.addView(RL);
            mMovieList.getChildAt(i).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Edit.class);
                    intent.putExtra("movie", movies.get(i_));
                    startActivityForResult(intent, 2);
                }
            });
            final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.edit), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(MainActivity.this, Edit.class);
                            intent.putExtra("movie", movies.get(i_));
                            startActivityForResult(intent, 2);
                        }
                    });
            alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.string.delete), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    database.deleteMovie(movies.get(i_));
                    movies.remove(i_);
                    recreate();
                }
            });
            mMovieList.getChildAt(i).setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View view) {
                            alertDialog.show();
                            return true;
                        }
                    });
            RL.setBackgroundResource(R.drawable.rectangle2);
            RL.setId(movies.get(i).get_id());
            RL.setGravity(RelativeLayout.CENTER_VERTICAL);
            LinearLayout.LayoutParams Params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            RL.setLayoutParams(Params);
            RelativeLayout.LayoutParams TV1Params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            RelativeLayout.LayoutParams TV2Params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            RelativeLayout.LayoutParams TV3Params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            TextView subject = new TextView(this);
            subject.setId(a);
            subject.setTextSize(20);
            subject.setText(movies.get(i).getSubject());
            TV1Params.height= 100;
            TV1Params.width = 700;
            subject.setLayoutParams(TV1Params);
            TextView body = new TextView(this);
            body.setId(b);
            body.setText(movies.get(i).getBody());
            body.setMovementMethod(new ScrollingMovementMethod());
            ImageView url = new ImageView(this);
            url.setId(c);
            if(movies.get(i).getUrl().length()>0)
                Picasso.with(this).load(movies.get(i).getUrl().toString()).into(url);
            TV2Params.addRule(RelativeLayout.ALIGN_LEFT, subject.getId());
            TV2Params.addRule(RelativeLayout.ALIGN_RIGHT, subject.getId());
            TV2Params.addRule(RelativeLayout.BELOW, subject.getId());
            body.setLayoutParams(TV2Params);
            TV3Params.addRule(RelativeLayout.ALIGN_TOP, subject.getId());
            TV3Params.addRule(RelativeLayout.ALIGN_BOTTOM, body.getId());
            TV3Params.addRule(RelativeLayout.RIGHT_OF, body.getId());
            url.setLayoutParams(TV3Params);
            RL.addView(subject);
            RL.addView(body);
            RL.addView(url);

        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.optionmenu, menu);
            //Menu items
            Group = menu.findItem(R.id.MainMenu);
            Group2 = menu.findItem(R.id.EditMenu);
            Exit = menu.findItem(R.id.Exit);
            DeleteAll = menu.findItem(R.id.DeleteAll);
            Manual = menu.findItem(R.id.Manual);
            Internet = menu.findItem(R.id.Internet);
            //Exiting the app
            Exit.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                   finish();
                   return true;
                }
            });

        DeleteAll.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int iterator = movies.size();
             for(int i =0;i<iterator;i++) {
                 database.deleteMovie(movies.get(0));
                 movies.remove(0);
                // Iterator<Movie> itr = movies.iterator();
                 //itr.remove();
             }
                recreate();
                return true;
            }

        });
            //Deleting all the movies
            // TODO: 14/03/2018  this

            Manual.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    Intent i = new Intent(MainActivity.this, Edit.class);
                    startActivityForResult(i, 1);
                    return true;
                }
            });

            Internet.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    Intent i = new Intent(MainActivity.this, InternetNew.class);
                    startActivityForResult(i, 1);
                    //toast
                    return true;
                }
            });

                return true;
        }




    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&resultCode==RESULT_OK)
        {
            Movie movie = (Movie) data.getSerializableExtra("movie");
            database.addMovie(movie);
            movies.add(movie);
            //Setting the view
            int a = 1, b=2, c=3;
            RelativeLayout RL = new RelativeLayout(this);
            mMovieList.addView(RL);
            RL.setBackgroundResource(R.drawable.rectangle2);
            RL.setId(movie.get_id());
            RL.setGravity(RelativeLayout.CENTER_VERTICAL);
            RelativeLayout.LayoutParams TV1Params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            RelativeLayout.LayoutParams TV2Params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            RelativeLayout.LayoutParams TV3Params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            TextView subject = new TextView(this);
            TV1Params.height= 100;
            TV1Params.width = 700;
            subject.setId(a); subject.setTextSize(20);
            subject.setText(movie.getSubject());
            subject.setLayoutParams(TV1Params);
            TextView body = new TextView(this);
            body.setId(b);
            body.setText(movie.getBody());
            ImageView url = new ImageView(this);
            url.setId(c);
            if(movies.get(i).getUrl().length()>0)
                Picasso.with(this).load(movie.getUrl().toString()).into(url);
            TV2Params.addRule(RelativeLayout.ALIGN_LEFT, subject.getId());
            TV2Params.addRule(RelativeLayout.ALIGN_RIGHT, subject.getId());
            TV2Params.addRule(RelativeLayout.BELOW, subject.getId());
            body.setLayoutParams(TV2Params);
            body.setMovementMethod(new ScrollingMovementMethod());
            TV3Params.addRule(RelativeLayout.ALIGN_TOP, subject.getId());
            TV3Params.addRule(RelativeLayout.ALIGN_BOTTOM, body.getId());
            TV3Params.addRule(RelativeLayout.RIGHT_OF, body.getId());
            url.setLayoutParams(TV3Params);
            RL.addView(subject);
            RL.addView(body);
            RL.addView(url);
            mMovieList.getChildAt(movies.size()-1).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Edit.class);
                    intent.putExtra("movie", movies.get(movies.size()-1));
                    startActivityForResult(intent, 2);
                }
            });


            Toast.makeText(MainActivity.this, "Movie added",
                    Toast.LENGTH_LONG).show();

        }

        else if(requestCode==2&&resultCode==RESULT_OK){
            Movie movie = (Movie) data.getSerializableExtra("movie");
            database.updateMovie(movie);
            this.recreate();
        }

    }



        //adding event listener to the ListView
     //   MyListView.setOnItemClickListener(
                //On item click we will show to the screen a popup with the item's content
             //   new AdapterView.OnItemClickListener(){
            //        @Override
               //     public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

      //              }

            //    }
    //    );
 //   }

}