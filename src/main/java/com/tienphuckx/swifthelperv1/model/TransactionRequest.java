package com.tienphuckx.swifthelperv1.model;

import lombok.Data;

@Data
public class TransactionRequest {
    private String reference;
    private String transactionType;
    private String transactionDate;
    private String currency;
    private double amount;
    private String senderAccount;
    private String senderName;
    private String senderAddress;
    private String receiverAccount;
    private String receiverName;
    private String receiverAddress;
    private String remittanceInfo;
    private String chargeType;
    private String additionalInfo;
}
