package com.ldv.bash_im.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ldv.bash_im.R;
import com.ldv.bash_im.ui.entities.FavouriteModel;

import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavouriteHolder> { //передали класс что нижечерез адаптер

    private List<FavouriteModel> favouriteList; //создали экземпляр списка

    public FavouriteAdapter(List<FavouriteModel> favouriteList) {//конструктор
        this.favouriteList=favouriteList;
    }

    @Override
    public FavouriteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favourite_item, parent, false);//данные передаем в categories_item(карточка).xml
        return new FavouriteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FavouriteHolder holder, int position) {
        FavouriteModel favourite = favouriteList.get(position); //применили метод гет, получили данные из таблицы с такой то позиции
        holder.favourite_name.setText(favourite.getName());
    }

    @Override
    public int getItemCount() {
        return favouriteList.size();
    }

    public class FavouriteHolder extends RecyclerView.ViewHolder {//тут ищем  текст вью для вывода категори

        TextView favourite_name;

        public FavouriteHolder(View itemView) {//konstruktor
            super(itemView);
            favourite_name = (TextView) itemView.findViewById(R.id.favourite_item_name);//нашли поле в текст вью

        }
    }
}




