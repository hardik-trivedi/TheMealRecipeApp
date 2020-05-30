package com.hardiktrivedi.theinternationaldhaba

import com.google.gson.Gson
import com.hardiktrivedi.theinternationaldhaba.data.Recipe
import com.hardiktrivedi.theinternationaldhaba.repository.data.*

private val recipeJson = """
    {
  "meals": [{
    "idMeal": "52942",
    "strMeal": "Roast fennel and aubergine paella",
    "strDrinkAlternate": null,
    "strCategory": "Vegan",
    "strArea": "Spanish",
    "strInstructions": "1 Put the fennel, aubergine, pepper and courgette in a roasting tray. Add a glug of olive oil, season with salt and pepper and toss around to coat the veggies in the oil. Roast in the oven for 20 minutes, turning a couple of times until the veg are pretty much cooked through and turning golden.\r\n\r\n2 Meanwhile, heat a paella pan or large frying pan over a low\u2013 medium heat and add a glug of olive oil. Saut\u00e9 the onion for 8\u201310 minutes until softened. Increase the heat to medium and stir in the rice, paprika and saffron. Cook for around 1 minute to start toasting the rice, then add the white wine. Reduce by about half before stirring in two-thirds of the stock. Reduce to a simmer and cook for 10 minutes without a lid, stirring a couple of times.\r\n\r\n3 Stir in the peas, add some seasoning, then gently mix in the roasted veg. Pour over the remaining stock, arrange the lemon wedges on top and cover with a lid or some aluminium foil. Cook for a further 10 minutes.\r\n\r\n4 To ensure you get the classic layer of toasted rice at the bottom of the pan, increase the heat to high until you hear a slight crackle. Remove from the heat and sit for 5 minutes before sprinkling over the parsley and serving.",
    "strMealThumb": "https:\/\/www.themealdb.com\/images\/media\/meals\/1520081754.jpg",
    "strTags": "Vegan,Paella",
    "strYoutube": "https:\/\/www.youtube.com\/watch?v=H5SmjR-fxUs",
    "strIngredient1": "Baby Aubergine",
    "strIngredient2": "Fennel",
    "strIngredient3": "Red Pepper",
    "strIngredient4": "Courgettes",
    "strIngredient5": "Onion",
    "strIngredient6": "Paella Rice",
    "strIngredient7": "Paprika",
    "strIngredient8": "Saffron",
    "strIngredient9": "White Wine",
    "strIngredient10": "Vegetable Stock",
    "strIngredient11": "Frozen Peas",
    "strIngredient12": "Lemon",
    "strIngredient13": "Parsley",
    "strIngredient14": "Salt",
    "strIngredient15": "Black Pepper",
    "strIngredient16": "",
    "strIngredient17": "",
    "strIngredient18": "",
    "strIngredient19": "",
    "strIngredient20": "",
    "strMeasure1": "6 small",
    "strMeasure2": "4 small",
    "strMeasure3": "1 thinly sliced",
    "strMeasure4": "1 medium",
    "strMeasure5": "1 finely chopped ",
    "strMeasure6": "300g",
    "strMeasure7": "1 tsp ",
    "strMeasure8": "pinch",
    "strMeasure9": "200ml",
    "strMeasure10": "700ml",
    "strMeasure11": "100g ",
    "strMeasure12": "1 chopped",
    "strMeasure13": "Handful",
    "strMeasure14": "pinch",
    "strMeasure15": "pinch",
    "strMeasure16": "",
    "strMeasure17": "",
    "strMeasure18": "",
    "strMeasure19": "",
    "strMeasure20": "",
    "strSource": "https:\/\/www.homestylemag.co.uk\/recipe\/517\/main-courses\/roast-fennel-and-aubergine-paella",
    "dateModified": null
  }]
} 
""".trimIndent()
val recipeListResponse = Gson().fromJson(recipeJson, RecipeListResponse::class.java)

val recipeOfTheDayWithNoMeals = RecipeListResponse(emptyList())


val recipe = Recipe(
    "52942", "Roast fennel and aubergine paella", "Vegan",
    "Spanish", "https://www.themealdb.com/images/media/meals/1520081754.jpg",
    listOf(
        Pair("6 small", "Baby Aubergine"),
        Pair("4 small", "Fennel"),
        Pair("1 thinly sliced", "Red Pepper"),
        Pair("1 medium", "Courgettes"),
        Pair("1 finely chopped ", "Onion"),
        Pair("300g", "Paella Rice"),
        Pair("1 tsp ", "Paprika"),
        Pair("pinch", "Saffron"),
        Pair("200ml", "White Wine"),
        Pair("700ml", "Vegetable Stock"),
        Pair("100g ", "Frozen Peas"),
        Pair("1 chopped", "Lemon"),
        Pair("Handful", "Parsley"),
        Pair("pinch", "Salt"),
        Pair("pinch", "Black Pepper")
    ),
    "1 Put the fennel, aubergine, pepper and courgette in a roasting tray. Add a glug of olive oil, season with salt and pepper and toss around to coat the veggies in the oil. Roast in the oven for 20 minutes, turning a couple of times until the veg are pretty much cooked through and turning golden.\r\n\r\n2 Meanwhile, heat a paella pan or large frying pan over a low\u2013 medium heat and add a glug of olive oil. Saut\u00e9 the onion for 8\u201310 minutes until softened. Increase the heat to medium and stir in the rice, paprika and saffron. Cook for around 1 minute to start toasting the rice, then add the white wine. Reduce by about half before stirring in two-thirds of the stock. Reduce to a simmer and cook for 10 minutes without a lid, stirring a couple of times.\r\n\r\n3 Stir in the peas, add some seasoning, then gently mix in the roasted veg. Pour over the remaining stock, arrange the lemon wedges on top and cover with a lid or some aluminium foil. Cook for a further 10 minutes.\r\n\r\n4 To ensure you get the classic layer of toasted rice at the bottom of the pan, increase the heat to high until you hear a slight crackle. Remove from the heat and sit for 5 minutes before sprinkling over the parsley and serving.",
    "https://www.youtube.com/watch?v=H5SmjR-fxUs"
)

val latestRecipesResponse = listOf(
    recipe,
    recipe,
    recipe,
    recipe,
    recipe
)

val recipeWithBasicInfo = listOf(
    Recipe(
        id = "1",
        recipeName = "Dessert",
        imageUrl = "https://www.themealdb.com/images/category/dessert.png"
    ),
    Recipe(
        id = "2",
        recipeName = "Soup",
        imageUrl = "https://www.themealdb.com/images/category/soup.png"
    ),
    Recipe(
        id = "3",
        recipeName = "Salad",
        imageUrl = "https://www.themealdb.com/images/category/salad.png"
    )
)