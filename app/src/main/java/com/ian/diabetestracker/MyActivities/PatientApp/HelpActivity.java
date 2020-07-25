package com.ian.diabetestracker.MyActivities.PatientApp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.ian.diabetestracker.R;

public class HelpActivity extends AppCompatActivity {

    String[] helplist = {"FAQ","contact us","App Info"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        ListView helpListView = findViewById(R.id.helpListView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,helplist);
        helpListView.setAdapter(adapter);

        final Dialog dialog = new Dialog(this);
        helpListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();

                if (item.equals("FAQ")){
                    Toast.makeText(HelpActivity.this, "FAQS", Toast.LENGTH_SHORT).show();
                }
                else if (item.equals("contact us")){
                    dialog.setContentView(R.layout.contactview);
                    Button btnMessageSend = dialog.findViewById(R.id.btnMessageSend);
                    btnMessageSend.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(HelpActivity.this, "working", Toast.LENGTH_SHORT).show();
                        }
                    });
                    dialog.show();
                }
                else if(item.equals("App Info")) {
                    Toast.makeText(HelpActivity.this, "App Info", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
