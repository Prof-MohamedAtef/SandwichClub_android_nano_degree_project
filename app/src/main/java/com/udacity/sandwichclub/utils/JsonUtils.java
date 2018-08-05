package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class JsonUtils {

    private static Sandwich sandwich;
    private static JSONObject SandwichFormattedJson ;
    private static JSONObject Name;
    private static String mainName_STR;
    private static ArrayList<String> alsoKnownAs_List;
    private static ArrayList<String> ingredients_List;
    private static String placeOfOrigin_STR;
    private static String description_STR;
    private static String image_STR;
    private static JSONArray AlsoKnownAsJsonAray;
    private static String oneAlsoKnownAsJson_STR;
    private static JSONArray IngredientsJsonArray;
    private static String ingredients_STR;

    public static Sandwich parseSandwichJson(String json) throws JSONException {
        alsoKnownAs_List = new ArrayList<>();
        ingredients_List=new ArrayList<>();
        SandwichFormattedJson = new JSONObject(json);
        Name = SandwichFormattedJson.getJSONObject("name");
        mainName_STR = Name.getString("mainName");
        placeOfOrigin_STR = SandwichFormattedJson.getString("placeOfOrigin");
        description_STR = SandwichFormattedJson.getString("description");
        image_STR = SandwichFormattedJson.getString("image");
        AlsoKnownAsJsonAray = Name.getJSONArray("alsoKnownAs");
        if (AlsoKnownAsJsonAray.length() > 0) {
            for (int x = 0; x < AlsoKnownAsJsonAray.length(); x++) {
                oneAlsoKnownAsJson_STR = AlsoKnownAsJsonAray.getString(x);
                alsoKnownAs_List.add(oneAlsoKnownAsJson_STR);
            }
        }
        IngredientsJsonArray = SandwichFormattedJson.getJSONArray("ingredients");
        if (IngredientsJsonArray.length() > 0) {
            for (int z = 0; z < IngredientsJsonArray.length(); z++) {
                ingredients_STR = IngredientsJsonArray.getString(z);
                ingredients_List.add(ingredients_STR);
            }
        }
        sandwich = new Sandwich(mainName_STR, alsoKnownAs_List, placeOfOrigin_STR, description_STR, image_STR, ingredients_List);
        return sandwich;
    }
}