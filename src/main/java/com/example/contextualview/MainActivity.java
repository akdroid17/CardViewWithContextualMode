package com.example.contextualview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener{
    Toolbar toolbar;
    // CheckBox checkBox;
    boolean action_mode=false;
    TextView counter_text;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    String[] names,emails,numbers;
    int[] imgs={R.drawable.amar,R.drawable.amar,R.drawable.amar,R.drawable.amar,R.drawable.amar,R.drawable.amar,R.drawable.amar,R.drawable.amar,R.drawable.amar,R.drawable.amar,R.drawable.amar,R.drawable.amar,R.drawable.amar,R.drawable.amar,R.drawable.amar,R.drawable.amar,R.drawable.amar,R.drawable.amar,R.drawable.amar,R.drawable.amar};
    ArrayList<Contact> arrayList=new ArrayList<Contact>();
    ArrayList<Contact> selectionList=new ArrayList<>();
    int counter=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // checkBox= (CheckBox) findViewById(R.id.checkBox);

        counter_text= (TextView) findViewById(R.id.counter_text);
        counter_text.setVisibility(View.GONE);

        recyclerView= (RecyclerView) findViewById(R.id.recyclerView);

        names=getResources().getStringArray(R.array.name);
        emails=getResources().getStringArray(R.array.email);
        numbers=getResources().getStringArray(R.array.mobileNumber);

        int count =0;
        for (String name: names)
        {
            Contact contact=new Contact(imgs[count],name,emails[count],numbers[count]);
            count++;
            arrayList.add(contact);
        }

        adapter=new ContactAdapter(arrayList,this);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.normal_mode,menu);
        return true;
    }

    @Override
    public boolean onLongClick(View view) {

        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.action_mode);
        counter_text.setVisibility(View.VISIBLE);
        action_mode=true;
        adapter.notifyDataSetChanged();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return true;
    }
    public void prepareSelection(View view, int position)
    {
        if (((CheckBox)view).isChecked())
        {
            selectionList.add(arrayList.get(position));
            counter=counter+1;
            updateCounter( counter);
        }
        else
        {
            selectionList.remove(arrayList.get(position));
            counter=counter-1;
            updateCounter(counter);
        }
    }

    private void updateCounter(int counter) {

        if (counter==0)
        {
            counter_text.setText("0 item selected");
        }
        else
        {
            counter_text.setText(counter+" items selected");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==R.id.delete)
        {
            action_mode=false;
            ContactAdapter contactAdapter = (ContactAdapter) adapter;

        }
        return super.onOptionsItemSelected(item);
    }
}
