package com.example.RecipeManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class RecipeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView empty_imageview;
    TextView empty_textview;

    DatabaseHelper myDB;
    ArrayList<String> recipe_id, recipe_name, recipe_ingredients, recipe_steps;
    CustomRecyclerViewAdapter customRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_list_screen);

        recyclerView = findViewById(R.id.recycler_view);
        add_button = findViewById(R.id.add_button);
        empty_imageview = findViewById(R.id.empty_imageView);
        empty_textview = findViewById(R.id.noData_textView);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecipeActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        myDB = new DatabaseHelper(RecipeActivity.this);
        recipe_id = new ArrayList<>();
        recipe_name = new ArrayList<>();
        recipe_ingredients = new ArrayList<>();
        recipe_steps = new ArrayList<>();

        storeDataInArrays();

        customRecyclerViewAdapter = new CustomRecyclerViewAdapter(RecipeActivity.this, this, recipe_id, recipe_name);
        recyclerView.setAdapter(customRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(RecipeActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate(); //refresh recipe_activity
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
            empty_imageview.setVisibility(View.VISIBLE);
            empty_textview.setVisibility(View.VISIBLE);
        }else{
            empty_imageview.setVisibility(View.GONE);
            empty_textview.setVisibility(View.GONE);
            while (cursor.moveToNext()){
                recipe_id.add(cursor.getString(0));
                recipe_name.add(cursor.getString(1));
                recipe_ingredients.add(cursor.getString(2));
                recipe_steps.add(cursor.getString(3));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.recipe_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.delete_all){
            confirmDialog_deleteAll();
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog_deleteAll(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All Data");
        builder.setMessage("Confirm to delete all data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DatabaseHelper myDB = new DatabaseHelper(RecipeActivity.this);
                myDB.deleteAllData();
                //refresh recipe activity
                Intent intent = new Intent(RecipeActivity.this, RecipeActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}