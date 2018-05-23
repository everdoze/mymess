package cc.mymess.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;

import cc.mymess.R;
import cc.mymess.utils.LazyAdapter;
import cc.mymess.utils.XMLParser;

public class Contacts extends AppCompatActivity {

    // XML node keys

    static Toolbar toolbar;
    static FloatingActionButton fab;
    static AppCompatActivity me;

    static public final String KEY_CONTACT = "contact";
    static public final String KEY_NICKNAME = "nickname";
    static public final String KEY_CONT_NAME = "contName";
    static public final String KEY_IMAGE = "image";

    ListView list;
    LazyAdapter adapter;
    private String xml;
    private XMLParser parser;
    public static MainActivity main;

    @Override
    public void onStop(){
        super.onStop();
        main.clearState();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_activity);
        me = this;
       // if(toolbar == null) {
        toolbar = (Toolbar) findViewById(R.id.contacts_toolbar);
        //}

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animOverride();
            }
        });

        xml = getIntent().getStringExtra("contacts");


    }

    @Override
    public void onStart(){
        super.onStart();
        parser = new XMLParser();
        ArrayList<HashMap<String, String>> contactsList = new ArrayList<HashMap<String, String>>();
        Document doc = parser.getDomElement(xml); // getting DOM element

        NodeList nl = doc.getElementsByTagName(KEY_CONTACT);
        // looping through all song nodes &lt;song&gt;
        for (int i = 0; i < nl.getLength(); i++) {
            // creating new HashMap
            HashMap<String, String> map = new HashMap<String, String>();
            Element e = (Element) nl.item(i);
            // adding each child node to HashMap key =&gt; value
            // map.put(KEY_ID, parser.getValue(e, KEY_ID));
            map.put(KEY_NICKNAME, parser.getValue(e, KEY_NICKNAME));
            map.put(KEY_CONT_NAME, parser.getValue(e, KEY_CONT_NAME));
            map.put(KEY_IMAGE, parser.getValue(e, KEY_IMAGE));

            // adding HashList to ArrayList
            contactsList.add(map);

            list = (ListView) findViewById(R.id.contacts_list);

            // Getting adapter by passing xml data ArrayList
            adapter = new LazyAdapter(this, contactsList);
            list.setAdapter(adapter);

            // Click event for single list row
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                }
            });
        }
    }

    @Override
    public void onBackPressed(){
        animOverride();
    }

    private void animOverride(){ //Set animation and close
        finish();
        overridePendingTransition(0,R.anim.fadeout);
    }



}

