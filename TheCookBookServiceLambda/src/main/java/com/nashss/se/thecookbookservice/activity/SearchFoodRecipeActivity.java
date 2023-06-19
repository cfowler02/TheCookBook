package com.nashss.se.thecookbookservice.activity;

import com.nashss.se.thecookbookservice.activity.requests.SearchFoodRecipeRequest;
import com.nashss.se.thecookbookservice.activity.results.SearchFoodRecipeResult;
import com.nashss.se.thecookbookservice.converters.ModelConverter;
import com.nashss.se.thecookbookservice.dynamodb.FoodRecipeDao;
import com.nashss.se.thecookbookservice.dynamodb.models.FoodRecipe;
import com.nashss.se.thecookbookservice.models.FoodRecipeModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

import java.util.List;

import static com.nashss.se.thecookbookservice.utils.NullUtils.ifNull;

public class SearchFoodRecipeActivity {
    private final Logger log = LogManager.getLogger();
    private final FoodRecipeDao foodRecipeDao;

    @Inject
    public SearchFoodRecipeActivity(FoodRecipeDao foodRecipeDao) {
        this.foodRecipeDao = foodRecipeDao;
    }

    public SearchFoodRecipeResult handleRequest(final SearchFoodRecipeRequest searchFoodRecipeRequest) {
        log.info("Received SearchFoodRecipeRequest {}", searchFoodRecipeRequest);
        String criteria = searchFoodRecipeRequest.getCriteria();
        String filter = searchFoodRecipeRequest.getFilter();

        List<FoodRecipe> results = foodRecipeDao.searchFoodRecipe(filter, criteria);
        List<FoodRecipeModel> foodRecipeModels = new ModelConverter().toFoodRecipeModelList(results);

        return SearchFoodRecipeResult.builder()
                .withFoodRecipeModels(foodRecipeModels)
                .build();
    }
}
