package com.bh.store.controller;


import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.bh.store.util.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.UUID;

@RestController
@RequestMapping("alipay")
public class PayController extends BaseController{
    private final String APP_ID = "2021000119646276";
    private final String APP_PRIVATE_KEY ="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCYmrmP+mAjZxlclhW1iSKYLxapwzTaLYQfEq+lC5dCMtbxgEncOdBlO7b3HOKx0e44LRQSTIw4mq1sHWMng9L+4fiBvzDQMkj7hrJaTMwBcl/ORAdt57vUrwcmSAFg5vRgegJZavsx0LYZUW7uW6+4VqTISZZsr3jGPlyK1gP1EHLuF1uOKtliaf7oFNMibYHAHejOhENE3AV5ooHrTJib15YQoZXEe6yZJJ9Fm/hnUiI6BJW3BHMFd1F+FefxqFLOV6rXX3lSrbg0VuyluD5g47jqtTRk/6cRmUWB1hf0wFQOqTZsuDsX1Aw+Wi8YAdyXq8dAgN0gTAk5JZdOpD0BAgMBAAECggEABUm/SmTzteZZs6Kjx/GjmEBxeZqBF1pibK06Fu8K48ldN2sppA8A0LE8Ct4eYOkUZy/wtR2f8kox3kA6cmEYC6sK8HwOlfhYHRcy0Pw+SYKMHVqwIzfXg2w5RDNUkOYP69Puhui1IanI1aHDxVC6Cxo9hY8/Pi7qcXwxusHlyzUj/itJmydrMyGHDJFKR6JqvI5AqCgcQWP5jw3NJ0LvHIpBDo/iT/LdH3IlNdH8Ac7unwO++Y+ciVGL1HoVOYnjs8VAbJgZ3I74LX+7jIk/zuDsC9U61n/jfJta6w55W4C4IjXqAVgFCrVkN04bwmKO4jIlNVRXn/yjsT9L3eXdrQKBgQDQ+2c/qG4TIIH6u3it2dc5mpKAEtJ4sRnxucAU2cvp7JwEcjxhCl9emr2LytfZU1TwgtyzkwUJ+AkD98LhJUj462ZQItnC9pe/XYLQFhs+4sQ9Mye5v2S3JNp9wnjbE+Eh8dnzdyJFJCITAairWJCDDkXFBk1hknnBpntzQojoGwKBgQC68C0lp59tB4uPmu4dSdx31SwhyFiUL9tyNd4efdJ6qh5rFleUkAtGZ/8RrgjKccidmZSNVhgy87hYy29atXntXriExOvKWbzV9EKTy2Fwp6LuDGdqOC3u2roZbV5RgoMG8k6BJ6IXdkugjmIJKM40lyXSfalmGViSb37XXzc5EwKBgFffblG5G9fVEnKAg8TuVW52TWWnBHMVXOhkh5z8255YDF5ePn87YlCgVlVCjLJoNM4Q6IZlqne/oOLXOk17ZQytxs6paNFu6O3hfxDKStiNd6CYMvsKvGm6CFla/YB5AnBIhrG2BWX3RJCjYO3zhohk5vdqp/0AH0xLGZukfXbDAoGBALM+yItqhruMmI1CCT+cKwHcLUzXRdgRa2G/tROXodJsfG5CJggVdjo0TacuMM+s+qfjM0WyX9G7T/AUYNmhWR/5ywPDrjOAiEi+xXfhc7UJiHio/CAlbiWxJPBsphuSAQEQJaZB0nKcwzdYTzhpIA/dPi7uvxdfBkajEgSBD/pxAoGBAMKrgsWy1ug8Df1wySfoX5n1aJSCIrLkUOCEIL//dRI4cImyH2BQ0lMAQsUAF9Ohy1QumVh9dRwAButhiEMM3m9lWXM7s/3Hiiz7nfne7zRv0eQWFpMgVioVc8NNoV0UEp/T7jCCVkPkC1dtdlYHZKi4wtCnPN0dxlu01krzz4l7";
    private final String CHARSET = "UTF-8";
    private final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmJq5j/pgI2cZXJYVtYkimC8WqcM02i2EHxKvpQuXQjLW8YBJ3DnQZTu29xzisdHuOC0UEkyMOJqtbB1jJ4PS/uH4gb8w0DJI+4ayWkzMAXJfzkQHbee71K8HJkgBYOb0YHoCWWr7MdC2GVFu7luvuFakyEmWbK94xj5citYD9RBy7hdbjirZYmn+6BTTIm2BwB3ozoRDRNwFeaKB60yYm9eWEKGVxHusmSSfRZv4Z1IiOgSVtwRzBXdRfhXn8ahSzleq1195Uq24NFbspbg+YOO46rU0ZP+nEZlFgdYX9MBUDqk2bLg7F9QMPlovGAHcl6vHQIDdIEwJOSWXTqQ9AQIDAQAB";
    //这是沙箱接口路径,正式路径为https://openapi.alipay.com/gateway.do
    private final String GATEWAY_URL ="https://openapi.alipaydev.com/gateway.do";
    private final String FORMAT = "JSON";
    //签名方式
    private final String SIGN_TYPE = "RSA2";
    //支付宝异步通知路径,付款完毕后会异步调用本项目的方法,必须为公网地址
    private final String NOTIFY_URL = "http://127.0.0.1/notifyUrl";
    //支付宝同步通知路径,也就是当付款完毕后跳转本项目的页面,可以不是公网地址
    private final String RETURN_URL = "http://127.0.0.1:8080/web/paySuccess.html";

    @RequestMapping("pay")
    public JsonResult<String> alipay(Integer price) throws IOException {

        //实例化客户端,填入所需参数
        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        //在公共参数中设置回跳和通知地址
        request.setReturnUrl(RETURN_URL);
        request.setNotifyUrl(NOTIFY_URL);

        //商户订单号，商户网站订单系统中唯一订单号
        //生成随机Id
        String out_trade_no = UUID.randomUUID().toString();
        //付款金额
        String total_amount =Integer.toString(price);
        //订单名称
        String subject ="网上商城订单";
        //商品描述
        String body = "网上商城订单";
        request.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        String form = "";
        try {
            form = alipayClient.pageExecute(request).getBody(); // 调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        return new JsonResult<>(OK,form);
    }

}
