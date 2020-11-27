package com.duanshl.aiapp.Utils;

import java.net.URLEncoder;

public class PlantRecg {
    public static String plant(String imageUri) {
        // 请求url
        String animal_url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/animal";
        String plant_url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/plant";
        String logo_url = "https://aip.baidubce.com/rest/2.0/image-classify/v2/logo";
        String ingredient_url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/classify/ingredient";
        String dish_url = "https://aip.baidubce.com/rest/2.0/image-classify/v2/dish";
        String redWine_url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/redwine";
        String currency_url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/currency";
        String landmark_url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/landmark";
        try {
            // 本地文件路径
            String filePath = imageUri;

//            Instant inst1 = Instant.now();
            byte[] imgData = FileUtil.readFileByBytes(filePath);
//            Instant inst2 = Instant.now();
//            System.out.println("读取文件所需的时间(毫秒)：" + Duration.between(inst1, inst2).toMillis());

//            Instant inst3 = Instant.now();
            String imgStr = Base64Util.encode(imgData);
//            Instant inst4 = Instant.now();
//            System.out.println("对图片进行Base64编码所需的时间(毫秒)：" + Duration.between(inst3, inst4).toMillis());


//            Instant inst5 = Instant.now();
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");
//            Instant inst6 = Instant.now();
//            System.out.println("对图片进行Url编码所需的时间(毫秒)：" + Duration.between(inst5, inst6).toMillis());

            String param = "image=" + imgParam;

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = AuthService.getAuth();
//            String accessToken = "24.84ccc914d96fbadd869af86e0a59fbee.2592000.1608729440.282335-22944169";

//            Instant inst7 = Instant.now();
            String result = HttpUtil.post(plant_url, accessToken, param);
//            Instant inst8 = Instant.now();

//            System.out.println("请求所需的时间(毫秒)：" + Duration.between(inst7, inst8).toMillis());

            //System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
