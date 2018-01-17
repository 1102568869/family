package tech.washmore.family.utils;


import okhttp3.*;
import org.springframework.scheduling.annotation.AsyncResult;

import java.io.IOException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * @author jerry.hu 补充规约
 */
public class OkHttpUtil {

    private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder().connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS).writeTimeout(120, TimeUnit.SECONDS).build();

    /**
     * post请求发送json数据
     *
     * @param postUrl
     * @param content
     * @return
     */
    public static Future<Boolean> postNewCallByJson(String postUrl, String content) {
        MediaType json = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(json, content);
        Request request = new Request.Builder()
                .url(postUrl)
                .post(body)
                .build();
        Call call = OK_HTTP_CLIENT.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                throw new RuntimeException("Http Post Json请求失败,url:" + postUrl);
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (response.code() == 200) {
                } else {
                    throw new RuntimeException("Http Post Json请求失败,url:" + postUrl);
                }
            }
        });
        return new AsyncResult(true);

    }

    public static String getNewCall(String getUrl) {
        Request request = new Request.Builder()
                .url(getUrl)
                .build();
        String bodyContent = "";
        try {
            Response response = OK_HTTP_CLIENT.newCall(request).execute();
            ResponseBody body = response.body();
            bodyContent = body.string();
        } catch (IOException e) {
            throw new RuntimeException("Http Get请求失败,url:" + getUrl);
        }
        return bodyContent;
    }

    public static Response syncCall(Request request) {

        try {
            return OK_HTTP_CLIENT.newCall(request).execute();

        } catch (IOException e) {
            throw new RuntimeException("Http服务请求异常");
        }
    }

    public static void asyncCall(Request request, Consumer<Exception> failure, Consumer<Response> respConsumer) {
        OK_HTTP_CLIENT.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                failure.accept(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                respConsumer.accept(response);
            }
        });
    }
}
