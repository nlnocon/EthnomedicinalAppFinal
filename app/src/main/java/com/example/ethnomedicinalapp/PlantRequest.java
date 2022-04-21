package com.example.ethnomedicinalapp;

import com.google.gson.JsonObject;

class PlantRequest {
    private JsonObject encoded;

    public JsonObject getEncoded() {
        return encoded;
    }

    public void setEncoded(JsonObject encoded) {
        this.encoded = encoded;
    }

}
