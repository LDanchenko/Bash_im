package com.ldv.bash_im.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ldv.bash_im.R;
import com.ldv.bash_im.ui.models.FavoriteModel;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteHolder> { //передали класс что нижечерез адаптер

    private List<FavoriteModel> favoriteList; //создали экземпляр списка

    public FavoriteAdapter(List<FavoriteModel> favoriteList) {//конструктор
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
        FavoriteModel favorite = favoriteList.get(position); //применили метод гет, получили данные из таблицы с такой то позиции
        holder.favorite_name.setText(favorite.getName());
    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    public class FavoriteHolder extends RecyclerView.ViewHolder {//тут ищем  текст вью для вывода категори

        TextView favorite_name;

        public FavoriteHolder(View itemView) {//konstruktor
            super(itemView);
            favorite_name = (TextView) itemView.findViewById(R.id.favorite_item_name);//нашли поле в текст вью

        }
    }
}



