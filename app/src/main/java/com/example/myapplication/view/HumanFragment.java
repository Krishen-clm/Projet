package com.example.myapplication.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import com.example.myapplication.R;
import com.example.myapplication.RestRickAndMortyApi;
import com.example.myapplication.controller.MyAdapter;
import com.example.myapplication.model.RestRickAndMortyResponse;
import com.example.myapplication.model.RickAndMortyCharacter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HumanFragment extends Fragment {
        private RecyclerView recyclerView;
        private MyAdapter mAdapter;
        private RecyclerView.LayoutManager layoutManager;
        private List<RickAndMortyCharacter> listRickAndMortyCharacter;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_human, container, false);
            recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view1);
            // use this setting to
            // improve performance if you know that changes
            // in content do not change the layout size
            // of the RecyclerView
            setHasOptionsMenu(true);


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

            return view;
        }


        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            inflater = getActivity().getMenuInflater();
            inflater.inflate(R.menu.menu_search, menu);
            MenuItem searchItem = menu.findItem(R.id.menuSearch);
            SearchView searchView = (SearchView) searchItem.getActionView();

            searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
            search(searchView);
        }

        public void search(SearchView searchView) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String query) {
                    if (mAdapter == null) {
                    } else {
                        mAdapter.getFilter().filter(query);
                    }
                    return true;
                }
            });
        }

        private void showCharacters(List<RickAndMortyCharacter> list) {
            List<RickAndMortyCharacter> humanList = new ArrayList<>();
            for (RickAndMortyCharacter rickAndMortyCharacter : list) {
                if (rickAndMortyCharacter.isHuman()) {
                    humanList.add(rickAndMortyCharacter);
                }
            }
            recyclerView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            mAdapter = new MyAdapter(humanList, getContext());
            recyclerView.setAdapter(mAdapter);
        }
}
