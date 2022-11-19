package com.cf.ssm.utils.none;

import com.baidubce.http.ApiExplorerClient;
import com.baidubce.http.HttpMethodName;
import com.baidubce.model.ApiExplorerRequest;
import com.baidubce.model.ApiExplorerResponse;

// 人脸检测 示例代码
public class RequestDemo {
    public static void main(String[] args) {
        String path = "https://aip.baidubce.com/rest/2.0/face/v3/detect";
        ApiExplorerRequest request = new ApiExplorerRequest(HttpMethodName.POST, path);


        // 设置header参数
        request.addHeaderParameter("Content-Type", "application/json;charset=UTF-8");

        // 设置query参数
        request.addQueryParameter("access_token", "24.a25eff6a35b6fa72be12c75119e798d0.2592000.1668771981.282335-27984404");

        // 设置jsonBody参数
        String jsonBody = "{\"image\":\"https://s1.ax1x.com/2022/10/27/xh1yQO.png\",\"image_type\":\"URL\"}";
        request.setJsonBody(jsonBody);

        ApiExplorerClient client = new ApiExplorerClient();

        try {
            ApiExplorerResponse response = client.sendRequest(request);
            // 返回结果格式为Json字符串
            System.out.println(response.getResult());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}