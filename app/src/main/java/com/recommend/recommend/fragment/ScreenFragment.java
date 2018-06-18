package com.recommend.recommend.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.recommend.recommend.Course;
import com.recommend.recommend.MainActivity;
import com.recommend.recommend.R;

import java.util.ArrayList;
import java.util.List;

public class ScreenFragment extends Fragment {
    //private Button btn_return;
    //private List<LinearLayout> linearLayouts;
    private ArrayList<Course> screenResult;
    //private LinearLayout mLinearLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_screen, container, false);
        //btn_return = view.findViewById(R.id.btn_return);
        //mLinearLayout = view.findViewById(R.id.linearLayout);
        //getActivity().setContentView(R.layout.fragment_screen);

        networkView();
//        btn_return.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), MainActivity.class);
//                startActivity(intent);
//            }
//        });
        return view;
    }

    //TODO ConvertView 网上的代码
    private void ConverView(){
        final LinearLayout mask = new LinearLayout(getActivity());
        mask.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams maskParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mask.setBackgroundColor(0x9bc8ea);

        Button btn = new Button(getActivity());
        ViewGroup.LayoutParams btnParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        btn.setText("返  回");
        btn.setBackgroundColor(0xffffff);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        mask.addView(btn,btnParams);
        //addView是在对应的布局上增加View
        //是在原有的视图上覆盖一个视图(就是这里的截图2上的)
        getActivity().setContentView(mask,maskParams);
        //mask.removeView(btn);
    }

    //TODO 网上的代码
    private void networkView() {
        screenResult = ((MainActivity)getActivity()).screenResult;
        //TODO 最底层布局
        LinearLayout layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);
        //TODO 返回按钮条目的LinearLayout
        LinearLayout mask = new LinearLayout(getActivity());
        mask.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams maskParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mask.setBackgroundColor(getResources().getColor(R.color.returnItem));
        //TODO 返回按钮控件初始化
        Button btn = new Button(getActivity());
        LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        btnParams.setMargins(15,20,15,20);
        btn.setGravity(Gravity.CENTER);
        btn.setText("返  回");
        btn.setBackgroundColor(getResources().getColor(R.color.white));
        //TODO 返回按钮控件监听器设置
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        //TODO 将返回按钮添加到条目的LinearLayout中
        mask.addView(btn,btnParams);
        //TODO 将返回按钮的条目添加到最底层布局中
        layout.addView(mask,maskParams);
        //TODO 一共有screenResult.size()行
        for (int i = 0; i < screenResult.size(); i++) {
            //布局是LinerLayout的话这里一般就是使用LinearLayout.LayoutParams什么布局使用什么
            LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            //TODO 一行的LinearLayout
            LinearLayout lLayout = new LinearLayout(getActivity());
            lLayout.setId(i + 10);
            lLayout.setOrientation(LinearLayout.VERTICAL);
            lLayout.setGravity(Gravity.CENTER_HORIZONTAL);
            llParams.setMargins(50, 50, 50, 0);

            //lLayout.setLayoutParams(lLayoutlayoutParams);

            TextView tv = new TextView(getActivity());
            tv.setId(i);
            tv.setText(screenResult.get(i).getName());
            tv.setTextColor(getResources().getColor(R.color.white));
            tv.setTextSize(20);
            tv.setGravity(Gravity.CENTER_HORIZONTAL);
            Log.i("SIZE", "第" + i + "个课程名字是" + screenResult.get(i).getName());
            LinearLayout.LayoutParams lp1_txt = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp1_txt.setMargins(0, 50, 0, 25);
            tv.setLayoutParams(lp1_txt);
            Log.i("TEXT", "第" + i + "个课程名字是" + tv.getText());
            lLayout.addView(tv, lp1_txt);
            //TODO 中间的分割线
            View line = new View(getActivity());
            line.setBackgroundColor(getResources().getColor(R.color.white));
            LinearLayout.LayoutParams lp2_line = new LinearLayout.LayoutParams
                    (250, 1);
            lp2_line.setMargins(5, 20, 5, 20);
            line.setLayoutParams(lp2_line);
            lLayout.addView(line,lp2_line);
            //TODO 第二个文本控件
            TextView tv_2 = new TextView(getActivity());
            tv_2.setText(screenResult.get(i).getType());
            tv_2.setTextColor(getResources().getColor(R.color.white));
            tv_2.setTextSize(20);
            tv_2.setGravity(Gravity.CENTER_HORIZONTAL);
            Log.i("SIZE", "第" + i + "个课程类型是" + screenResult.get(i).getType());
            LinearLayout.LayoutParams lp2_txt = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp2_txt.setMargins(0, 25, 0, 50);
            tv_2.setLayoutParams(lp2_txt);
            lLayout.addView(tv_2,lp2_txt);;

            switch (screenResult.get(i).getType()) {
                case "社会科学":
                    lLayout.setBackgroundColor(getResources().getColor(R.color.social));
                    break;
                case "人文核心":
                    lLayout.setBackgroundColor(getResources().getColor(R.color.culture));
                    break;
                case "科学技术":
                    lLayout.setBackgroundColor(getResources().getColor(R.color.science));
                    break;
                case "人文科学":
                    lLayout.setBackgroundColor(getResources().getColor(R.color.culture));
                    break;
                case "科学核心":
                    lLayout.setBackgroundColor(getResources().getColor(R.color.science));
                    break;
                case "社科核心":
                    lLayout.setBackgroundColor(getResources().getColor(R.color.social));
                    break;
                default:
                    break;
            }
            // 添加到每行的linearlayout中
            //lLayout.addView(tv);
            //lLayout.addView(tv_2);
            layout.addView(lLayout, llParams);

        }
        //就是Activity上显示的最底层的View
        getActivity().setContentView(layout);
    }

//    private void initView() {
//        screenResult = ((MainActivity)getActivity()).screenResult;
////        LinearLayout.LayoutParams lp_m = new LinearLayout.LayoutParams
////                (LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
////        mLinearLayout.setOrientation(LinearLayout.VERTICAL);
//        if (screenResult != null) {
//            int size = screenResult.size();
//            Log.i("SIZE", "传过来的筛选数组大小为：" + size);
//            for (int i = 0; i < size; ++i) {
//                //TODO 设置线性布局的基本属性
//                LinearLayout linearLayout = new LinearLayout(getActivity());
//                linearLayout.setId(i);
//                linearLayout.setOrientation(LinearLayout.VERTICAL);
//                linearLayout.setGravity(Gravity.CENTER_HORIZONTAL);
//                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams
//                        (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//                lp.setMargins(25, 20, 25, 0);
//                linearLayout.setLayoutParams(lp);
//
//                //TODO 设置文本控件的基本属性
//                TextView tv_1 = new TextView(getActivity());
//                tv_1.setText(screenResult.get(i).getName());
//                tv_1.setTextColor(0xffffff);
//                tv_1.setTextSize(20);
//                Log.i("SIZE", "第" + i + "个课程名字是" + screenResult.get(i).getName());
//                LinearLayout.LayoutParams lp1_txt = new LinearLayout.LayoutParams
//                        (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//                lp1_txt.setMargins(0, 20, 0, 5);
//                tv_1.setLayoutParams(lp1_txt);
//                Log.i("TEXT", "第" + i + "个课程名字是" + tv_1.getText());
//                linearLayout.addView(tv_1, lp1_txt);
//                //TODO 中间的分割线
//                View line = new View(getActivity());
//                line.setBackgroundColor(0xffffff);
//                LinearLayout.LayoutParams lp2_line = new LinearLayout.LayoutParams
//                        (250, 1);
//                lp2_line.setMargins(5, 5, 5, 5);
//                line.setLayoutParams(lp2_line);
//                linearLayout.addView(line,lp2_line);
//                //TODO 第二个文本控件
//                TextView tv_2 = new TextView(getActivity());
//                tv_2.setText(screenResult.get(i).getType());
//                tv_2.setTextColor(0xffffff);
//                tv_2.setTextSize(20);
//                Log.i("SIZE", "第" + i + "个课程类型是" + screenResult.get(i).getType());
//                tv_2.setLayoutParams(lp1_txt);
//                linearLayout.addView(tv_2,lp1_txt);
//
//                switch (screenResult.get(i).getType()) {
//                    case "社会科学":
//                        linearLayout.setBackgroundColor(0x7BBFC0);
//                        break;
//                    case "人文核心":
//                        linearLayout.setBackgroundColor(0xE59CA5);
//                        break;
//                    case "科学技术":
//                        linearLayout.setBackgroundColor(0xF6CF94);
//                        break;
//                    case "人文科学":
//                        linearLayout.setBackgroundColor(0xE59CA5);
//                        break;
//                    case "科学核心":
//                        linearLayout.setBackgroundColor(0xF6CF94);
//                        break;
//                    case "社科核心":
//                        linearLayout.setBackgroundColor(0x7BBFC0);
//                        break;
//                    default:
//                        break;
//
//                }
//                //RelativeLayout.LayoutParams layoutParams;
//                //mLinearLayout.addView(linearLayout,lp);
//                getActivity().addContentView(linearLayout,lp);
//            }
//            //getActivity().addContentView(mLinearLayout,lp_m);
//
//        }
//    }
}
