package com.isil.am2conexionremota.user;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.isil.am2conexionremota.OnFragmentInteractionListener;
import com.isil.am2conexionremota.R;
import com.isil.am2conexionremota.entity.SpeakerEntity;
import com.isil.am2conexionremota.entity.SpeakerResponseEntity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by @eduardomedina on 23/08/2014.
 */
public class SpeakerListFragment extends Fragment
{

    private ListView lviSpeaker;
    private View rlayLoading;
    private RequestQueue queue;
    private List<SpeakerEntity> dataSpeaker;
    private Button butRefresh, butAdd;
    private OnFragmentInteractionListener mListener;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_speaker, null);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        lviSpeaker= (ListView)getView().findViewById(R.id.lviSpeaker);
        rlayLoading= getView().findViewById(R.id.rlayLoading);
        rlayLoading.setVisibility(View.GONE);
        butRefresh = (Button)getView().findViewById(R.id.butRefresh);
        butAdd = (Button)getView().findViewById(R.id.butAdd);

        butAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    mListener.gotoAddSpeaker();
            }
        });
        butRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
            }
        });
        loadData();
    }

    private void loadData() {
        rlayLoading.setVisibility(View.VISIBLE);
        dataSpeaker= new ArrayList<SpeakerEntity>();
        queue = Volley.newRequestQueue(getActivity());

        String url = getString(R.string.url_speaker_get);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("Speaker", response.toString());
                        GsonBuilder builder = new GsonBuilder();
                        Gson gson = builder.create();
                        SpeakerResponseEntity objects = gson.fromJson(response.toString(), SpeakerResponseEntity.class);

                        dataSpeaker= objects.getResults();
                        populateSpeaker();

                        rlayLoading.setVisibility(View.GONE);

                    }


                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Speaker", "Error: " + error.getMessage());
                // hide the progress dialog

                rlayLoading.setVisibility(View.GONE);

            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("X-Parse-Application-Id", getString(R.string.application_id));
                params.put("X-Parse-REST-API-Key", getString(R.string.rest_api_key));

                return params;
            }
        };
        queue.add(jsonObjReq);

    }

    private void populateSpeaker()
    {
        SpeakerAdapter adapter = new SpeakerAdapter(getActivity(), R.layout.row_speaker, dataSpeaker);
        lviSpeaker.setAdapter(adapter);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
