package cc.mymess.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;

import cc.mymess.R;
import cc.mymess.utils.DrawerMenuItem;
import cc.mymess.utils.LazyAdapter;
import cc.mymess.utils.XMLParser;

public class ContactsFragment extends Fragment {

    // XML node keys

    static Toolbar toolbar;
    static FloatingActionButton fab;

    static public final String KEY_CONTACT = "contact";
    static public final String KEY_NICKNAME = "nickname";
    static public final String KEY_CONT_NAME = "contName";
    static public final String KEY_IMAGE = "image";

   // static final String KEY_SONG = "song"; // parent node
   // static final String KEY_ID = "id";
   // static final String KEY_TITLE = "title";
   // static final String KEY_ARTIST = "artist";
   // static final String KEY_DURATION = "duration";
  //  static final String KEY_THUMB_URL = "thumb_url";

    ListView list;
    LazyAdapter adapter;
    private String xml;
    private XMLParser parser;

    public static ContactsFragment newInstance(@Nullable Bundle data) {
        ContactsFragment fragment = new ContactsFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {

        xml = getArguments().getString("contacts");

        return inflater.inflate(R.layout.content_contacts, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        if(xml!=null) {
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

                list = (ListView) getView().findViewById(R.id.contacts_list);

                // Getting adapter by passing xml data ArrayList
                adapter = new LazyAdapter(this.getActivity(), contactsList);
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
    }

}

