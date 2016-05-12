package com.example.kenzack.applicationtest.model;

        import com.j256.ormlite.field.DataType;
        import com.j256.ormlite.field.DatabaseField;
        import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable
public class Image {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String nom;
    @DatabaseField(dataType= DataType.BYTE_ARRAY,columnDefinition = "LONGBLOB")
    private byte[] image;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Utilisateur proprietaire;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Utilisateur getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Utilisateur proprietaire) {
        this.proprietaire = proprietaire;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


}

