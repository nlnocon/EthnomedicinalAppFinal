package com.example.ethnomedicinalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailedPlantActivity extends AppCompatActivity {

    PlantActivity selectedPlant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_plants);

        getSelectedPlant();
        setValues();
    }

    public void getSelectedPlant() {
        Intent previousIntent = getIntent();
        String parsedStringID = previousIntent.getStringExtra("id");
        selectedPlant = MainActivity.plantActivityArrayList.get(Integer.valueOf(parsedStringID));
    }

    public void setValues() {
        TextView commonName = (TextView) findViewById(R.id.commonName);
        TextView scientificName = (TextView) findViewById(R.id.scientificName);
        TextView plantDescription = (TextView) findViewById(R.id.plantDescription);
        TextView plantBenefits = (TextView) findViewById(R.id.plantBenefits);
        ImageView plantImg = (ImageView) findViewById(R.id.plantImage);

        commonName.setText(selectedPlant.getCommonName());
        scientificName.setText(selectedPlant.getScientificName());
        plantDescription.setText(selectedPlant.getPlantDescription());
        plantBenefits.setText(selectedPlant.getPlantBenefits());
        plantImg.setImageResource(selectedPlant.getPlant_img());
    }
}