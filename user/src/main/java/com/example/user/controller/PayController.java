package com.example.user.controller;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.easysdk.factory.Factory;
import com.example.user.vo.AliPay;
import com.example.user.vo.AlipayVo;
import com.example.user.vo.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@CrossOrigin
public class PayController {
    @Autowired
    private AlipayVo alipayVo;  //自动注入配置类信息

//    @Autowired
//    private OrderMapper orderMapper;

    @GetMapping(value = "/pay", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String orderPayPage(AliPay aliPay, HttpServletResponse response) throws Exception {
//            public String orderPayPage(AliPay aliPay, Order order, HttpServletResponse response) throws Exception {
        AlipayClient alipayClient = new DefaultAlipayClient(
                alipayVo.getGatewayUrl(),  //网关
                alipayVo.getAppId(),  //商户id
                alipayVo.getAppPrivateKey(),  //私钥
                alipayVo.getFormat(),  //格式
                alipayVo.getCharset(),  //编码
                alipayVo.getAlipayPublicKey(),  //公钥
                alipayVo.getSignType()  //签名
        );
        AlipayTradePagePayRequest alipayTradePagePayRequest = new AlipayTradePagePayRequest();
        alipayTradePagePayRequest.setBizContent("{\"out_trade_no\":\"" + aliPay.getTraceNo() + "\","
                + "\"total_amount\":\"" + aliPay.getTotalAmount() + "\","
                + "\"subject\":\"" + aliPay.getSubject() + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        alipayTradePagePayRequest.setNotifyUrl(alipayVo.getNotifyUrl());
        alipayTradePagePayRequest.setReturnUrl(alipayVo.getReturnUrl());
        AlipayTradePagePayResponse pagePayResponse = alipayClient.pageExecute(alipayTradePagePayRequest);
        String body = pagePayResponse.getBody();

// 这里调用订单接口创建订单，根据个人需求写就好

        return body;
    }

    @ResponseBody
    @PostMapping("/notify")
    public RE payNotify(HttpServletRequest request) throws Exception {
        if (request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
            System.out.println("=========支付宝异步回调========");

            Map<String, String> params = new HashMap<>();
            Map<String, String[]> requestParams = request.getParameterMap();

            for (String name : requestParams.keySet()) {
                params.put(name, request.getParameter(name));
//                 System.out.println(name + " = " + request.getParameter(name));
            }

            String tradeNo = params.get("out_trade_no");
            String gmtPayment = params.get("gmt_payment");
            String alipayTradeNo = params.get("trade_no");
            // 支付宝验签
            if (Factory.Payment.Common().verifyNotify(params)) {
                // 验签通过
                System.out.println("交易名称: " + params.get("subject"));
                System.out.println("交易状态: " + params.get("trade_status"));
                System.out.println("支付宝交易凭证号: " + params.get("trade_no"));
                System.out.println("商户订单号: " + params.get("out_trade_no"));
                System.out.println("交易金额: " + params.get("total_amount"));
                System.out.println("买家在支付宝唯一id: " + params.get("buyer_id"));
                System.out.println("买家付款时间: " + params.get("gmt_payment"));
                System.out.println("买家付款金额: " + params.get("buyer_pay_amount"));

                // 这里订单为支付

            }
        }
        return RE.ok();
    }
}
