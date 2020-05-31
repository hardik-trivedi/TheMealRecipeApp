package com.hardiktrivedi.theinternationaldhaba.recipeList

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.hardiktrivedi.theinternationaldhaba.R
import com.hardiktrivedi.theinternationaldhaba.data.Recipe
import com.hardiktrivedi.theinternationaldhaba.exception.NoInternetAvailableException
import com.hardiktrivedi.theinternationaldhaba.exception.SomethingWentWrongException
import com.hardiktrivedi.theinternationaldhaba.recipeDetail.RecipeDetailFragment
import com.hardiktrivedi.theinternationaldhaba.utility.extenstions.gone
import com.hardiktrivedi.theinternationaldhaba.utility.extenstions.setLoopingAnimation
import com.hardiktrivedi.theinternationaldhaba.utility.extenstions.showErrorWithAnimation
import com.hardiktrivedi.theinternationaldhaba.utility.extenstions.visible
import com.hardiktrivedi.theinternationaldhaba.viewstate.ViewStateAwareFragment
import kotlinx.android.synthetic.main.fragment_recipe_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass. It is used to show list of [Recipe]
 *
 */
class RecipeListFragment : ViewStateAwareFragment() {

    override val viewModel: RecipeListViewModel by viewModel()

    private val args: RecipeListFragmentArgs by navArgs()

    private val recipeObserver = Observer<List<Recipe>> { recipes ->
        searchResultRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = ListRecipeAdapter(recipes) {
                val bundle = bundleOf(
                    RecipeDetailFragment.RECIPE to it
                )
                findNavController()
                    .navigate(R.id.action_recipeListFragment_to_recipeDetailFragment, bundle)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe_list, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_list, menu)
        (menu.findItem(R.id.search).actionView as? SearchView)?.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    showRecipes(it)
                } ?: Toast.makeText(
                    activity,
                    "Sorry can not perform search",
                    Toast.LENGTH_LONG
                ).show()

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? AppCompatActivity)?.setSupportActionBar(toolbar)
    }

    private fun showRecipes(recipeName: String) {
        viewModel.run {
            getRecipeByName
                .observe(viewLifecycleOwner, recipeObserver)
            getRecipeByName(recipeName)
        }
    }

    /**
     * Overriding [Observer] of type [NoInternetAvailableException]. View will provide it's implementation.
     *
     * @see [ViewStateAwareFragment]
     */
    override val networkErrorObserver = Observer<NoInternetAvailableException> {
        loaderView.showErrorWithAnimation(R.raw.no_internet, R.string.no_internet_available) {

        }
    }

    /**
     * Overriding [Observer] of type [SomethingWentWrongException]. View will provide it's implementation.
     *
     * @see [ViewStateAwareFragment]
     */
    override val genericErrorObserver = Observer<SomethingWentWrongException> {
        loaderView.showErrorWithAnimation(R.raw.generic_error, R.string.something_went_wrong) {

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
                searchResultRecyclerView.gone()
            } else {
                searchResultRecyclerView.visible()
                loaderView.gone()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.dispose()
    }
}
