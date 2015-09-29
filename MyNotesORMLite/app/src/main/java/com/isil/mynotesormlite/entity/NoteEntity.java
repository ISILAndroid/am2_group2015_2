package com.isil.mynotesormlite.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by emedinaa on 15/09/15.
 * 1. ESTA OPCION ME PERMITE INGRESAR MANUALMENTE UN ID
 * @DatabaseField(id = true)
 * 2. ESTA OPCION ME PERMITE TENER UN ID AUTOGENERADO
 * @DatabaseField(generatedId=true)
 */

@DatabaseTable(tableName = "note.tb")
public class NoteEntity implements Serializable {

    public static  final String TIMESTAMP_FIELD="addedDate";

    //@DatabaseField(id = true)
    @DatabaseField(generatedId=true)
    private int id;

    @DatabaseField()
    private String name;

    @DatabaseField()
    private String description;

    @DatabaseField()
    private String path;

    @DatabaseField()
    private String addedDate;

    public NoteEntity() {
    }

    public NoteEntity(int id, String name, String description, String path) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.path = path;
    }

    public NoteEntity(String name, String description, String path) {
        this.name = name;
        this.description = description;
        this.path = path;
    }

    public NoteEntity(String name, String description, String path,String addedDate) {
        this.name = name;
        this.description = description;
        this.path = path;
        this.addedDate= addedDate;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
    }


    @Override
    public String toString() {
        return "NoteEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", path='" + path + '\'' +
                ", addedDate='" + addedDate + '\'' +
                '}';
    }
}
