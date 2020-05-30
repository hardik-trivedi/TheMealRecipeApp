package com.hardiktrivedi.theinternationaldhaba

import com.hardiktrivedi.theinternationaldhaba.utility.extenstions.formatCookingInstruction
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class StringExtensionKtTest {
    @Nested
    @DisplayName("Given String is not null")
    inner class StringIsNotNull {


        @Nested
        @DisplayName("When string has statements which ends with full stop")
        inner class EndsWithFullStop() {

            private lateinit var cookingInstructions: String

            @BeforeEach
            fun setUp() {
                cookingInstructions =
                    "Bring a large pot of water to a boil. Add kosher salt to the boiling water, then add the pasta. Cook according to the package instructions, about 9 minutes. In a large skillet over medium-high heat, add the olive oil and heat until the oil starts to shimmer. Add the garlic and cook, stirring, until fragrant, 1 to 2 minutes. Add the chopped tomatoes, red chile flakes, Italian seasoning and salt and pepper to taste. Bring to a boil and cook for 5 minutes. Remove from the heat and add the chopped basil. Drain the pasta and add it to the sauce. Garnish with Parmigiano-Reggiano flakes and more basil and serve warm."
            }

            @Test
            @DisplayName("Then adds numbering and new line character after each statement.")
            fun verifyLoggedIn() {
                assertEquals(
                    """1. Bring a large pot of water to a boil.

2. Add kosher salt to the boiling water, then add the pasta.

3. Cook according to the package instructions, about 9 minutes.

4. In a large skillet over medium-high heat, add the olive oil and heat until the oil starts to shimmer.

5. Add the garlic and cook, stirring, until fragrant, 1 to 2 minutes.

6. Add the chopped tomatoes, red chile flakes, Italian seasoning and salt and pepper to taste.

7. Bring to a boil and cook for 5 minutes.

8. Remove from the heat and add the chopped basil.

9. Drain the pasta and add it to the sauce.

10. Garnish with Parmigiano-Reggiano flakes and more basil and serve warm.
""",
                    cookingInstructions.formatCookingInstruction()
                )
            }
        }

        @Test
        @DisplayName("When string has windows compatible new line character, Then adds numbering and new line character after each statement.")
        fun windowsCompatibleNewLineCharacter_AddsNumberingAndNewLineCharacter() {
            val cookingInstructions =
                "Bring a large pot of water to a boil. Add kosher salt to the boiling water, then add the pasta.\r\nCook according to the package instructions, about 9 minutes. In a large skillet over medium-high heat, add the olive oil and heat until the oil starts to shimmer. Add the garlic and cook, stirring, until fragrant, 1 to 2 minutes. Add the chopped tomatoes, red chile flakes, Italian seasoning and salt and pepper to taste. Bring to a boil and cook for 5 minutes. Remove from the heat and add the chopped basil. Drain the pasta and add it to the sauce. Garnish with Parmigiano-Reggiano flakes and more basil and serve warm."
            assertEquals(
                """1. Bring a large pot of water to a boil.

2. Add kosher salt to the boiling water, then add the pasta.

3. Cook according to the package instructions, about 9 minutes.

4. In a large skillet over medium-high heat, add the olive oil and heat until the oil starts to shimmer.

5. Add the garlic and cook, stirring, until fragrant, 1 to 2 minutes.

6. Add the chopped tomatoes, red chile flakes, Italian seasoning and salt and pepper to taste.

7. Bring to a boil and cook for 5 minutes.

8. Remove from the heat and add the chopped basil.

9. Drain the pasta and add it to the sauce.

10. Garnish with Parmigiano-Reggiano flakes and more basil and serve warm.
""",
                cookingInstructions.formatCookingInstruction()
            )
        }

        @Test
        @DisplayName("When string has multiple windows compatible new line character, Then adds numbering and new line character after each statement.")
        fun multipleWindowsCompatibleNewLineCharacter_AddsNumberingAndNewLineCharacter() {
            val cookingInstructions =
                "Bring a large pot of water to a boil. Add kosher salt to the boiling water, then add the pasta.\r\n\r\nCook according to the package instructions, about 9 minutes. In a large skillet over medium-high heat, add the olive oil and heat until the oil starts to shimmer. Add the garlic and cook, stirring, until fragrant, 1 to 2 minutes. Add the chopped tomatoes, red chile flakes, Italian seasoning and salt and pepper to taste. Bring to a boil and cook for 5 minutes. Remove from the heat and add the chopped basil. Drain the pasta and add it to the sauce. Garnish with Parmigiano-Reggiano flakes and more basil and serve warm."
            assertEquals(
                """1. Bring a large pot of water to a boil.

2. Add kosher salt to the boiling water, then add the pasta.

3. Cook according to the package instructions, about 9 minutes.

4. In a large skillet over medium-high heat, add the olive oil and heat until the oil starts to shimmer.

5. Add the garlic and cook, stirring, until fragrant, 1 to 2 minutes.

6. Add the chopped tomatoes, red chile flakes, Italian seasoning and salt and pepper to taste.

7. Bring to a boil and cook for 5 minutes.

8. Remove from the heat and add the chopped basil.

9. Drain the pasta and add it to the sauce.

10. Garnish with Parmigiano-Reggiano flakes and more basil and serve warm.
""",
                cookingInstructions.formatCookingInstruction()
            )
        }
    }
}

