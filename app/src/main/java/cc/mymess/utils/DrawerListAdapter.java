package cc.mymess.utils;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cc.mymess.R;
import cc.mymess.activities.MainActivity;

public class DrawerListAdapter extends ArrayAdapter<DrawerMenuItem> {

    private MainActivity activity;
    private ArrayList<DrawerMenuItem> menuItems;

    public DrawerListAdapter(MainActivity activity, int resource, ArrayList<DrawerMenuItem> objects) {
        super(activity, resource, objects);
        this.activity = activity;
        this.menuItems = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        // If holder not exist then locate all view from UI file.
        if (convertView == null) {
            // inflate UI from XML file
            convertView = inflater.inflate(R.layout.drawer_list_item, parent, false);
            // get all UI view
            holder = new ViewHolder(convertView);
            // set tag for holder
            convertView.setTag(holder);
        } else {
            // if holder created, get tag from view
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(getItem(position).getName());
        holder.icon.setImageResource(getItem(position).getImageId());

        //handling each item on click
        //update main layout by this action
        convertView.setOnClickListener(onClickListener(position));

        return convertView;
    }

    private View.OnClickListener onClickListener(final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.updateMainLayout(getItem(position));
            }
        };
    }

    private class ViewHolder {
        private ImageView icon;
        private TextView name;

        public ViewHolder(View v) {
            icon = (ImageView) v.findViewById(R.id.drawer_item_icon);
            name = (TextView) v.findViewById(R.id.drawer_item_name);
        }
    }
}
