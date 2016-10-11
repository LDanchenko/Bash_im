package com.ldv.bash_im.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
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
import com.ldv.bash_im.ui.entities.StoriesModel;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EFragment
public class StoriesFragment extends Fragment {

    private RecyclerView recyclerView;
    private String LOG_TAG="LOGI";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.stories_fragment,container,false);


        recyclerView = (RecyclerView) rootView.findViewById(R.id.list_of_stories);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

       // getLoaderManager().restartLoader(1, null, this);
        StoriesAdapter storiesAdapter = new StoriesAdapter(getStories());
        recyclerView.setAdapter(storiesAdapter);
        return rootView;
    }


    public void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onn resume, load stories");
    }


    private List<StoriesModel> getStories(){
        List<StoriesModel> stories = new ArrayList<>();
        stories.add(new StoriesModel("Look"));


        return stories;
    }

}
