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

    static class ViewHolder {
        TextView tviName;
        TextView tviGrade;
        ImageView imgGrade;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){

            // inflate the layout
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView= inflater.inflate(R.layout.row_course, null);

            // well set up the ViewHolder
            viewHolder = new ViewHolder();
            viewHolder.imgGrade= (ImageView)convertView.findViewById(R.id.iviCourse);
            viewHolder.tviName= (TextView)convertView.findViewById(R.id.tviName);
            viewHolder.tviGrade= (TextView)convertView.findViewById(R.id.tviGrade);

            // store the holder with the view.
            convertView.setTag(viewHolder);

        }else{
            // we've just avoided calling findViewById() on resource everytime
            // just use the viewHolder
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //Extraer la entidad
        CourseEntity courseEntity= this.data.get(position);
        if(courseEntity!=null)
        {
            //Asociar la entidad con el XML
            String name = courseEntity.getName();
            double grade = courseEntity.getGrade();
            viewHolder.tviName.setText(name);
            viewHolder.tviGrade.setText(Double.toString(grade));
            //imgContact.setImageResource(contactEntity.getPhoto());
        }

        return convertView;
    }
}
