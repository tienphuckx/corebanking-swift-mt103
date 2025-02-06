package com.tienphuckx.swifthelperv1.service;


import com.tienphuckx.swifthelperv1.model.TransactionRequest;
import org.springframework.stereotype.Service;

@Service
public class SwiftMt103Service {

    public String generateMT103(TransactionRequest request) {
        return "{1:F01BANKBEBBAXXX0000000000}\n" +
                "{2:I103BANKBEBBXXXXN0000000000}\n" +
                "{3:{108:" + request.getReference() + "}}\n" +
                "{4:\n" +
                ":20:" + request.getReference() + "\n" +
                ":23B:" + request.getTransactionType() + "\n" +
                ":32A:" + request.getTransactionDate() + request.getCurrency() + request.getAmount() + "\n" +
                ":50K:/" + request.getSenderAccount() + "\n" +
                request.getSenderName() + "\n" +
                request.getSenderAddress() + "\n" +
                ":59:/" + request.getReceiverAccount() + "\n" +
                request.getReceiverName() + "\n" +
                request.getReceiverAddress() + "\n" +
                ":70:" + request.getRemittanceInfo() + "\n" +
                ":71A:" + request.getChargeType() + "\n" +
                ":72:" + request.getAdditionalInfo() + "\n" +
                "-}";
    }
}