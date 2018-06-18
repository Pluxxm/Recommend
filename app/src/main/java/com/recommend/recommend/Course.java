package com.recommend.recommend;

public class Course {

        public String name;
        public String type;
        public String day;
        public int startTime;
        public int endTime;

    public Course() {
    }

    public Course(String name, String type, String day, int startTime, int endTime) {
                this.name = name;
                this.type = type;
                this.day = day;
                this.startTime = startTime;
                this.endTime = endTime;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getType() {
                return type;
        }

        public void setType(String type) {
                this.type = type;
        }

        public String getDay() {
                return day;
        }

        public void setDay(String day) {
                this.day = day;
        }

        public int getStartTime() {
                return startTime;
        }

        public void setStartTime(int startTime) {
                this.startTime = startTime;
        }

        public int getEndTime() {
                return endTime;
        }

        public void setEndTime(int endTime) {
                this.endTime = endTime;
        }
}
