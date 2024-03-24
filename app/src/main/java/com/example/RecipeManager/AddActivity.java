package com.example.RecipeManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    EditText name_input, ingredients_input, steps_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name_input = findViewById(R.id.name_input);
        ingredients_input = findViewById(R.id.ingredients_input);
        steps_input = findViewById(R.id.steps_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
           public void onClick (View view) {
               DatabaseHelper myDB = new DatabaseHelper(AddActivity.this);
               myDB.addRecipe(name_input.getText().toString().trim(),
                       ingredients_input.getText().toString().trim(),
                       steps_input.getText().toString().trim());
           }
        });

    }
}