package com.baje.cookbook

import com.auth0.spring.security.api.authentication.AuthenticationJsonWebToken
import com.baje.cookbook.models.CookBookUser
import com.baje.cookbook.models.Ingredient
import com.baje.cookbook.models.Recipe
import com.baje.cookbook.models.Section
import com.baje.cookbook.models.SectionStep
import com.baje.cookbook.repository.CookBookUserRepository
import com.baje.cookbook.repository.RecipeRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import com.baje.cookbook.restmodels.request.Recipe as RESTRecipe

@RestController
class YoloController {
    @Autowired
    lateinit var cookBookUserRepository: CookBookUserRepository

    @Autowired
    lateinit var recipeRepository: RecipeRepository

    val logger: Logger = LoggerFactory.getLogger(YoloController::class.java)

    @GetMapping("yolo")
    fun yolo(): String = "yolo"

    @GetMapping("yolo-secured")
    fun yoloSecuredGet(jwt: AuthenticationJsonWebToken): String {
        val principal = jwt.principal // "auth0|jfjfjfj"
        logger.info("User $principal is requesting the endpoint: yolo-secured")
        return "yolo-secured"
    }

    @PostMapping("yolo-secured")
    fun yoloSecuredPost(@RequestBody map: Map<String, String>): String {
        println("received the following body: $map")
        return "ok, mapped received"
    }

    @GetMapping("user")
    fun user(): List<CookBookUser> = cookBookUserRepository.findAll()

    @GetMapping("recipes")
    fun getRecipes(jwt: AuthenticationJsonWebToken): List<Recipe> {
        logger.info("Fetching user with external id ${jwt.principal}, for getRecipes")
        val loggedInUser = cookBookUserRepository.findByExternalUserProviderId(jwt.principal.toString())?.copy(recipes = emptyList())
                ?: throw UsernameNotFoundException(jwt.principal.toString())

        return recipeRepository.findAllByOwnerId(loggedInUser.id!!)
    }

    @PostMapping("recipe")
    fun createRecipe(jwt: AuthenticationJsonWebToken, @RequestBody recipe: RESTRecipe) {
        logger.info("Fetching user with external id ${jwt.principal}")
        val loggedInUser = cookBookUserRepository.findByExternalUserProviderId(jwt.principal.toString())?.copy(recipes = emptyList())
                ?: throw UsernameNotFoundException(jwt.principal.toString())

        // TODO: Rydde opp i denne stygge snutten her...
        val backendRecipe = recipe.let { restRecipe ->
            Recipe(
                    name = restRecipe.name,
                    activeTimeMax = restRecipe.activeTimeMax,
                    activeTimeMin = restRecipe.activeTimeMin,
                    intro = restRecipe.intro,
                    totalTimeMax = restRecipe.totalTimeMax,
                    totalTimeMin = restRecipe.totalTimeMin,
                    baseUnit = restRecipe.baseUnit,
                    owner = loggedInUser,
                    sections = emptyList(),
                    id = null
            ).let { backendRecipe ->
                val sections = restRecipe.sections?.map { restSection ->
                    Section(
                            id = null,
                            sequenceNumber = restSection.sequenceNumber,
                            ingredients = emptyList(),
                            steps = emptyList(),
                            recipe = backendRecipe
                    ).let { backendSection ->
                        val ingredients = restSection.ingredients.map { restIngredient ->
                            Ingredient(
                                    id = null,
                                    name = restIngredient.name,
                                    measurement = restIngredient.measurement,
                                    measureUnit = restIngredient.measureUnit,
                                    section = backendSection
                            )
                        }
                        val steps = restSection.steps.map { restStep ->
                            SectionStep(
                                    id = null,
                                    description = restStep.description,
                                    sequenceNumber = restStep.sequenceNumber,
                                    section = backendSection
                            )
                        }
                        val sectionsWithInfo = backendSection.copy(
                                ingredients = ingredients,
                                steps = steps
                        )
                        sectionsWithInfo.ingredients.forEach { it.section = sectionsWithInfo }
                        sectionsWithInfo.steps.forEach { it.section = sectionsWithInfo }
                        sectionsWithInfo
                    }
                } ?: emptyList()

                backendRecipe.sections = sections
                backendRecipe.sections.forEach { it.recipe = backendRecipe }
                backendRecipe
            }
        }
        logger.info("Saving the recipe named ${backendRecipe.name} with user ${loggedInUser.externalUserProviderId}")
        recipeRepository.save(backendRecipe)
    }
}