package com.isil.am2lesson3.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.isil.am2lesson3.R;
import com.isil.am2lesson3.model.CourseEntity;

import java.util.List;

/**
 * Created by emedinaa on 12/10/15.
 */
public class CourseAdapter extends BaseAdapter {

    private Context context;
    private List<CourseEntity> data;

    public CourseAdapter(Context context, List<CourseEntity> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View container= inflater.inflate(R.layout.row_course, null);
        ImageView imgContact= (ImageView)container.findViewById(R.id.iviCourse);
        TextView tviName= (TextView)container.findViewById(R.id.tviName);
        TextView tviGrade= (TextView)container.findViewById(R.id.tviGrade);

        //Extraer la entidad
        CourseEntity courseEntity= this.data.get(position);

        //Asociar la entidad con el XML
        String name = courseEntity.getName();
        double grade = courseEntity.getGrade();
        tviName.setText(name);
        tviGrade.setText(Double.toString(grade));

        //imgContact.setImageResource(contactEntity.getPhoto());

        return container;
    }
}
