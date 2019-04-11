package com.example.ibrahimshaltout.test;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.ibrahimshaltout.test.screens.menu.MenuFragment;
import com.example.ibrahimshaltout.test.screens.messages.MessageActivity;
import com.example.ibrahimshaltout.test.screens.notification.NotificationActivity;
import com.example.ibrahimshaltout.test.screens.search.SearchFragment;
import com.example.ibrahimshaltout.test.screens.tracks.TracksFragment;
import com.example.ibrahimshaltout.test.screens.people.PeopleFragment;
import com.example.ibrahimshaltout.test.screens.Profile.UserProfileActivity;
import com.example.ibrahimshaltout.test.screens.newsfeed.NewsFeedFragment;
import com.example.ibrahimshaltout.test.login.LoginActivity;
import com.example.ibrahimshaltout.test.menu_bar.AboutUs;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbarTop;
    private Button profile;

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    private ImageView imgNavHeaderBg, imgProfile;
    private TextView txtName, txtWebsite;

       // index to identify current nav top_bar_menu item
    public static int navItemIndex = 0;

    // tags used to attach the fragments
    private static final String TAG_HOME = "home";
    private static final String TAG_PHOTOS = "photos";
    private static final String TAG_MOVIES = "movies";
    private static final String TAG_NOTIFICATIONS = "notifications";
    private static final String TAG_SETTINGS = "settings";
    public static String CURRENT_TAG = TAG_HOME;

    // main_tool_bar titles respected to selected nav top_bar_menu item
    private String[] activityTitles;

    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbarTop = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbarTop);
        toolbarTop.setTitle("");

        loadFragment(new NewsFeedFragment());

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        mHandler = new Handler();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        navHeader = navigationView.getHeaderView(0);
        txtName = (TextView) navHeader.findViewById(R.id.name);
        txtWebsite = (TextView) navHeader.findViewById(R.id.website);
        imgNavHeaderBg = (ImageView) navHeader.findViewById(R.id.img_header_bg);
        imgProfile = (ImageView) navHeader.findViewById(R.id.img_profile);

        // initializing bottom_navigation_menu top_bar_menu
        setUpNavigationView();
        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadHomeFragment();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_bar_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.message) {
            startActivity(new Intent(this, MessageActivity.class));
            Toast.makeText(this, "you click Message", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.notification) {
            startActivity(new Intent(this, NotificationActivity.class));
            Toast.makeText(this, "you click Message", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.profile) {
            startActivity(new Intent(this, UserProfileActivity.class));
            Toast.makeText(this, "you click User Profile", Toast.LENGTH_SHORT).show();
        }
        return true;
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.newsfeed_tab:
                    toolbarTop.setTitle("NewsFeed");
                    fragment = new NewsFeedFragment();
                    loadFragment(fragment);
                    drawer.closeDrawer(Gravity.END);
                    return true;
                case R.id.tracks_tab:
                    toolbarTop.setTitle("Tracks");
                    fragment = new TracksFragment();
                    loadFragment(fragment);
                    drawer.closeDrawer(Gravity.END);
                    return true;
                case R.id.search_tab:
                    toolbarTop.setTitle("Search");
                    fragment = new SearchFragment();
                    loadFragment(fragment);
                    drawer.closeDrawer(Gravity.END);
                    return true;
                case R.id.people_tab:
                    toolbarTop.setTitle("People");
                    fragment = new PeopleFragment();
                    loadFragment(fragment);
                    drawer.closeDrawer(Gravity.END);
                    return true;
                case R.id.menu_tab:
//                    toolbarTop.setTitle("Menu");
//                    fragment = new MenuFragment();
//                    loadFragment(fragment);
                    if (drawer.isDrawerOpen(Gravity.END)) {
                        drawer.closeDrawer(Gravity.END);
                    } else {
                        drawer.openDrawer(Gravity.END);
                    }

                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void loadHomeFragment() {
        // selecting appropriate nav top_bar_menu item
        selectNavMenu();

        // if user select the current bottom_navigation_menu top_bar_menu again, don't do anything
        // just close the bottom_navigation_menu drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();
            return;
        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between bottom_navigation_menu menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame_container, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        //Closing drawer on item click
        drawer.closeDrawers();

        // refresh main_tool_bar top_bar_menu
        invalidateOptionsMenu();

    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                // home
                NewsFeedFragment newsfeedFragment = new NewsFeedFragment();
                return newsfeedFragment;
            case 1:
                // photos
                TracksFragment tracksFragment = new TracksFragment();
                return tracksFragment;
            case 2:
                // movies fragment
                SearchFragment searchFragment = new SearchFragment();
                return searchFragment;
            case 3:
                // notifications fragment
                PeopleFragment peopleFragment = new PeopleFragment();
                return peopleFragment;

            case 4:
                // settings fragment
                MenuFragment settingsFragment = new MenuFragment();
                return settingsFragment;
            default:
                return new NewsFeedFragment();
        }
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the bottom_navigation_menu top_bar_menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of bottom_navigation_menu top_bar_menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.newsfeed_tab:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_HOME;
                        break;
                    case R.id.tracks_tab:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_PHOTOS;
                        break;
                    case R.id.search_tab:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_MOVIES;
                        break;
                    case R.id.people_tab:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_NOTIFICATIONS;
                        break;
                    case R.id.nav_settings:
                        navItemIndex = 4;
                        CURRENT_TAG = TAG_SETTINGS;
                        break;
                    case R.id.nav_about_us:
                        // launch new intent instead of loading fragment
                        startActivity(new Intent(MainActivity.this, AboutUs.class));
                        drawer.closeDrawers();
                        return true;
                    case R.id.nav_privacy_policy:
                        //LogOut
                        drawer.closeDrawers();
                        signOut();

                        return true;
                    default:
                        navItemIndex = 0;
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                loadHomeFragment();

                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }

        // This code loads home fragment when back key is pressed
        // when user is in other fragment than home
        if (shouldLoadHomeFragOnBackPress) {
            // checking if user is on other bottom_navigation_menu top_bar_menu
            // rather than home
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                loadHomeFragment();
                return;
            }
        }
        super.onBackPressed();
    }

    public void signOut() {
        FirebaseAuth.getInstance().signOut();
        moveToLoginActivity();
    }

    private void moveToLoginActivity() {
//        Toast.makeText(this, "Go to Hell", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }
}
