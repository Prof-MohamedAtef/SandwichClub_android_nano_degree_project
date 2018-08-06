package com.udacity.SandwichClub.utils;

import com.udacity.SandwichClub.model.Sandwich;
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

    private static final String Key_Name= "name";
    private static final String Key_MainName= "mainName";
    private static final String Key_PlaceOFOrigin= "placeOfOrigin";
    private static final String Key_Description= "description";
    private static final String Key_Image= "image";
    private static final String Key_AlsoKnownAs= "alsoKnownAs";
    private static final String Key_Ingredients= "ingredients";

    public static Sandwich parseSandwichJson(String json) throws JSONException {
        alsoKnownAs_List = new ArrayList<>();
        ingredients_List=new ArrayList<>();
        SandwichFormattedJson = new JSONObject(json);
        Name = SandwichFormattedJson.optJSONObject(Key_Name);
        mainName_STR = Name.optString(Key_MainName);
        placeOfOrigin_STR = SandwichFormattedJson.optString(Key_PlaceOFOrigin);
        description_STR = SandwichFormattedJson.optString(Key_Description);
        image_STR = SandwichFormattedJson.optString(Key_Image);
        AlsoKnownAsJsonAray = Name.optJSONArray(Key_AlsoKnownAs);
        if (AlsoKnownAsJsonAray.length() > 0) {
            for (int x = 0; x < AlsoKnownAsJsonAray.length(); x++) {
                oneAlsoKnownAsJson_STR = AlsoKnownAsJsonAray.optString(x);
                alsoKnownAs_List.add(oneAlsoKnownAsJson_STR);
            }
        }
        IngredientsJsonArray = SandwichFormattedJson.optJSONArray(Key_Ingredients);
        if (IngredientsJsonArray.length() > 0) {
            for (int z = 0; z < IngredientsJsonArray.length(); z++) {
                ingredients_STR = IngredientsJsonArray.optString(z);
                ingredients_List.add(ingredients_STR);
            }
        }
        sandwich = new Sandwich(mainName_STR, alsoKnownAs_List, placeOfOrigin_STR, description_STR, image_STR, ingredients_List);
        return sandwich;
    }
}