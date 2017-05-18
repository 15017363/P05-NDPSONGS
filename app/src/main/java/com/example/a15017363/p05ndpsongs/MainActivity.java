package com.example.a15017363.p05ndpsongs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnShow;
    EditText etTitle, etYear, etSinger;
    RadioGroup rgStars;
    RadioButton radiobtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = (Button)findViewById(R.id.btnInsert);
        btnShow = (Button)findViewById(R.id.btnShow);

        etTitle = (EditText)findViewById(R.id.editTextTitle);
        etSinger = (EditText)findViewById(R.id.editTextSinger);
        etYear = (EditText)findViewById(R.id.editTextYear);

        rgStars = (RadioGroup)findViewById(R.id.rgStars);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selected = rgStars.getCheckedRadioButtonId();
                radiobtn = (RadioButton)findViewById(selected);
                String title = etTitle.getText().toString();
                String singer = etSinger.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());
                int rbstars = Integer.parseInt(radiobtn.getText().toString());

                DBHelper db = new DBHelper(MainActivity.this);
                //Insert a task
                db.insertSong(title,singer,year,rbstars);
                Toast.makeText(MainActivity.this, "Song inserted; title: "+title+", singer: "+singer+", year: "+year+", stars: "+rbstars , Toast.LENGTH_SHORT).show();
                db.close();

            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),SecondActivity.class);
                startActivity(intent);
            }
        });


    }
}
