package com.payment_service.service;

import com.stripe.param.checkout.SessionCreateParams;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    public Session createCheckoutSession(Long orderId ,Long amount){
        SessionCreateParams params =
                SessionCreateParams.builder()
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setSuccessUrl(orderId)
                        .setCancelUrl()
                        .addLineItem();
    }


}
