package cc.mymess.activities;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ListView;

import org.w3c.dom.Document;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import cc.mymess.R;
import cc.mymess.fragments.ContactsFragment;
import cc.mymess.fragments.ContentFragment;
import cc.mymess.utils.DrawerListAdapter;
import cc.mymess.utils.DrawerMenuItem;
import cc.mymess.utils.Utils;

public class MainActivity extends AppCompatActivity {

    public static final String ACTION_CONTACTS = "_contacts";
    //public static final String ACTION_ = "_";

    private String xml;

    private String currentState;

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private ListView listView;
    private ArrayList<DrawerMenuItem> socialNetworks;
    private DrawerListAdapter adapter;
    private GridLayout drawerHeader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act);

        xml = Utils.readRawTextFile(this, R.raw.contacts);
        //Resources res = this.getResources();
        //XmlResourceParser xpp = res.getXml(R.xml.contacts);
        //xml = xpp.

       // XmlResourceParser xmlParser = getBaseContext().getResources().getXml(R.xml.contacts);
       // xml = xmlParser.getText();

        socialNetworks = new ArrayList<>();
        findViewById();
        setSupportActionBar(toolbar);

        initDrawerLayout();
    }

    private void findViewById() {
        listView = (ListView) findViewById(R.id.list);
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
    }

    private void initDrawerLayout() {
        setListViewData();
        setListViewHeader();
        //Mount listview with adapter
        adapter = new DrawerListAdapter(this, R.layout.drawer_list_item, socialNetworks);
        listView.setAdapter(adapter);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_closed) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
    }

    private void setListViewHeader() {
        LayoutInflater inflater = getLayoutInflater();
        View header = inflater.inflate(R.layout.drawer_header, listView, false);
        drawerHeader = (GridLayout) findViewById(R.id.drawerHeader);
        drawerHeader.addView(header);
        //listView.addHeaderView(header, null, false);
    }

    private void setListViewData() {
        socialNetworks.add(new DrawerMenuItem(R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round, "Contacts", "Contacts", ACTION_CONTACTS ));
        socialNetworks.add(new DrawerMenuItem(R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round, "Facebook", "USA", null));
        socialNetworks.add(new DrawerMenuItem(R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round, "Twitter", "USA", null));
        socialNetworks.add(new DrawerMenuItem(R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round, "Pinterest", "USA", null));
        socialNetworks.add(new DrawerMenuItem(R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round, "Google+", "USA", null));
        socialNetworks.add(new DrawerMenuItem(R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round, "Baidu", "CHINA", null));
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public void clearState(){
        this.currentState = null;
    }

    public void updateMainLayout(DrawerMenuItem item) {
        String action = item.getAction();
        if ((action == ACTION_CONTACTS)&&(this.currentState != ACTION_CONTACTS)){
            this.currentState = ACTION_CONTACTS;
            Bundle data = new Bundle();
            data.putString("contacts",xml);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.container, ContactsFragment.newInstance(data))
                    .addToBackStack(null)
                    .setCustomAnimations(R.anim.fadein, R.anim.fadeout)
                    .commit();
        }

       // transaction.replace(R.id.container, ContentFragment.newInstance(item));

        //close navigation drawer after replace fragment

        drawerLayout.closeDrawers();
    }
}