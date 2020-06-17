package com.example.medic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.medic.MyAdapter;
import com.example.medic.R;

import java.util.ArrayList;

public class Items extends AppCompatActivity {

    RecyclerView mRecyclerView;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = findViewById(R.id.item_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAdapter = new MyAdapter(this, getMyList());
        mRecyclerView.setAdapter(myAdapter);
    }

    private ArrayList<com.example.medic.Model.Items> getMyList() {

        ArrayList<com.example.medic.Model.Items> items = new ArrayList<>();

        com.example.medic.Model.Items m = new com.example.medic.Model.Items();
        m.setTitle("abc");
        m.setPrice("500");
        m.setImg(R.drawable.medic_logo);
        items.add(m);

        m = new com.example.medic.Model.Items();
        m.setTitle("abc");
        m.setPrice("500");        m.setImg(R.drawable.medic_logo);
        items.add(m);

        m = new com.example.medic.Model.Items();
        m.setTitle("abc");
        m.setPrice("500");        m.setImg(R.drawable.medic_logo);
        items.add(m);

        m = new com.example.medic.Model.Items();
        m.setTitle("abc");
        m.setPrice("500");        m.setImg(R.drawable.medic_logo);
        items.add(m);

        return items;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.cart, menu);

        MenuItem mCartIconMenuItem=menu.findItem(R.id.cart_count_menu_item);
        View actionView = mCartIconMenuItem.getActionView();
        /*if (actionView!=null)
        {
            CartImageBtn = actionView.findViewById(R.id.cart_image_button);
            CartCountTv = actionView.findViewById(R.id.count_tv_layout);
        }*/


        //Cart toolbar image view
       /* CartImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

        return super.onCreateOptionsMenu(menu);
    }
}
