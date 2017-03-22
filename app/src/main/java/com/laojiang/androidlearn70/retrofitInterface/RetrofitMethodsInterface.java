package com.laojiang.androidlearn70.retrofitInterface;


import com.laojiang.androidlearn70.bean.PictureBean;
import com.laojiang.androidlearn70.bean.TestBeanSS;
import com.laojiang.androidlearn70.bean.TestCourseList;
import com.laojiang.androidlearn70.bean.TestHttp;
import com.laojiang.androidlearn70.bean.TestRepair;
import com.laojiang.retrofithttp.weight.bean.BaseReponseResult;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 类介绍（必填）：
 * Created by Jiang on 2017/3/9 10:15.
 */

public interface RetrofitMethodsInterface {
    @GET("top250")
    Flowable<TestHttp> getRetrofitData(@Query("start") int start, @Query("count") int count);
    @GET("getGradeExams")
    Flowable<TestBeanSS> getRetrofitDatas(@Query("accessToken") String accessToken);
    @GET("chargeGetRepairList")
    Flowable<BaseReponseResult<List<TestRepair.ResultEntity>>> getRepair(@Query("pageNo") int pageNo,
                                                                        @Query("repairState") int repairState,
                                                                        @Query("pageSize") int pageSize,
                                                                        @Query("accessToken") String accessToken);
    @GET("getSemesterWeekBF")
    Flowable<BaseReponseResult<TestCourseList.ResultEntity>> getCourse(@Query("accessToken") String accessToken);
    @GET("api/data/福利/{count}/{pageNo}")
    Flowable<PictureBean> getPicture(@Path("count") int count,@Path("pageNo") int pageNo);
}
