package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    ImageView ingredientsIv;
    TextView ingredients_data,description_data, place_of_origin_data,also_known_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ingredientsIv = findViewById(R.id.image_iv);
        ingredients_data=(TextView)findViewById(R.id.ingredients_data);
        description_data=(TextView)findViewById(R.id.description_data);
        place_of_origin_data=(TextView)findViewById(R.id.place_of_origin_data);
        also_known_data=(TextView)findViewById(R.id.also_known_data);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }
        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }else if (position>DEFAULT_POSITION){
            String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
            String json = sandwiches[position];
            Sandwich sandwich = null;
            try {
                sandwich = JsonUtils.parseSandwichJson(json);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (sandwich == null) {
                closeOnError();
                return;
            }

            populateUI(sandwich);
        }
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        Picasso.with(getApplicationContext()).load(sandwich.getImage()).into(ingredientsIv);
        this.setTitle(sandwich.getMainName());
        ArrayList<String> AlsoKnownAsList = sandwich.getAlsoKnownAs();
        if (AlsoKnownAsList.size()>0){
            for(int i=0 ; i< AlsoKnownAsList.size() ; i++){
                also_known_data.append(AlsoKnownAsList.get(i) + ", ");
            }
        }else {
            also_known_data.setText("no also known as data");
        }
        if (!sandwich.getPlaceOfOrigin().equals("")){
            place_of_origin_data.append(sandwich.getPlaceOfOrigin());
        }else {
            place_of_origin_data.setText("no place exists");
        }
        if (!sandwich.getDescription().equals("")){
            description_data.append(sandwich.getDescription());
        }else {
            description_data.setText("no description exists");
        }
        ArrayList<String> IngredientsList = sandwich.getIngredients();
        if (IngredientsList.size()>0) {
            for (int i = 0; i < IngredientsList.size(); i++) {
                ingredients_data.append(IngredientsList.get(i) + ", ");
            }
        }else {
            ingredients_data.setText("no ingredients data");
        }
    }
}