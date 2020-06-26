package com.example.medic.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medic.Adapters.CategoryAdapter;
import com.example.medic.Api_Interfaces.CategoryInterface;
import com.example.medic.Api_Interfaces.UserInterface;
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

    ImageView CartImageBtn;
    LinearLayout CategoryBtn;
    TextView CartCountTv;
    private RetrofitClient retrofitClient;
    private CategoryInterface categoryInterface;
    private CategoryListResponse categoryListResponse;
    private CategoryAdapter categoryAdapter;
    RecyclerView categoryRecyclerView;
    private ArrayList<CategoryListResponse> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Paper.init(Home.this);
/*

        categoryRecyclerView = findViewById(R.id.);

        categoryListResponse=new CategoryListResponse();


        getCategoryList();
*/
        initViews();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.cart, menu);

        MenuItem mCartIconMenuItem = menu.findItem(R.id.cart_count_menu_item);
        View actionView = mCartIconMenuItem.getActionView();
        if (actionView!=null)
        {
            CartImageBtn = actionView.findViewById(R.id.cart_image_button);
            CartCountTv = actionView.findViewById(R.id.count_tv_layout);
        }


        //Cart toolbar image view
        CartImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void initViews(){
        categoryRecyclerView = (RecyclerView)findViewById(R.id.cat_recyclerview);
        categoryRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2,GridLayoutManager.VERTICAL, false);
        categoryRecyclerView.setLayoutManager(layoutManager);
        getCategoryList();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_item_one) {

        } else if (id == R.id.nav_item_two) {

        } else if (id == R.id.nav_item_three) {

        } else if (id == R.id.group_menu) {

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

    private void getCategoryList() {

        try {
            retrofitClient = RetrofitClient.getInstance();
            categoryInterface = retrofitClient.getRetrofit().create(CategoryInterface.class);
            Call<CategoryListResponse> call = categoryInterface.getCategoryList(retrofitClient.getJwtToken());

            call.enqueue(new Callback<CategoryListResponse>() {
                @Override
                public void onResponse(Call<CategoryListResponse> call, Response<CategoryListResponse> response) {

                    // loadingBar.dismiss();

                    categoryListResponse = response.body();
                    categoryAdapter = new CategoryAdapter(Home.this, (ArrayList<CategoryResponse>) categoryListResponse.getCategories());


                  //  categoryAdapter = new CategoryAdapter(data);

                    categoryRecyclerView.setAdapter(categoryAdapter);
                    Log.v("ayaz","ayaz"+response);



                    //   Toast.makeText(SignUp.this,registerUserResponse.getResponseMessage(),Toast.LENGTH_LONG).show();

                    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                    toolbar.setLogo(R.drawable.icon);
                    setSupportActionBar(toolbar);


                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                            Home.this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
                    drawer.addDrawerListener(toggle);
                    toggle.syncState();

                    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                    navigationView.setNavigationItemSelectedListener(Home.this);
                    categoryAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<CategoryListResponse> call, Throwable t) {
                    // loadingBar.dismiss();
                    System.out.println("Failed");
                }
            });
        } catch (Exception e) {
            System.out.println(e);
        }

    }


}
