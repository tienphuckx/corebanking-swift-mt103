# DEMO
curl -X POST "http://localhost:8080/api/mt103/generate" \
     -H "Content-Type: application/json" \
     -d '{
          "reference": "TEST12345",
          "transactionType": "CRED",
          "transactionDate": "250206",
          "currency": "USD",
          "amount": 1000.00,
          "senderAccount": "123456789012345",
          "senderName": "John Doe",
          "senderAddress": "123 Main St, New York, NY",
          "receiverAccount": "987654321098765",
          "receiverName": "Jane Smith",
          "receiverAddress": "456 Park Ave, London, UK",
          "remittanceInfo": "INVOICE PAYMENT",
          "chargeType": "SHA",
          "additionalInfo": "/INS/INT PAYMENT FOR INVOICE 12345"
        }'

