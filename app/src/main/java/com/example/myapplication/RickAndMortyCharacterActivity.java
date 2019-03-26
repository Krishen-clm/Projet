package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class RickAndMortyCharacterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rick_and_morty_character);
        getTheIntent();
    }

    private void getTheIntent(){
        String characterName = getIntent().getStringExtra("character_description");
        String image = getIntent().getStringExtra("image");
    }

    private void setCharacterName(String characterName, String image) {
        TextView name = findViewById(R.id.character_nom);
        name.setText("Nom du personnage : " + characterName);
        ImageView imageView = findViewById(R.id.Image);
        Glide.with(this).load(image).into(imageView);

    }
}
