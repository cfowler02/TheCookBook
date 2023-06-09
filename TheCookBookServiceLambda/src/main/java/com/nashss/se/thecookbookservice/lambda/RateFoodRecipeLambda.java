package com.nashss.se.thecookbookservice.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.thecookbookservice.activity.requests.RateFoodRecipeRequest;
import com.nashss.se.thecookbookservice.activity.results.RateFoodRecipeResult;

public class RateFoodRecipeLambda
        extends LambdaActivityRunner<RateFoodRecipeRequest, RateFoodRecipeResult>
        implements RequestHandler<AuthenticatedLambdaRequest<RateFoodRecipeRequest> , LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<RateFoodRecipeRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    RateFoodRecipeRequest unauthenticatedRequest = input.fromBody(RateFoodRecipeRequest.class);
                    return input.fromUserClaims(claims ->
                            RateFoodRecipeRequest.builder()
                                    .withCreator(unauthenticatedRequest.getCreator())
                                    .withRecipeTitle(unauthenticatedRequest.getRecipeTitle())
                                    .withRating(unauthenticatedRequest.getRating())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideRateFoodRecipeActivity().handleRequest(request)
        );
    }
}
