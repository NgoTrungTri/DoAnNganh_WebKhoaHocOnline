import React from 'react';
import './PaymentSuccesful.css'; // Đảm bảo bạn tạo file CSS này để định dạng

const PaymentSuccessful = () => {
    return (
        <div className="payment-successful-container">
            <div className="payment-successful-wrapper">
                <h1 className="payment-successful-title">Cảm Ơn Bạn!</h1>
                <p className="payment-successful-message">
                    Giao dịch của bạn đã được thực hiện thành công.
                </p>
                <p className="payment-successful-detail">
                    Chúng tôi rất cảm kích sự tin tưởng của bạn. Vui lòng kiểm tra email của bạn để nhận thông tin chi tiết về khóa học.
                </p>
            </div>
        </div>
    );
}

export default PaymentSuccessful;
