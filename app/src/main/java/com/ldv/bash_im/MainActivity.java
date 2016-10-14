package com.ldv.bash_im;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.ldv.bash_im.ui.BackgroundTask;
import com.ldv.bash_im.ui.fragments.FavoriteFragment_;
import com.ldv.bash_im.ui.fragments.StoriesFragment_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.NonConfigurationInstance;
import org.androidannotations.annotations.ViewById;



@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @ViewById
    Toolbar toolbar;

    @ViewById (R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @ViewById(R.id.navigation_view)
    NavigationView navigationView;

    @NonConfigurationInstance
    @Bean
    BackgroundTask task;

      @AfterViews
      void ready(){
          toolbar.setTitle("TOOLBAR");
          setupActionBar();
          setupDrawerLayout();
          replaceFragment(new StoriesFragment_());
          getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {//повесили слушатель на бэк стэк (метод replace fragment)
              //обновляет в зависимости от фрагмента
              @Override
              public void onBackStackChanged() { //для обновления тулбара и нажатой кнопки при replace
                  Fragment f = getSupportFragmentManager().findFragmentById(R.id.main_container);
                  if (f != null) {
                      updateTitleAndDrawer(f);
                  }
              }
          });

      }

    private void setupActionBar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    //добавление навигации
        private void setupDrawerLayout() {
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawerLayout, toolbar, R.string.navigation_drawer_open,
                    R.string.navigation_drawer_close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();
            navigationView.setNavigationItemSelectedListener(this);
            setTitle(getString(R.string.app_name));
        }

    //кнопка назад
    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1){
            finish();
        }
        else {
            super.onBackPressed();
        }
    }

    //клик по навигатору
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (drawerLayout != null) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }

        switch (item.getItemId()){//нашли id из drawer_menu
            case R.id.drawer_stories:
                replaceFragment(new StoriesFragment_());//вівели фрагмент
                return true;

            case R.id.drawer_favorite:
                replaceFragment(new FavoriteFragment_());
                return true;

            case R.id.drawer_exit:
                this.finish();
        }
        return true;

    }

    public void replaceFragment(Fragment fragment) { //для правильного вывода фрагментов
        String backStackName = fragment.getClass().getName(); //нашли фрагмент
        FragmentManager manager = getSupportFragmentManager();

        boolean fragmentPopped = manager.popBackStackImmediate(backStackName, 0);
//выталкивает фрагмент в майн контейнер, если фрагмент не в стеке, делаем транзакцию
        if (! fragmentPopped && manager.findFragmentByTag(backStackName) == null) {
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.main_container, fragment, backStackName);
            ft.addToBackStack(backStackName);
            ft.commit();
        }
    }


    public void updateTitleAndDrawer (Fragment fragment){//метод для обновления тулбара и меню по нажатию кнопки назад
        String fragmentClassName = fragment.getClass().getName();//получили имя фрагмента

        if (fragmentClassName.equals(StoriesFragment_.class.getName())){//если имя фрагмента,на который вернулись, равно имени фрагмента траты
            toolbar.setTitle (getString(R.string.nav_drawer_stories));
            navigationView.setCheckedItem(R.id.drawer_stories);//Выделили пункт меню
        }
        else if (fragmentClassName.equals(FavoriteFragment_.class.getName())){//если имя фрагмента,на который вернулись, равно имени фрагмента траты
            toolbar.setTitle (getString(R.string.nav_drawer_favourite));
            navigationView.setCheckedItem(R.id.drawer_favorite);//Выделили пункт меню
        }
    }
}


