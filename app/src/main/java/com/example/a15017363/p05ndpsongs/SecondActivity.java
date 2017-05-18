package com.example.a15017363.p05ndpsongs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    Button btnShow;
    Spinner spinnerYear;
    ArrayAdapter spinnerAdapter;
    ListView lvSong;
    ArrayList<Song> SongList = new ArrayList<Song>();
    SongAdapter caSong = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        lvSong = (ListView)findViewById(R.id.lvSong);
        caSong = new SongAdapter(this, R.layout.row, SongList);
        lvSong.setAdapter(caSong);

        DBHelper db = new DBHelper(SecondActivity.this);
        ArrayList<Song> song = db.getAllSongs();
        db.close();

        for (int i = 0; i < song.size(); i++){
            SongList.add(new Song(song.get(i).get_id(), song.get(i).getTitle(), song.get(i).getSingers(), song.get(i).getYear(),song.get(i).getStars()));
        }
        caSong.notifyDataSetChanged();

    }
}
