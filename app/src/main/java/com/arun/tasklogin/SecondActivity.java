package com.arun.tasklogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.splashscreen.SplashScreen;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
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

    //for notification
    private String CHANNEL_ID = "channel_id";
    private int NOTIFICATION_ID = 1;
    private String CHANNEL_NAME = "Notification name";
    public int NOTIFICIATION_IMPORTANCE = NotificationManager.IMPORTANCE_DEFAULT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_TaskLogin);
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

    @Override
    public void onBackPressed() {
        showNotification();
        finish();

    }

    void showNotification(){

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,CHANNEL_NAME,NOTIFICIATION_IMPORTANCE);
            notificationChannel.setDescription("This is a notification channel");

            //register it
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        Intent intent = new Intent(this, AfterNotification.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_notification_clear_all)
                .setContentTitle("You exited ??")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Click to enter"))
                .setContentIntent(pendingIntent);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NOTIFICATION_ID,builder.build());


    }
}