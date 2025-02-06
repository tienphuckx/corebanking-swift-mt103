# Core Banking - Swift - MT103

### Use Prowide Core

```sh
curl -X POST "http://localhost:8080/api/mt103/generate_v1" \
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
```

### Not use Prowide Core
```sh
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
```

### Output
```
{1:F01BANKBEBBAXXX0000000000}
{2:I103BANKBEBBXXXXN0000000000}
{3:{108:TEST12345}}
{4:
:20:TEST12345
:23B:CRED
:32A:250206USD1000.0
:50K:/123456789012345
John Doe
123 Main St, New York, NY
:59:/987654321098765
Jane Smith
456 Park Ave, London, UK
:70:INVOICE PAYMENT
:71A:SHA
:72:/INS/INT PAYMENT FOR INVOICE 12345
-}
```

## Giải thích về MT103

MT103 là một chuẩn tin nhắn SWIFT được sử dụng để thực hiện các giao dịch chuyển tiền quốc tế giữa các ngân hàng. Dưới đây là giải thích về các trường dữ liệu trong thông điệp MT103 từ ví dụ trên:

### Block 1 - Basic Header

```
{1:F01BANKBEBBAXXX0000000000}
```

- `F01`: Loại tin nhắn (Financial message, phiên bản 1)
- `BANKBEBBAXXX`: Mã SWIFT BIC của ngân hàng gửi
- `0000000000`: Mã số tham chiếu phiên giao dịch

### Block 2 - Application Header

```
{2:I103BANKBEBBXXXXN0000000000}
```

- `I103`: Loại tin nhắn (MT103 - Single Customer Credit Transfer)
- `BANKBEBBXXXX`: Mã SWIFT BIC của ngân hàng nhận
- `N`: Chế độ truyền dữ liệu bình thường
- `0000000000`: Số tham chiếu liên quan

### Block 3 - User Header

```
{3:{108:TEST12345}}
```

- `108:TEST12345`: Số tham chiếu của giao dịch, do ngân hàng gửi tạo

### Block 4 - Text Block (Chi tiết giao dịch)

```
:20:TEST12345
```

- `20`: Tham chiếu giao dịch của người gửi

```
:23B:CRED
```

- `23B`: Mã giao dịch, `CRED` biểu thị chuyển khoản tín dụng

```
:32A:250206USD1000.0
```

- `32A`: Ngày giao dịch và số tiền:
     - `250206`: Ngày giao dịch (06/02/2025 theo định dạng YYMMDD)
     - `USD1000.0`: Số tiền chuyển khoản (1,000.00 USD)

```
:50K:/123456789012345
John Doe
123 Main St, New York, NY
```

- `50K`: Thông tin người gửi
     - `123456789012345`: Số tài khoản người gửi
     - `John Doe`: Tên người gửi
     - `123 Main St, New York, NY`: Địa chỉ người gửi

```
:59:/987654321098765
Jane Smith
456 Park Ave, London, UK
```

- `59`: Thông tin người nhận
     - `987654321098765`: Số tài khoản người nhận
     - `Jane Smith`: Tên người nhận
     - `456 Park Ave, London, UK`: Địa chỉ người nhận

```
:70:INVOICE PAYMENT
```

- `70`: Thông tin bổ sung về thanh toán (VD: Thanh toán hóa đơn)

```
:71A:SHA
```

- `71A`: Loại phí
     - `SHA`: Phí chia sẻ (người gửi trả phí ngân hàng của họ, người nhận trả phí ngân hàng của họ)

```
:72:/INS/INT PAYMENT FOR INVOICE 12345
```

- `72`: Thông tin hướng dẫn ngân hàng trung gian (VD: Mã nội bộ của ngân hàng)

## Kết luận

Thông điệp MT103 này đại diện cho một giao dịch chuyển tiền quốc tế từ John Doe (Mỹ) đến Jane Smith (Anh), với số tiền 1,000 USD, loại giao dịch là tín dụng (CRED), thanh toán hóa đơn, phí được chia sẻ, và có thông tin hướng dẫn ngân hàng trung gian.
