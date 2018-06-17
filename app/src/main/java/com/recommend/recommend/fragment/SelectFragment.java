package com.recommend.recommend.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.recommend.recommend.Course;
import com.recommend.recommend.CourseScreening;
import com.recommend.recommend.MainActivity;
import com.recommend.recommend.R;

import java.util.ArrayList;
import java.util.List;

public class SelectFragment extends Fragment {
    private Spinner timeSpinner;
    private Spinner typeSpinner;
    private EditText et_keyword; // TODO 输入查找关键字的EditText 获取后作为筛选条件
    private Button btn_import;  //TODO 导入个人课表的按钮 数据库中存一张需要排除的时间的表
    private Button btn_selected; //TODO 开始筛选
    private ArrayAdapter<String> timeAdapter; //TODO 筛选时间的适配器
    private ArrayAdapter<String> typeAdapter; //TODO 课程类型的适配器
    private List<String> timeList; //TODO 筛选时间的数据
    private List<String> typeList; //TODO 课程类型的数据
    public String keyWord;
    private String type;
    CourseScreening courseScreening;
    boolean subThreadIsCompleted = false;
    private ScreenFragment screenFragment;
    //private SearchFragment searchFragment;
    private ArrayList<Course> screenResult;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select,container,false);
        //TODO 控件的初始化
        timeSpinner = view.findViewById(R.id.spin_time);
        typeSpinner = view.findViewById(R.id.spin_type);
        et_keyword = view.findViewById(R.id.keyword);
        btn_import = view.findViewById(R.id.btn_import);
        btn_selected = view.findViewById(R.id.btn_selected);
        courseScreening = new CourseScreening();
        screenFragment = new ScreenFragment();
        //searchFragment = new SearchFragment();

        timeList = new ArrayList<String>();
        timeList.add("周一5/6节");
        timeList.add("周一7/8节");
        typeList = new ArrayList<String>();
        typeList.add("社会科学");
        typeList.add("人文科学");
        typeList.add("人文核心");
        typeList.add("科学技术");
        typeList.add("科学核心");
        typeList.add("社科核心");

        timeAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,timeList);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeSpinner.setAdapter(timeAdapter);
        typeAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,typeList);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);


        //TODO 两个下拉列表的监听器
        timeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                courseScreening.initExceptCourseTime();
                switch (position) {
                    case 0:
                        courseScreening.addExceptCourseTime("周一", 5, 6);
                         break;
                    case 1:
                        courseScreening.addExceptCourseTime("周一", 7, 8);
                        break;
                }
                //selectedTime.setText(timeAdapter.getItem(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                courseScreening.initExceptCourseTime();
                courseScreening.addExceptCourseTime("周一",5,6);
                //selectedTime.setText("请选择排除时间");
            }
        });
        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              switch (position) {
                  case 0:
                      type  = "社会科学";
                      break;
                  case 1:
                      type = "人文科学";
                      break;
                  case 2:
                      type = "人文核心";
                      break;
                  case 3:
                      type = "科学技术";
                      break;
                  case 4:
                      type = "科学核心";
                      break;
                  case 5:
                      type = "社科核心";
                      break;
                  default:
                      type = "社会科学";
                      break;

              }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                type = "社会科学";
            }
        });
        final FragmentManager fm = this.getFragmentManager();
        //TODO 筛选按钮的监听器
        btn_selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyWord =  et_keyword.getText().toString();
                new Thread() {
                    public void run() {
                        //Toast.makeText(SelectFragment.this.getActivity(), timeAdapter.toString(), Toast.LENGTH_SHORT).show();
                        screenResult = courseScreening.screening(keyWord, type);
                        subThreadIsCompleted = true;
                    }
                }.start();
                while (subThreadIsCompleted == false);
                //TODO 将screenResult传到MainActivity
                ((MainActivity)getActivity()).setScreenResult(screenResult);
                Log.i("TRANS",screenResult.get(0).getName());
                Log.i("TRANS","已经将screenResult传到MainActivity");

                fm.beginTransaction()
                        .replace(R.id.container_content, screenFragment)
                        .commit();
            }
        });
        btn_import.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SelectFragment.this.getActivity(),"已导入个人课表",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
