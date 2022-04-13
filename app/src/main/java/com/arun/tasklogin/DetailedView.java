package com.arun.tasklogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.arun.tasklogin.databinding.ActivityDetailedViewBinding;
import com.squareup.picasso.Picasso;

public class DetailedView extends AppCompatActivity {

    private ItemsModel itemsDet;

    private ImageView courseimg;
    private TextView courseTracks, courseMode, courseName;

    private ActivityDetailedViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityDetailedViewBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        Intent intent =getIntent();
        binding.courseName.setText(intent.getStringExtra("name"));
        binding.courseMode.setText(intent.getStringExtra("mode"));
        binding.courseTracks.setText(intent.getStringExtra("tracks"));
        Picasso.get().load(intent.getStringExtra("image")).into(binding.courseimg);

//        courseimg = findViewById(R.id.courseimg);
//        courseMode = findViewById(R.id.courseMode);
//        courseTracks = findViewById(R.id.courseTracks);
//        courseName = findViewById(R.id.courseName);

//        itemsDet = getIntent().getParcelableExtra("details");
//
//        if (itemsDet != null){
//            Picasso.get().load(itemsDet.getCourseimg()).into(binding.courseimg);
//            binding.courseMode.setText(itemsDet.getCourseMode());
//            binding.courseTracks.setText(itemsDet.getCourseTracks());
//            binding.courseName.setText(itemsDet.getCourseName());




        }


}