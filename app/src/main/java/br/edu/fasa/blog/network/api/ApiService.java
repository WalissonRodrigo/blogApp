package br.edu.fasa.blog.network.api;

import br.edu.fasa.blog.models.Postagems;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Android on 20/04/2018.
 */

public interface ApiService {

    @GET("postagems")
    Call<Postagems> getPostagems();

    @FormUrlEncoded
    @POST("postagems/create")
    Call<Postagems> postPostagem(@Field("titulo") String name, @Field("conteudo") String conteudo, @Field("autor") String autor, @Field("image_1") String image1, @Field("image_2") String image2);

    @FormUrlEncoded
    @PUT("postagems/{id}/update")
    Call<Postagems> putPostagem(@Path("id") int id, @Field("titulo") String name, @Field("conteudo") String conteudo, @Field("autor") String autor, @Field("image_1") String image1, @Field("image_2") String image2);

    @POST("postagems/{id}")
    Call<Postagems> deletePostagem(@Path("id") int id);
}
