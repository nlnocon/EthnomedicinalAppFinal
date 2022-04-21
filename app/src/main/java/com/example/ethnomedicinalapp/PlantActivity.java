package com.example.ethnomedicinalapp;

class PlantActivity {
    private String id;
    private String commonName;
    private String scientificName;
    private String plantDescription;
    private String plantBenefits;
    private String plantIdentifier;
    private int plant_img;

    public PlantActivity(String id, String commonName, String scientificName, String plantDescription, String plantBenefits, String plantIdentifier, int plant_img) {
        this.id = id;
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.plantDescription = plantDescription;
        this.plantBenefits = plantBenefits;
        this.plantIdentifier = plantIdentifier;
        this.plant_img = plant_img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getPlantDescription() {
        return plantDescription;
    }

    public void setPlantDescription(String plantDescription) {
        this.plantDescription = plantDescription;
    }

    public String getPlantBenefits() {
        return plantBenefits;
    }

    public void setPlantBenefits(String plantBenefits) {
        this.plantBenefits = plantBenefits;
    }

    public String getPlantIdentifier() {
        return plantIdentifier;
    }

    public void setPlantIdentifier(String plantIdentifier) {
        this.plantIdentifier = plantIdentifier;
    }

    public int getPlant_img() {
        return plant_img;
    }

    public void setPlant_img(int plant_img) {
        this.plant_img = plant_img;
    }
}
