package com.nashss.se.thecookbookservice.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.thecookbookservice.activity.requests.GetFoodRecipeRequest;
import com.nashss.se.thecookbookservice.activity.results.GetFoodRecipeResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetFoodRecipeLambda
        extends LambdaActivityRunner<GetFoodRecipeRequest, GetFoodRecipeResult>
        implements RequestHandler<LambdaRequest<GetFoodRecipeRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetFoodRecipeRequest> input, Context context) {
        log.info("handleRequest");
        return super.runActivity(
                () -> input.fromPath(path ->
                        GetFoodRecipeRequest.builder()
                                .withCreator(path.get("creator"))
                                .withRecipeTitle(path.get("recipeTitle"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetFoodRecipeActivity().handleRequest(request)
        );
    }
}
