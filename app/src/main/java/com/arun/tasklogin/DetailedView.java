package com.arun.tasklogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailedView extends AppCompatActivity {

    private ItemsModel items;

    private ImageView courseimg;
    private TextView courseTracks, courseMode, courseName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_view);

        courseimg = findViewById(R.id.courseimg);
        courseMode = findViewById(R.id.courseMode);
        courseTracks = findViewById(R.id.courseTracks);
        courseName = findViewById(R.id.courseName);

        items = getIntent().getParcelableExtra("details");

        if (items != null){
            Picasso.get().load(items.getCourseimg()).into(courseimg);
            courseMode.setText(items.getCourseMode());
            courseTracks.setText(items.getCourseTracks());
            courseName.setText(items.getCourseName());




        }

    }
}