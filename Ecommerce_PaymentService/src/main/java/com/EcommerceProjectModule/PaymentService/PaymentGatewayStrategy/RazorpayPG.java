package com.EcommerceProjectModule.PaymentService.PaymentGatewayStrategy;


import com.EcommerceProjectModule.PaymentService.Models.Order;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RazorpayPG implements IPaymentGateway {

    @Autowired
    private RazorpayClient razorpayClient;

    @Override
    public String generatePaymentLink(Order order) {
        try {
            JSONObject paymentLinkRequest = new JSONObject();


            paymentLinkRequest.put("amount", order.getTotalAmount());
            paymentLinkRequest.put("currency", "INR");
            paymentLinkRequest.put("accept_partial", false);
            paymentLinkRequest.put("expire_by", 1801624719);
            paymentLinkRequest.put("reference_id", order.getId());
            paymentLinkRequest.put("description", "Payment for order #" + order.getId());
            JSONObject customer = new JSONObject();
            customer.put("name", order.getPhone());
            customer.put("contact", "Suraj Kadam");
            customer.put("email", order.getEmail());
            paymentLinkRequest.put("customer", customer);
            JSONObject notify = new JSONObject();
            notify.put("sms", true);
            notify.put("email", true);
            paymentLinkRequest.put("notify", notify);
            paymentLinkRequest.put("reminder_enable", true);
            paymentLinkRequest.put("callback_url", "https://example-callback-url.com/");
            paymentLinkRequest.put("callback_method", "get");

            PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);
            return payment.get("short_url").toString();
        } catch (Exception e) {
            System.out.println(e.toString());
            return "something is wrong";
        }
    }
}


