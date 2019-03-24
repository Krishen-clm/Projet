package com.example.myapplication.model;

import java.util.List;

public class RestRickAndMortyResponse {

    private List<Info> infos;
    private List<RickAndMortyCharacter> results;

    public List<Info> getInfos() {
        return infos;
    }

    public void setInfos(List<Info> infos) {
        this.infos = infos;
    }

    public List<RickAndMortyCharacter> getResults() {
        return results;
    }

    public void setResults(List<RickAndMortyCharacter> results) {
        this.results = results;
    }
}
