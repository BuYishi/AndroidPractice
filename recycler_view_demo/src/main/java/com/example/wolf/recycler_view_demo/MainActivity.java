package com.example.wolf.recycler_view_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView personRecyclerView;
    private PersonRecyclerViewAdapter personRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        personRecyclerView = findViewById(R.id.personRecyclerView);
        personRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        personRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        findViewById(R.id.showPersonListButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personRecyclerView.setAdapter(personRecyclerViewAdapter);
            }
        });
        ArrayList<Person> persons = getPersonList();
        personRecyclerViewAdapter = new PersonRecyclerViewAdapter(persons);
    }

    private ArrayList getPersonList() {
        ArrayList<Person> persons = new ArrayList<>();
        persons.add(new Person(R.mipmap.bruce_lee, "Bruce Lee", "Male", 32, "Be water, my friends."));
        persons.add(new Person(R.mipmap.jackie_chan, "Jackie Chan", "Male", 64, "I know that Bruce Lee is the best."));
        persons.add(new Person(R.mipmap.wu_jing, "吴京", "男", 44, "犯我中华者,虽远必诛！"));
        persons.add(new Person(R.mipmap.zhao_wen_zhuo, "赵文卓", "男", 46, "这些年来我都没什么对手，高处不胜寒。"));
        persons.add(new Person(R.mipmap.ic_launcher, "Android", "Male", 15, "I'm a good operating system!"));
        persons.add(new Person(R.mipmap.da_kong_yi, "大空翼", "男", 24, "足球是我的朋友!"));
        persons.add(new Person(R.mipmap.ri_xiang_xiao_ci_lang,"日向小次郎","男",24,"沉睡的猛虎"));
        persons.add(new Person(R.mipmap.ruo_lin_yuan_san, "若林源三", "男", 24, "天才门将"));
        return persons;
    }
}