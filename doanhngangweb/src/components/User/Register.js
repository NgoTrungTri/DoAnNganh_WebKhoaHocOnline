import { useRef, useState } from "react";
import { Button, Container, Form } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import APIs, { endpoints } from "../../configs/APIs";

const Register = () => {
    const fields = [
        {
            label: "Nhập Họ",
            type: "text",
            field: "ho"
        },
        {
            label: "Nhập Tên",
            type: "text",
            field: "ten"
        },       
        {
            label: "Email",
            type: "text",
            field: "email"
        }, {
            label: "Tên đăng nhập",
            type: "text",
            field: "username"
        }, {
            label: "Mật khẩu",
            type: "password",
            field: "password"
        }, {
            label: "Xác nhận mật khẩu",
            type: "password",
            field: "confirm"
        }, {
            label: "Ngày Sinh",
            type: "date",
            field: "ngaySinh"
        }];

    const [user, setUser] = useState({});
    const avatar = useRef();
    const nav = useNavigate();

    const change = (e, field) => {
        setUser(current => ({ ...current, [field]: e.target.value }));
    }

    const register = async (e) => {
        e.preventDefault();

        let form = new FormData();
        for (let key in user)
            if (key !== 'confirm')
                form.append(key, user[key]);

        if (avatar.current?.files[0])
            form.append('file', avatar.current.files[0]);

        try {
            let res = await APIs.post(endpoints['register'], form, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            });
            if (res.status === 201)
                nav("/login");
        } catch (ex) {
            console.error(ex);
        }
    }

    return (
        <Container>
            <h1 className="text-center text-info mt-3">ĐĂNG KÝ NGƯỜI DÙNG</h1>
            <Form onSubmit={register} style={{ width: '75%', margin: 'auto' }}>
                {fields.map(f => (
                    <Form.Group key={f.field} className="mb-3" controlId={f.field}>
                        <Form.Label>{f.label}</Form.Label>
                        <Form.Control onChange={e => change(e, f.field)} value={user[f.field] || ''} type={f.type} placeholder={f.label} />
                    </Form.Group>
                ))}

                {/* Gender Selection */}
                <Form.Group className="mb-3">
                    <Form.Label>Giới Tính</Form.Label><br />
                    <Form.Check
                        inline
                        label="Nam"
                        type="radio"
                        name="gioiTinh"
                        value="Nam"
                        checked={user.gioiTinh === "Nam"}
                        onChange={e => change(e, 'gioiTinh')}
                    />
                    <Form.Check
                        inline
                        label="Nữ"
                        type="radio"
                        name="gioiTinh"
                        value="Nữ"
                        checked={user.gioiTinh === "Nữ"}
                        onChange={e => change(e, 'gioiTinh')}
                    />
                </Form.Group>

                <Form.Group className="mb-3" controlId="avatar">
                    <Form.Label>Ảnh đại diện</Form.Label>
                    <Form.Control type="file" accept=".png,.jpg" ref={avatar} />
                </Form.Group>

                <Form.Group className="mb-3">
                    <Button type="submit" variant="primary">Đăng ký</Button>
                </Form.Group>
            </Form>
        </Container>
    );
}

export default Register;
