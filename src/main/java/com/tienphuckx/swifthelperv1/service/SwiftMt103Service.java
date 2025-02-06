package com.tienphuckx.swifthelperv1.service;


import com.tienphuckx.swifthelperv1.builder.MT103MessageBuilder;
import com.tienphuckx.swifthelperv1.model.TransactionRequest;
import org.springframework.stereotype.Service;

@Service
public class SwiftMt103Service {

    public String generateMT103(TransactionRequest request) {
        MT103MessageBuilder builder = new MT103MessageBuilder();

        return builder.withHeader("BANKBEBBAXXX", "BANKBEBBXXXX")
                .withTransactionReference(request.getReference())
                .withDetails(request)
                .build();
    }
}