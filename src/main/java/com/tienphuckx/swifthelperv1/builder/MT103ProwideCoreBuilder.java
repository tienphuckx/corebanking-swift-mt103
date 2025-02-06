package com.tienphuckx.swifthelperv1.builder;

import com.prowidesoftware.swift.model.SwiftMessage;
import com.prowidesoftware.swift.model.SwiftBlock2Input;
import com.prowidesoftware.swift.model.mt.mt1xx.MT103;
import com.prowidesoftware.swift.model.field.*;
import com.tienphuckx.swifthelperv1.model.TransactionRequest;

public class MT103ProwideCoreBuilder {

    public static String generateMT103ProwideCore(TransactionRequest request) {

        MT103 mt103 = new MT103();

        // Set Block 1 (Basic Header)
        mt103.getSwiftMessage().getBlock1().setSender("BANKABCXXXX");

        // Set Block 2 (Application Header)
        SwiftBlock2Input block2 = new SwiftBlock2Input();
        block2.setMessageType("103"); // MT103 message type
        block2.setReceiver("BANKXYZXXXX"); // Receiver BIC
        block2.setInput(true); // Mark as an incoming message
        mt103.getSwiftMessage().setBlock2(block2);

        // Add fields to Block 4 (Message Text)
        mt103.addField(new Field20(request.getReference())); // Transaction Reference
        mt103.addField(new Field23B(request.getTransactionType())); // Transaction Type

        // Format Field 32A: Date, Currency, and Amount
        String field32AValue = request.getTransactionDate() + request.getCurrency() + formatAmount(request.getAmount());
        mt103.addField(new Field32A(field32AValue));

        // Set Field 50K (Ordering Customer)
        Field50K field50K = new Field50K();
        field50K.setComponent1("/" + request.getSenderAccount());
        field50K.setComponent2(request.getSenderName());
        field50K.setComponent3(request.getSenderAddress());
        mt103.addField(field50K);

        // Set Field 59 (Beneficiary Customer)
        Field59 field59 = new Field59();
        field59.setComponent1("/" + request.getReceiverAccount());
        field59.setComponent2(request.getReceiverName());
        field59.setComponent3(request.getReceiverAddress());
        mt103.addField(field59);

        mt103.addField(new Field70(request.getRemittanceInfo())); // Remittance Information
        mt103.addField(new Field71A(request.getChargeType())); // Charges Type
        mt103.addField(new Field72(request.getAdditionalInfo())); // Additional Information

        // Return the formatted MT103 message as a string
        return mt103.message();
    }


    private static String formatAmount(double amount) {
        return String.format("%.2f", amount).replace(".", ",");
    }
}

