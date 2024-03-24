package com.example.RecipeManager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<CustomRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList recipe_id, recipe_name, recipe_ingredients, recipe_steps;
    Animation translate_anim;
    
    CustomRecyclerViewAdapter(Activity activity, Context context,
                              ArrayList recipe_id,
                              ArrayList recipe_name
                              ){
        this.activity = activity;
        this.context = context;
        this.recipe_id = recipe_id;
        this.recipe_name = recipe_name;
    }
    @NonNull
    @Override
    public CustomRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recipe_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomRecyclerViewAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.recipe_id_text.setText(String.valueOf(recipe_id.get(position)));
        holder.recipe_name_text.setText(String.valueOf(recipe_name.get(position)));
        // Load animation
        Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.translate_anim);
        holder.itemView.startAnimation(animation);
        holder.recipe_item_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(recipe_id.get(position)));
                intent.putExtra("name", String.valueOf(recipe_name.get(position)));
                //context.startActivity(intent);
                activity.startActivityForResult(intent, 1);
            }
        });
    }
    @Override
    public void onViewDetachedFromWindow(CustomRecyclerViewAdapter.MyViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

    @Override
    public int getItemCount() {
        return recipe_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView recipe_id_text, recipe_name_text;
        LinearLayout recipe_item_layout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            recipe_id_text = itemView.findViewById(R.id.recipe_id_text);
            recipe_name_text = itemView.findViewById(R.id.recipe_name_text);
            recipe_item_layout = itemView.findViewById(R.id.recipe_item_layout);
            //translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            //recipe_item_layout.setAnimation(translate_anim);
        }
    }
}
