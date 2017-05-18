package com.example.a15017363.p05ndpsongs;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 15017103 on 18/5/2017.
 */

public class SongAdapter extends ArrayAdapter<Song>{
    Context context;
    ArrayList<Song> song;
    TextView tvYear, tvTitle, tvSinger;
    int resource;
    ImageView ivStar1, ivStar2, ivStar3, ivStar4, ivStar5;

    public SongAdapter(Context context, int resource, ArrayList<Song> song) {
        super(context, resource, song);
        this.context = context;
        this.song = song;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(resource, parent, false);

        //Match the UI components with Java variables
        tvYear = (TextView) rowView.findViewById(R.id.tvYear);
        tvTitle = (TextView) rowView.findViewById(R.id.tvTitle);
        tvSinger = (TextView) rowView.findViewById(R.id.tvSinger);
        ivStar1 = (ImageView) rowView.findViewById(R.id.ivStar1);
        ivStar2 = (ImageView) rowView.findViewById(R.id.ivStar2);
        ivStar3 = (ImageView) rowView.findViewById(R.id.ivStar3);
        ivStar4 = (ImageView) rowView.findViewById(R.id.ivStar4);
        ivStar5 = (ImageView) rowView.findViewById(R.id.ivStar5);

        Song current = song.get(position);
        tvYear.setText("" + current.getYear());
        tvTitle.setText(current.getTitle());
        tvSinger.setText(current.getSingers());


        return rowView;
    }


}
