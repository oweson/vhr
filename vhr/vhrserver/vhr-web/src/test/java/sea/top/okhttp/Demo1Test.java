package sea.top.okhttp;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class Demo1Test {
    OkHttpClient client = new OkHttpClient();

    public String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static void main(String[] args) throws IOException {
        Demo1Test demo1Test = new Demo1Test();
        String run = demo1Test.run("http://localhost:8080/iapstdb/");
        System.out.println(run);
    }
}
