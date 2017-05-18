package com.example.a15017363.p05ndpsongs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {
    EditText etTitle, etSinger, etYear;
    TextView tvId;
    Button btnUpdate, btnDelete, btnCancel;
    RadioButton rb1 , rb2, rb3, rb4, rb5;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        tvId = (TextView)findViewById(R.id.tvId);
        etTitle = (EditText)findViewById(R.id.etTitle);
        etSinger =(EditText)findViewById(R.id.etSinger);
        etYear = (EditText)findViewById(R.id.etYear);
        btnUpdate = (Button)findViewById(R.id.btnUpdate);
        btnDelete = (Button)findViewById(R.id.btnDelete);
        btnCancel = (Button)findViewById(R.id.btnCancel);

        Intent i = getIntent();
        data = (Song)i.getSerializableExtra("data");
        tvId.setText(data.get_id());
        etTitle.setText(data.getTitle());
        etSinger.setText(data.getSingers());
        etYear.setText(data.getYear());
        int selected = data.getStars();
        if(selected == 1){
            rb1 = (RadioButton)findViewById(R.id.rb1);
            rb1.setChecked(true);
        } else if(selected == 2){
            rb2 = (RadioButton)findViewById(R.id.rb2);
            rb2.setChecked(true);
        } else if(selected == 3){
            rb3 = (RadioButton)findViewById(R.id.rb3);
            rb3.setChecked(true);
        } else if(selected == 4){
            rb4 = (RadioButton)findViewById(R.id.rb4);
            rb4.setChecked(true);
        } else {
            rb5 = (RadioButton)findViewById(R.id.rb5);
            rb5.setChecked(true);
        }
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(Main3Activity.this);
                RadioGroup rg = (RadioGroup)findViewById(R.id.rgStars);
                int selected = rg.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton)findViewById(selected);
                int stars = Integer.parseInt(rb.getText().toString());
                data.setSingers(etSinger.getText().toString());
                data.setTitle(etTitle.getText().toString());
                data.setStars(stars);
                dbh.updateSong(data);
                dbh.close();
                Intent i = new Intent();
                setResult(RESULT_OK,i);
                finish();

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(Main3Activity.this);
                dbh.deleteSong(data.get_id());
                dbh.close();
                Intent i= new Intent();
                setResult(RESULT_OK,i);
                finish();
            }
        });



    }
}
