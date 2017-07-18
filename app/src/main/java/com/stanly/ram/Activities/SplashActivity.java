package com.stanly.ram.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.stanly.ram.Activities.MainActivity;
import com.stanly.ram.R;
import com.stanly.ram.utils.ConnectionDetector;
import com.stanly.ram.utils.Utils1;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class SplashActivity  extends FragmentActivity {


    Realm realm;
    private parsejson pj;
    private String TAG = "SplashActivity";
    private ProgressBar progressBar;
    private Element el;
    private int versionCodePrecedent;
    private ConnectionDetector cd;
    SharedPreferences wmbPreference;
    private List<Node> Nodes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Utils1.activity = this;

        cd = new ConnectionDetector(getApplicationContext());

        if(cd.isConnectingToInternet()) {






            progressBar = (ProgressBar) findViewById(R.id.progress);

            if(true)
            {
                pj = new parsejson();
                pj.execute();

            }
            else
            {
                Log.e(" ===>","open MainActivity");
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
            }


        }
        else{
            Toast.makeText(getApplicationContext(), "Vérifiez votre connexion internet !",Toast.LENGTH_LONG).show();
            Handler h = new Handler();
            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            },800);
        }


    }
    private class parsejson extends AsyncTask<Void,Integer, Void> {


        public parsejson() {

        }

        @Override
        protected void onPreExecute() {


            super.onPreExecute();


        }

        @Override
        protected Void doInBackground(Void ... objects) {
            try {
                Nodes = Jsoup.connect("https://www.royalairmaroc.com/ma-fr").timeout(0)
                        .get().body().getAllElements().get(0).getAllElements().get(1)
                        .getElementById("contentPage").child(0).children().get(2)
                        .childNode(3).childNode(1).childNodes();

                /**
                 Jsoup.connect("https://www.royalairmaroc.com/ma-fr").timeout(0)
                 .get().body().getAllElements().get(0).getAllElements().get(1)
                 .getElementById("contentPage").child(0).children().get(3)
                 .getElementById("my-tab-content").getElementById("tabs-1")
                 .getElementById("tab").children().get(1).getElementById("tab-1")
                 .children().get(3).children().get(1).children().get(0)
                 .children().get(0).children().get(1).children().get(1).children()
                 **/
                List<String> urls = new ArrayList<>();
                for (int i = 0; i <Nodes.size() ; i++) {
                    if(Nodes.get(i) instanceof Element)
                    {
                        Node node = Nodes.get(i);
                        List<Node> subNodes = node.childNodes();
                        for (int j = 0; j <subNodes.size() ; j++) {
                            if(subNodes.get(j) instanceof Element)
                            {
                                if(subNodes.get(j).attr("src") != null && !subNodes.get(j).attr("src").isEmpty() )
                                {
                                    if(!subNodes.get(j).attr("src").contains(".gif"))
                                    {
                                        urls.add(subNodes.get(j).attr("src"));

                                    }
                                    else {
                                        if(subNodes.get(j).attr("pagespeed_lazy_src") != null )
                                        {
                                            urls.add(subNodes.get(j).attr("pagespeed_lazy_src"));
                                        }

                                    }
                                    Log.e("url =>", "" + urls);
                                }

                            }

                        }

                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
            finish();
        }
    }

}

