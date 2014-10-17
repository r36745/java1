package rosemak.recipestation;

/**
 * Created by stevierose on 10/11/14.
 */
public class FamilyRecipe {

    private String recipeName;
    private String recipeRating;
    private String category;


    public FamilyRecipe (String _recipeName, String _category, String _recipeRating) {

        recipeName = _recipeName;
        category = _category;
        recipeRating = _recipeRating;


    }


    public String getRecipeName() {return this.recipeName;}
    public String getCategory() {return this.category;}
    public String getRecipeRating() {return this.recipeRating;}



    public void setRecipeName(String _recipeName) {this.recipeName = _recipeName;}
    public void setCategory(String _category) {this.category = _category;}
    public void setRecipeRating(String _recipeRating) {this.recipeRating = _recipeRating;}

}
