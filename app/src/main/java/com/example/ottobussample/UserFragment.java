package com.example.ottobussample;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {

    private EditText editText;
    private TextView textView;
    private View view;

    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setOnClickListener(view);
    }

    private void setOnClickListener(View view) {
        Button button = (Button) view.findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessageToActivity();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        GlobalBus.bus.register(this);
    }

    public void sendMessageToActivity() {
        EditText editText = (EditText) getView().findViewById(R.id.editText);
        Event.FragmentToActivity fragmentToActivity = new Event.FragmentToActivity(editText.getText().toString());
        GlobalBus.bus.post(fragmentToActivity);
    }

    @Subscribe
    public void getMessage(Event.ActivityToFragment activityToFragment) {
        TextView textView = (TextView)getView().findViewById(R.id.txt_message);

        textView.setText("Message Received is : " + activityToFragment.getMessage());
    }

    @Override
    public void onStop() {
        super.onStop();
        GlobalBus.bus.unregister(this);
    }
}
