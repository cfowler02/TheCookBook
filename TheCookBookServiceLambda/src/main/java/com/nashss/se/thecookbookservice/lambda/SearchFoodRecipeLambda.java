package com.nashss.se.thecookbookservice.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.thecookbookservice.activity.requests.SearchFoodRecipeRequest;
import com.nashss.se.thecookbookservice.activity.results.SearchFoodRecipeResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SearchFoodRecipeLambda
        extends LambdaActivityRunner<SearchFoodRecipeRequest, SearchFoodRecipeResult>
        implements RequestHandler<LambdaRequest<SearchFoodRecipeRequest>, LambdaResponse> {

        private final Logger log = LogManager.getLogger();
        @Override
        public LambdaResponse handleRequest(LambdaRequest<SearchFoodRecipeRequest> input, Context context) {
            log.info("handleRequest");
            System.out.println("Search Food Recipe LAMBDA HANDLE REQUEST");
            return super.runActivity(
                    () -> input.fromQuery(query ->
                            SearchFoodRecipeRequest.builder()
                                    .withCriteria(query.get("q"))
                                    .build()),
                    (request, serviceComponent) ->
                            serviceComponent.provideSearchFoodRecipeActivity().handleRequest(request)
            );
        }
}
