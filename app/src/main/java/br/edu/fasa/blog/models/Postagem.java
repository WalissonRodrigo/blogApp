package br.edu.fasa.blog.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Android on 13/04/2018.
 */

public class Postagem  implements Serializable{

    @SerializedName("id")
    private int id;
    @SerializedName("titulo")
    private String titulo;
    @SerializedName("conteudo")
    private String conteudo;
    @SerializedName("autor")
    private String autor;
    @SerializedName("user_id")
    private int user_id;
    @SerializedName("image_1")
    private String image_1;
    @SerializedName("image_2")
    private String image_2;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getImage_1() {
        return image_1;
    }

    public void setImage_1(String image_1) {
        this.image_1 = image_1;
    }

    public String getImage_2() {
        return image_2;
    }

    public void setImage_2(String image_2) {
        this.image_2 = image_2;
    }
}
