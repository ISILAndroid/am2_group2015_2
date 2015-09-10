package com.isil.fragmentexamples.view.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.isil.fragmentexamples.R;
import com.isil.fragmentexamples.model.ContactEntity;
import com.isil.fragmentexamples.view.OnFragmentListener;
import com.isil.fragmentexamples.view.adapters.ContactAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 *  interface
 * to handle interaction events.
 * Use the {@link ContactsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentListener mListener;
    private ListView lstContacts;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactsFragment newInstance(String param1, String param2) {
        ContactsFragment fragment = new ContactsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ContactsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentListener) activity;
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

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lstContacts= (ListView)getView().findViewById(R.id.lstContacts);

        //1. DATA
        ContactEntity contactEntity= new ContactEntity();
        contactEntity.setId(1);
        contactEntity.setName("Pedro Palotes");
        contactEntity.setEmail("pedro@gmail.com");
        contactEntity.setGroup("Familia");
        contactEntity.setPhone("92835056");
        contactEntity.setPhoto(R.drawable.img002);

        ContactEntity contactEntity1= new ContactEntity();
        contactEntity1.setId(2);
        contactEntity1.setName("Carlos Palotes");
        contactEntity1.setEmail("carlos@gmail.com");
        contactEntity1.setGroup("Familia");
        contactEntity1.setPhone("96859685");
        contactEntity1.setPhoto(R.drawable.img004);

        ContactEntity contactEntity2= new ContactEntity();
        contactEntity2.setId(3);
        contactEntity2.setName("Jose Mendoza");
        contactEntity2.setEmail("jose@gmail.com");
        contactEntity2.setGroup("Familia");
        contactEntity2.setPhone("97457434");
        contactEntity2.setPhoto(R.drawable.img005);

        List<ContactEntity> data = new ArrayList<>();
        data.add(contactEntity);
        data.add(contactEntity1);
        data.add(contactEntity2);

        //2 Lista lstContacts
        //3 Item row_contact
        //4 Adapter
        ContactAdapter contactAdapter= new ContactAdapter(data,getActivity());
        lstContacts.setAdapter(contactAdapter);

       /* lstContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });*/
    }
}
