package com.example.myapplication.model;

import java.util.List;

public class RestRickAndMortyResponse {

    private List<RickAndMortyCharacter> results;

    public List<RickAndMortyCharacter> getResults() {
        return results;
    }

    public void setResults(List<RickAndMortyCharacter> results) {
        this.results = results;
    }
}