package com.example.ethnomedicinalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button cameraButton;
    ToggleButton leavesFilterButton;
    ToggleButton flowersFilterButton;
    ToggleButton stemsFilterButton;
    ToggleButton rootsFilterButton;
    public static ArrayList<PlantActivity> plantActivityArrayList = new ArrayList<PlantActivity>();
    private ListView listView;
    private String selectedFilter = "";
    private String currentSearchText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cameraButton = findViewById(R.id.cameraButton);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UploadingActivity.class);
                startActivity(intent);
            }
        });

        leavesFilterButton = findViewById(R.id.leavesFilterButton);
        leavesFilterButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if (stemsFilterButton.isChecked()){
                        stemsFilterButton.setChecked(false);
                    }
                    else if (flowersFilterButton.isChecked()){
                        flowersFilterButton.setChecked(false);
                    }
                    else if (rootsFilterButton.isChecked()){
                        rootsFilterButton.setChecked(false);
                    }
                    leavesTapped();
                }

                else {
                    PlantAdapter adapter = new PlantAdapter(getApplicationContext(), 0, plantActivityArrayList);
                    listView.setAdapter(adapter);
                }
            }
        });

        stemsFilterButton = findViewById(R.id.stemsFilterButton);
        stemsFilterButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if (leavesFilterButton.isChecked()){
                        leavesFilterButton.setChecked(false);
                    }
                    else if (flowersFilterButton.isChecked()){
                        flowersFilterButton.setChecked(false);
                    }
                    else if (rootsFilterButton.isChecked()){
                        rootsFilterButton.setChecked(false);
                    }
                    stemsTapped();
                }

                else {
                    PlantAdapter adapter = new PlantAdapter(getApplicationContext(), 0, plantActivityArrayList);
                    listView.setAdapter(adapter);
                }
            }
        });

        flowersFilterButton = findViewById(R.id.flowersFilterButton);
        flowersFilterButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if (leavesFilterButton.isChecked()){
                        leavesFilterButton.setChecked(false);
                    }
                    else if (stemsFilterButton.isChecked()){
                        stemsFilterButton.setChecked(false);
                    }
                    else if (rootsFilterButton.isChecked()){
                        rootsFilterButton.setChecked(false);
                    }
                    flowersTapped();
                }

                else {
                    PlantAdapter adapter = new PlantAdapter(getApplicationContext(), 0, plantActivityArrayList);
                    listView.setAdapter(adapter);
                }
            }
        });

        rootsFilterButton = findViewById(R.id.rootsFilterButton);
        rootsFilterButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if (leavesFilterButton.isChecked()){
                        leavesFilterButton.setChecked(false);
                    }
                    else if (stemsFilterButton.isChecked()){
                        stemsFilterButton.setChecked(false);
                    }
                    else if (flowersFilterButton.isChecked()){
                        flowersFilterButton.setChecked(false);
                    }
                    rootsTapped();
                }

                else {
                    PlantAdapter adapter = new PlantAdapter(getApplicationContext(), 0, plantActivityArrayList);
                    listView.setAdapter(adapter);
                }
            }
        });

        initSearchWidgets();
        setupData();
        setupList();
        plantOnClickListener();
    }

    private void initSearchWidgets(){
        SearchView searchView = (SearchView) findViewById(R.id.plantListSearchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                currentSearchText = s;
                ArrayList<PlantActivity> filteredPlants = new ArrayList<PlantActivity>();
                for (PlantActivity plant: plantActivityArrayList){
                    if (plant.getCommonName().toLowerCase().contains(s.toLowerCase())){
                        if (selectedFilter != ""){
                            if(plant.getPlantIdentifier().toLowerCase().contains(selectedFilter)){
                                filteredPlants.add(plant);
                            }
                        }
                        else{
                            filteredPlants.add(plant);
                        }
                    }
                }

                if (s != ""){
                    PlantAdapter adapter = new PlantAdapter(getApplicationContext(), 0, filteredPlants);
                    listView.setAdapter(adapter);
                }
                else{
                    PlantAdapter adapter = new PlantAdapter(getApplicationContext(), 0, filteredPlants);
                    listView.setAdapter(adapter);
                }

                return false;
            }
        });
    }

    private void setupData() {

        PlantActivity Lagundi = new PlantActivity(
                "0",
                "Lagundi",
                "Vitex Negundo",
                "Plant - Erect, branched shrub 2.5m high. \n\n" +
                        "The leaves are perfoliate(the stem passes through the leaf, the leaf surrounds the stem), decussate (the leaves form an x when looked from the above), and yellow-green. \n\n" +
                        "The leaflets are lanceloate (shaped like a spear), 4-10 cm long, hairy beneath and pointed at both ends. \n\n" +
                        "It has many flowes; the flowers are blue, 6-7 mm long.\n\n" +
                        "The fruit is a succulent drupe (tasty, fleshy fruit that contains a seed in the center), black when ripe, round and about 4 mm in diameter.\n\n",
                "The leaf decoction can be used for cleaning wounds and ulcers, helping mothers on lactating, stimulating menstruation, help with flatulence.\n\n" +
                        "Eating the seeds after boiling or drinking the water prevent the spread of toxins from poisonous bites of animals.\n\n" +
                        "Tincture of the root bark (dissolving the root bark in alcohol) is a tonic, can reduce fevers, relieve coughs caused by the common cold, bronchitis or other breathing illnesses. It is also good for rheumatism.\n\n" +
                        "Powdered flowers are administered for discharge of blood from stomach or intestines.\n\n" +
                        "Fresh fruit can cure headaches, buildup of mucus in sore or throat. It is a vermifuge (antiparasitic drugs that expel parasitic worms from the body) when dried.\n\n",
                "leaves" + "roots",
                R.mipmap.lagundi);

        plantActivityArrayList.add(Lagundi);

        PlantActivity Guava = new PlantActivity(
                "1",
                "Guava",
                "Psidium guajava",
                "A tropical tree typically farmed for its fruits. \n\n" +
                        "Native to tropical and subtropical areas worldwide. \n\n" +
                        "Its branchlets are quadrangular. \n\n" +
                        "Its flowers are 1 inch wide and is a four-petaled one.\n\n" +
                        "Leaves are oval/oblong that are about 7.6 centimeters long. Leaves grow in a manner that opposes each other.\n\n",
                "Guava is popular for its use as medicine for stomach problems like diarrhea, infections that result in blood in stool, stomach aches, and indigestion. \n\n" +
                        "Decoction from leaves used to treat wounds by soaking affected areas before bathing.\n\n" +
                        "Ointment extracted from the leaves of a Guava plant can be used to treat wounds as it has wound healing properties.\n\n" +
                        "Aqueous leaf extracts of Guava is an effective cough remedy. \n\n" +
                        "The fruit is rich in vitamins A and C as well as iron, phosphorus and calcium. The guava fruit contains more vitamin C than orange. \n\n"+
                        "Extract of leaves has a high antimicrobial property. ",
                "leaves",
                R.mipmap.guavaleaf);

        plantActivityArrayList.add(Guava);

        PlantActivity Gumamela = new PlantActivity(
                "2",
                "Gumamela",
                "Hibiscus",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit.\n\n"+
                        "Phasellus vitae felis non lacus rhoncus iaculis. Sed metus lorem, commodo vel est ut, egestas lobortis sem. \n\n" +
                        "Nunc vehicula commodo nisi vel gravida. Phasellus sed nulla dignissim urna cursus commodo non ac elit. \n\n" +
                        "Nulla nec lorem auctor tellus molestie tempus sed non lacus. \n\n" +
                        "Curabitur metus ligula, sollicitudin nec turpis sit amet, tempor semper mi. Sed pretium quam enim, eget convallis risus placerat eleifend. \n\n" +
                        "Fusce sit amet euismod ante. Suspendisse sodales sodales justo volutpat vestibulum. \n\n" +
                        "Nunc vehicula quis ex eu tempus. Vivamus accumsan lacus at viverra venenatis.",
                "Nullam vel nibh leo. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. \n\n" +
                        "Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. \n\n" +
                        "Quisque ut consectetur enim. Cras consectetur finibus enim, et accumsan orci suscipit id. \n\n" +
                        "Proin tempus arcu risus, sed feugiat lectus lacinia id. Proin sollicitudin arcu a tempus hendrerit. \n\n" +
                        "Fusce rutrum fermentum purus, at eleifend odio euismod non.",
                "flowers" + "stems",
                R.mipmap.gumamela);
        plantActivityArrayList.add(Gumamela);

        PlantActivity Ginger = new PlantActivity(
                "3",
                "Ginger",
                "Zingiber officinale",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit.\n\n"+
                        "Phasellus vitae felis non lacus rhoncus iaculis. Sed metus lorem, commodo vel est ut, egestas lobortis sem. \n\n" +
                        "Nunc vehicula commodo nisi vel gravida. Phasellus sed nulla dignissim urna cursus commodo non ac elit. \n\n" +
                        "Nulla nec lorem auctor tellus molestie tempus sed non lacus. \n\n" +
                        "Curabitur metus ligula, sollicitudin nec turpis sit amet, tempor semper mi. Sed pretium quam enim, eget convallis risus placerat eleifend. \n\n" +
                        "Fusce sit amet euismod ante. Suspendisse sodales sodales justo volutpat vestibulum. \n\n" +
                        "Nunc vehicula quis ex eu tempus. Vivamus accumsan lacus at viverra venenatis.",
                "Nullam vel nibh leo. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. \n\n" +
                        "Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. \n\n" +
                        "Quisque ut consectetur enim. Cras consectetur finibus enim, et accumsan orci suscipit id. \n\n" +
                        "Proin tempus arcu risus, sed feugiat lectus lacinia id. Proin sollicitudin arcu a tempus hendrerit. \n\n" +
                        "Fusce rutrum fermentum purus, at eleifend odio euismod non.",
                "roots",
                R.mipmap.ginger);
        plantActivityArrayList.add(Ginger);

        PlantActivity Eucalyptus = new PlantActivity(
                "4",
                "Eucalyptus",
                "Eucalyptus",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit.\n\n"+
                        "Phasellus vitae felis non lacus rhoncus iaculis. Sed metus lorem, commodo vel est ut, egestas lobortis sem. \n\n" +
                        "Nunc vehicula commodo nisi vel gravida. Phasellus sed nulla dignissim urna cursus commodo non ac elit. \n\n" +
                        "Nulla nec lorem auctor tellus molestie tempus sed non lacus. \n\n" +
                        "Curabitur metus ligula, sollicitudin nec turpis sit amet, tempor semper mi. Sed pretium quam enim, eget convallis risus placerat eleifend. \n\n" +
                        "Fusce sit amet euismod ante. Suspendisse sodales sodales justo volutpat vestibulum. \n\n" +
                        "Nunc vehicula quis ex eu tempus. Vivamus accumsan lacus at viverra venenatis.",
                "Nullam vel nibh leo. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. \n\n" +
                        "Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. \n\n" +
                        "Quisque ut consectetur enim. Cras consectetur finibus enim, et accumsan orci suscipit id. \n\n" +
                        "Proin tempus arcu risus, sed feugiat lectus lacinia id. Proin sollicitudin arcu a tempus hendrerit. \n\n" +
                        "Fusce rutrum fermentum purus, at eleifend odio euismod non.",
                "leaves",
                R.mipmap.eucalyptus);
        plantActivityArrayList.add(Eucalyptus);
    }

    private void setupList() {
        listView = (ListView) findViewById(R.id.plantListView);

        PlantAdapter adapter = new PlantAdapter(getApplicationContext(), 0, plantActivityArrayList);
        listView.setAdapter(adapter);
    }

    private void plantOnClickListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PlantActivity selectedPlant = (PlantActivity) (listView.getItemAtPosition(position));
                Intent showDetail = new Intent (getApplicationContext(), DetailedPlantActivity.class);
                showDetail.putExtra("id", selectedPlant.getId());
                startActivity(showDetail);
            }
        });
    }

    private void filterList(String status){
        selectedFilter = status;

        ArrayList<PlantActivity> filteredPlants = new ArrayList<PlantActivity>();

        for (PlantActivity plant: plantActivityArrayList){
            if(plant.getPlantIdentifier().toLowerCase().contains(status)){
                if (currentSearchText == ""){
                    filteredPlants.add(plant);
                }
                else{
                    if (plant.getCommonName().toLowerCase().contains(currentSearchText)){
                        filteredPlants.add(plant);
                    }
                }
            }
        }
        if (selectedFilter != ""){
            PlantAdapter adapter = new PlantAdapter(getApplicationContext(), 0, filteredPlants);
            listView.setAdapter(adapter);
        }

    }
    public void leavesTapped() {
        filterList("leaves");
    }

    public void stemsTapped() {
        filterList("stems");
    }

    public void flowersTapped() {
        filterList("flowers");
    }

    public void rootsTapped() {
        filterList("roots");
    }
}