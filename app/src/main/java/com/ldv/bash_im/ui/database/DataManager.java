package com.ldv.bash_im.ui.database;

import android.support.annotation.NonNull;

import com.ldv.bash_im.rest.RestService;
import com.ldv.bash_im.rest.StoriesModel;
import com.ldv.bash_im.ui.database.entities.StoriesEntity;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

import static com.ldv.bash_im.ui.ConstantsManager.NAME;
import static com.ldv.bash_im.ui.ConstantsManager.NUM;
import static com.ldv.bash_im.ui.ConstantsManager.SITE;

@EBean
public class DataManager {

    public void loadQuotes(final List<StoriesModel> storiesEntities)  {
        FlowManager.getDatabase(StoriesDatabase.class).executeTransaction(new ITransaction() {
            @Override
            public void execute(DatabaseWrapper databaseWrapper) {
                for (StoriesModel quote : storiesEntities) {
                    StoriesEntity quoteEntity = new StoriesEntity();
                    quoteEntity.setId(quote.getLink());
                    quoteEntity.setElementPureHtml(quote.getElementPureHtml());
                    quoteEntity.setFavorite(false);
                    quoteEntity.save(databaseWrapper);
                }
            }
        });
    }}