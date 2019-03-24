package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class RickAndMortyCharacterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rick_and_morty_character);
        getTheIntent();
    }

    private void getTheIntent(){
        String characterName = getIntent().getStringExtra("character_description");
        setCharacterName(characterName);
    }

    private void setCharacterName(String characterName) {
        TextView name = findViewById(R.id.character_nom);
        name.setText(characterName);
    }
}
