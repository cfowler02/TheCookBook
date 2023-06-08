package com.nashss.se.thecookbookservice.dependency;

import com.nashss.se.thecookbookservice.activity.CreateDrinkRecipeActivity;
import com.nashss.se.thecookbookservice.activity.CreateFoodRecipeActivity;
import com.nashss.se.thecookbookservice.activity.GetDrinkRecipeActivity;
import com.nashss.se.thecookbookservice.activity.GetFoodRecipeActivity;

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
}
