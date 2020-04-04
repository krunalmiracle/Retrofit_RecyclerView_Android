package edu.upc.dsa.tracksfrontendandroid;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TracksService {
    @GET("{location}/")
    Call<List<Track>> listTracks(@Path("location") String location);
}
