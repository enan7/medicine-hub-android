package com.example.medic.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medic.Adapters.CategoryAdapter;
import com.example.medic.Api_Interfaces.CategoryInterface;
import com.example.medic.Api_Interfaces.UserInterface;
import com.example.medic.Fragments.CategoryFragment;
import com.example.medic.Holders.CategoryHolder;
import com.example.medic.R;
import com.example.medic.Responses.CategoryListResponse;
import com.example.medic.Responses.CategoryResponse;
import com.example.medic.Responses.LoginUserResponse;
import com.example.medic.RetrofitClient.RetrofitClient;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ImageView CartImageBtn, BackButton;
    static TextView CartCountTv;
    private static int count = 0;
    ActionBarDrawerToggle toggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        /*toolbar.setLogo(R.drawable.icon);*/
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
         toggle = new ActionBarDrawerToggle(
                Home.this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(Home.this);


        Paper.init(Home.this);
        BackButton = findViewById(R.id.back_btn);

        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Home.super.onBackPressed();
            }
        });

        count = RetrofitClient.getInstance().getCartCount();

        CategoryFragment categoryFragment = new CategoryFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, categoryFragment);
        transaction.commit();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.cart, menu);

        MenuItem mCartIconMenuItem = menu.findItem(R.id.cart_count_menu_item);
        View actionView = mCartIconMenuItem.getActionView();
        if (actionView != null) {
            CartImageBtn = actionView.findViewById(R.id.cart_image_button);
            CartCountTv = actionView.findViewById(R.id.count_tv_layout);
            setCartCount(count);
        }


        //Cart toolbar image view
        CartImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_item_one) {

        } else if (id == R.id.nav_item_two) {

        } else if (id == R.id.nav_item_three) {

        } else if (id == R.id.group_menu) {


        } else if (id == R.id.nav_item_seven) {


        } else if (id == R.id.logout_btn) {

            Paper.book().destroy();

            Intent intent = new Intent(Home.this, SignIn.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    public void increaseCount() {
        ++count;
        CartCountTv.setText(String.valueOf(count));

    }

    public void setCartCount(int count) {
        CartCountTv.setText(String.valueOf(count));

    }

    public void showBackButton() {
        BackButton.setVisibility(View.VISIBLE);
    }

    public void hideBackButton() {
        BackButton.setVisibility(View.INVISIBLE);
    }

    public void showDrawerButton() {
        toggle.setDrawerIndicatorEnabled(true);
    }

    public void hideDrawerButton() {
        toggle.setDrawerIndicatorEnabled(false);
    }


}
