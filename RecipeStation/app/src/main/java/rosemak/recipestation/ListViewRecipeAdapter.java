package rosemak.recipestation;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by stevierose on 10/14/14.
 */
public class ListViewRecipeAdapter extends BaseAdapter{

    private HashMap recipeData;
    private Activity activity;
    private Resources resources;
    private FamilyRecipe familyRecipe;
    private LayoutInflater inflater;


    public ListViewRecipeAdapter(Main mContext,int resource, HashMap recipeObjects, Resources resLocal) {

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
    public Object getItem(int position) {
        return recipeData.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {

        View row = inflater.inflate(R.layout.listview_reciperow, parent, false);

        familyRecipe = null;
        familyRecipe = (FamilyRecipe) recipeData.get(position);

        TextView listViewRecipeName = (TextView) row.findViewById(R.id.listView_recipeName);
        TextView listViewRecipeCategory = (TextView) row.findViewById(R.id.listView_recipeCategory);
        TextView listViewRecipeRating = (TextView) row.findViewById(R.id.listView_recipeRating);


        if (position == 0) {

            listViewRecipeName.setText("Favorite Recipes");
            listViewRecipeName.setTextColor(Color.RED);
        } else {

            listViewRecipeName.setText(familyRecipe.getRecipeName());
            listViewRecipeCategory.setText(familyRecipe.getCategory());
            listViewRecipeRating.setText(familyRecipe.getRecipeRating());

        }

        return row;
    }
}
