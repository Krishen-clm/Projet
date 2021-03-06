package com.example.myapplication.model;

public class RickAndMortyCharacter {

    private int id;
    private String name;
    private String status;
    private String species;
    private String gender;
    private String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isAlien () {
        if (this.species.equals("Alien")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isHuman () {
        if (this.species.equals("Human")){
            return true;
        } else {
            return false;
        }
    }

}
