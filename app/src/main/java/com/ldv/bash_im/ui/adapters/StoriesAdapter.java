package com.ldv.bash_im.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ldv.bash_im.R;
import com.ldv.bash_im.ui.entities.StoriesEntity;

import java.util.List;

public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.StoriesHolder> { //передали класс что нижечерез адаптер

    private List<StoriesEntity> storiesList; //создали экземпляр списка

    public StoriesAdapter(List<StoriesEntity> storiesList) {//конструктор
        this.storiesList = storiesList;
    }


    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public StoriesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stories_item, parent, false);//данные передаем в categories_item(карточка).xml
        return new StoriesHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StoriesHolder holder, int position) {
        StoriesEntity stories = storiesList.get(position); //применили метод гет, получили данные из таблицы с такой то позиции
        holder.stories_name.setText(Html.fromHtml(stories.getElementPureHtml()));
        if (stories.getFavorite()==true){
            holder.button.setImageResource(R.drawable.button_pressed);
        }
      else  if (stories.getFavorite()==false){
            holder.button.setImageResource(R.drawable.button_normal);

        }

    }

    @Override
    public int getItemCount() {
        return storiesList.size();
    }

    public class StoriesHolder extends RecyclerView.ViewHolder {//тут ищем  текст вью для вывода категори

        TextView stories_name;
        ImageButton button;

        public StoriesHolder(View itemView) {//konstruktor
            super(itemView);
            stories_name = (TextView) itemView.findViewById(R.id.stories_item_name);//нашли поле в текст вью
            button = (ImageButton) itemView.findViewById(R.id.favorite);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int position = getItemViewType();
                        StoriesEntity storiesEntity = StoriesEntity.findById(StoriesEntity.class, position + 1);

                        if (storiesEntity.getFavorite()==false){
                        storiesEntity.setFavorite(true);
                        button.setImageResource(R.drawable.button_pressed);
                        }
                           else {
                                storiesEntity.setFavorite(false);
                            button.setImageResource(R.drawable.button_normal);

                        }
                        storiesEntity.save();
                    }
                });
        }
    }}




