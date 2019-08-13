package edu.mum.cs.salmans.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "hairstyles")
public class HairStyle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer hairstyleId;

    @NotBlank(message = "Hair Style Name is Required")
    @Column(nullable = false)
    private String hairstyleName;

    @Column(nullable = false)
    private String imageUrl;

    public HairStyle( String hairstyleName, String imageUrl) {
        this.hairstyleName = hairstyleName;
        this.imageUrl = imageUrl;
    }

    public HairStyle() {
    }

    private Integer getHairstyleId() {
        return hairstyleId;
    }

    private String getHairstyleName() {
        return hairstyleName;
    }

    private String getImageUrl() {
        return imageUrl;
    }

    private void setHairstyleId(Integer hairstyleId) {
        this.hairstyleId = hairstyleId;
    }

    private void setHairstyleName(String hairstyleName) {
        this.hairstyleName = hairstyleName;
    }

    private void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


}
