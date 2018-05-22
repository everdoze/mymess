package cc.mymess.utils;

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

public class Contacts extends AppCompatActivity {


    // XML node keys

    static Toolbar toolbar;
    static FloatingActionButton fab;
    static AppCompatActivity me;

    static final String KEY_CONTACT = "contact";
    static final String KEY_NAME = "name";
    static final String KEY_SURNAME = "surname";

    static final String KEY_SONG = "song"; // parent node
    static final String KEY_ID = "id";
    static final String KEY_TITLE = "title";
    static final String KEY_ARTIST = "artist";
    static final String KEY_DURATION = "duration";
    static final String KEY_THUMB_URL = "thumb_url";

    ListView list;
    LazyAdapter adapter;
    private String xml;
    private XMLParser parser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_contacts);
        me = this;
       // if(toolbar == null) {
            toolbar = (Toolbar) findViewById(R.id.main_toolbar);
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
        parser = new XMLParser();
        ArrayList<HashMap<String, String>> contactsList = new ArrayList<HashMap<String, String>>();
        Document doc = parser.getDomElement(xml); // getting DOM element

        NodeList nl = doc.getElementsByTagName(KEY_SONG);
        // looping through all song nodes &lt;song&gt;
        for (int i = 0; i < nl.getLength(); i++) {
            // creating new HashMap
            HashMap<String, String> map = new HashMap<String, String>();
            Element e = (Element) nl.item(i);
            // adding each child node to HashMap key =&gt; value
            map.put(KEY_ID, parser.getValue(e, KEY_ID));
            map.put(KEY_TITLE, parser.getValue(e, KEY_TITLE));
            map.put(KEY_ARTIST, parser.getValue(e, KEY_ARTIST));
            map.put(KEY_DURATION, parser.getValue(e, KEY_DURATION));
            map.put(KEY_THUMB_URL, parser.getValue(e, KEY_THUMB_URL));

            // adding HashList to ArrayList
            contactsList.add(map);


                list=(ListView)findViewById(R.id.contacts_list);

                // Getting adapter by passing xml data ArrayList
                adapter=new LazyAdapter(me, contactsList);
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

