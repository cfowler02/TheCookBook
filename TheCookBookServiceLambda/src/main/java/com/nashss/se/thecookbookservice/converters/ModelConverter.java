package com.nashss.se.thecookbookservice.converters;

import com.nashss.se.thecookbookservice.dynamodb.models.AlbumTrack;
import com.nashss.se.thecookbookservice.dynamodb.models.Playlist;
import com.nashss.se.thecookbookservice.dynamodb.models.FoodRecipe;
import com.nashss.se.thecookbookservice.dynamodb.models.DrinkRecipe;
import com.nashss.se.thecookbookservice.models.PlaylistModel;
import com.nashss.se.thecookbookservice.models.FoodRecipeModel;
import com.nashss.se.thecookbookservice.models.DrinkRecipeModel;
import com.nashss.se.thecookbookservice.models.SongModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Converts between Data and API models.
 */
public class ModelConverter {
    /**
     * Converts a provided {@link Playlist} into a {@link PlaylistModel} representation.
     *
     * @param playlist the playlist to convert
     * @return the converted playlist
     */
    public PlaylistModel toPlaylistModel(Playlist playlist) {
        List<String> tags = null;
        if (playlist.getTags() != null) {
            tags = new ArrayList<>(playlist.getTags());
        }

        return PlaylistModel.builder()
                .withId(playlist.getId())
                .withName(playlist.getName())
                .withCustomerId(playlist.getCustomerId())
                .withCustomerName(playlist.getCustomerName())
                .withSongCount(playlist.getSongCount())
                .withTags(tags)
                .build();
    }

    public FoodRecipeModel toFoodRecipeModel(FoodRecipe foodRecipe){
        return FoodRecipeModel.builder()
                .withCreator(foodRecipe.getCreator())
                .withRecipeTitle(foodRecipe.getRecipeTitle())
                .withIngredients(foodRecipe.getIngredients())
                .withInstructionSteps(foodRecipe.getInstructionSteps())
                .withDescription(foodRecipe.getDescription())
                .withDescriptionTags(foodRecipe.getDescriptionTags())
                .withTimeEstimate(foodRecipe.getTimeEstimate())
                .withFoodCategory(foodRecipe.getFoodCategory())
                .withFoodItem(foodRecipe.getFoodItem())
                .withAllergies(foodRecipe.getAllergies())
                .withRatings(foodRecipe.getRatings())
                .build();
    }

    public DrinkRecipeModel toDrinkRecipeModel(DrinkRecipe drinkRecipe){
        return DrinkRecipeModel.builder()
                .withCreator(drinkRecipe.getCreator())
                .withRecipeTitle(drinkRecipe.getRecipeTitle())
                .withIngredients(drinkRecipe.getIngredients())
                .withInstructionSteps(drinkRecipe.getInstructionSteps())
                .withDescription(drinkRecipe.getDescription())
                .withDescriptionTags(drinkRecipe.getDescriptionTags())
                .withFoodCategory(drinkRecipe.getFoodCategory())
                .withFoodItem(drinkRecipe.getFoodItem())
                .withAllergies(drinkRecipe.getAllergies())
                .withRatings(drinkRecipe.getRatings())
                .build();
    }

    /**
     * Converts a provided AlbumTrack into a SongModel representation.
     *
     * @param albumTrack the AlbumTrack to convert to SongModel
     * @return the converted SongModel with fields mapped from albumTrack
     */
    public SongModel toSongModel(AlbumTrack albumTrack) {
        return SongModel.builder()
                .withAsin(albumTrack.getAsin())
                .withTrackNumber(albumTrack.getTrackNumber())
                .withAlbum(albumTrack.getAlbumName())
                .withTitle(albumTrack.getSongTitle())
                .build();
    }

    /**
     * Converts a list of AlbumTracks to a list of SongModels.
     *
     * @param albumTracks The AlbumTracks to convert to SongModels
     * @return The converted list of SongModels
     */
    public List<SongModel> toSongModelList(List<AlbumTrack> albumTracks) {
        List<SongModel> songModels = new ArrayList<>();

        for (AlbumTrack albumTrack : albumTracks) {
            songModels.add(toSongModel(albumTrack));
        }

        return songModels;
    }

    /**
     * Converts a list of Playlists to a list of PlaylistModels.
     *
     * @param playlists The Playlists to convert to PlaylistModels
     * @return The converted list of PlaylistModels
     */
    public List<PlaylistModel> toPlaylistModelList(List<Playlist> playlists) {
        List<PlaylistModel> playlistModels = new ArrayList<>();

        for (Playlist playlist : playlists) {
            playlistModels.add(toPlaylistModel(playlist));
        }

        return playlistModels;
    }
}
