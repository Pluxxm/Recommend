package com.recommend.recommend;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.recommend.recommend.fragment.CourseFragment;
import com.recommend.recommend.fragment.ScreenFragment;
import com.recommend.recommend.fragment.SearchFragment;
import com.recommend.recommend.fragment.SelectFragment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener {
    //TODO 点击事件控件初始化
    private LinearLayout mMenuCourse;
    private LinearLayout mMenuSelect;
    private Button btn_course;
    private Button btn_select;
    private CourseFragment courseFragment;  //TODO 课程的列表页面
    private SelectFragment selectFragment;  //TODO 筛选页面
    private SearchFragment searchFragment;  //TODO 搜索页面
   //public ScreenFragment screenFragment;  //TODO 筛选后页面
    public static ArrayList<Course> screenResult = new ArrayList<>();  //TODO 筛选结果存储


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //TODO 初始化事务
        this.getFragmentManager()
                .beginTransaction()
                .add(R.id.container_content,courseFragment)
                .add(R.id.container_content,searchFragment)
                .add(R.id.container_content,selectFragment)
                //.add(R.id.container_content,screenFragment)
                .hide(searchFragment)
                .hide(selectFragment)
                //.hide(screenFragment)
                .commit();

    }
    public void setScreenResult(ArrayList<Course> screenResult){
        this.screenResult = screenResult;
        Log.i("TRANS",screenResult.get(0).getName());
        //Bundle bundle = new Bundle();
        //bundle.putSerializable("screenResult", this.screenResult);
        //screenFragment.setArguments(bundle);
        Log.i("TRANS","MainActivity已经收到screenResult并传到了screenFragment");
    }

//    public ScreenFragment getScreenFragment(){
//        return screenFragment;
//    }
    /**
     * TODO 初始化视图
     */
    public void initView(){
        mMenuCourse=(LinearLayout)findViewById(R.id.menu_course);
        mMenuSelect=(LinearLayout)findViewById(R.id.menu_select);
        btn_course=findViewById(R.id.btn_course);
        btn_select=findViewById(R.id.btn_select);
        courseFragment = new CourseFragment();
        selectFragment = new SelectFragment();
        searchFragment = new SearchFragment();
        //screenFragment = new ScreenFragment();

        mMenuCourse.setOnClickListener(this);
        mMenuSelect.setOnClickListener(this);
        btn_course.setOnClickListener(this);
        btn_select.setOnClickListener(this);
    }

    /**
     * 点击事件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.menu_course:
                this.getFragmentManager()
                        .beginTransaction()
                        .show(courseFragment)
                        .hide(selectFragment)
                        .hide(searchFragment)
                        //.hide(screenFragment)
                        .commit();

                btn_course.setBackgroundResource(R.drawable.menu_course_iconclicked_selector);
                btn_select.setBackgroundResource(R.drawable.menu_select_icon_selector);
                break;
            case R.id.menu_select:
                this.getFragmentManager()
                        .beginTransaction()
                        .hide(courseFragment)
                        .show(selectFragment)
                        .hide(searchFragment)
                        //.hide(screenFragment)
                        .commit();
                btn_select.setBackgroundResource(R.drawable.menu_select_iconclicked_selector);
                btn_course.setBackgroundResource(R.drawable.menu_course_icon_selector);
                break;
            case R.id.btn_course:
                this.getFragmentManager()
                        .beginTransaction()
                        .show(courseFragment)
                        .hide(selectFragment)
                        .hide(searchFragment)
                        //.hide(screenFragment)
                        .commit();
                btn_course.setBackgroundResource(R.drawable.menu_course_iconclicked_selector);
                btn_select.setBackgroundResource(R.drawable.menu_select_icon_selector);
                break;
            case R.id.btn_select:
                this.getFragmentManager()
                        .beginTransaction()
                        .hide(courseFragment)
                        .show(selectFragment)
                        .hide(searchFragment)
                        //.hide(screenFragment)
                        .commit();
                btn_select.setBackgroundResource(R.drawable.menu_select_iconclicked_selector);
                btn_course.setBackgroundResource(R.drawable.menu_course_icon_selector);
                break;
            default:
                break;
        }
    }

}


