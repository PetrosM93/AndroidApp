package com.example.terrorizer.teopiotrpromitheuths;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class EditPelates extends AppCompatActivity {
    MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
    Spinner My_spinner;
    EditText PelatisName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diaxhrhsh_pelatwn);

        My_spinner = (Spinner) findViewById(R.id.Spinner1);
        ArrayList<String> my_array;
        my_array = getTableValues();

        ArrayAdapter<String> my_Adapter = new ArrayAdapter<String>(this, R.layout.spinner_row, my_array);
        My_spinner.setAdapter(my_Adapter);
        My_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                PelatisName = (EditText) findViewById(R.id.onomaPelatiEdit);
                String text = parentView.getSelectedItem().toString();
                Customer pelatis = dbHandler.loadCustomer(text);
                if(pelatis != null){
                    PelatisName.setText(pelatis.getPelatisName());
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }
    public ArrayList<String> getTableValues() {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        ArrayList<String> my_array = new ArrayList<String>();
        String temp = dbHandler.loadAllCustomer().toString();
        String qusChoice = temp.substring(1,temp.length() - 1);
        String[] arrayList = qusChoice.split(",");
        for (int i = 0; i < arrayList.length; i++) {

            my_array.add(arrayList[i]);
        }
        return my_array;
    }
    public void findCustomer(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        Customer customer = dbHandler.loadCustomer(My_spinner.getSelectedItem().toString());
        if (customer != null) {
            PelatisName.setText(String.valueOf(customer.getPelatisID()));
        } else {
            PelatisName.setText("No Match Found");
        }
    }

}