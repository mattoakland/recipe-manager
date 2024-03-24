package com.example.RecipeManager;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    EditText name_input, ingredients_input, steps_input;
    Button update_button, delete_button;

    String id, name, ingredients, steps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name_input = findViewById(R.id.name_input2);
        ingredients_input = findViewById(R.id.ingredients_input2);
        steps_input = findViewById(R.id.steps_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);
        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if(ab != null) {
            ab.setTitle(name);
        }
        update_button.setOnClickListener(new View.OnClickListener() {
            public void onClick (View view) {
                DatabaseHelper myDB = new DatabaseHelper(UpdateActivity.this);
                name = name_input.getText().toString().trim();
                myDB.updateData(id, name);
            }
        });
        delete_button.setOnClickListener(v -> {
            confirmDialog_deleteOneRow();
        });
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name")){
            //getting data from intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");

            //setting intent data
            name_input.setText(name);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog_deleteOneRow(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Confirm to delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DatabaseHelper myDB = new DatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
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