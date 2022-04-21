package com.example.ethnomedicinalapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class PlantAdapter extends ArrayAdapter<PlantActivity> {
    public PlantAdapter(Context context, int resource, List<PlantActivity> plantActivityList){
        super(context,resource,plantActivityList);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PlantActivity plantActivity = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.plant_cells, parent, false);
        }

        TextView commonName = (TextView) convertView.findViewById(R.id.commonName);
        TextView scientificName = (TextView) convertView.findViewById(R.id.scientificName);
        TextView plantDescription = (TextView) convertView.findViewById(R.id.plantDescription);
        TextView plantBenefits = (TextView) convertView.findViewById(R.id.plantBenefits);
        TextView plantIdentifier = (TextView) convertView.findViewById(R.id.plantIdentifier);
        ImageView plantImg = (ImageView) convertView.findViewById(R.id.plantImage);

        commonName.setText(plantActivity.getCommonName());
        scientificName.setText(plantActivity.getScientificName());
        plantDescription.setText(plantActivity.getPlantDescription());
        plantBenefits.setText(plantActivity.getPlantBenefits());
        plantIdentifier.setText(plantActivity.getPlantIdentifier());
        plantImg.setImageResource(plantActivity.getPlant_img());


        return convertView;
    }
}
