package com.ldv.bash_im.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ldv.bash_im.R;
import com.ldv.bash_im.ui.adapters.FavoriteAdapter;
import com.ldv.bash_im.ui.entities.StoriesEntity;

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
        return rootView;
    }


    public void onResume() {
        super.onResume();
        loadFavoriteStories();
        Log.d(LOG_TAG, "onn resume, load stories");
    }


    public void loadFavoriteStories (){
        getLoaderManager().restartLoader(0, null, new LoaderManager.LoaderCallbacks<List<StoriesEntity>>() {
            @Override
            public Loader<List<StoriesEntity>> onCreateLoader(int id, Bundle args) {
                final AsyncTaskLoader<List<StoriesEntity>> loader = new AsyncTaskLoader<List<StoriesEntity>>(getActivity()) {
                    @Override
                    public List<StoriesEntity> loadInBackground() {
                       return StoriesEntity.selectFavorite();
                    }
                };
                loader.forceLoad();
                return loader;
            }

            @Override
            public void onLoadFinished(Loader<List<StoriesEntity>> loader, List<StoriesEntity> data) {
                recyclerView.setAdapter(new FavoriteAdapter(data));//через адаптер подгрузили данные во фрагмент

            }

            @Override
            public void onLoaderReset(Loader<List<StoriesEntity>> loader) {

            }
        });

    }

}
