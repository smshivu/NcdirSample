package com.example.manju.myapplication;
import com.example.manju.myapplication.*;
import com.github.barteksc.pdfviewer.PDFView;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Ncdirpofile extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ncdirpofile);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
//        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//            int previousGroup = -1;
//            @Override
//            public void onGroupExpand(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        listDataHeader.get(groupPosition) + " Expanded",
//                        Toast.LENGTH_SHORT).show();
//            }
//        });

        // OPTIONAL : Show one list at a time
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousGroup)
                    expListView.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });

        // Listview Group collasped listener
//        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
//
//            @Override
//            public void onGroupCollapse(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        listDataHeader.get(groupPosition) + " Collapsed",
//                        Toast.LENGTH_SHORT).show();
//
//            }
//        });





        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
              /*  // TODO Auto-generated method stub
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;*/

                Intent pdfOpen;
                switch (listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition)) {
                    case "1. Trends of Mouth and Tongue Cancer in India":
                        CopyReadAssets("wod.pdf");
                        return true;
                    case "2. Relative proportion of sites of cancer associated with the use of tobacco to all cancers":
                        CopyReadAssets("wod2.pdf");
                        return true;
                    case "1. Top Seven Leading Sites of Cancer among both sexs in India":

                        CopyReadAssets("ncd_2.pdf");
                        return true;
                    case "2. Risk factors related to specific sites of Cancer":

                        CopyReadAssets("ncd_2.pdf");
                        return true;
                    case "Comparison of Age Adjusted Rates(AARs)if PBCRs":

                        CopyReadAssets("online_analysis.pdf");
                        return true;
                    case "Cancer ESOPHAGUS-Incidence trends over time based on PBCR 1982-2010":
                        CopyReadAssets("oesophagus1.pdf");
                        return true;
                    case "Comparison of Cancer ESOPHAGUS Incidence Rate in 27 PBCR(2012-14)":

                        CopyReadAssets("oesophagus2.pdf");
                        return true;
                    case "CANCER PROFILE OF NORTH EAST INDIA":

                        CopyReadAssets("cancer_profile.pdf");
                        return true;
                    case "Comparison of Cancer incidence Rates in 27  PBCR 1982-2010":
                        CopyReadAssets("tongue_mouth.pdf");
                        return true;
                    case "Comparison of Age Adjusted Incidence Rates (AARs) of all PBCRs":
                        CopyReadAssets("brain_ns1.pdf");
                        return true;
                    case "Testicular cancer cases ranges from 0.74 - 1.59 percentage":
                        CopyReadAssets("testicular.pdf");
                        return true;
                    case "Liver Cancer - TOP 10 Incidence Rate of PBCR(2012-14)":
                        CopyReadAssets("liver_flyer.pdf");
                        return true;
                    default:
                        Toast.makeText(getApplicationContext(), listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition), Toast.LENGTH_SHORT).show();
                        return true;
                }
            }
        });
    }
    //--end of the OnCreate function

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Projected Oral Cancer");
        listDataHeader.add("Top Seven Leading Sites of Cancer in India");
        listDataHeader.add("Cancer Incidence Analysis");
        listDataHeader.add("CANCER PROFILE OF NORTH EAST INDIA");
        listDataHeader.add("Cancer ESOPHAGUS");
        listDataHeader.add("HEAD AND NECK CANCER");
        listDataHeader.add("BRAIN CANCER");
        listDataHeader.add("TESTICULAR CANCER");
        listDataHeader.add("LIVER CANCER");
        listDataHeader.add("COMMING_SOON...");

        // Adding child data
        List<String> OralCancer = new ArrayList<String>();
        OralCancer.add("1. Trends of Mouth and Tongue Cancer in India");
        OralCancer.add("2. Relative proportion of sites of cancer associated with the use of tobacco to all cancers");


        List<String> TopLeadingSite = new ArrayList<String>();
        TopLeadingSite.add("1. Top Seven Leading Sites of Cancer among both sexs in India");
        TopLeadingSite.add("2. Risk factors related to specific sites of Cancer");


        List<String> RESEARCH_FOCUS = new ArrayList<String>();
        RESEARCH_FOCUS.add("Comparison of Age Adjusted Rates(AARs)if PBCRs");

        List<String> CANCER_PROFILE_IN_NORTHEAST = new ArrayList<String>();
        CANCER_PROFILE_IN_NORTHEAST.add("CANCER PROFILE OF NORTH EAST INDIA");


        List<String> ESOPHAGAS = new ArrayList<String>();
        ESOPHAGAS.add("Cancer ESOPHAGUS-Incidence trends over time based on PBCR 1982-2010");
        ESOPHAGAS.add("Comparison of Cancer ESOPHAGUS Incidence Rate in 27 PBCR(2012-14)");

        List<String> HEAD_NECK = new ArrayList<String>();
        HEAD_NECK.add("Comparison of Cancer incidence Rates in 27  PBCR 1982-2010");

        List<String> BRAIN_NS = new ArrayList<String>();
        BRAIN_NS.add("Comparison of Age Adjusted Incidence Rates (AARs) of all PBCRs");
        List<String> TESTICULAR = new ArrayList<String>();
        TESTICULAR.add("Testicular cancer cases ranges from 0.74 - 1.59 percentage");
        List<String> LIVER = new ArrayList<String>();
        LIVER.add("Liver Cancer - TOP 10 Incidence Rate of PBCR(2012-14)");

        List<String> COMMING_SOON = new ArrayList<String>();
        COMMING_SOON.add("");

        listDataChild.put(listDataHeader.get(0), OralCancer); // Header, Child data
        listDataChild.put(listDataHeader.get(1), TopLeadingSite);
        listDataChild.put(listDataHeader.get(2), RESEARCH_FOCUS);
        listDataChild.put(listDataHeader.get(3), CANCER_PROFILE_IN_NORTHEAST);
        listDataChild.put(listDataHeader.get(4), ESOPHAGAS);
        listDataChild.put(listDataHeader.get(5), HEAD_NECK);
        listDataChild.put(listDataHeader.get(6), BRAIN_NS);
        listDataChild.put(listDataHeader.get(7), TESTICULAR);
        listDataChild.put(listDataHeader.get(8), LIVER);
        listDataChild.put(listDataHeader.get(9), COMMING_SOON);
    }
    //--end main function---------------------------------------------------------------------

    //----Method for pdf display-------------------------------------------------------------------
    private void CopyReadAssets(String FileName) {
        AssetManager assetManager = getAssets();

        InputStream in = null;
        OutputStream out = null;
        File file = new File(getFilesDir(), FileName);
        try {
            in = assetManager.open(FileName);
            out = openFileOutput(file.getName(), Context.MODE_WORLD_READABLE);

            copyFile(in, out);
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
        } catch (Exception e) {
            Log.e("tag", e.getMessage());
        }

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(
                Uri.parse("file://" + getFilesDir() + "/" + FileName),
                "application/pdf");

        startActivity(intent);
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }


    //-----------------------------------------------------------






}
