import React, { useContext, useState } from "react";
import { Button, Form, Alert } from "react-bootstrap";
import cookie from "react-cookies";
import { Navigate, useNavigate } from "react-router-dom";
import { MyDispatchContext, MyUserContext } from "../../configs/Context";
import authApi, { endpoints } from "../../configs/APIs";
const Login = () => {
    const fields = [
        {
            "label": "Tên đăng nhập",
            "type": "text",
            "field": "username"
        },
        {
            "label": "Mật khẩu",
            "type": "password",
            "field": "password"
        }
    ];
    const [user, setUser] = useState({});
    const [error, setError] = useState(null);
    const dispatch = useContext(MyDispatchContext);
    const currentUser = useContext(MyUserContext);
    const nav = useNavigate();

    const login = async (e) => {
        e.preventDefault();
        console.log("Login attempt with user:", user);

        try {
            // Gửi yêu cầu POST đến /api/login/ để nhận token
            const loginResponse = await authApi.post(endpoints['login'], user, {
                headers: {
                    'Content-Type': 'application/json'
                }
            });

            // Lưu token vào cookie
            const token = loginResponse.data;
            // console.log(token);
            cookie.save("token", token);

            // Sau khi nhận được token, gửi yêu cầu GET đến /api/current-user/ để lấy thông tin người dùng
            setTimeout(async () => {
                try {
                    const userResponse = await authApi.get(endpoints['current-user'], {
                        headers: {
                            'Authorization': cookie.load('token')
                        }
                    });
                    dispatch({
                        type: "login",
                        payload: userResponse.data
                    });

                    nav("/");
                } catch (ex) {
                    console.error("Login error:", ex);
                    setError("Đăng nhập không thành công. Vui lòng thử lại.");
                }
            }, 100);
        } catch (ex) {
            console.error("Login error:", ex);
            setError("Đăng nhập không thành công. Vui lòng thử lại.");
        }
    }

    const handleChange = (event, field) => {
        setUser(current => ({
            ...current,
            [field]: event.target.value
        }));
    }

    if (currentUser !== null) {
        return <Navigate to="/" />;
    }

    return (
        <div className="container-fluid vh-100 d-flex justify-content-center align-items-center" style={{ background: "#f0f0f0" }}>
            <div className="card p-4" style={{ maxWidth: "440px", width: "100%", height: "490px", boxShadow: "0 0 10px rgba(0, 0, 0, 0.1)", background: "url(https://id.ou.edu.vn/_loginform/images/bg2.jpg)", backgroundSize: "cover", backgroundPosition: "center" }}>
                <h1 className="text-center mb-4 mt-4" style={{ color: "#FFFF00" }}>ĐĂNG NHẬP</h1>
                <Form onSubmit={login} style={{ color: "white" }}>
                    {error && <Alert variant="danger">{error}</Alert>}
                    {fields.map(f => (
                        <Form.Group key={f.field} controlId={f.field} className="mb-3 mt-3">
                            <Form.Label>{f.label}</Form.Label>
                            <Form.Control
                                type={f.type}
                                placeholder={f.label}
                                value={user[f.field] || ''}
                                onChange={e => handleChange(e, f.field)}
                                className="mb-3"
                            />
                        </Form.Group>
                    ))}
                    <Button variant="primary" type="submit" className="w-100" style={{ backgroundColor: '#FFFF00', borderColor: '#FFFF00', color: "#CC9966", marginTop: "30px" }}>
                        Đăng nhập
                    </Button>
                    <div className="mt-3 text-center">
                        <a href="http://localhost:8080/doannganh/api/login/" className="text-decoration-none" style={{ color: "#33CC00" }}>Quên mật khẩu?</a>
                    </div>
                </Form>
            </div>
        </div>
    );
}

export default Login;
