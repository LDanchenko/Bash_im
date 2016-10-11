package com.ldv.bash_im.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ldv.bash_im.R;
import com.ldv.bash_im.ui.adapters.FavoriteAdapter;
import com.ldv.bash_im.ui.models.FavoriteModel;

import org.androidannotations.annotations.EFragment;

import java.util.ArrayList;
import java.util.List;

@EFragment
public class FavoriteFragment extends Fragment {

    private RecyclerView recyclerView;
    private String LOG_TAG="LOGI";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.favorite_fragment,container,false);


        recyclerView = (RecyclerView) rootView.findViewById(R.id.list_of_favorite);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // getLoaderManager().restartLoader(1, null, this);
        FavoriteAdapter favoriteAdapter = new FavoriteAdapter(getFavorite());
        recyclerView.setAdapter(favoriteAdapter);
        return rootView;
    }


    public void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onn resume, load stories");
    }


    private List<FavoriteModel> getFavorite(){
        List<FavoriteModel> favorite = new ArrayList<>();
        favorite.add(new FavoriteModel("FAVORITE"));


        return favorite;
    }

}
