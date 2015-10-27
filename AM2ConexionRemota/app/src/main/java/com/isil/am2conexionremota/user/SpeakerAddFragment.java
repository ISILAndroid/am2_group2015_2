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
import android.widget.EditText;
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

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by @eduardomedina on 23/08/2014.
 */
public class SpeakerAddFragment extends Fragment
{

    private View rlayLoading;
    private RequestQueue queue;
    private View butAddSpeaker;
    private EditText eTxtName,eTxtSkill;
    private SpeakerEntity entity;
    private OnFragmentInteractionListener mListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_speaker, null);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        butAddSpeaker= getView().findViewById(R.id.butAddSpeaker);
        eTxtName= (EditText)getView().findViewById(R.id.eTxtName);
        eTxtSkill= (EditText)getView().findViewById(R.id.eTxtSkill);
        rlayLoading= getView().findViewById(R.id.rlayLoading);
        rlayLoading.setVisibility(View.GONE);

        butAddSpeaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    validateAddSpeaker();
            }
        });
    }

    private void validateAddSpeaker() {

        addSpeaker();
    }
    private JSONObject toJSONObject(Object obj)
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writer();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(mapper.writeValueAsString(obj));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    private void addSpeaker()
    {
        rlayLoading.setVisibility(View.VISIBLE);
        queue = Volley.newRequestQueue(getActivity());

        String url = getString(R.string.url_speaker_get);
        SpeakerEntity request= new SpeakerEntity();
        request.setName(eTxtName.getText().toString().trim());
        request.setSkills(eTxtSkill.getText().toString().trim());
        JSONObject params= toJSONObject(request);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, params,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("HomeActivity","add response "+ response.toString());
                        rlayLoading.setVisibility(View.GONE);

                        mListener.gotoListSpeaker();

                    }
                }, new Response.ErrorListener()
        {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("HomeActivity", "add Error: " + error.getMessage());
                // hide the progress dialog
                rlayLoading.setVisibility(View.GONE);

            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("X-Parse-Application-Id", getString(R.string.application_id));
                params.put("X-Parse-REST-API-Key", getString(R.string.rest_api_key));

                return params;
            }
        };
        queue.add(jsonObjReq);
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
