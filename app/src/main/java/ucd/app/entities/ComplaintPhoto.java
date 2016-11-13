package ucd.app.entities;

import com.google.gson.annotations.SerializedName;

public class ComplaintPhoto {

    @SerializedName("id")
    private Integer id;

    @SerializedName("extension")
    private String extension;

    @SerializedName("name")
    private String name;

    @SerializedName("path")
    private String path;

    @SerializedName("base")
    private String base;

    public ComplaintPhoto(Integer id) {
        this.id = id;
    }

    public ComplaintPhoto(Integer id, String extension, String name, String path, String base) {
        this.id = id;
        this.extension = extension;
        this.name = name;
        this.path = path;
        this.base = base;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    @Override
    public String toString() {
        return "{id: " + id + ", extension='" + extension + ", name: " + name + ", path: " + path + ", base: " + base + "}";
    }
}
