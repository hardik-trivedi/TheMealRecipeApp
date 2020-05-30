package com.hardiktrivedi.theinternationaldhaba.recipeList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.hardiktrivedi.theinternationaldhaba.R
import com.hardiktrivedi.theinternationaldhaba.data.Recipe

/**
 * A recycler view adapter to display all recipe in [RecyclerView]
 *
 * @param items List of type [Recipe]
 * @param clickListener A function which can take parameter of type [Recipe] and returns nothing.
 * This servers as implementation of click listener which is provided from the view class
 * i.e. [Fragment] or [Activity]
 */
class ListRecipeAdapter(
    private val items: List<Recipe>,
    private val clickListener: (Recipe) -> Unit
) :
    RecyclerView.Adapter<ListRecipeAdapter.RecipeViewHolder>() {

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recipe_list_item, parent, false)
        // set the view's size, margins, paddings and layout parameters
        return RecipeViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        (holder as? RecipeViewHolder)?.bindRecipe(items[position])
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = items.size

    /**
     * Simple sub class of [RecyclerView.ViewHolder] to hold recipe data
     */
    inner class RecipeViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val recipeImageView: ImageView = view.findViewById(R.id.recipeImageView)
        private val recipeNameTextView: TextView = view.findViewById(R.id.recipeNameTextView)
        private val recipeInformation: TextView = view.findViewById(R.id.recipeInformation)
        /**
         * Binds instance of [Recipe] with respective view elements
         */
        fun bindRecipe(recipe: Recipe) {
            with(recipe) {
                recipeNameTextView.text = recipeName
                recipeInformation.text = category
                Glide.with(view.context).load(imageUrl).transform(
                    CenterCrop(),
                    RoundedCorners(24)
                )
                    .into(recipeImageView)
            }

            view.setOnClickListener {
                clickListener.invoke(recipe)
            }
        }
    }
}
