package com.recommend.recommend;
import java.util.ArrayList;
import java.sql.*;
//import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
public class CourseScreening {
	private ArrayList<CourseTime> exceptCourseTime;
	//private String keyword;
	//private String type;
	//public void setKeyword(String key) {
	//	keyword = key;
	//}
	//public void setType(String t) {
	//	type = t;
	//}
	public CourseScreening() {
		exceptCourseTime = new ArrayList<CourseTime>();
	}
	public ArrayList<Course> screening(String keyWord, String type) {
		String sql = "select Courses.* from Courses left join TimeTable using (day, startTime, endTime) where TimeTable.name is NULL";
		for(int i = 0; i < exceptCourseTime.size(); ++i) {
		  sql = "select * from ("+ sql + ") as exceptCourseTime" + i
				  +" where exceptCourseTime" + i +" .day !='"
				  + exceptCourseTime.get(i).day + "' and exceptCourseTime" + i +" .startTime !="
				  + exceptCourseTime.get(i).startTime +  " and exceptCourseTime" + i +" .endTime !="
				  + exceptCourseTime.get(i).endTime;
		}
		sql = "select * from (" + sql + ") as Final where Final.name like '%"+keyWord+"%' and Final.type = '" + type + "';";
		DBManager db = DBManager.createInstance();
		db.connectDB();
		ResultSet rs = db.executeQuery(sql);
		//ResultSetMetaData rsmd = rs.getMetaData();
		ArrayList<Course> temp = new ArrayList<Course>();
		if(rs == null) return temp;
		try {
			while (rs.next()) {  //结果集中有记录
				Course course = new Course();
				course.name = rs.getString("Name");
				course.type = rs.getString("Type");
				course.day = rs.getString("Day");
				course.startTime = rs.getInt("StartTime");
				course.endTime = rs.getInt("EndTime");
				temp.add(course);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}

	public void addExceptCourseTime(String day, int startTime, int endTime) {
		CourseTime except = new CourseTime(day,startTime,endTime);
		exceptCourseTime.add(except);
	}

	public void initExceptCourseTime() {
	    exceptCourseTime.clear();
    }
}

class CourseTime{
	public String day;
	public int startTime;
	public int endTime;
	
	CourseTime(String day, int startTime, int endTime) {
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
	}
}

//课程名字 类型 周次 节数