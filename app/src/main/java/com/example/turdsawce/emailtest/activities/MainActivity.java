package com.example.turdsawce.emailtest.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.turdsawce.emailtest.MessageAdapter;
import com.example.turdsawce.emailtest.R;
import com.example.turdsawce.emailtest.RecyclerViewClickHandler;
import com.example.turdsawce.emailtest.models.Message;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // Instance variables
    private final List<Message> messageList;

    private TextView noMsgText;
    private RecyclerView msgRecyclerView;
    private MessageAdapter messageAdapter;

    public MainActivity() {
        messageList = new ArrayList<>();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Please wait while scan is in progress", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Finder the recycler view and the text view
        this.msgRecyclerView = findViewById(R.id.message_view);
        this.noMsgText = findViewById(R.id.nomsg_text);

        // set a Layout Manager for the recycler view
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        this.msgRecyclerView.setLayoutManager(layoutManager);

        RecyclerViewClickHandler handler = new RecyclerViewClickHandler() {
            @Override
            public void onClick(View view, int pos) {
                String msgId = MainActivity.this.messageAdapter.getMsgId(pos);

                // using intents load the message detail activity
                Toast t = Toast.makeText(
                        MainActivity.this,
                        "Click detected on row " + pos,
                        Toast.LENGTH_LONG);
                t.show();
            }
        };

        // set an adapter for the recycler view
        messageAdapter = new MessageAdapter(
                this.loadMessages(), handler);

        this.msgRecyclerView.setAdapter(messageAdapter);

        // set the visible element based on the size of the message list
        if(this.messageList.size()<1) {
            // show no message TextView and hide the RecyclerView
            this.noMsgText.setVisibility(View.VISIBLE);
            this.msgRecyclerView.setVisibility(View.GONE);
        }
        else {
            // show the RecyclerView and hide the no message TextView
            this.noMsgText.setVisibility((View.GONE));
            this.msgRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void updateView() {

    }

    private List<Message> loadMessages() {
        this.messageList.add(
                new Message("Message 1", "potatoking99@gmail.com ",
                        "diabetesIsGreat@yahoo.com ", null,
                        null, "Surplus of fries", "text/html",
                        "I have a extra bag of fries  ")
        );
        this.messageList.add(
                new Message("Message 2", "cheeseWhizz@yahoo.com ",
                        "diabetesIsGreat@yahoo.com ", null,
                        null, "Ya like cheese?", "text/html",
                        "There should be a wheel of cheese en route ")
        );
        this.messageList.add(
                new Message("Message 3", "blackhat@motyga.ru ",
                        "diabetesIsGreat@yahoo.com ", null,
                        null, "Problem with your account", "text/html",
                        "Please sign into your account via the embedded link  ")
        );

        this.messageList.add(
                new Message("Message 4", "potatoking99@gmail.com ",
                        "diabetesIsGreat@yahoo.com ", null,
                        null, "Surplus is going to expire!", "text/html",
                        "I still have some left overs...  ")
        );

        this.messageList.add(
                new Message("Message 5", "yaMama@aol.com ",
                        "diabetesIsGreat@yahoo.com ", null,
                        null, "Why haven't you called", "text/html",
                        "It's been 12 years, why haven't you rang?  ")
        );

        this.messageList.add(
                new Message("Message 6", "silkRoad@motyga.ru ",
                        "diabetesIsGreat@yahoo.com ", null,
                        null, "Claim your refund", "text/html",
                        "Hello, we are contacting about your $200,000.00 entitlement. ")
        );

        this.messageList.add(
                new Message("Message 7", "3Dcakes4All@yahoo.com ",
                        "diabetesIsGreat@yahoo.com ", null,
                        null, "$60 FREE GIFT! ", "text/html",
                        "We offer the highest quality of 3D cakes for decoration(do not eat ")
        );

        return this.messageList;
    }
}
