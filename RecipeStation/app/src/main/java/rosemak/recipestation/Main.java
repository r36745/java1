package rosemak.recipestation;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.HashMap;
//Steven A. Roseman II
//Java I
//10/11/14



public class Main extends Activity {

    Spinner recipeSpinner;
    Resources res;
    SpinnerAdapter spinnerAdapter;
    ListViewRecipeAdapter listViewAdapter;
    ListView recipeListView;
    public HashMap<Integer, FamilyRecipe> favRecipes = new HashMap<Integer, FamilyRecipe>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            recipeSpinner = (Spinner) findViewById(R.id.recipe_spinner);
            res = getResources();

            recipeData();
            final TextView sFoodName = (TextView)findViewById(R.id.spinner_foodName);
            final TextView sCategoryName = (TextView)findViewById(R.id.spinner_foodType);
            final TextView sRecipeName = (TextView)findViewById(R.id.spinner_tasteRating);

            spinnerAdapter = new SpinnerRecipeAdapter(this, R.layout.spinner_reciperow,favRecipes, res);

            recipeSpinner.setAdapter(spinnerAdapter);

            recipeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View v, int position, long id) {
                    String rName = ((TextView)v.findViewById(R.id.spinner_recipeName)).getText().toString();
                    String rCategory = ((TextView)v.findViewById(R.id.spinner_recipeCategory)).getText().toString();
                    String rRating = ((TextView)v.findViewById(R.id.spinner_recipeRating)).getText().toString();
                    Log.i("TAG", "rating = " + rRating);

                    sFoodName.setText(rName);
                    sCategoryName.setText(rCategory);
                    sRecipeName.setText(rRating);


                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });



        }else {

            recipeListView = (ListView) findViewById(R.id.recipe_listView);
            res = getResources();
            recipeData();
            listViewAdapter = new ListViewRecipeAdapter(this,R.layout.listview_reciperow, favRecipes, res);
            recipeListView.setAdapter(listViewAdapter);
            final TextView detailedTextView = (TextView) findViewById(R.id.detailNameTextView);
            final TextView detailCategoryTextView = (TextView) findViewById(R.id.detailCategoryTextView);
            final TextView detailRatingTextView = (TextView) findViewById(R.id.detailRatingTextView);

            recipeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View v, int position, long id) {

                    String rName = ((TextView)v.findViewById(R.id.listView_recipeName)).getText().toString();
                    String rCategory = ((TextView)v.findViewById(R.id.listView_recipeCategory)).getText().toString();
                    String rRating = ((TextView)v.findViewById(R.id.listView_recipeRating)).getText().toString();

                    detailedTextView.setText(rName);
                    detailCategoryTextView.setText(rCategory);
                    detailRatingTextView.setText(rRating);



                }
            });




        }


    }



    private void recipeData() {

        favRecipes.put(1, new FamilyRecipe("Jambalaya", "Dinner", "Four Plates"));
        favRecipes.put(2, new FamilyRecipe("Eggs Benedict", "Breakfast", "Two Plates"));
        favRecipes.put(3, new FamilyRecipe("Beer Can Chicken", "Football Munchies", "Five Plates"));
        favRecipes.put(4, new FamilyRecipe("Lobster Bisque", "Comfort", "Five bowls"));
        favRecipes.put(5, new FamilyRecipe("Curry Chicken", "Comfort", "Four Bowls"));
        favRecipes.put(6, new FamilyRecipe("Jerk Chicken", "Dinner", " Four Bowls"));


    }


}
