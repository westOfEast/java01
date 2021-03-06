package io.github.kimmking.gateway.outbound.okhttp;

import io.github.kimmking.gateway.outbound.IHttpOutboundHandler;
import io.github.kimmking.gateway.router.HttpEndpointRouter;
import io.github.kimmking.gateway.router.RandomRouter;
import io.github.kimmking.gateway.router.RoundRobinRouter;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class OkhttpOutboundHandlerI implements IHttpOutboundHandler {

    public OkHttpClient okHttpClient;

    private static final String BACKEND_SERVER = "127.0.0.1:9091";

    private static final HashMap<String,List<String>> BACKEND_SERVER_MAP = new HashMap();

    private HttpEndpointRouter httpEndpointRouter;

    public OkhttpOutboundHandlerI(String url,HttpEndpointRouter httpEndpointRouter){
        okHttpClient = new okhttp3.OkHttpClient.Builder()
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
        BACKEND_SERVER_MAP.put("/mms",new ArrayList<String>(Arrays.asList("127.0.0.1:9091")));
        BACKEND_SERVER_MAP.put("/geekbang",new ArrayList<String>(Arrays.asList("u.geekbang.org")));

        this.httpEndpointRouter = httpEndpointRouter;
    }

    public okhttp3.Response call(String url, String method, @Nullable RequestBody body,Headers headers){
        Request request = new Request.Builder()
                .method(method,body)
                .headers(headers)
                .url(url)
                .build();

        Call call = okHttpClient.newCall(request);
        okhttp3.Response response = null;
        try {
            response = call.execute();
//            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    @Override
    public void handle(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        try{
            HttpMethod method = fullRequest.method();
            if(StringUtils.compareIgnoreCase(method.name(),"GET") == 0){
                handleResponse(fullRequest,ctx,call(getTargetUrl(fullRequest.uri()),"GET",null,getHeader(fullRequest)));
            }else if(StringUtils.compareIgnoreCase(method.name(),"POST") == 0){
                RequestBody requestBody = RequestBody.create(null, String.valueOf(fullRequest.content()));
                handleResponse(fullRequest,ctx,call(getTargetUrl(fullRequest.uri()),method.name().toUpperCase(),requestBody,getHeader(fullRequest)));
            }
        }catch(Exception e){
            System.out.println(ExceptionUtils.getStackTrace(e));
        }
    }

    private Headers getHeader(FullHttpRequest fullRequest) {
        HttpHeaders header = fullRequest.headers();//获取Netty内置的请求头对象
        List<Map.Entry<String, String>> list = header.entries(); //将包含的请求信息赋值到list中
        Map<String, String> headerMap = new HashMap<>();
        Optional.ofNullable(list).orElse(new ArrayList<>()).stream().forEach(stringStringEntry -> {
            headerMap.put(stringStringEntry.getKey(),stringStringEntry.getValue());
        });
        return Headers.of(headerMap);
    }

    private String getTargetUrl(String uri){
        if(uri.startsWith("/geekbang")){
            String endpoint = httpEndpointRouter.route(BACKEND_SERVER_MAP.get("/geekbang"));
            return "http://"+endpoint+uri.replace("/geekbang","");
        }else if(uri.startsWith("/mms")){
            String endpoint = httpEndpointRouter.route(BACKEND_SERVER_MAP.get("/mms"));
            return "http://"+endpoint+uri;
        }
        return "http://"+BACKEND_SERVER+uri;
    }

    private void handleResponse(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, final okhttp3.Response endpointResponse) throws Exception {
        FullHttpResponse response = null;
        try {
//            String value = "hello,kimmking";
//            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(value.getBytes("UTF-8")));
//            response.headers().set("Content-Type", "application/json");
//            response.headers().setInt("Content-Length", response.content().readableBytes());


//            byte[] body = endpointResponse.body().;
            String resp = endpointResponse.body().string();
            System.out.println(resp);
            byte[] body = resp.getBytes();
//            System.out.println(new String(body));
//            System.out.println(body.length);

            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body));
//            response.headers().set("Content-Type", endpointResponse.header("Content-Type"));//"application/json"
//            response.headers().setInt("Content-Length", Integer.parseInt(endpointResponse.header("Content-Length")));

            Map<String, List<String>> headersMap =  endpointResponse.headers().toMultimap();
            for (String key : headersMap.keySet()) {
                List<String> valueList = headersMap.get(key);
                String[] values = new String[valueList.size()];
                response.headers().set(key, StringUtils.join(valueList.toArray(values),";"));
                System.out.println(key + " => " + StringUtils.join(valueList.toArray(values),";"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
            exceptionCaught(ctx, e);
        } finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    //response.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(response);
                }
            }
            ctx.flush();
            //ctx.close();
        }

    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
