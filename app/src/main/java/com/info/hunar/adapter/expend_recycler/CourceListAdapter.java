package com.info.hunar.adapter.expend_recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.info.hunar.R;
import com.info.hunar.model_pojo.subcategory_course_model.CourseData;
import com.info.hunar.model_pojo.subcategory_course_model.CourseDataVideo;

import java.util.List;

/**
 * Created by Raghvendra Sahu on 19-Jan-20.
 */
public class CourceListAdapter extends ExpandableRecyclerAdapter<CourseParentViewHolder, CourseChildViewHolder> {

    private LayoutInflater mInflator;

    public CourceListAdapter(Context context, List<? extends ParentListItem> parentItemList) {
        super(parentItemList);
        mInflator = LayoutInflater.from(context);
    }

    @Override
    public CourseParentViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        View movieCategoryView = mInflator.inflate(R.layout.course_list_item, parentViewGroup, false);
        return new CourseParentViewHolder(movieCategoryView);
    }

    @Override
    public CourseChildViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
        View moviesView = mInflator.inflate(R.layout.course_child_item, childViewGroup, false);
        return new CourseChildViewHolder(moviesView);
    }

    @Override
    public void onBindParentViewHolder(CourseParentViewHolder movieCategoryViewHolder, int position, ParentListItem parentListItem) {
        CourseData movieCategory = (CourseData) parentListItem;
        movieCategoryViewHolder.bind(movieCategory);
    }

    @Override
    public void onBindChildViewHolder(CourseChildViewHolder moviesViewHolder, int position, Object childListItem) {
        CourseDataVideo movies = (CourseDataVideo) childListItem;
        moviesViewHolder.bind(movies);



    }
}
