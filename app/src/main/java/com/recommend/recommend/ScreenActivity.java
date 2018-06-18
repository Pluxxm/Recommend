package com.recommend.recommend;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class ScreenActivity extends AppCompatActivity{

    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);
        toolbar = findViewById(R.id.toolbar);
        setTitle("");
        setSupportActionBar(toolbar);

        RecyclerView Container = findViewById(R.id.Container);
        ContainerAdapter adapter = new ContainerAdapter(this,MainActivity.screenResult);
        Container.setAdapter(adapter);
        Container.setLayoutManager(new LinearLayoutManager(this));
        initListener();


    }



    private void initListener(){
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

class ContainerAdapter extends RecyclerView.Adapter<MyViewHodler>{

    List<Course> courseList;
    Context context;

    public ContainerAdapter(Context context,List<Course> courseList){
        this.context = context;
        this.courseList = courseList;
    }

    @NonNull
    @Override
    public MyViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHodler(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_result,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHodler holder, int position) {

        Course course = courseList.get(position);
        holder.courseName.setText(courseList.get(position).getName());
        holder.courseType.setText(courseList.get(position).getType());
        switch (course.getType()){
            case "社会科学":
                holder.root.setBackgroundColor(context.getResources().getColor(R.color.social));
                break;
            case "人文核心":
                holder.root.setBackgroundColor(context.getResources().getColor(R.color.culture));
                break;
            case "科学技术":
                holder.root.setBackgroundColor(context.getResources().getColor(R.color.science));
                break;
            case "人文科学":
                holder.root.setBackgroundColor(context.getResources().getColor(R.color.culture));
                break;
            case "科学核心":
                holder.root.setBackgroundColor(context.getResources().getColor(R.color.science));
                break;
            case "社科核心":
                holder.root.setBackgroundColor(context.getResources().getColor(R.color.social));
        }
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }
}

class MyViewHodler extends RecyclerView.ViewHolder{

    TextView courseName;
    TextView courseType;
    RelativeLayout root;

    public MyViewHodler(View itemView) {
        super(itemView);
        root = itemView.findViewById(R.id.root);
        courseName = itemView.findViewById(R.id.tv_course_name);
        courseType = itemView.findViewById(R.id.tv_course_type);


    }
}



