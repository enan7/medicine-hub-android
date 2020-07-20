package com.example.medic.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.medic.R;

public abstract class ToolBar extends AppCompatActivity {

    private ImageView homeButton;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_toolbar);

        homeButton = findViewById(R.id.home_btn);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ToolBar.this, Home.class);
                startActivity(intent);
                finish();
            }
        });

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);*/

      /*  Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        assert myToolbar != null;
        myToolbar.setLogo(R.mipmap.logo_big);

        TextView usernameField = (TextView) findViewById(R.id.username);
        try {
            usernameField.setText(User.getInstance().getUsername());
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.cart, menu);

        MenuItem mCartIconMenuItem = menu.findItem(R.id.cart_count_menu_item);
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
