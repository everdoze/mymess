package cc.mymess.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cc.mymess.R;


public class ChatFragment extends Fragment {

    public static ChatFragment newInstance(@Nullable Bundle data) {
        ChatFragment fragment = new ChatFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.contact_activity, container, false);
    }

}
