package com.ldv.bash_im.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ldv.bash_im.R;
import com.ldv.bash_im.ui.entities.StoriesEntity;

import java.util.ArrayList;
import java.util.List;

public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.StoriesHolder> { //передали класс что нижечерез адаптер

    private Context context;
    private List<StoriesEntity> storiesList; //создали экземпляр списка

    public StoriesAdapter(Context context) {//конструктор //РАЗБЕРИСЬ
        this.context = context;
        storiesList = new ArrayList<>();
   // storiesList =this.storiesList;
    }


    public void setStoriesList(List<StoriesEntity> storiesList) {//И С ЭТИМ
        this.storiesList = storiesList;
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getItemCount() {
        return storiesList.size();
    }

    @Override
    public StoriesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stories_item, parent, false);//данные передаем в categories_item(карточка).xml
        return new StoriesHolder(itemView);
    }

    public StoriesEntity getItemById(int itemId) {
        return storiesList.get(itemId);
    }

    @Override
    public void onBindViewHolder(final StoriesHolder holder, final int position) {
        final StoriesEntity stories = storiesList.get(position); //применили метод гет, получили данные из таблицы с такой то позиции
        holder.stories_name.setText(Html.fromHtml(stories.getElementPureHtml()));
        if (stories.getFavorite()==true){
            Glide
                    .with(context)
                    .load(R.drawable.button_pressed)
                   // .crossFade()//от прозрачного к видному -анимация
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE) //сохранить оригинал изображеня в кєш
                    .into(holder.favorite);
            //holder.favorite.setImageResource(R.drawable.button_pressed);
        }
      else  if (stories.getFavorite()==false){
            Glide
                    .with(context)
                    .load(R.drawable.button_normal)
                    // .crossFade()//от прозрачного к видному -анимация
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE) //сохранить оригинал изображеня в кєш
                    .into(holder.favorite);
        }
        holder.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StoriesEntity storiesEntity = storiesList.get(position);

                if (storiesEntity.getFavorite()==false){
                    storiesEntity.setFavorite(true);
                    Glide
                            .with(context)
                            .load(R.drawable.button_pressed)
                            .diskCacheStrategy(DiskCacheStrategy.SOURCE) //сохранить оригинал изображеня в кєш
                            .into(holder.favorite);

                }
                else {
                    storiesEntity.setFavorite(false);
                   Glide
                            .with(context)
                            .load(R.drawable.button_normal)
                            .diskCacheStrategy(DiskCacheStrategy.SOURCE) //сохранить оригинал изображеня в кєш
                            .into(holder.favorite);
                }
                storiesEntity.save();
            }
        });
    }


    public class StoriesHolder extends RecyclerView.ViewHolder {

        TextView stories_name;
        ImageView favorite;

        public StoriesHolder(View itemView) {//konstruktor
            super(itemView);
            stories_name = (TextView) itemView.findViewById(R.id.stories_item_name);//нашли поле в текст вью
            favorite = (ImageView) itemView.findViewById(R.id.favorite);
            }
    }
}







