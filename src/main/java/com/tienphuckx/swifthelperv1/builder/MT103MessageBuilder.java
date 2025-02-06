package com.tienphuckx.swifthelperv1.builder;

import com.tienphuckx.swifthelperv1.model.TransactionRequest;
import com.tienphuckx.swifthelperv1.tag.MT103Headers;
import com.tienphuckx.swifthelperv1.tag.MT103Tags;

public class MT103MessageBuilder {
    private final StringBuilder message;

    public MT103MessageBuilder() {
        message = new StringBuilder();
    }

    public MT103MessageBuilder withHeader(String bankSenderCode, String bankReceiverCode) {
        message.append(MT103Headers.BASIC_HEADER_PREFIX)
                .append(bankSenderCode)
                .append(MT103Headers.BASIC_HEADER_SUFFIX)
                .append(MT103Headers.APPLICATION_HEADER_PREFIX)
                .append(bankReceiverCode)
                .append(MT103Headers.APPLICATION_HEADER_SUFFIX);
        return this;
    }


    public MT103MessageBuilder withTransactionReference(String reference) {
        message.append(MT103Headers.USER_HEADER_PREFIX)
                .append(reference)
                .append(MT103Headers.USER_HEADER_SUFFIX);
        return this;
    }


    public MT103MessageBuilder withDetails(TransactionRequest request) {
        message.append("{4:\n")
                .append(MT103Tags.TRANSACTION_REFERENCE).append(request.getReference()).append("\n")
                .append(MT103Tags.TRANSACTION_TYPE).append(request.getTransactionType()).append("\n")
                .append(MT103Tags.TRANSACTION_DATE).append(request.getTransactionDate())
                .append(request.getCurrency()).append(request.getAmount()).append("\n")
                .append(MT103Tags.SENDER).append("/").append(request.getSenderAccount()).append("\n")
                .append(request.getSenderName()).append("\n")
                .append(request.getSenderAddress()).append("\n")
                .append(MT103Tags.RECEIVER).append("/").append(request.getReceiverAccount()).append("\n")
                .append(request.getReceiverName()).append("\n")
                .append(request.getReceiverAddress()).append("\n")
                .append(MT103Tags.REMITTANCE_INFO).append(request.getRemittanceInfo()).append("\n")
                .append(MT103Tags.CHARGE_TYPE).append(request.getChargeType()).append("\n")
                .append(MT103Tags.ADDITIONAL_INFO).append(request.getAdditionalInfo()).append("\n")
                .append("-}");

        return this;
    }

    public String build() {
        return message.toString();
    }
}
