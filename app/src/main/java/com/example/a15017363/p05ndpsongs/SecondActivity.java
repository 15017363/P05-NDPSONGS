package com.example.a15017363.p05ndpsongs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    Button btnShow;
    ListView lvSong;
    ArrayList<Song> SongList = new ArrayList<Song>();
    SongAdapter caSong = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        btnShow = (Button)findViewById(R.id.btnShow);
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

        lvSong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getBaseContext(), Main3Activity.class);
                Song data = new Song(SongList.get(i).get_id(), SongList.get(i).getTitle(), SongList.get(i).getSingers(), SongList.get(i).getYear(), SongList.get(i).getStars());
                intent.putExtra("data", data);
                startActivityForResult(intent, i);

            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SongList.clear();
                DBHelper db = new DBHelper(SecondActivity.this);
                ArrayList<Song> song = db.getAllSongByStars(5);
                db.close();

                for (int i = 0; i < song.size(); i++){
                    SongList.add(new Song(song.get(i).get_id(), song.get(i).getTitle(), song.get(i).getSingers(), song.get(i).getYear(),song.get(i).getStars()));
                }
                caSong.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 9){
            caSong.notifyDataSetChanged();
        }
    }
}
