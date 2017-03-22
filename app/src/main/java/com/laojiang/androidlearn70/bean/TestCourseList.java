package com.laojiang.androidlearn70.bean;

import com.google.gson.annotations.SerializedName;
import com.laojiang.retrofithttp.weight.bean.BaseReponseResult;

/**
 * 类介绍（必填）：
 * Created by Jiang on 2017/3/14 9:47.
 */

public class TestCourseList  extends BaseReponseResult{

    @Override
    public String toString() {
        return "TestCourseList{" +
                "resultX=" + resultX +
                '}';
    }

    /**
     * result : {"days":"2017-03-13 ~ 2017-03-19","semester":{"id":4,"platId":2,"gradeYear":"2016","gradeCode":"2","name":"2016-2017第二学期","startTime":1489075200000,"firstWeek":22},"week":2}
     */

    @SerializedName("result")
    private ResultEntity resultX;

    public ResultEntity getResultX() {
        return resultX;
    }

    public void setResultX(ResultEntity resultX) {
        this.resultX = resultX;
    }

    public static class ResultEntity {
        @Override
        public String toString() {
            return "ResultEntity{" +
                    "days='" + days + '\'' +
                    ", semester=" + semester +
                    ", week=" + week +
                    '}';
        }

        /**
         * days : 2017-03-13 ~ 2017-03-19
         * semester : {"id":4,"platId":2,"gradeYear":"2016","gradeCode":"2","name":"2016-2017第二学期","startTime":1489075200000,"firstWeek":22}
         * week : 2
         */

        private String days;
        private SemesterEntity semester;
        private int week;

        public String getDays() {
            return days;
        }

        public void setDays(String days) {
            this.days = days;
        }

        public SemesterEntity getSemester() {
            return semester;
        }

        public void setSemester(SemesterEntity semester) {
            this.semester = semester;
        }

        public int getWeek() {
            return week;
        }

        public void setWeek(int week) {
            this.week = week;
        }

        public static class SemesterEntity {
            @Override
            public String toString() {
                return "SemesterEntity{" +
                        "id=" + id +
                        ", platId=" + platId +
                        ", gradeYear='" + gradeYear + '\'' +
                        ", gradeCode='" + gradeCode + '\'' +
                        ", name='" + name + '\'' +
                        ", startTime=" + startTime +
                        ", firstWeek=" + firstWeek +
                        '}';
            }

            /**
             * id : 4
             * platId : 2
             * gradeYear : 2016
             * gradeCode : 2
             * name : 2016-2017第二学期
             * startTime : 1489075200000
             * firstWeek : 22
             */

            private int id;
            private int platId;
            private String gradeYear;
            private String gradeCode;
            private String name;
            private long startTime;
            private int firstWeek;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getPlatId() {
                return platId;
            }

            public void setPlatId(int platId) {
                this.platId = platId;
            }

            public String getGradeYear() {
                return gradeYear;
            }

            public void setGradeYear(String gradeYear) {
                this.gradeYear = gradeYear;
            }

            public String getGradeCode() {
                return gradeCode;
            }

            public void setGradeCode(String gradeCode) {
                this.gradeCode = gradeCode;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public long getStartTime() {
                return startTime;
            }

            public void setStartTime(long startTime) {
                this.startTime = startTime;
            }

            public int getFirstWeek() {
                return firstWeek;
            }

            public void setFirstWeek(int firstWeek) {
                this.firstWeek = firstWeek;
            }
        }
    }
}
