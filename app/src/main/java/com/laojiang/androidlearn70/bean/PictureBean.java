package com.laojiang.androidlearn70.bean;

import java.util.List;

/**
 * 类介绍（必填）：
 * Created by Jiang on 2017/3/14 13:14.
 */

public class PictureBean {
    @Override
    public String toString() {
        return "PictureBean{" +
                "error=" + error +
                ", results=" + results +
                '}';
    }

    /**
     * error : false
     * results : [{"_id":"58c5e184421aa90f1317864d","createdAt":"2017-03-13T08:02:12.179Z","desc":"3-13","publishedAt":"2017-03-13T12:37:59.782Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-03-13-17265708_396005157434387_3099040288153272320_n.jpg","used":true,"who":"dmj"},{"_id":"58c1f808421aa95810795c34","createdAt":"2017-03-10T08:49:12.756Z","desc":"3-10","publishedAt":"2017-03-10T11:43:50.30Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-03-10-17127037_231706780569079_1119464847537340416_n.jpg","used":true,"who":"代码家"},{"_id":"58c0ac1f421aa90f03345143","createdAt":"2017-03-09T09:13:03.655Z","desc":"3-9","publishedAt":"2017-03-09T11:42:30.849Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-03-09-17127109_1652837611687612_1425055271046086656_n.jpg","used":true,"who":"daimajia"},{"_id":"58bf522a421aa90efc4fb689","createdAt":"2017-03-08T08:36:58.695Z","desc":"3-8","publishedAt":"2017-03-08T11:27:16.65Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-03-08-17126216_1253875034703554_7520300169779216384_n.jpg","used":true,"who":"daimajia"},{"_id":"58be00ad421aa95810795c13","createdAt":"2017-03-07T08:37:01.730Z","desc":"3-7","publishedAt":"2017-03-07T11:52:11.670Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-03-07-003645.jpg","used":true,"who":"daimajia"},{"_id":"58bcae33421aa90efc4fb666","createdAt":"2017-03-06T08:32:51.490Z","desc":"3-6","publishedAt":"2017-03-06T11:17:33.140Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-03-06-tumblr_ombicf0KXi1vpybydo6_540.jpg","used":true,"who":"daimajia"},{"_id":"58b88a2f421aa90efc4fb649","createdAt":"2017-03-03T05:10:07.940Z","desc":"3-3","publishedAt":"2017-03-03T12:13:36.333Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-03-02-16906481_1495916493759925_5770648570629718016_n.jpg","used":true,"who":"daimajia"},{"_id":"58b76cb9421aa90f131785e4","createdAt":"2017-03-02T08:52:09.811Z","desc":"3-02","publishedAt":"2017-03-02T11:51:29.672Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-03-02-17077393_314135475655975_7855162741030387712_n.jpg","used":true,"who":"代码家"},{"_id":"58b60881421aa90efc4fb627","createdAt":"2017-03-01T07:32:17.106Z","desc":"3-1","publishedAt":"2017-03-01T12:03:57.461Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-02-28-15057157_446684252387131_4267811446148038656_n.jpg","used":true,"who":"daimajia"},{"_id":"58b4b764421aa90efc4fb61b","createdAt":"2017-02-28T07:33:56.976Z","desc":"2-28","publishedAt":"2017-02-28T11:45:44.815Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-02-27-tumblr_om1aowIoKa1qbw5qso1_540.jpg","used":true,"who":"daimajia"},{"_id":"58b372a7421aa90f033450e0","createdAt":"2017-02-27T08:28:23.976Z","desc":"2-27","publishedAt":"2017-02-27T11:31:40.141Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-02-27-002809.jpg","used":true,"who":"daimajia"},{"_id":"58af7dca421aa957f9dd6dcc","createdAt":"2017-02-24T08:26:50.512Z","desc":"2-24","publishedAt":"2017-02-24T11:47:11.416Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-02-24-16906635_1749384985376684_7563808952991875072_n.jpg","used":true,"who":"daimajia"},{"_id":"58ae32e1421aa957f9dd6dbe","createdAt":"2017-02-23T08:54:57.835Z","desc":"2-23","publishedAt":"2017-02-23T11:50:23.116Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-02-23-16906361_1846962082218545_7116720067412230144_n.jpg","used":true,"who":"daimajia"},{"_id":"58acdc37421aa93d376f74fd","createdAt":"2017-02-22T08:32:55.801Z","desc":"2-22","publishedAt":"2017-02-22T11:43:57.286Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-02-22-16789541_749566998525174_1194252851069583360_n.jpg","used":true,"who":"daimajia "},{"_id":"58ab0187421aa93d3d15aa3b","createdAt":"2017-02-20T22:47:35.276Z","desc":"2-21","publishedAt":"2017-02-21T11:14:09.564Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-02-20-16788512_385322578500460_8844315265740046336_n.jpg","used":true,"who":"daimajia"},{"_id":"58a9752b421aa93d3d15aa31","createdAt":"2017-02-19T18:36:27.16Z","desc":"2-20","publishedAt":"2017-02-20T11:56:22.616Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-02-19-16789884_666922016823652_4719569931841044480_n.jpg","used":true,"who":"daimajia"},{"_id":"58a641a4421aa9662f429734","createdAt":"2017-02-17T08:19:48.413Z","desc":"2-17","publishedAt":"2017-02-17T11:31:19.996Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-02-17-16464474_721724277990542_654863958657728512_n.jpg","used":true,"who":"daimajia"},{"_id":"58a504d1421aa966366d05ce","createdAt":"2017-02-16T09:48:01.526Z","desc":"2-16","publishedAt":"2017-02-16T10:07:37.13Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-02-16-16790004_607292222809583_5160406710837313536_n.jpg","used":true,"who":"daimajia"},{"_id":"58a39d1c421aa966366d05c0","createdAt":"2017-02-15T08:13:16.351Z","desc":"2-15","publishedAt":"2017-02-15T11:24:04.127Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-02-15-16464434_414363458902323_3665858302006263808_n.jpg","used":true,"who":"daimajia"},{"_id":"58a24ce0421aa901ef40579f","createdAt":"2017-02-14T08:18:40.781Z","desc":"2-14","publishedAt":"2017-02-14T11:42:37.303Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-02-14-16123260_755771577930478_8918176595718438912_n.jpg","used":true,"who":"daimajia"}]
     */

    private boolean error;
    private List<ResultsEntity> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsEntity> getResults() {
        return results;
    }

    public void setResults(List<ResultsEntity> results) {
        this.results = results;
    }

    public static class ResultsEntity {
        @Override
        public String toString() {
            return "ResultsEntity{" +
                    "_id='" + _id + '\'' +
                    ", createdAt='" + createdAt + '\'' +
                    ", desc='" + desc + '\'' +
                    ", publishedAt='" + publishedAt + '\'' +
                    ", source='" + source + '\'' +
                    ", type='" + type + '\'' +
                    ", url='" + url + '\'' +
                    ", used=" + used +
                    ", who='" + who + '\'' +
                    '}';
        }

        /**
         * _id : 58c5e184421aa90f1317864d
         * createdAt : 2017-03-13T08:02:12.179Z
         * desc : 3-13
         * publishedAt : 2017-03-13T12:37:59.782Z
         * source : chrome
         * type : 福利
         * url : http://7xi8d6.com1.z0.glb.clouddn.com/2017-03-13-17265708_396005157434387_3099040288153272320_n.jpg
         * used : true
         * who : dmj
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }
}
