package com.tienphuckx.swifthelperv1.builder;

import com.tienphuckx.swifthelperv1.model.TransactionRequest;

public class MT103MessageBuilder {
    private StringBuilder message;

    public MT103MessageBuilder() {
        message = new StringBuilder();
    }

    public MT103MessageBuilder withHeader() {
        message.append("{1:F01BANKBEBBAXXX0000000000}\n")
                .append("{2:I103BANKBEBBXXXXN0000000000}\n");
        return this;
    }

    public MT103MessageBuilder withTransactionReference(String reference) {
        message.append("{3:{108:").append(reference).append("}}\n");
        return this;
    }

    public MT103MessageBuilder withDetails(TransactionRequest request) {
        message.append("{4:\n")
                .append(":20:").append(request.getReference()).append("\n")
                .append(":23B:").append(request.getTransactionType()).append("\n")
                .append(":32A:").append(request.getTransactionDate())
                .append(request.getCurrency()).append(request.getAmount()).append("\n")
                .append(":50K:/").append(request.getSenderAccount()).append("\n")
                .append(request.getSenderName()).append("\n")
                .append(request.getSenderAddress()).append("\n")
                .append(":59:/").append(request.getReceiverAccount()).append("\n")
                .append(request.getReceiverName()).append("\n")
                .append(request.getReceiverAddress()).append("\n")
                .append(":70:").append(request.getRemittanceInfo()).append("\n")
                .append(":71A:").append(request.getChargeType()).append("\n")
                .append(":72:").append(request.getAdditionalInfo()).append("\n")
                .append("-}");
        return this;
    }

    public String build() {
        return message.toString();
    }
}
