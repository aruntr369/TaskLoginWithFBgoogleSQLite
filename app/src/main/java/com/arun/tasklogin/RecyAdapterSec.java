package com.arun.tasklogin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyAdapterSec extends RecyclerView.Adapter<RecyAdapterSec.ItemsViewHolder> {

    private List<ItemsModel> itemsList;
    private Context context;


    public RecyAdapterSec(SecondActivity secondActivity, List<ItemsModel> listitems) {
        this.itemsList = listitems;
        this.context = secondActivity;
    }

    ItemClickListener itemClickListener;
    public RecyAdapterSec(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }


    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.each_items,parent,false);
        return new ItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsViewHolder holder, int position) {
        ItemsModel items = itemsList.get(position);

        holder.courseName.setText(items.getCourseName());
        holder.courseTracks.setText(items.getCourseTracks());
        holder.courseMode.setText(items.getCourseMode());
        Picasso.get().load(items.getCourseimg()).into(holder.courseimg);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // itemClickListener.onItemClicked(items);
                Intent intent = new Intent(context,DetailedView.class);
                intent.putExtra("image",items.getCourseimg());
                intent.putExtra("name",items.getCourseName());
                intent.putExtra("tracks",items.getCourseTracks());
                intent.putExtra("mode",items.getCourseMode());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class ItemsViewHolder extends RecyclerView.ViewHolder {
        private ImageView courseimg;
        private TextView courseTracks, courseMode, courseName;
        private CardView cardView;
        public ItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            courseimg = itemView.findViewById(R.id.courseimgDet);
            courseMode = itemView.findViewById(R.id.courseModeDet);
            courseTracks = itemView.findViewById(R.id.courseTracksDet);
            courseName = itemView.findViewById(R.id.courseNameDet);
            cardView = itemView.findViewById(R.id.cardViewDet);
        }
    }

    public interface ItemClickListener{
        void onItemClicked(ItemsModel items);
    }
}
