package br.edu.fasa.blog.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Android on 20/04/2018.
 */

public class Postagems {

    @SerializedName("data")
    private List<Postagem> results;

    public List<Postagem> getResults() { return results; }

    public void setResults(List<Postagem> results) {
        this.results = results;
    }
}
