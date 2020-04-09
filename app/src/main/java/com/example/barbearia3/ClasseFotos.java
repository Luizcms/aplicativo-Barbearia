package com.example.barbearia3;

public class ClasseFotos {

    private String img;
    private String id;
    private  String estilo;

    public ClasseFotos() {
    }

    public ClasseFotos(String img, String id, String estilo){

        this.img = img;

        this.id = id;
        this.estilo = estilo;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }
}
