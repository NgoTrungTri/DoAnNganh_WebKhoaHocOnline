// import { useState } from "react";
// import { Button, Container, Form, Alert } from "react-bootstrap";
// import { useNavigate, useLocation } from "react-router-dom";
// import APIs, { endpoints } from "../../configs/APIs";

// const OtpVerify = () => {
//     const location = useLocation();
//     const [otp, setOtp] = useState("");
//     const [email, setEmail] = useState(location.state?.email || ""); // Lấy email từ state
//     const [error, setError] = useState("");
//     const nav = useNavigate();

//     const verifyOtp = async (e) => {
//         e.preventDefault();

//         try {
//             let res = await APIs.post(endpoints["verifyOtp"], { email, otp });
//             if (res.status === 201) {
//                 // OTP hợp lệ, chuyển người dùng đến trang login
//                 nav("/login");
//             }
//         } catch (ex) {
//             console.error(ex);
//             setError("Mã OTP không hợp lệ hoặc có lỗi xảy ra.");
//         }
//     };

//     return (
//         <Container>
//             <h1 className="text-center text-info mt-3">XÁC NHẬN OTP</h1>
//             {error && <Alert variant="danger">{error}</Alert>}
//             <Form onSubmit={verifyOtp} style={{ width: "50%", margin: "auto" }}>
//                 <Form.Group className="mb-3" controlId="email">
//                     <Form.Label>Email</Form.Label>
//                     <Form.Control type="email" value={email} readOnly /> {/* Email không thay đổi */}
//                 </Form.Group>

//                 <Form.Group className="mb-3" controlId="otp">
//                     <Form.Label>Mã OTP</Form.Label>
//                     <Form.Control
//                         type="text"
//                         placeholder="Nhập mã OTP"
//                         value={otp}
//                         onChange={(e) => setOtp(e.target.value)}
//                     />
//                 </Form.Group>

//                 <Form.Group className="mb-3">
//                     <Button type="submit" variant="primary">
//                         Xác nhận OTP
//                     </Button>
//                 </Form.Group>
//             </Form>
//         </Container>
//     );
// };

// export default OtpVerify;
import { useState } from "react";
import { Button, Container, Form, Alert, Row, Col, Card } from "react-bootstrap";
import { useNavigate, useLocation } from "react-router-dom";
import APIs, { endpoints } from "../../configs/APIs";

const OtpVerify = () => {
    const location = useLocation();
    const [otp, setOtp] = useState("");
    const [email, setEmail] = useState(location.state?.email || ""); 
    const [error, setError] = useState("");
    const nav = useNavigate();

    const verifyOtp = async (e) => {
        e.preventDefault();
        try {
            let res = await APIs.post(endpoints["verifyOtp"], { email, otp });
            if (res.status === 201) {
                nav("/login");
            }
        } catch (ex) {
            console.error(ex);
            setError("Mã OTP không hợp lệ hoặc có lỗi xảy ra.");
        }
    };

    return (
        <Container className="d-flex justify-content-center align-items-center" style={{ minHeight: "100vh" }}>
            <Card className="shadow-lg" style={{ width: "30rem", padding: "2rem" }}>
                <Card.Body>
                    <h3 className="text-center text-info mb-4">Xác Nhận OTP</h3>
                    {error && <Alert variant="danger">{error}</Alert>}
                    <Form onSubmit={verifyOtp}>
                        <Form.Group className="mb-3" controlId="email">
                            <Form.Label>Email đã đăng ký</Form.Label>
                            <Form.Control type="email" value={email} readOnly className="bg-light" />
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="otp">
                            <Form.Label>Mã OTP</Form.Label>
                            <Form.Control
                                type="text"
                                placeholder="Nhập mã OTP"
                                value={otp}
                                onChange={(e) => setOtp(e.target.value)}
                                required
                            />
                        </Form.Group>
                        <Button type="submit" variant="primary" className="w-100 mt-3">
                            Xác Nhận OTP
                        </Button>
                    </Form>
                </Card.Body>
            </Card>
        </Container>
    );
};

export default OtpVerify;
