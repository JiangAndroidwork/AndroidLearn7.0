package com.laojiang.androidlearn70.bean.mydownfiles;

/**
 * 类介绍（必填）：
 * Created by Jiang on 2017/3/22 15:38.
 */

public class DownFilesInfo {
    private String urlStr;
    private String outStr;

    public DownFilesInfo(String urlStr, String outStr) {
        this.urlStr = urlStr;
        this.outStr = outStr;
    }

    public String getOutStr() {
        return outStr;
    }

    public void setOutStr(String outStr) {
        this.outStr = outStr;
    }

    public String getUrlStr() {
        return urlStr;
    }

    public void setUrlStr(String urlStr) {
        this.urlStr = urlStr;
    }
}
