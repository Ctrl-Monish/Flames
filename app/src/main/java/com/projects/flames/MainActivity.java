package com.projects.flames;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView flamesAns;
    EditText name1tv, name2tv;
    String name1, name2;
    Button flameOn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flamesAns = findViewById(R.id.resultTV);
        name1tv = findViewById(R.id.name1TV);
        name2tv = findViewById(R.id.name2TV);
        flameOn = findViewById(R.id.btn);
        flameOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name1 = name1tv.getText().toString().toLowerCase().replaceAll("\\s","");
                name2 = name2tv.getText().toString().toLowerCase().replaceAll("\\s","");
                flamesLogic(name1, name2);
            }
        });
    }

    public void flamesLogic(String name1, String name2){

        if(name1.isEmpty() || name2.isEmpty()){
            if(name1.isEmpty()){
                Toast.makeText(MainActivity.this, "Please Enter 1st Name", Toast.LENGTH_SHORT);
            }
            if(name2.isEmpty()){
                Toast.makeText(MainActivity.this, "Please Enter 2nd Name", Toast.LENGTH_SHORT);
            }
        }
        else {
            int[] name1freq = new int[26]; //frequency array to keep count of name1 characters
            for(int i=0; i<name1.length(); i++){
                //subtract ASCII vale of a (97) from ASCII value of currchar to get the index
                int index = name1.charAt(i)-'a';
                //increments the value at the index of the alphabet
                //whenever the alphabet is encountered in String
                name1freq[index]++;
            }

            int[] name2freq = new int[26]; //frequency array to keep count of  name 2 characters
            for(int j=0; j<name2.length(); j++){
                int index = name2.charAt(j)-'a';
                name2freq[index]++;
            }

            int[] totalfreq = new int[26];//frequency array to keep count of not same characters in both names
            for (int i=0; i<totalfreq.length; i++){
                if(name1freq[i]>0 || name2freq[i]>0){
                    if(name1freq[i]>name2freq[i]){
                        totalfreq[i] = name1freq[i] - name2freq[i];
                    }else
                        totalfreq[i] = name2freq[i] - name1freq[i];
                }
            }
            int noteSame = 0;
            for (int i=0; i<totalfreq.length; i++){ //to find total number of not same characters
                noteSame += totalfreq[i];
            }
            System.out.println(noteSame);

            char[] flames = {'F','L','A','M','E','S'};

            int mod = noteSame%6;
            if(mod==0){
                noteSame = 6;
            }
            if(mod>0) {
                noteSame = mod;
            }
            
            switch (flames[noteSame-1]){
                case 'F' :
                    flamesAns.setText("YOU ARE FRIENDZONED!!");
                    break;
                case 'L' :
                    flamesAns.setText("YOU ARE IN LOVE");
                    break;
                case 'A' :
                    flamesAns.setText("YOU ARE AFFECTIONATE");
                    break;
                case 'M' :
                    flamesAns.setText("YOU WILL GET MARRIED");
                    break;
                case 'E' :
                    flamesAns.setText("YOU ARE ENEMIES!!");
                    break;
                case 'S' :
                    flamesAns.setText("AWW YOU ARE SIBLINGS!!");
                    break;
            }
        }
    }
}