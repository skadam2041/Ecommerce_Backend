package com.EcommerceProjectModule.PaymentService.PaymentGatewayStrategy;

import com.EcommerceProjectModule.PaymentService.Models.Order;
import com.stripe.param.PaymentLinkCreateParams;
import org.springframework.stereotype.Service;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.model.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.stripe.Stripe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StripePG implements IPaymentGateway {
    @Value("${stripe.key.secret}")
    private String apiKey;

    @Override
    public String generatePaymentLink(Order order) {
        Stripe.apiKey = apiKey;
        Map<String, Object> params = new HashMap<>();
        params.put("name", "Gold Special");
        Product product = null;
        try {
            product = Product.create(params);
        } catch (StripeException e) {
            System.out.println(e.getMessage());
        }

        Map<String, Object> params1 = new HashMap<>();
        params1.put("unit_amount", order.getTotalAmount());
        params1.put("currency", "usd");
        params1.put("product", product.getId());

        Price price = null;
        try {
            price = Price.create(params1);
        } catch (StripeException e) {
            System.out.println(e.getMessage());
        }

        List<Object> lineItems = new ArrayList<>();
        Map<String, Object> lineItem1 = new HashMap<>();
        lineItem1.put(
                "price",
                price.getId()
        );
        lineItem1.put("quantity", 1);
        lineItems.add(lineItem1);
        Map<String, Object> params2 = new HashMap<>();
        params2.put("line_items", lineItems);


        Map<String, Object> redirect = new HashMap<>();
        redirect.put("url", "http://localhost:8090/stripePayment/callbackStatus/{CHECKOUT_SESSION_ID}");
//        redirect.put("url", "https://scaler.com?payment_id={CHECKOUT_SESSION_ID}");

        Map<String, Object> afterComp = new HashMap<>();
        afterComp.put("type", "redirect");
        afterComp.put("redirect", redirect);

        params2.put("after_completion", afterComp);

        PaymentLink paymentLink = null;
        try {
            paymentLink =
                    PaymentLink.create(params2);
        } catch (StripeException e) {
            System.out.println(e.getMessage());
        }
        return paymentLink.getUrl();

//        Stripe.apiKey = apiKey;
//        PaymentLinkCreateParams params =
//                PaymentLinkCreateParams.builder()
//                        .addLineItem(
//                                PaymentLinkCreateParams.LineItem.builder()
//                                        .setPrice("price_1MoC3TLkdIwHu7ixcIbKelAC")
//                                        .setQuantity(1L)
//                                        .build()
//                        )
//                        .build();
//        PaymentLink paymentLink = null;
//        try {
//            paymentLink = PaymentLink.create(params);
//        } catch (StripeException e) {
//            System.out.println(e.getMessage());
//        }
//        return paymentLink.getUrl();
    }
}



