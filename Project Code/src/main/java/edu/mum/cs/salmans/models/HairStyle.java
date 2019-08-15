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

    @Column(nullable = true)
    private String imageUrl;

    public HairStyle(String hairstyleName, String imageUrl) {
        this.hairstyleName = hairstyleName;
        this.imageUrl = imageUrl;
    }

    public HairStyle(String hairstyleName) {
        this.hairstyleName = hairstyleName;
    }

    public HairStyle() {
    }

    public Integer getHairstyleId() {
        return hairstyleId;
    }

    public void setHairstyleId(Integer hairstyleId) {
        this.hairstyleId = hairstyleId;
    }

    public String getHairstyleName() {
        return hairstyleName;
    }

    public void setHairstyleName(String hairstyleName) {
        this.hairstyleName = hairstyleName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "HairStyle{" +
                "hairstyleId=" + hairstyleId +
                ", hairstyleName='" + hairstyleName + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
