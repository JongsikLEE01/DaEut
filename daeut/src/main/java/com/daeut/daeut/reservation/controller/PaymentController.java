package com.daeut.daeut.reservation.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class PaymentController {
    private IamportClient iamportClient;

    // 키 가져오기
    @Value("${import.key}")
    private String apiKey;
    @Value("${import.secret}")
    private String secretKey;

    // iamportClient 초기화
    @PostConstruct
    public void init() {
        this.iamportClient = new IamportClient(apiKey, secretKey);
    }


    @GetMapping("/reservation/paymentDone")
    public String paymentDone() {
        return "/reservation/paymentDone";
    }

    @GetMapping("/reservation/paymentFalse")
    public String paymentFalse() {
        return "/reservation/paymentFalse";
    }

    /**
     * 해당 거래의 상세 정보를 조회 및 반환
     * @param imp_uid -> 고객사 식별코드
     * @return
     * @throws Exception
     */
    @PostMapping("/payment/validation/{imp_uid}")
    @ResponseBody
    public IamportResponse<Payment> validateIamport(@PathVariable String imp_uid) throws Exception{
        IamportResponse<Payment> payment = iamportClient.paymentByImpUid(imp_uid);
        log.info("결제 요청 응답. 결제 내역 - 주문 번호: {}", payment.getResponse().getMerchantUid());
        
        return payment;
    }
}