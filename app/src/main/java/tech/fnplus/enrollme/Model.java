package tech.fnplus.enrollme;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Model {
    public Model() {
    }

    public Model(int id, String name, String link, String city, String description, String members) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.city = city;
        this.description = description;
        this.members = members;
    }

    int id;
    String name;
    String link;
    String city;
    String description;
    String members;


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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }
}
