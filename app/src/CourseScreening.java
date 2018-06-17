import java.sql.*;
import DBManager;
public class CourseScreening {
	private ArrayList<CourseTime> exceptCourseTime;
	public ArrayList<Course> screening(string keyWord) {
		string sql = 'select Courses.* from Courses left join TimeTable using (day, startTime, endTime) where TimeTable.name is NULL';
		for(int i = 0; i < exceptCourseTime.size(); ++i) {
		  sql = 'select * from ('+ sql + ') as exceptCourseTime' + i ' where exceptCourseTime' + i +' .day !=' + exceptCourseTime[i].day + 'exceptCourseTime' + i +' .startTime !=' + exceptCourseTime[i].startTime +  'exceptCourseTime' + i +' .endTime !=' + exceptCourseTime[i].endTime;
		}
		DBManager db = DBManager.createInstance();
		db.connectDB();
		ResultSet rs = db.excuteQuery(sql);
		ResultSetMetaData rsmd = rs.getMetaData();
		ArrayList<Course> temp = new ArrayList<Course>();
		while(rs.hasNext()){  //结果集中有记录
           Course course = new Course();
           course.name = rs.getString("Name");
           course.type = rs.getString("Type");
		   course.day = rs.getString("Day");
		   course.startTime = rs.getInt("StartTime");
		   course.endTime = rs.getInt("EndTime");
           temp.add(course);
}
		return temp;
	}
	
	public addExceptCourseTime(string day, int startTime, int endTime) {
		CourseTime except = new CourseTime(day,startTime,endTime); 
		exceptCourseTime.add(except);
	}
}
class Course {
	public string name;
	public string type;
	public string day;
	public int startTime;
	public int endTime;
}
class CourseTime {
	public string day;
	public int startTime;
	public int endTime;
	
	CourseTime(string day, int startTime, int endTime) {
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
	}
}

//课程名字 类型 周次 节数