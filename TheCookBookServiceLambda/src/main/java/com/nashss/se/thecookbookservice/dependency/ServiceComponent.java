package com.nashss.se.thecookbookservice.dependency;

import com.nashss.se.thecookbookservice.activity.*;

import dagger.Component;

import javax.inject.Singleton;

/**
 * Dagger component for providing dependency injection in the Music Playlist Service.
 */
@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {

    CreateFoodRecipeActivity provideCreateFoodRecipeActivity();

    CreateDrinkRecipeActivity provideCreateDrinkRecipeActivity();

    GetFoodRecipeActivity provideGetFoodRecipeActivity();

    GetDrinkRecipeActivity provideGetDrinkRecipeActivity();

    RateDrinkRecipeActivity provideRateDrinkRecipeActivity();

    RateFoodRecipeActivity provideRateFoodRecipeActivity();

    SearchDrinkRecipeActivity provideSearchDrinkRecipeActivity();

    SearchFoodRecipeActivity provideSearchFoodRecipeActivity();

    ViewDrinkRecipeActivity provideViewDrinkRecipeActivity();

    ViewFoodRecipeActivity provideViewFoodRecipeActivity();
}
