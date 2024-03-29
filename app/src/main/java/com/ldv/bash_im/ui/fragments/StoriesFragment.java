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
import com.ldv.bash_im.ui.adapters.StoriesAdapter;
import com.ldv.bash_im.ui.database.entities.StoriesEntity;

import org.androidannotations.annotations.EFragment;

import java.util.List;

@EFragment
public class StoriesFragment extends Fragment {

    private RecyclerView recyclerView;
    private StoriesAdapter storiesAdapter;
        private static final String LOG_TAG = "StoriesFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.stories_fragment,container,false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.list_of_stories);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Log.d(LOG_TAG, "onCreateView");
        storiesAdapter = new StoriesAdapter(getActivity()); //повесили адаптер,он с констекстом, для глайда
        recyclerView.setAdapter(storiesAdapter);
        return rootView;
    }


    public void onResume() {
        super.onResume();
       loadStories();
    }


    public void loadStories (){
        getLoaderManager().restartLoader(0, null, new LoaderManager.LoaderCallbacks<List<StoriesEntity>>() {
            @Override
            public Loader<List<StoriesEntity>> onCreateLoader(int id, Bundle args) {
                final AsyncTaskLoader<List<StoriesEntity>> loader = new AsyncTaskLoader<List<StoriesEntity>>(getActivity()) {
                    @Override
                    public List<StoriesEntity> loadInBackground() {
                        return StoriesEntity.selectAll();
                    }
                };
                loader.forceLoad();
                return loader;
            }

            @Override
            public void onLoadFinished(Loader<List<StoriesEntity>> loader, List<StoriesEntity> data) {
                //recyclerView.setAdapter(new StoriesAdapter(data));//через адаптер подгрузили данные во фрагмент
                storiesAdapter.setStoriesList(data); //Передаёте новый список в котором обновились данные (допустим, один из айтемов стал избранным)
                storiesAdapter.notifyDataSetChanged();//сообщаете адаптеру, что датасэт поменялся и он должен перегрузить список

            }
            @Override
            public void onLoaderReset(Loader<List<StoriesEntity>> loader) {

            }
        });

    }

}
