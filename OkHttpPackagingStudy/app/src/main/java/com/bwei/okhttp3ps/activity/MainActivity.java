package com.bwei.okhttp3ps.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.okhttp3ps.R;
import com.bwei.okhttp3ps.bean.DataObj;
import com.bwei.okhttp3ps.bean.News;
import com.bwei.okhttp3ps.bean.User;
import com.bwei.okhttp3ps.utils.GsonArrayCallback;
import com.bwei.okhttp3ps.utils.GsonObjectCallback;
import com.bwei.okhttp3ps.utils.NetWorkUtils;
import com.bwei.okhttp3ps.utils.OkHttp3Utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    String url = "http://www.93.gov.cn/93app/data.do?";
    String picUrl = "http://169.254.249.24:8080/08web/FileUploadServlet";
    String apkPath = "http://d.988wan.com/zft/qmzft32_988wan_01.apk";
    String apkUrl = "http://shouji.360tpcdn.com/160804/a05b75b7779f7a4afae83601c195ed2b/com.qihoo.haosou_708.apk";
    private TextView tv, tv_result;
    String uri = "https://api.github.com/repos/square/retrofit/contributors";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        tv_result = (TextView) findViewById(R.id.tv_result);
        boolean netWorkAvailable = NetWorkUtils.isNetWorkAvailable(this);
        if (!netWorkAvailable) {
            Toast.makeText(MainActivity.this, "联网：" + netWorkAvailable, Toast.LENGTH_SHORT).show();
        }
        getData();
        //  postData();
        //  postJson();
        //  uploadPic();
        //  downloadApk();
    }


    //get的请求
    private void getData() {
        //封装回调传入对象 封装了Callback回调
        OkHttp3Utils.getInstance().doGet(url + "channelId=" + 0 + "&startNum=" + 0, new GsonObjectCallback<User>() {
            @Override
            public void onUi(User user) {
                Log.i("aaa", user.getData().get(0).getTITLE());
                tv.setText(user.getData().get(0).getTITLE());
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
        //封装回调传入集合 封装了Callback回调
       /* OkHttp3Utils.getInstance().doGet(uri, new GsonArrayCallback<DataObj>() {
            @Override
            public void onUi(List<DataObj> list) {
                Log.i("fff", list.toString());
                String login = list.get(0).getLogin();
                tv_result.setText(login);
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });*/
         //数据结构最外层是集合 封装了Callback回调
        OkHttp3Utils.getInstance().doGet("http://mnews.gw.com.cn/wap/data/news/txs/page_1.json", new GsonArrayCallback<News>() {
            @Override
            public void onUi(List<News> list) {
                News news = list.get(0);
                List<News.DataInfo> data = news.getData();
                News.DataInfo dataInfo = data.get(0);
                String title = dataInfo.getTitle();
                tv_result.setText(title);
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
        //没有封装Callback回调
       /* OkHttp3Utils.getInstance().doGet(url + "channelId=" + 0 + "&startNum=" + 0, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //回调在子线程
                Log.i("sss", response.body().string());
            }
        });*/

    }

    //post请求 键值对参数 String url = "http://www.93.gov.cn/93app/data.do?" + "channelId=" + 0 + "&startNum=" + 0;
    private void postData() {
        Map<String, String> params = new HashMap<>();
        params.put("channelId", "0");
        params.put("startNum", "0");
        OkHttp3Utils.doPost(url, params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //回调在子线程
            }
        });
    }

    //Post发送JSON数据
    private void postJson() {
        String json = "{\n" +
                "    \"data\": [\n" +
                "        {\n" +
                "            \"title\": \"少林寺\",\n" +
                "            \"url\": \"http://pic67.nipic.com/file/20150514/21036787_181947848862_2.jpg\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"title\": \"武当\",\n" +
                "            \"url\": \"http://c.hiphotos.baidu.com/zhidao/pic/item/1f178a82b9014a909461e9baa1773912b31bee5e.jpg\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        OkHttp3Utils.doPostJson("", json, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

    //图片上传
    private void uploadPic() {
        File file = new File(Environment.getExternalStorageDirectory(), "a.jpg");
        String fileName = "a.jpg";
        OkHttp3Utils.uploadPic(picUrl, file, fileName);

    }
    //文件下载apk

    private void downloadApk() {
        String saveDir = "app";
        OkHttp3Utils.download(this, apkPath, saveDir);
    }

    //其它看需求


}
