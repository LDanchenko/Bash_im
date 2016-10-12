package com.ldv.bash_im.ui.adapters;

import android.content.ClipData;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ldv.bash_im.R;
import com.ldv.bash_im.ui.entities.StoriesEntity;
import com.ldv.bash_im.ui.fragments.FavoriteFragment;
import com.ldv.bash_im.ui.models.FavoriteModel;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteHolder> { //передали класс что нижечерез адаптер

    public ClipData.Item currentItem;

    private List<StoriesEntity> favoriteList; //создали экземпляр списка

    public FavoriteAdapter(List<StoriesEntity> favoriteList) {//конструктор
        this.favoriteList=favoriteList;
    }

    @Override
    public FavoriteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favorite_item, parent, false);//данные передаем в categories_item(карточка).xml
        return new FavoriteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FavoriteHolder holder, int position) {
        StoriesEntity favorite = favoriteList.get(position); //применили метод гет, получили данные из таблицы с такой то позиции
      //  boolean b = favorite.getFavorite();
        //String t = Boolean.toString(b);
       holder.favorite_name.setText(Html.fromHtml(favorite.getElementPureHtml()));
            }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    public class FavoriteHolder extends RecyclerView.ViewHolder {//тут ищем  текст вью для вывода категори

        TextView favorite_name;

        public FavoriteHolder(final View itemView) {//konstruktor
            super(itemView);



            favorite_name = (TextView) itemView.findViewById(R.id.favorite_item_name);//нашли поле в текст вью




        }
    }
}




