package com.example.xujichang.nestedscrollinglearn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private NestedLayout nestedLayout;
    private NestedListView nestedListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nestedLayout = (NestedLayout) findViewById(R.id.nested_linear_layout);
        nestedListView = (NestedListView) findViewById(R.id.nested_list);

    }
}
