package com.example.manju.myapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

public class eLang extends AppCompatActivity {
private  Button lchange;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_lang);

    }

    private void showchangeLanguageDialog()
    {
        final String [] listItems={"kannada","English"};

        AlertDialog.Builder mBuilder =new AlertDialog.Builder(eLang.this);
        mBuilder.setTitle("choose any language");

        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                if (which==0){

                    setLocale("kn");
                    recreate() ;
                }
                else if (which==1){
                    setLocale("en");
                    recreate() ;
                }
                dialog.dismiss() ;
            }

        });
        AlertDialog mDialog=mBuilder.create();
        mDialog.show();

    }

    private void setLocale(String lang)
    {
        Locale locale =new Locale (lang);
        Locale.setDefault(locale);
        Configuration config =new Configuration() ;
        config.locale =locale ;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor =getSharedPreferences("settings",MODE_PRIVATE).edit();
        editor.putString("mylang",lang);
        editor.apply();

    }

    public void loadlacole ()
    {
        SharedPreferences prefs=getSharedPreferences("settings", Activity.MODE_PRIVATE);
        String language= prefs.getString("mylang","") ;
        setLocale(language );

    }

    public  void onLang(View view)
    {

    }
}
