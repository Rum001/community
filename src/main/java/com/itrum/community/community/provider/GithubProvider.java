package com.itrum.community.community.provider;

import com.alibaba.fastjson.JSON;
import com.itrum.community.community.dto.AccessTokenDTO;
import com.itrum.community.community.dto.GithubUserDTO;
import com.itrum.community.community.utils.JsonUtils;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {
    public static final MediaType mediaType
            = MediaType.get("application/json; charset=utf-8");
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(mediaType, JsonUtils.serialize(accessTokenDTO));
            Request request = new Request.Builder()
                    .url("https://github.com/login/oauth/access_token")
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                String string = response.body().string().split("&")[0].split("=")[1];
                return string;
            } catch (IOException e) {
                e.printStackTrace();
            }
        return null;
        }

    public GithubUserDTO getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.github.com/user?access_token="+accessToken)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                String string = response.body().string();
                return JSON.parseObject(string,GithubUserDTO.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        return null;
    }

}
