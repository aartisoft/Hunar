package com.info.hunar.adapter.expend_recycler;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.info.hunar.R;
import com.info.hunar.Activity.Activity_VideoPlay;
import com.info.hunar.model_pojo.subcategory_course_model.CourseDataVideo;

/**
 * Created by Raghvendra Sahu on 19-Jan-20.
 */
public class CourseChildViewHolder extends ChildViewHolder {

    private TextView tv_video_name,tv_video_time;

    public CourseChildViewHolder(View itemView) {
        super(itemView);
        tv_video_name = (TextView) itemView.findViewById(R.id.tv_video_name);
        tv_video_time = (TextView) itemView.findViewById(R.id.tv_video_time);
    }

    public void bind(final CourseDataVideo movies) {
        tv_video_name.setText(movies.getVideoName());
        tv_video_time.setText("Video- "+movies.getTimeDuration()+" mins ");


        tv_video_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String course_name=movies.getVideoName();
                Intent in = new Intent(v.getContext(), Activity_VideoPlay.class);
                in.putExtra("course_name",course_name);
                in.putExtra("course_url",movies.getVideo());
                v.getContext().startActivity(in);

            }
        });
    }
}
