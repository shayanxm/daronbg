package com.example.daronapp.utill;

import android.util.Log;

import com.example.daronapp.model.Data;
import com.example.daronapp.model.Product;
import com.example.daronapp.network.GetDateService;
import com.example.daronapp.network.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HandelPage {
    List<Data> currentDatas = new ArrayList<>();


    public static void calc() {
        GetDateService service = RetrofitClientInstance.getRetrofitInstance().create(GetDateService.class);
        Call<Product> call = service.getAllPhotos(2);


        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {

                // Toast.makeText(MainActivity.this, "sucsees", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

                Log.e("test", t + "e");
            }
        });

    }
}


