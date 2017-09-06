package com.georgekortsaridis.boosterelitefitness;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class WODFragment extends Fragment {

    ListView listView;
    ArrayList<WOD> wods;

    public WODFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wod, container, false);

        wods = new ArrayList<>();
        listView = (ListView) view.findViewById(R.id.listview);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://www.boosterfitness.gr/wod/", new AsyncHttpResponseHandler() {

            ProgressDialog pd;

            @Override
            public void onStart() {
                // called before request is started
                pd = new ProgressDialog(getActivity());
                pd.setTitle("Παρακαλώ Περιμένετε");
                pd.setMessage("Πραγματοποιείται ανάκτηση του εβδομαδιαίου WOD");
                pd.setCancelable(false);
                pd.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK"
                Document doc = Jsoup.parse(new String(response));
                Elements wods_list = doc.select("div.callout_box");
                Log.i("WODS",wods_list.size()+"");
                for(int i=0; i<7; i++){
                    Log.i(i+"",wods_list.get(i).toString());
                    Log.i(i+"",wods_list.get(i).select("p").toString().replace("<p>","").replace("</p>","").replace("<br />","").replace("<br/>","").replace("&nbsp;"," "));

                    wods.add(new WOD(
                            wods_list.get(i).select("span.day").text()+" "+wods_list.get(i).select("span.title").text(),
                            wods_list.get(i).select("p").toString().replace("<p>","").replace("</p>","").replace("<br />","").replace("<br/>","").replace("&nbsp;"," ")
                    ));
                }

                listView.setAdapter(new WODListViewAdapter(wods,getContext()));
                listView.setDivider(null);

                pd.cancel();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                pd.cancel();
            }

        });

        return view;
    }

}
