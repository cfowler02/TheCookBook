package com.nashss.se.thecookbookservice.dependency;

import com.nashss.se.thecookbookservice.activity.AddSongToPlaylistActivity;
import com.nashss.se.thecookbookservice.activity.CreatePlaylistActivity;
import com.nashss.se.thecookbookservice.activity.GetPlaylistActivity;
import com.nashss.se.thecookbookservice.activity.GetPlaylistSongsActivity;
import com.nashss.se.thecookbookservice.activity.SearchPlaylistsActivity;
import com.nashss.se.thecookbookservice.activity.UpdatePlaylistActivity;
import com.nashss.se.thecookbookservice.activity.CreateDrinkRecipeActivity;
import com.nashss.se.thecookbookservice.activity.GetDrinkRecipeActivity;
import com.nashss.se.thecookbookservice.activity.CreateFoodRecipeActivity;
import com.nashss.se.thecookbookservice.activity.GetFoodRecipeActivity;

import dagger.Component;

import javax.inject.Singleton;

/**
 * Dagger component for providing dependency injection in the Music Playlist Service.
 */
@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {

    /**
     * Provides the relevant activity.
     * @return AddSongToPlaylistActivity
     */
    AddSongToPlaylistActivity provideAddSongToPlaylistActivity();

    /**
     * Provides the relevant activity.
     * @return CreatePlaylistActivity
     */
    CreatePlaylistActivity provideCreatePlaylistActivity();

    /**
     * Provides the relevant activity.
     * @return GetPlaylistActivity
     */
    GetPlaylistActivity provideGetPlaylistActivity();

    /**
     * Provides the relevant activity.
     * @return GetPlaylistActivity
     */
    SearchPlaylistsActivity provideSearchPlaylistsActivity();

    /**
     * Provides the relevant activity.
     * @return GetPlaylistSongsActivity
     */
    GetPlaylistSongsActivity provideGetPlaylistSongsActivity();

    /**
     * Provides the relevant activity.
     * @return UpdatePlaylistActivity
     */
    UpdatePlaylistActivity provideUpdatePlaylistActivity();

    CreateFoodRecipeActivity provideCreateFoodRecipeActivity();

    CreateDrinkRecipeActivity provideCreateDrinkRecipeActivity();

    GetFoodRecipeActivity provideGetFoodRecipeActivity();

    GetDrinkRecipeActivity provideGetDrinkRecipeActivity();
}
