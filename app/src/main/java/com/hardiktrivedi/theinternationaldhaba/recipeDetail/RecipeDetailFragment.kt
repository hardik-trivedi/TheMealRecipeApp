package com.hardiktrivedi.theinternationaldhaba.recipeDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.hardiktrivedi.theinternationaldhaba.R
import com.hardiktrivedi.theinternationaldhaba.data.Recipe
import com.hardiktrivedi.theinternationaldhaba.exception.NoInternetAvailableException
import com.hardiktrivedi.theinternationaldhaba.exception.SomethingWentWrongException
import com.hardiktrivedi.theinternationaldhaba.utility.extenstions.*
import com.hardiktrivedi.theinternationaldhaba.viewstate.ViewStateAwareFragment
import kotlinx.android.synthetic.main.recipe_detail_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass. It is used to show details of any [Recipe]
 *
 */
class RecipeDetailFragment : ViewStateAwareFragment() {

    override val viewModel: RecipeDetailViewModel by viewModel()

    companion object ArgumentParams {
        const val RECIPE = "recipe"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.recipe_detail_fragment, container, false)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                activity?.onBackPressed()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? AppCompatActivity)?.setSupportActionBar(toolbar)
        (activity as? AppCompatActivity)?.apply {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }
        fetchRecipeDetail()
    }

    /**
     * Fetches recipe detail either from local available instance or from Server.
     * A server call will only happen if no instance of [Recipe] is found when [Fragment] is instantiated.
     */
    private fun fetchRecipeDetail() {
        val arguments = requireArguments()
        val recipe =
            requireNotNull(arguments.getParcelable<Recipe>(RECIPE)) { "Recipe can not be null" }

        viewModel.run {
            fetchRecipeById(recipe = recipe)
            recipeDetail.observe(viewLifecycleOwner, Observer { showRecipeDetail(it) })
        }
    }

    /**
     * Displays fetched [Recipe].
     */
    private fun showRecipeDetail(recipe: Recipe) {
        with(recipe) {
            toolbar.title = recipeName
            recipeNameTextView.text = recipeName
            recipeCategoryTextView.text = category
            recipeImageView.loadUrl(imageUrl)
            ingredients?.forEach { ingredient ->
                ingredientsContainer.addView(getIngredientTextView()?.apply {
                    text = "${ingredient.first} ${ingredient.second}"
                })
            }
            cookingStepsTextView.text = cookingInstruction?.formatCookingInstruction()
        }
    }

    /**
     * Generates a chip view kind of [TextView] this is used to represents [Recipe.ingredients]
     *
     * @return TextView inflated [TextView]. Can be null.
     */
    private fun getIngredientTextView(): TextView? {
        return (LayoutInflater.from(activity).inflate(
            R.layout.ingredient_item, null
        ) as? TextView)
    }

    /**
     * Overriding [Observer] of type [NoInternetAvailableException]. View will provide it's implementation.
     *
     * @see [ViewStateAwareFragment]
     */
    override val networkErrorObserver = Observer<NoInternetAvailableException> {
        loaderView.showErrorWithAnimation(R.raw.no_internet, R.string.no_internet_available) {
            fetchRecipeDetail()
        }
    }

    /**
     * Overriding [Observer] of type [SomethingWentWrongException]. View will provide it's implementation.
     *
     * @see [ViewStateAwareFragment]
     */
    override val genericErrorObserver = Observer<SomethingWentWrongException> {
        loaderView.showErrorWithAnimation(R.raw.generic_error, R.string.something_went_wrong) {
            fetchRecipeDetail()
        }
    }

    /**
     * Overriding [Observer] of type [Boolean]. View will provide it's implementation.
     * If true progress needs to be displayed else hide the view.
     *
     * @see [ViewStateAwareFragment]
     */
    override val progressObserver = Observer<Boolean> { progressing ->
        with(loaderView) {
            if (progressing) {
                setLoopingAnimation(R.raw.recipe_loader)
                visible()
            } else {
                loaderView.gone()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.dispose()
    }
}
