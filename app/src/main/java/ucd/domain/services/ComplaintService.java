package ucd.domain.services;

import retrofit2.Call;
import retrofit2.http.*;
import ucd.domain.entities.Complaint;

import java.util.List;

public interface ComplaintService {

    @GET("/complaint")
    Call<List<Complaint>> index();

    @GET("/complaint/{complaint}")
    Call<Complaint> show(@Path("complaint") String idComplaint);

    @POST("/complaint")
    Call<Complaint> store();

    @PUT("/complaint/{complaint}")
    Call<Complaint> update(@Path("complaint") String idComplaint);

    @DELETE("/complaint/{complaint}")
    Call<Complaint> destroy(@Path("complaint") String idComplaint);
}
