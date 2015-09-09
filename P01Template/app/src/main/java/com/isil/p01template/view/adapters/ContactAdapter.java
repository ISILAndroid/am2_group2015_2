package com.isil.p01template.view.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.isil.p01template.model.ContactEntity;

import java.util.List;

/**
 * Created by emedinaa on 08/09/15.
 */
public class ContactAdapter extends BaseAdapter {

    private List<ContactEntity> contactEntities;
    private Context context;

    public ContactAdapter(List<ContactEntity> contactEntities, Context context) {
        this.contactEntities=contactEntities;
        this.context=context;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
