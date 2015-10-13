package com.isil.am2lesson3.model;

import java.io.Serializable;

/**
 * Created by emedinaa on 12/10/15.
 */
public class CourseEntity implements Serializable {
    private String name;
    private double grade;
    private int image;

    public CourseEntity(String name, double grade, int image) {
        this.name = name;
        this.grade = grade;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
