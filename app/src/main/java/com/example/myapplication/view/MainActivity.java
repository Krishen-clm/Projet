package com.example.myapplication.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.controller.MyAdapter;
import com.example.myapplication.R;
import com.example.myapplication.RestRickAndMortyApi;
import com.example.myapplication.model.RestRickAndMortyResponse;
import com.example.myapplication.model.RickAndMortyCharacter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<RickAndMortyCharacter> listRickAndMortyCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView

        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://rickandmortyapi.com/api/").addConverterFactory(GsonConverterFactory.create(gson)).build();

        final RestRickAndMortyApi restRickAndMortyApi = retrofit.create(RestRickAndMortyApi.class);

        Call<RestRickAndMortyResponse> call = restRickAndMortyApi.getListCharacters();
        call.enqueue(new Callback<RestRickAndMortyResponse>() {
            @Override
            public void onResponse(Call<RestRickAndMortyResponse> call, Response<RestRickAndMortyResponse> response) {
                RestRickAndMortyResponse restRickAndMortyResponse = response.body();
                listRickAndMortyCharacter = restRickAndMortyResponse.getResults();
                showCharacters(listRickAndMortyCharacter);
            }

            @Override
            public void onFailure(Call<RestRickAndMortyResponse> call, Throwable t) {
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    private void showCharacters(List<RickAndMortyCharacter> list) {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter(list,this);
        recyclerView.setAdapter(mAdapter);
    }


}

