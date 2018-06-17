package com.recommend.recommend.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.recommend.recommend.MainActivity;
import com.recommend.recommend.R;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

public class CourseFragment extends Fragment {
    //TODO fragment之间传递数据 在数据库中查找
    private Button btn_search;  //TODO 搜索按钮
    private EditText edit_search;  //TODO 搜索输入的内容
    private SearchFragment searchFragment;
    //public static int requestCode=0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course, container, false);
        btn_search = view.findViewById(R.id.btn_search);
        edit_search = view.findViewById(R.id.edit_search);
//        //TODO 获取数据
//        String searchKey = edit_search.getText().toString();
//        //TODO 传递数据
//        final Intent intent = new Intent();
//        intent.putExtra("searchKey",searchKey);
        //TODO 初始化搜索页面
        searchFragment = new SearchFragment();
        //TODO 获取管理器
        final FragmentManager fm = this.getFragmentManager();

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //getTargetFragment().onActivityResult(requestCode, Activity.RESULT_OK,intent);
                fm.beginTransaction()
                        .replace(R.id.container_content, searchFragment)
                        .commit();
            }
        });

        return view;
    }
}
