package com.laojiang.androidlearn70.bean;

import com.google.gson.annotations.SerializedName;
import com.laojiang.retrofithttp.weight.bean.BaseReponseResult;

import java.util.List;

/**
 * 类介绍（必填）：
 * Created by Jiang on 2017/3/14 9:08.
 */

public class TestRepair extends BaseReponseResult{

    /**
     * result : {"totalCount":2,"pageSize":20,"pageNo":1,"condition":null,"sort":null,"order":null,"list":[{"apply_time":"2017-03-06","category_id":1,"id":60,"plat_id":12,"categoryName":"电脑","applyUser":"张亭宇","evaluation_state":0,"charge_user":33,"repair_state":0,"apply_user":33,"description":"请解","name":"错误显示","chargeName":"张亭宇","contect_Info":"69696969"},{"apply_time":"2017-02-27","category_id":1,"id":58,"plat_id":12,"categoryName":"电脑","applyUser":"徐统","evaluation_state":0,"charge_user":33,"repair_state":0,"apply_user":34,"description":"呵呵","name":"小新","chargeName":"张亭宇","contect_Info":"15757188631"}],"totalPage":1,"firstPage":true,"lastPage":true,"nextPage":1,"pretPage":1,"firstResult":0}
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
        /**
         * totalCount : 2
         * pageSize : 20
         * pageNo : 1
         * condition : null
         * sort : null
         * order : null
         * list : [{"apply_time":"2017-03-06","category_id":1,"id":60,"plat_id":12,"categoryName":"电脑","applyUser":"张亭宇","evaluation_state":0,"charge_user":33,"repair_state":0,"apply_user":33,"description":"请解","name":"错误显示","chargeName":"张亭宇","contect_Info":"69696969"},{"apply_time":"2017-02-27","category_id":1,"id":58,"plat_id":12,"categoryName":"电脑","applyUser":"徐统","evaluation_state":0,"charge_user":33,"repair_state":0,"apply_user":34,"description":"呵呵","name":"小新","chargeName":"张亭宇","contect_Info":"15757188631"}]
         * totalPage : 1
         * firstPage : true
         * lastPage : true
         * nextPage : 1
         * pretPage : 1
         * firstResult : 0
         */

        private int totalCount;
        private int pageSize;
        private int pageNo;
        private Object condition;
        private Object sort;
        private Object order;
        private int totalPage;
        private boolean firstPage;
        private boolean lastPage;
        private int nextPage;
        private int pretPage;
        private int firstResult;
        private List<ListEntity> list;

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public Object getCondition() {
            return condition;
        }

        public void setCondition(Object condition) {
            this.condition = condition;
        }

        public Object getSort() {
            return sort;
        }

        public void setSort(Object sort) {
            this.sort = sort;
        }

        public Object getOrder() {
            return order;
        }

        public void setOrder(Object order) {
            this.order = order;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public boolean isFirstPage() {
            return firstPage;
        }

        public void setFirstPage(boolean firstPage) {
            this.firstPage = firstPage;
        }

        public boolean isLastPage() {
            return lastPage;
        }

        public void setLastPage(boolean lastPage) {
            this.lastPage = lastPage;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public int getPretPage() {
            return pretPage;
        }

        public void setPretPage(int pretPage) {
            this.pretPage = pretPage;
        }

        public int getFirstResult() {
            return firstResult;
        }

        public void setFirstResult(int firstResult) {
            this.firstResult = firstResult;
        }

        public List<ListEntity> getList() {
            return list;
        }

        public void setList(List<ListEntity> list) {
            this.list = list;
        }

        public static class ListEntity {
            /**
             * apply_time : 2017-03-06
             * category_id : 1
             * id : 60
             * plat_id : 12
             * categoryName : 电脑
             * applyUser : 张亭宇
             * evaluation_state : 0
             * charge_user : 33
             * repair_state : 0
             * apply_user : 33
             * description : 请解
             * name : 错误显示
             * chargeName : 张亭宇
             * contect_Info : 69696969
             */

            private String apply_time;
            private int category_id;
            private int id;
            private int plat_id;
            private String categoryName;
            private String applyUser;
            private int evaluation_state;
            private int charge_user;
            private int repair_state;
            private int apply_user;
            private String description;
            private String name;
            private String chargeName;
            private String contect_Info;

            public String getApply_time() {
                return apply_time;
            }

            public void setApply_time(String apply_time) {
                this.apply_time = apply_time;
            }

            public int getCategory_id() {
                return category_id;
            }

            public void setCategory_id(int category_id) {
                this.category_id = category_id;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getPlat_id() {
                return plat_id;
            }

            public void setPlat_id(int plat_id) {
                this.plat_id = plat_id;
            }

            public String getCategoryName() {
                return categoryName;
            }

            public void setCategoryName(String categoryName) {
                this.categoryName = categoryName;
            }

            public String getApplyUser() {
                return applyUser;
            }

            public void setApplyUser(String applyUser) {
                this.applyUser = applyUser;
            }

            public int getEvaluation_state() {
                return evaluation_state;
            }

            public void setEvaluation_state(int evaluation_state) {
                this.evaluation_state = evaluation_state;
            }

            public int getCharge_user() {
                return charge_user;
            }

            public void setCharge_user(int charge_user) {
                this.charge_user = charge_user;
            }

            public int getRepair_state() {
                return repair_state;
            }

            public void setRepair_state(int repair_state) {
                this.repair_state = repair_state;
            }

            public int getApply_user() {
                return apply_user;
            }

            public void setApply_user(int apply_user) {
                this.apply_user = apply_user;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getChargeName() {
                return chargeName;
            }

            public void setChargeName(String chargeName) {
                this.chargeName = chargeName;
            }

            public String getContect_Info() {
                return contect_Info;
            }

            public void setContect_Info(String contect_Info) {
                this.contect_Info = contect_Info;
            }
        }
    }
}
