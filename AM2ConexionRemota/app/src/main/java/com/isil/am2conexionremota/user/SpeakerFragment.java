package com.isil.am2conexionremota.user;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.isil.am2conexionremota.OnFragmentInteractionListener;
import com.isil.am2conexionremota.R;
import com.isil.am2conexionremota.entity.SpeakerEntity;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by @eduardomedina on 23/08/2014.
 */
public class SpeakerFragment extends Fragment
{

    private static final String TAG = "SpeakerFragment";
    private View rlayLoading;
    private RequestQueue queue;
    private View butEdit,butDelete;
    private EditText eTxtName,eTxtSkill;
    private SpeakerEntity entity;
    private SpeakerEntity nSpeakerEntity;
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
        extras();
        butEdit= getView().findViewById(R.id.butEdit);
        butDelete= getView().findViewById(R.id.butDelete);
        eTxtName= (EditText)getView().findViewById(R.id.eTxtName);
        eTxtSkill= (EditText)getView().findViewById(R.id.eTxtSkill);
        rlayLoading= getView().findViewById(R.id.rlayLoading);
        rlayLoading.setVisibility(View.GONE);

        butEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rlayLoading.setVisibility(View.VISIBLE);
                editSpeaker();
            }
        });
        butDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteSpeaker();
            }
        });

        if(entity!=null)
        {
            eTxtName.setText(entity.getName());
            eTxtSkill.setText(entity.getSkills());
        }
    }

    private void editSpeaker() {
        String name= eTxtName.getText().toString().trim();
        String skills= eTxtSkill.getText().toString().trim();

        String objectId= entity.getObjectId();
        nSpeakerEntity= new SpeakerEntity();
        nSpeakerEntity.setObjectId(objectId);
        nSpeakerEntity.setName(name);
        nSpeakerEntity.setSkills(skills);

        updateSpeaker();

    }

    private void updateSpeaker() {
            queue = Volley.newRequestQueue(getActivity());
            String url =getString(R.string.url_speaker_get)+"/"+nSpeakerEntity.getObjectId();
            JSONObject params= new JSONObject();
            try {
                params.put("name",nSpeakerEntity.getName());
                params.put("skills",nSpeakerEntity.getSkills());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JsonObjectRequest jsonStringRequest= new JsonObjectRequest(Request.Method.PUT,
                    url,params,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.v(TAG, "update speaker response " + response);
                            //view.completeSuccess(response, 100);
                            rlayLoading.setVisibility(View.GONE);
                            gotoMain();
                        }
                    },
                    new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //view.completeError(error, 100);
                            rlayLoading.setVisibility(View.GONE);

                        }
                    }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("X-Parse-Application-Id", getString(R.string.application_id));
                    params.put("X-Parse-REST-API-Key", getString(R.string.rest_api_key));

                    return params;
                }
            };
            queue.add(jsonStringRequest);
        }

    private void gotoMain() {
        mListener.gotoListSpeaker();
    }


    private void deleteSpeaker() {
        queue = Volley.newRequestQueue(getActivity());
        String url =getString(R.string.url_speaker_get)+"/"+entity.getObjectId();

        JsonObjectRequest jsonStringRequest= new JsonObjectRequest(Request.Method.DELETE,
                url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.v(TAG, "update speaker response " + response);
                        //view.completeSuccess(response, 100);
                        rlayLoading.setVisibility(View.GONE);
                        gotoMain();
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //view.completeError(error, 100);
                        rlayLoading.setVisibility(View.GONE);

                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("X-Parse-Application-Id", getString(R.string.application_id));
                params.put("X-Parse-REST-API-Key", getString(R.string.rest_api_key));

                return params;
            }
        };
        queue.add(jsonStringRequest);
    }


    private void extras() {
        if (getArguments() != null)
        {
            entity= (SpeakerEntity)getArguments().getSerializable("ENTITY");
        }
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
