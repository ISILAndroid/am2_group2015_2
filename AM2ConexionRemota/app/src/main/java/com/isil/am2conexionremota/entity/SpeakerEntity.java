package com.isil.am2conexionremota.entity;

import java.io.Serializable;

/**
 * Created by @eduardomedina on 23/08/2014.
 */
public class SpeakerEntity implements Serializable {

    private String objectId;
    private int id;
    private String name;
    private String skills;
    private int img;
    private PhotoSpeaker photo;


    public SpeakerEntity(int id, String name, String skills, int img) {
        this.id = id;
        this.name = name;
        this.skills = skills;
        this.img = img;
    }

    public SpeakerEntity(int id, String name, String skills) {
        this.id = id;
        this.name = name;
        this.skills = skills;
    }

    public SpeakerEntity() {
    }

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

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public PhotoSpeaker getPhoto() {
        return photo;
    }

    public void setPhoto(PhotoSpeaker photo) {
        this.photo = photo;
    }

    public String getPhotoPath()
    {
        String path="";
        if(this.photo!=null)
        {
            path=this.photo.getUrl();
        }
        return path;
    }

    public class PhotoSpeaker implements Serializable
    {
        private String name;
        private String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "PhotoPokemon{" +
                    "name='" + name + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }
}
