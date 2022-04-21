package com.example.ethnomedicinalapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UploadingActivity extends AppCompatActivity {
    Button uploadButton;
    String encodedString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploading);
        uploadButton = findViewById(R.id.uploadButton);

        uploadButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                ImagePicker.with(UploadingActivity.this)
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .start();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();

            if (uri != null) {
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                    byte[] byteArray = outputStream.toByteArray();
                    //Use your Base64 String as you wish
                    encodedString = Base64.encodeToString(byteArray, Base64.DEFAULT);
                    JsonObject obj = new JsonObject();
                    obj.addProperty("image", encodedString);

                    savePlant(plantRequest(obj));
                    //PlantRequest image = new PlantRequest(encodedString);

                } catch (IOException | JsonIOException e) {
                    e.printStackTrace();
                }
            }
    }

    public PlantRequest plantRequest(JsonObject obj){
        PlantRequest plantRequest = new PlantRequest();
        plantRequest.setEncoded(obj);

        return plantRequest;
    }

    public void savePlant (PlantRequest plantRequest){
        Call<PlantResponse> plantResponseCall = ApiClient.getPlantService().savePlant(plantRequest);
        plantResponseCall.enqueue(new Callback<PlantResponse>() {
            @Override
            public void onResponse(Call<PlantResponse> call, Response<PlantResponse> response) {
                if (response.isSuccessful()){
                    String responseFromServer = response.body().getStatus();
                    if (responseFromServer.equals("true")){
                        PlantActivity selectedPlant = MainActivity.plantActivityArrayList.get(1);
                        Intent showDetail = new Intent (getApplicationContext(), DetailedPlantActivity.class);
                        showDetail.putExtra("id", selectedPlant.getId());
                        startActivity(showDetail);
                        Toast.makeText(UploadingActivity.this, "Saved Successfully", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(UploadingActivity.this, "Plant not found", Toast.LENGTH_LONG).show();
                    }


                }
                else{
                    String error = new String();
                    error = response.toString();
                    Toast.makeText(UploadingActivity.this, "Request failed " + error, Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<PlantResponse> call, Throwable t) {
                String error = new String();
                error = t.getLocalizedMessage();
                Toast.makeText(UploadingActivity.this, "Save failed " + t.getLocalizedMessage(), Toast.LENGTH_LONG ).show();
            }
        });
    }
}

