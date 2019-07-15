package com.example.ottobussample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addFragemnt();
    }

    private void addFragemnt() {
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, new UserFragment()).commit();
    }

    public void sendMessageToFragment(final View view) {
        editText = findViewById(R.id.editText);
        Event.ActivityToFragment activityToFragment = new Event.ActivityToFragment(editText.getText().toString());
        GlobalBus.bus.post(activityToFragment);
    }

    @Subscribe
    public void getMessage(Event.FragmentToActivity fragmentToActivity) {
        textView = findViewById(R.id.txt_message);
        textView.setText("Message Revceived :" + fragmentToActivity.getMessage());
    }

    @Override
    protected void onStart() {
        super.onStart();
        GlobalBus.bus.register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        GlobalBus.bus.unregister(this);
    }
}
