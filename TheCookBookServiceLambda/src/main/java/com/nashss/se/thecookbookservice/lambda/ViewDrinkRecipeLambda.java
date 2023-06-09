package com.nashss.se.thecookbookservice.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.thecookbookservice.activity.requests.ViewDrinkRecipeRequest;
import com.nashss.se.thecookbookservice.activity.results.ViewDrinkRecipeResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ViewDrinkRecipeLambda
        extends LambdaActivityRunner<ViewDrinkRecipeRequest, ViewDrinkRecipeResult>
        implements RequestHandler<LambdaRequest<ViewDrinkRecipeRequest>, LambdaResponse> {

        private final Logger log = LogManager.getLogger();
        @Override
        public LambdaResponse handleRequest(LambdaRequest<ViewDrinkRecipeRequest> input, Context context) {
            log.info("handleRequest");
            System.out.println("View Drink Recipe LAMBDA HANDLE REQUEST");
            return super.runActivity(
                    () -> input.fromQuery(query ->
                            ViewDrinkRecipeRequest.builder()
                                    .build()),
                    (request, serviceComponent) ->
                            serviceComponent.provideViewDrinkRecipeActivity().handleRequest(request)
            );
        }
}
