import React, { useContext, useState } from "react";
import { Button, Form, Alert } from "react-bootstrap";
import { Navigate, useNavigate } from "react-router-dom";
import cookie from "react-cookies";
import { MyDispatchContext, MyUserContext } from "../../configs/Context";
import authApi, { endpoints } from "../../configs/APIs";
import { auth, provider, signInWithPopup } from "../../configs/Firebase";  // Import Firebase config
import APIs from "../../configs/APIs";

const Login = () => {
  const fields = [
    { "label": "Tên đăng nhập", "type": "text", "field": "username" },
    { "label": "Mật khẩu", "type": "password", "field": "password" }
  ];
  const [user, setUser] = useState({});
  const [error, setError] = useState(null);
  const dispatch = useContext(MyDispatchContext);
  const currentUser = useContext(MyUserContext);
  const nav = useNavigate();

  const login = async (e) => {
    e.preventDefault();
    try {
      const loginResponse = await authApi.post(endpoints['login'], user, {
        headers: {
          'Content-Type': 'application/json'
        }
      });

      // Lưu token vào cookie
      const token = loginResponse.data;
      cookie.save("token", token);

      // Sau khi nhận được token, gửi yêu cầu GET đến /api/current-user/ để lấy thông tin người dùng
      const userResponse = await authApi.get(endpoints['current-user'], {
        headers: {
          'Authorization': cookie.load('token')
        }
      });
      dispatch({ type: "login", payload: userResponse.data });
      nav("/");
    } catch (ex) {
      console.error("Login error:", ex);
      setError("Đăng nhập không thành công. Vui lòng thử lại.");
    }
  };

  const handleGoogleLogin = async () => {
    try {
      const result = await signInWithPopup(auth, provider);
      const user = result.user;

      // Lấy token ra
      const firebaseToken = await user.getIdToken();
      // console.log("Token Firebase:", firebaseToken);

      // Gửi yêu cầu tới API
      try {
        const response = await APIs.post(endpoints["google-firebaseuser"], null, {
          params: { token: firebaseToken }
        });
        // console.log(response.data);

        if (response.status === 200) {
          ///Nếu thành công thì fetch API tạo token
          try {
            const responseJwtToken = await APIs.post(endpoints["google-createtoken"], null, {
              params: { token: firebaseToken }
            });
            // console.log("Thành công sinh jwt token");
            // console.log("Token JWT: " + responseJwtToken.data);

            if (responseJwtToken.status === 200) {
              cookie.save("token", responseJwtToken.data);

              // Chạy Current user
              const userResponseJwt = await authApi.get(endpoints['current-user'], {
                headers: {
                  'Authorization': cookie.load('token')
                }
              });
              dispatch({ type: "login", payload: userResponseJwt.data });
              nav("/");
            }
          } catch (error) {
            console.error('Lỗi, token hết hạn', error);
            setError('Lỗi, token hết hạn');
          }
        }
      } catch (error) {
        setError('Lỗi, đã có tài khoản đăng ký với gmail này');
        console.error('Lỗi, đã có tài khoản đăng ký với gmail này', error);
      }

    } catch (error) {
      console.error("Google login error:", error);
      setError("Đăng nhập bằng Google không thành công. Vui lòng thử lại.");
    }
  };

  const handleChange = (event, field) => {
    setUser(current => ({
      ...current,
      [field]: event.target.value
    }));
  };

  if (currentUser !== null) {
    return <Navigate to="/" />;
  }

  return (
    <div className="container-fluid vh-100 d-flex justify-content-center align-items-center" style={{ background: "#f0f0f0" }}>
      <div className="card p-4" style={{ maxWidth: "440px", width: "100%", height: "580px", boxShadow: "0 0 10px rgba(0, 0, 0, 0.1)", background: "url(https://id.ou.edu.vn/_loginform/images/bg2.jpg)", backgroundSize: "cover", backgroundPosition: "center" }}>
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
          <Button
            variant="secondary"
            onClick={handleGoogleLogin}
            className="w-100 mt-3"
            style={{
              backgroundColor: '#ffffff',
              borderColor: '#4285F4',
              color: '#4285F4',
              borderWidth: '2px',
              borderStyle: 'solid',
              display: 'flex',
              alignItems: 'center',
              justifyContent: 'center',
              padding: '10px',
              borderRadius: '6px',
            }}
          >
            <img
              src="https://cdn1.iconfinder.com/data/icons/google-s-logo/150/Google_Icons-09-512.png "
              alt="Google Icon"
              style={{ width: '20px', height: '20px', marginRight: '8px' }}
            />
            Đăng nhập bằng Google
          </Button>

          <div className="mt-3 text-center">
            <a href="http://localhost:8080/doannganh/api/login/" className="text-decoration-none" style={{ color: "#33CC00" }}>Quên mật khẩu?</a>
          </div>
        </Form>
      </div>
    </div>
  );
};

export default Login;
