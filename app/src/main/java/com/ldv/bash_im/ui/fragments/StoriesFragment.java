package com.ldv.bash_im.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ldv.bash_im.R;
import com.ldv.bash_im.ui.adapters.StoriesAdapter;
import com.ldv.bash_im.ui.entities.StoriesEntity;
import com.ldv.bash_im.ui.models.StoriesModel;

import org.androidannotations.annotations.EFragment;

import java.util.ArrayList;
import java.util.List;


public class StoriesFragment extends Fragment {

    private RecyclerView recyclerView;
    private String LOG_TAG="LOGI";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.stories_fragment,container,false);


        recyclerView = (RecyclerView) rootView.findViewById(R.id.list_of_stories);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

      //  getLoaderManager().restartLoader(1, null, this);

     //StoriesAdapter storiesAdapter = new StoriesAdapter(List);
       //recyclerView.setAdapter(storiesAdapter);
        return rootView;
    }



    public void onResume() {
        super.onResume();
        //generateCategories();
         //loadCategories();
    }

    public void showResult(List<StoriesEntity> storiesEntities) {
        StoriesAdapter storiesAdapter = new StoriesAdapter(storiesEntities);
        recyclerView.setAdapter(storiesAdapter);
        //storiesModels.
        //Toast.makeText(getApplicationContext(),"RESULT "+ name + "  " + link , Toast.LENGTH_SHORT).show();
        //textView1.setText(" name: " + name +  "  link:" + link + "  text" + Html.fromHtml(text));
    }


   /* private void generateCategories() { //тут сами генерим категории
        StoriesEntity storiesEntity = new StoriesEntity();
        storiesEntity.setName("Products");
        storiesEntity.save();
        Log.d(LOG_TAG, "onn resume, load stories");

    }*/


    public void loadCategories (){
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
                recyclerView.setAdapter(new StoriesAdapter(data));//через адаптер подгрузили данные во фрагмент

            }


            @Override
            public void onLoaderReset(Loader<List<StoriesEntity>> loader) {

            }
        });

    }

}
