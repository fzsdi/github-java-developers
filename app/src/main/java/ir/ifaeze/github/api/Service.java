package ir.ifaeze.github.api;

import ir.ifaeze.github.model.ItemResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {
    @GET("/search/users")
    Call<ItemResponse> getItems(
            @Query("q") String language
    );
}
