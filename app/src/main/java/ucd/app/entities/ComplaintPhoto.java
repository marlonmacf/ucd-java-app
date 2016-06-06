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

    public ComplaintPhoto(Integer id) {
        this.id = id;
    }

    public ComplaintPhoto(Integer id, String extension, String name, String path) {
        this.id = id;
        this.extension = extension;
        this.name = name;
        this.path = path;
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

    @Override
    public String toString() {
        return "{id: " + id + ", extension='" + extension + ", name: " + name + ", path: " + path + "}";
    }
}
