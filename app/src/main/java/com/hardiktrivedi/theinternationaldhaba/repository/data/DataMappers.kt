package com.hardiktrivedi.theinternationaldhaba.repository.data

import com.hardiktrivedi.theinternationaldhaba.data.Recipe

/**
 * Extension function over class [Meal] to convert it into type [Recipe]
 *
 * @return Recipe after mapping
 */
fun Meal.mapToRecipe(): Recipe {
    return Recipe(
        id = idMeal,
        recipeName = strMeal,
        category = strCategory,
        region = strArea,
        imageUrl = strMealThumb,
        ingredients = getIngredients(this),
        cookingInstruction = strInstructions,
        videoUrl = strYoutube
    )
}

/**
 * Maps [Meal.strMeasure1] to [Meal.strIngredient1]
 * There are total twenty such properties for class [Meal]. This function maps each one of them to it's corresponding one.
 *
 * @return List of Pair<String, String>
 */
private fun getIngredients(meal: Meal): MutableList<Pair<String, String>> {
    val ingredients = mutableListOf<Pair<String, String>>()
    ingredients.let {
        addIngredientIfNotNull(it, meal.strMeasure1, meal.strIngredient1)
        addIngredientIfNotNull(it, meal.strMeasure2, meal.strIngredient2)
        addIngredientIfNotNull(it, meal.strMeasure3, meal.strIngredient3)
        addIngredientIfNotNull(it, meal.strMeasure4, meal.strIngredient4)
        addIngredientIfNotNull(it, meal.strMeasure5, meal.strIngredient5)
        addIngredientIfNotNull(it, meal.strMeasure6, meal.strIngredient6)
        addIngredientIfNotNull(it, meal.strMeasure7, meal.strIngredient7)
        addIngredientIfNotNull(it, meal.strMeasure8, meal.strIngredient8)
        addIngredientIfNotNull(it, meal.strMeasure9, meal.strIngredient9)
        addIngredientIfNotNull(it, meal.strMeasure10, meal.strIngredient10)
        addIngredientIfNotNull(it, meal.strMeasure11, meal.strIngredient11)
        addIngredientIfNotNull(it, meal.strMeasure12, meal.strIngredient12)
        addIngredientIfNotNull(it, meal.strMeasure13, meal.strIngredient13)
        addIngredientIfNotNull(it, meal.strMeasure14, meal.strIngredient14)
        addIngredientIfNotNull(it, meal.strMeasure15, meal.strIngredient15)
        addIngredientIfNotNull(it, meal.strMeasure16, meal.strIngredient16)
        addIngredientIfNotNull(it, meal.strMeasure17, meal.strIngredient17)
        addIngredientIfNotNull(it, meal.strMeasure18, meal.strIngredient18)
        addIngredientIfNotNull(it, meal.strMeasure19, meal.strIngredient19)
        addIngredientIfNotNull(it, meal.strMeasure20, meal.strIngredient20)
    }

    return ingredients
}

/**
 * Extension function over class [RecipeBasicInfo] to convert it into type [Recipe]
 *
 * @return Recipe after mapping
 */
fun RecipeBasicInfo.mapToRecipe(): Recipe {
    return Recipe(id = idMeal, recipeName = strMeal, imageUrl = strMealThumb)
}

/**
 * Add it to list only if it's null. List is passed by reference hence can be updated from another function.
 */
private fun addIngredientIfNotNull(
    ingredients: MutableList<Pair<String, String>>,
    measure: String?,
    ingredient: String?
) {
    getIngredientsWithMeasure(ingredient = ingredient, measure = measure)?.let {
        ingredients.add(it)
    }
}

/**
 * Convert measure and ingredients into type Pair of both are non null and not blank.
 *
 * @param measure value of measure of type [String]?. Can be null
 * @param ingredient value of ingredient of type [String]?. Can be null
 *
 * @return Pair<String, String>? Pair if both params are valid else null
 */
private fun getIngredientsWithMeasure(
    measure: String?,
    ingredient: String?
): Pair<String, String>? {
    return if (!ingredient.isNullOrBlank() && !measure.isNullOrBlank()) {
        Pair(measure, ingredient)
    } else {
        null
    }
}
