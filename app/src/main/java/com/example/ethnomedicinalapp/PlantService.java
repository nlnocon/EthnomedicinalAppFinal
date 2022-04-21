package com.example.ethnomedicinalapp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PlantService {

    @POST ("func2")
    Call<PlantResponse> savePlant(@Body PlantRequest plantRequest);

}
