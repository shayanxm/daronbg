package com.example.daronapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.daronapp.adapter.CustomAdapter;
import com.example.daronapp.model.Data;
import com.example.daronapp.model.Product;
import com.example.daronapp.network.GetDateService;
import com.example.daronapp.network.RetrofitClientInstance;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CustomAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    boolean mIsLoading;
    boolean mIsLastPage;
    int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutManager = new LinearLayoutManager(MainActivity.this);
        ///
        // initialise loading state
        mIsLoading = false;
        mIsLastPage = false;

        // amount of items you want to load per page
        final int pageSize = 4;

        recyclerView = findViewById(R.id.product_rv);
        recyclerView.setLayoutManager(layoutManager);
        // set up scroll listener


        // set up scroll listener
        recyclerView.addOnScrollListener(new  RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // number of visible items
                int visibleItemCount = layoutManager.getChildCount();
                // number of items in layout
                int totalItemCount = layoutManager.getItemCount();
                // the position of first visible item

                int firstVisibleItemPosition = ((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                boolean isNotLoadingAndNotLastPage = !mIsLoading && !mIsLastPage;
                // flag if number of visible items is at the last
                boolean isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount;
                // validate non negative values
                boolean isValidFirstItem = firstVisibleItemPosition >= 0;
                // validate total items are more than possible visible items
                boolean totalIsMoreThanVisible = totalItemCount >= pageSize;
                // flag to know whether to load more
                boolean shouldLoadMore = isValidFirstItem && isAtLastItem && totalIsMoreThanVisible && isNotLoadingAndNotLastPage;

                if (shouldLoadMore) loadMoreItems(false);
            }
        });

        // load the first page
        loadMoreItems(true);
    }

        ///

        /*Create handle for the RetrofitInstance interface*/
//        GetDateService service = RetrofitClientInstance.getRetrofitInstance().create(GetDateService.class);
//        Call<Product> call = service.getAllPhotos(1);
//
//
//        call.enqueue(new Callback<Product>() {
//            @Override
//            public void onResponse(Call<Product> call, Response<Product> response) {
//                generateDataList(response.body());
//                // Toast.makeText(MainActivity.this, "sucsees", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<Product> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_SHORT).show();
//                Log.e("test", t + "e");
//            }
//        });



    /*Method to generate List of data using RecyclerView with custom adapter*/


    private void generateDataList(Product product) {


///

        List<Data> data = product.getDataList();

        //Toast.makeText(this, data.getName()+"w8", Toast.LENGTH_SHORT).show();

        recyclerView = findViewById(R.id.product_rv);
        adapter = new CustomAdapter(this, data);


        recyclerView.setAdapter(adapter);
    }

    private void loadMoreItems(final boolean isFirstPage) {
        // change loading state

        mIsLoading = true;
        mCurrentPage = mCurrentPage + 1;
        if (mCurrentPage!=1) Toast.makeText(this, "ended", Toast.LENGTH_SHORT).show();

        GetDateService service = RetrofitClientInstance.getRetrofitInstance().create(GetDateService.class);
        Call<Product> call = service.getAllPhotos(mCurrentPage);


        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                generateDataList(response.body());


                // PagedList<Object> result = response.body();
                Product product = response.body();
                product.getMeta().getPerPage();
                if (product == null) return;
                else if (!isFirstPage) adapter.addAll(product.getDataList());
                else adapter.setList(product.getDataList());

                mIsLoading = false;
                mIsLastPage = mCurrentPage == product.getMeta().getLastPage();


            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_SHORT).show();
                Log.e("test", t + "e");
            }
        });

        Toast.makeText(this, "loading", Toast.LENGTH_SHORT).show();
    }
}