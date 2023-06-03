import com.nashss.se.thecookbookservice.dynamodb.models.DrinkRecipe;
import com.nashss.se.thecookbookservice.metrics.MetricsPublisher;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DrinkRecipeDao {
    private final DynamoDBMapper dynamoDBMapper;
    private final MetricsPublisher metricsPublisher;

    @Inject
    public DrinkRecipeDao(DynamoDBMapper dynamoDBMapper, MetricsPublisher metricsPublisher){
        this.dynamoDBMapper = dynamoDBMapper;
        this.metricsPublisher = metricsPublisher;
    }

    public DrinkRecipe getDrinkRecipe(String creator, String recipe_title){
        DrinkRecipe drinkRecipe = this.dynamoDBMapper.load(DrinkRecipe.class, creator, recipe_title);

        if (drinkRecipe == null){
            metricsPublisher.addCount(MetricsConstants.GETDRINKRECIPE_DRINKRECIPENOTFOUND_COUNT, 1);
            throw new FoodRecipeNotFoundException("Could not find drink recipe with creator and title " + creator + recipe_title);
        }
        metricsPublisher.addCount(MetricsConstants.GETDRINKRECIPE_DRINKRECIPENOTFOUND_COUNT, 0);
        return drinkRecipe;
    }

    public DrinkRecipe saveDrinkRecipe(DrinkRecipe drinkRecipe){
        this.dynamoDBMapper.save(drinkRecipe);
        return drinkRecipe;
    }
}
