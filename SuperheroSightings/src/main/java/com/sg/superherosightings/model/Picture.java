/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author peterriggs
 */
public class Picture implements Serializable {
    
    private int pictureId;
    private String title;
    private byte[] image;
    private String base64Image;

    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.pictureId;
        hash = 43 * hash + Objects.hashCode(this.title);
        hash = 43 * hash + Arrays.hashCode(this.image);
        hash = 43 * hash + Objects.hashCode(this.base64Image);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Picture other = (Picture) obj;
        if (this.pictureId != other.pictureId) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.base64Image, other.base64Image)) {
            return false;
        }
        if (!Arrays.equals(this.image, other.image)) {
            return false;
        }
        return true;
    }

    
    
}
