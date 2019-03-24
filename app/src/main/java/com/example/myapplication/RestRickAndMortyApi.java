package com.example.myapplication;

import com.example.myapplication.model.RestRickAndMortyResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestRickAndMortyApi {

    @GET("character")
    Call<RestRickAndMortyResponse> getListCharacters();

}
