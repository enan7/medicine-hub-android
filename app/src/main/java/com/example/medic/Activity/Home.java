package com.example.medic.Activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import com.example.medic.Fragments.CartDetailFragment;
import com.example.medic.Fragments.CategoryFragment;
import com.example.medic.Fragments.ContactUsFragment;
import com.example.medic.Fragments.OrderListFragment;
import com.example.medic.R;
import com.example.medic.RetrofitClient.RetrofitClient;
import com.google.android.material.navigation.NavigationView;

import io.paperdb.Paper;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ImageView CartImageBtn, BackButton;
    static TextView CartCountTv;
    private static int count;
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
        transaction.replace(R.id.fragment_container, categoryFragment,"Category frg");
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

                if (String.valueOf(count).equals ("0")) {
                    Toast toast = Toast.makeText(Home.this, "", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    View view = getLayoutInflater().inflate(R.layout.cart_empty_toast, (ViewGroup) findViewById(R.id.toastcustom));
                    toast.setView(view);
                    toast.show();

                }
                else {
                    CartDetailFragment cartDetailFragment = new CartDetailFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, cartDetailFragment);
                    transaction
                            .addToBackStack(null)
                            .commit();
                }

            }
        });

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //Handle navigation view item clicks here.
        int id = item.getItemId();

      /*  if (id == R.id.nav_item_one) {

        } else*/ if (id == R.id.orderList) {

            OrderListFragment orderListFragment = new OrderListFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, orderListFragment,"OrderList frg");
            transaction.addToBackStack(null);
            transaction.commit();

        } else if (id == R.id.share) {

            shareApp();

        }

     else if (id == R.id.contact) {

            ContactUsFragment contactUsFragment = new ContactUsFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, contactUsFragment,"Contact frg");
            transaction.addToBackStack(null);
            transaction.commit();

    }/*else if (id == R.id.group_menu) {


        }*//* else if (id == R.id.nav_item_seven) {


        }*/ else if (id == R.id.logout_btn) {

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

    public void emptyCart()
    {
        setCartCount(0);
        count = 0;
    }

    public void deleteCartItem()
    {
        setCartCount(--count);

    }

    private void shareApp() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = ("I want to share with you this fantastic medic app");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Share");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }


}
