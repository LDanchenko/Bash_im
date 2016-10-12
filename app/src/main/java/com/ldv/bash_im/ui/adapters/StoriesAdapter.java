package com.ldv.bash_im.ui.adapters;

import android.content.ClipData;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ldv.bash_im.R;
import com.ldv.bash_im.rest.StoriesModel;
import com.ldv.bash_im.ui.entities.StoriesEntity;

import java.util.List;

public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.StoriesHolder> { //передали класс что нижечерез адаптер


    private static View.OnClickListener itemListener;

    private List<StoriesEntity> storiesList; //создали экземпляр списка

    public StoriesAdapter(List<StoriesEntity> storiesList) {//конструктор
        this.storiesList = storiesList;
    }

    @Override
    public StoriesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stories_item, parent, false);//данные передаем в categories_item(карточка).xml
      //  .inflate(R.layout.splash_activity, parent, false);
        return new StoriesHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StoriesHolder holder, int position) {
        StoriesEntity stories = storiesList.get(position); //применили метод гет, получили данные из таблицы с такой то позиции
        holder.stories_name.setText(Html.fromHtml(stories.getElementPureHtml()));
        //holder.stories_name.setText(stories.getName());

    }

    @Override
    public int getItemCount() {
        return storiesList.size();
    }

    public class StoriesHolder extends RecyclerView.ViewHolder  {//тут ищем  текст вью для вывода категори

        TextView stories_name;
        Button button;


        public StoriesHolder(View itemView) {//konstruktor
           super(itemView);
            View v = itemView;


            button = (Button) itemView.findViewById(R.id.favorite_button);
            stories_name = (TextView) itemView.findViewById(R.id.stories_item_name);//нашли поле в текст вью
         //   button.setOnClickListener(new View.OnClickListener() {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position  =   getAdapterPosition();
                    StoriesEntity storiesEntity = StoriesEntity.findById(StoriesEntity.class, position+1);
                    storiesEntity.setFavorite(true);
                    storiesEntity.save();
                }
            });

        }

    }
}




