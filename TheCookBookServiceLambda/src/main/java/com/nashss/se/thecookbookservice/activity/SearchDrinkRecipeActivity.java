package com.nashss.se.thecookbookservice.activity;

import com.nashss.se.thecookbookservice.activity.requests.SearchDrinkRecipeRequest;
import com.nashss.se.thecookbookservice.activity.results.SearchDrinkRecipeResult;
import com.nashss.se.thecookbookservice.converters.ModelConverter;
import com.nashss.se.thecookbookservice.dynamodb.DrinkRecipeDao;
import com.nashss.se.thecookbookservice.dynamodb.models.DrinkRecipe;
import com.nashss.se.thecookbookservice.models.DrinkRecipeModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.List;

import static com.nashss.se.thecookbookservice.utils.NullUtils.ifNull;

public class SearchDrinkRecipeActivity {
    private final Logger log = LogManager.getLogger();
    private final DrinkRecipeDao drinkRecipeDao;

    @Inject
    public SearchDrinkRecipeActivity(DrinkRecipeDao drinkRecipeDao) {
        this.drinkRecipeDao = drinkRecipeDao;
    }

    public SearchDrinkRecipeResult handleRequest(final SearchDrinkRecipeRequest searchDrinkRecipeRequest) {
        log.info("Received SearchDrinkRecipeRequest {}", searchDrinkRecipeRequest);
        String criteria = ifNull(searchDrinkRecipeRequest.getCriteria(), "");
        // String[] criteriaArray = criteria.isBlank() ? new String[0] : criteria.split("\\s");

        List<DrinkRecipe> results = drinkRecipeDao.searchDrinkRecipe(criteria);
        List<DrinkRecipeModel> drinkRecipeModels = new ModelConverter().toDrinkRecipeModelList(results);

        return SearchDrinkRecipeResult.builder()
                .withDrinkRecipeModels(drinkRecipeModels)
                .build();
    }
}
