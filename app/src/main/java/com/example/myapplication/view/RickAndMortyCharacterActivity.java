package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

public class RickAndMortyCharacterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rick_and_morty_character);
        getTheIntent();
    }

    private void getTheIntent(){
        String characterName = getIntent().getStringExtra("character_nom");
        String image = getIntent().getStringExtra("image");
        String characterSpecies = getIntent().getStringExtra("character_espece");
        setCharacterName(characterName, image, characterSpecies);
    }

    private void setCharacterName(String characterName, String image, String characterSpecies) {
        TextView name = findViewById(R.id.character_nom);
        name.setText("Nom du personnage : " + characterName);
        Log.d("AZERT", "URL : "+ image);
        ImageView imageView = findViewById(R.id.image_char);
        Glide.with(this).load(image).into(imageView);
        TextView desc = findViewById(R.id.character_espece);
        desc.setText("Espèce : " + characterSpecies);
    }
}
