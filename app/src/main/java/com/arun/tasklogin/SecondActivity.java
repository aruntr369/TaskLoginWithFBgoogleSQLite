package com.arun.tasklogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SecondActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        recyclerView = findViewById(R.id.recycleViewSecond);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getView();

        itemClick();



    }
    void getView(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonkeeper.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ItemsAPI itemsAPI = retrofit.create(ItemsAPI.class);
        Call<List<ItemsModel>> call = itemsAPI.getItemss();
        call.enqueue(new Callback<List<ItemsModel>>() {
            @Override
            public void onResponse(Call<List<ItemsModel>> call, Response<List<ItemsModel>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(SecondActivity.this, "response code = "+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<ItemsModel> listitems = response.body();
                RecyAdapterSec recyAdapterSec = new RecyAdapterSec(SecondActivity.this,listitems);
                recyclerView.setAdapter(recyAdapterSec);
                toDetailedView();
            }

            @Override
            public void onFailure(Call<List<ItemsModel>> call, Throwable t) {
                Toast.makeText(SecondActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    void itemClick(){
        RecyAdapterSec recyAdapterSec = new RecyAdapterSec(new RecyAdapterSec.ItemClickListener() {
            @Override
            public void onItemClicked(ItemsModel items) {
                Intent i = new Intent(SecondActivity.this,DetailedView.class);
                i.putExtra("details",items);
                startActivity(i);
            }
        });

    }
    void toDetailedView(){

    }
}