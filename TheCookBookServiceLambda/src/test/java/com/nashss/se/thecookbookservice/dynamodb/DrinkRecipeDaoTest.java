package com.nashss.se.thecookbookservice.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.thecookbookservice.dynamodb.models.DrinkRecipe;
import com.nashss.se.thecookbookservice.exceptions.DrinkRecipeNotFoundException;
import com.nashss.se.thecookbookservice.exceptions.FoodRecipeNotFoundException;
import com.nashss.se.thecookbookservice.metrics.MetricsConstants;
import com.nashss.se.thecookbookservice.metrics.MetricsPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class DrinkRecipeDaoTest {
    @Mock
    private DynamoDBMapper dynamoDBMapper;
    @Mock
    private MetricsPublisher metricsPublisher;


    private DrinkRecipeDao drinkRecipeDao;

    @BeforeEach
    public void setup() {
        openMocks(this);
        drinkRecipeDao = new DrinkRecipeDao(dynamoDBMapper, metricsPublisher);
    }

    @Test
    public void getDrinkRecipe_witCreatorAndRecipeTitle_callsMapper() {
        // GIVEN
        String creator = "creator";
        String recipeTitle = "recipe title";
        when(dynamoDBMapper.load(DrinkRecipe.class, creator, recipeTitle)).thenReturn(new DrinkRecipe());

        // WHEN
        DrinkRecipe drinkRecipe = drinkRecipeDao.getDrinkRecipe(creator, recipeTitle);

        // THEN
        assertNotNull(drinkRecipe);
        verify(dynamoDBMapper).load(DrinkRecipe.class, creator, recipeTitle);
        verify(metricsPublisher).addCount(eq(MetricsConstants.GETDRINKRECIPE_DRINKRECIPENOTFOUND_COUNT), anyDouble());

    }

    @Test
    public void getDrinkRecipe_KeysNotFound_throwsDrinkRecipeNotFoundException() {
        // GIVEN
        String nonexistentCreator = "NotReal";
        String nonexistentRecipeTitle = "AlsoNotReal";
        when(dynamoDBMapper.load(DrinkRecipe.class, nonexistentCreator, nonexistentRecipeTitle)).thenReturn(null);

        // WHEN + THEN
        assertThrows(DrinkRecipeNotFoundException.class, () -> drinkRecipeDao.getDrinkRecipe(nonexistentCreator, nonexistentRecipeTitle));
        verify(metricsPublisher).addCount(eq(MetricsConstants.GETDRINKRECIPE_DRINKRECIPENOTFOUND_COUNT), anyDouble());
    }

    @Test
    public void saveDrinkRecipe_callsMapperWithFoodRecipe() {
        // GIVEN
        DrinkRecipe drinkRecipe = new DrinkRecipe();

        // WHEN
        DrinkRecipe result = drinkRecipeDao.saveDrinkRecipe(drinkRecipe);

        // THEN
        verify(dynamoDBMapper).save(drinkRecipe);
        assertEquals(drinkRecipe, result);
    }
}
