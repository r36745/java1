package rosemak.recipestation;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by stevierose on 10/13/14.
 */
public class SpinnerRecipeAdapter extends BaseAdapter {

    private HashMap recipeData;
    private Activity activity;
    private Resources resources;
    private FamilyRecipe familyRecipe;
    private LayoutInflater inflater;
    private TextView spinnerRecipeName, spinnerRecipeCategory, spinnerRecipeRating;



    public SpinnerRecipeAdapter (Main mContext,int resource, HashMap recipeObjects, Resources resLocal) {

        recipeData = recipeObjects;
        activity = mContext;
        resources = resLocal;


        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return recipeData.size();
    }

    @Override
    public Object getItem(int i) {
        return recipeData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        View row = inflater.inflate(R.layout.spinner_reciperow, parent, false);
        familyRecipe = null;
        familyRecipe = (FamilyRecipe) recipeData.get(position);

        spinnerRecipeName     = (TextView) row.findViewById(R.id.spinner_recipeName);
        spinnerRecipeCategory = (TextView) row.findViewById(R.id.spinner_recipeCategory);
        spinnerRecipeRating   = (TextView) row.findViewById(R.id.spinner_recipeRating);

        if(position != 0 ) {

            spinnerRecipeName.setText(familyRecipe.getRecipeName());
             spinnerRecipeCategory.setText(familyRecipe.getCategory());
             spinnerRecipeRating.setText(familyRecipe.getRecipeRating());



        } else {

            spinnerRecipeName.setText("Favorite Recipes");

        }

        return row;
    }
}
