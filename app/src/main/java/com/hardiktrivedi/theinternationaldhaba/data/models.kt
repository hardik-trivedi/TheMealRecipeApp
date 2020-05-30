package com.hardiktrivedi.theinternationaldhaba.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * A class which represents the entity Recipe. This is used to hold the final recipe data after
 * removing noise from raw data.
 *
 * @param id unique identifier of the recipe
 * @param recipeName Name to be displayed
 * @param category Category's name to be displayed. Can be null.
 * @param region Region's name to be displayed. Can be null.
 * @param imageUrl A https url which holds path to thumbnail of recipe on server
 * @param ingredients List of Pair which holds ingredients. Can be null.
 * @param cookingInstruction A step by step cooking instructions. Can be null.
 * @param videoUrl Video URL of youtube. Can be null.
 */
@Parcelize
data class Recipe(
    val id: String,
    val recipeName: String,
    val category: String? = null,
    val region: String? = null,
    val imageUrl: String,
    val ingredients: List<Pair<String, String>>? = null,
    val cookingInstruction: String? = null,
    val videoUrl: String? = null
) : Parcelable
