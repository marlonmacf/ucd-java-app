package ucd.app.views.activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import ucd.app.R;
import ucd.app.views.adapters.ViewPagerAdapter;
import ucd.app.views.drawers.profileActivity;
import ucd.app.views.fragments.InfoFragment;
import ucd.app.views.fragments.PhotoFragment;
import ucd.app.views.fragments.PlaceFragment;
import ucd.app.views.fragments.RankingFragment;
import ucd.app.views.fragments.TaskFragment;

import static ucd.app.views.activities.MainActivity.loggedUser;


public class ContentActivity extends AppCompatActivity {

    private final android.support.v4.app.Fragment infoFragment = new InfoFragment();
    private final android.support.v4.app.Fragment photoFragment = new PhotoFragment();
    private final android.support.v4.app.Fragment placeFragment = new PlaceFragment();
    private final android.support.v4.app.Fragment taskFragment = new TaskFragment();
    private final android.support.v4.app.Fragment rankingFragment = new RankingFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        // Booting the elements for the TabLayout in the Toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();

        // Defines the number of tabs by setting appropriate fragment and tab name.
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(this.infoFragment);
        adapter.addFragment(this.photoFragment);
        adapter.addFragment(this.placeFragment);
        adapter.addFragment(this.taskFragment);
        adapter.addFragment(this.rankingFragment);
        viewPager.setAdapter(adapter);

        // Assigns the ViewPager to TabLayout, Tabs with only Icons.
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_tab_info_dark);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_tab_photo_dark);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_tab_place_dark);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_tab_task_dark);
        tabLayout.getTabAt(4).setIcon(R.drawable.ic_tab_ranking_dark);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorAccent));

        // Create the AccountHeader
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.color.cardview_dark_background)
                .withProfileImagesVisible(true)
                .withProfileImagesClickable(false)
                .withSelectionListEnabledForSingleProfile(false)
                .addProfiles(
                        new ProfileDrawerItem().withName(loggedUser.getName()).withEmail(loggedUser.getEmail()).withIcon(getResources().getDrawable(R.drawable.ic_logo_green))
                )
                .build();

        //create the drawer and remember the `Drawer` result object
        Drawer result = new DrawerBuilder()
                .withAccountHeader(headerResult)
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        new PrimaryDrawerItem().withIdentifier(1).withName(R.string.nav_profile).withIcon(R.drawable.ic_assignment_ind_white_36dp),
                        new PrimaryDrawerItem().withIdentifier(2).withName(R.string.nav_settings).withIcon(R.drawable.ic_settings_white_36dp),
                        new PrimaryDrawerItem().withIdentifier(3).withName(R.string.nav_about).withIcon(R.drawable.ic_help_white_36dp),
                        new PrimaryDrawerItem().withIdentifier(4).withName(R.string.nav_logout).withIcon(R.drawable.ic_exit_to_app_white_36dp)
                )
                /**
                 * Listener nas opções do menu de navegação.
                 * Switch case para direcionar para tela respectiva de cada opção.
                 *
                 * 1-> Dados Pessoais.
                 * 2-> Alterar Senha.
                 * 3-> Sobre.
                 * 4-> Sair.
                 *
                 * @param view
                 * @param position
                 * @param drawerItem
                 */
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        Intent intent;
                        switch (position) {
                            case 1:
                                intent = new Intent(view.getContext(), profileActivity.class);
                                startActivity(intent);
                                break;
                            case 2:

                                break;
                            case 3:

                                break;
                            case 4:
                                loggedUser = null;
                                intent = new Intent(view.getContext(), LoginActivity.class);
                                startActivity(intent);
                                break;
                        }
                        return true;
                    }
                })
                .build();
        result.setSelection(0);
    }

    @Override
    public void onBackPressed() {
        // Do nothing.
    }
}
