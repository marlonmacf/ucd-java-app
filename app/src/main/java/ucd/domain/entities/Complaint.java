package ucd.domain.entities;

import com.google.gson.annotations.SerializedName;

import java.util.Set;

public class Complaint {

    @SerializedName("id")
    private Integer id;

    @SerializedName("user")
    private User user;

    @SerializedName("inspector")
    private User inspector;

    @SerializedName("status")
    private String status;

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("longitude")
    private String longitude;

    @SerializedName("description")
    private String description;

    @SerializedName("complaintPhotos")
    private Set<ComplaintPhoto> complaintPhotos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getInspector() {
        return inspector;
    }

    public void setInspector(User inspector) {
        this.inspector = inspector;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<ComplaintPhoto> getComplaintPhotos() {
        return complaintPhotos;
    }

    public void setComplaintPhotos(Set<ComplaintPhoto> complaintPhotos) {
        this.complaintPhotos = complaintPhotos;
    }

    @Override
    public String toString() {
        return "{id: " + id + ", user: " + user.toString() + ", inspector: " + inspector.toString() + ", status: " + status + ", latitude: " + latitude + ", longitude: " + longitude + ", description: " + description + ", complaintPhotos: " + complaintPhotos.toString() + "}";
    }
}
