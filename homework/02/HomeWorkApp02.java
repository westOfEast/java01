package u.geekbang.org;

import okhttp3.Call;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

public class HomeWorkApp02 {

    public static void main(String[] args){
        OkHttpClient okHttpClient = new okhttp3.OkHttpClient.Builder()
                //设置连接超时
                .connectTimeout(10, TimeUnit.SECONDS)
                //设置读超时
                .readTimeout(5*60, TimeUnit.SECONDS)
                //设置写超时
                .writeTimeout(5*60, TimeUnit.SECONDS)
                //是否自动重连
                .retryOnConnectionFailure(true)
                .connectionPool(new ConnectionPool(10, 5L, TimeUnit.MINUTES))
                .build();

        Request request = new Request.Builder()
                .get()
                .url("http://localhost:8801")
                .build();

        Call call = okHttpClient.newCall(request);
        try {
            okhttp3.Response response = call.execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
