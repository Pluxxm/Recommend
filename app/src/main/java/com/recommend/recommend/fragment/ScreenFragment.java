package com.recommend.recommend.fragment;

import android.app.Fragment;
import android.content.Intent;
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
    private Button btn_return;
    private List<LinearLayout> linearLayouts;
    private ArrayList<Course> screenResult;
    private LinearLayout mLinearLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_screen, container, false);
        btn_return = view.findViewById(R.id.btn_return);
        mLinearLayout = view.findViewById(R.id.linearLayout);

        initView();
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void initView() {
        screenResult = ((MainActivity)getActivity()).screenResult;
        if (screenResult != null) {
            int size = screenResult.size();
            Log.i("SIZE", "传过来的筛选数组大小为：" + size);
            for (int i = 0; i < size; ++i) {
                //TODO 设置线性布局的基本属性
                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setId(i);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                linearLayout.setGravity(Gravity.CENTER_HORIZONTAL);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams
                        (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(25, 20, 25, 0);
                linearLayout.setLayoutParams(lp);

                //TODO 设置文本控件的基本属性
                TextView tv_1 = new TextView(getActivity());
                tv_1.setText(screenResult.get(i).getName());
                tv_1.setTextColor(0xffffff);
                tv_1.setTextSize(20);
                Log.i("SIZE", "第" + i + "个课程名字是" + screenResult.get(i).getName());
                LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams
                        (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp1.setMargins(0, 20, 0, 5);
                tv_1.setLayoutParams(lp1);
                linearLayout.addView(tv_1);
                //TODO 中间的分割线
                View line = new View(getActivity());
                line.setBackgroundColor(0xffffff);
                LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams
                        (250, 1);
                lp2.setMargins(5, 5, 5, 5);
                line.setLayoutParams(lp2);
                linearLayout.addView(line);
                //TODO 第二个文本控件
                TextView tv_2 = new TextView(getActivity());
                tv_2.setText(screenResult.get(i).getType());
                tv_2.setTextColor(0xffffff);
                tv_2.setTextSize(20);
                Log.i("SIZE", "第" + i + "个课程类型是" + screenResult.get(i).getType());
                tv_2.setLayoutParams(lp1);
                linearLayout.addView(tv_2);

                switch (screenResult.get(i).getType()) {
                    case "社会科学":
                        linearLayout.setBackgroundColor(0x7BBFC0);
                        break;
                    case "人文核心":
                        linearLayout.setBackgroundColor(0xE59CA5);
                        break;
                    case "科学技术":
                        linearLayout.setBackgroundColor(0xF6CF94);
                        break;
                    case "人文科学":
                        linearLayout.setBackgroundColor(0xE59CA5);
                        break;
                    case "科学核心":
                        linearLayout.setBackgroundColor(0xF6CF94);
                        break;
                    case "社科核心":
                        linearLayout.setBackgroundColor(0x7BBFC0);
                        break;
                    default:
                        break;

                }
                //RelativeLayout.LayoutParams layoutParams;
                mLinearLayout.addView(linearLayout);
            }
        }
    }
}
