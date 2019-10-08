package com.example.daronapp.network;

import com.example.daronapp.model.Product;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface GetDateService {
    @GET("product")

    @Headers({
            "Accept:  application/json",
            "content-type: application/json",
            "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjE2MDZiNzYwOWU1ODY4NGQyYTVlYmI4MTQxNWEzZjFjMGJkMWIyOGExYmE5NWNkMzhkMmY2YzhhOGE1MTk2N2FkZTFkMDgyZDJjMjI2OTk5In0.eyJhdWQiOiIxIiwianRpIjoiMTYwNmI3NjA5ZTU4Njg0ZDJhNWViYjgxNDE1YTNmMWMwYmQxYjI4YTFiYTk1Y2QzOGQyZjZjOGE4YTUxOTY3YWRlMWQwODJkMmMyMjY5OTkiLCJpYXQiOjE1NjQyMTA5NTAsIm5iZiI6MTU2NDIxMDk1MCwiZXhwIjoxNTk1ODMzMzUwLCJzdWIiOiIyMjEiLCJzY29wZXMiOltdfQ.Qjp2psf4AhVP6AayA_fnuiyI6FMONmJzB9ai492DtBF1705JU6bdKyp2wti1MyZwib6a4Z9HT99OYjhS8YfEV5m7gd8vqWxgIgr0CNDka0RJe6nQS40iuZKYoAUu5h3h00h6rrbciDo4QzsVTsIyIemyq6LpQyFyv75fdDWEq_WFaNdS4PMpyKSj_Pf0EY7Pxybs0w7GmJhZRNkOzJUF5wrOKp8NSPRGjNhsM6DJMy1x6t-MAQxpPzH75hzsIMtqmG2Mqcva6dhBZVsuav--xqfxz4i4nWPQDu6yDPf2_F-26eb3b8UXUterbptFxKpJ_NnNYrmLE64FAiFo7JtNJhNnQJNlAkxYRe1_MsrwxUoQrrJRJi17FX0rCgE_uDNbA9oZ9Fvy6_lzFM5ouIL5GDpUts9p9PcBi9b447uqwa2sSFpB0K7pHuydHLlTEiZdPBCCazayKPL_BiPwnH9ouN1XuTpl4hXw2djIhpGVhQ7D7HXRnRXRsyQ7jDjcVMEri4CIxhzib6NqsPnishOOAr1hRG47mvimOctYQtsOw7ItIBKgNAegkwX9JhqdnRVfX7MX2W4sHJVVr3ltVnAp9H1gQimh7KV8ErSU_KDN2K2cAtQQcqAdMCA_K0xNTJomgV9mkE76KlHsRUNbFLUGPJYgwUpON-swV8RUWcgRvIo"
    })
    Call<Product> getAllPhotos(@Query("page")int pageNumber);



}
