import React, { useEffect, useState } from "react";
import { Card, Container, Row, Col, Table } from "react-bootstrap";
import authApi, { endpoints } from "../../configs/APIs";
import cookie from "react-cookies";
import moment from "moment";// Cái này để formart date lại

const UserDetails = () => {
    const [userDetails, setUserDetails] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchUserDetails = async () => {
            try {
                const response = await authApi.get(endpoints['current-user'], {
                    headers: {
                        'Authorization': cookie.load('token')
                    }
                });
                setUserDetails(response.data);
                setLoading(false);
            } catch (ex) {
                console.error("Error fetching user details:", ex);
                setError("Lỗi khi tải thông tin.");
                setLoading(false);
            }
        };

        fetchUserDetails();
    }, []);

    if (loading) {
        return <p>Đang tải thông tin học viên...</p>;
    }

    if (error) {
        return <p>{error}</p>;
    }

    if (!userDetails) {
        return <p>Không có thông tin học viên để hiển thị.</p>;
    }

    return (
        <Container className="mt-5 mb-4" style={{paddingTop: 60}}>
            <Card>
                <Card.Header as="h4">Thông tin người dùng</Card.Header>
                <Card.Body>
                    <Row>
                        <Col md={4} className="text-center">
                            <div style={{ width: '100%', paddingTop: '100%', position: 'relative'  }}>
                                <img
                                    src={userDetails.avatar}
                                    alt="Avatar"
                                    className="img-fluid"
                                    style={{
                                        position: 'absolute',
                                        top: 0,
                                        left: 0,
                                        width: '100%',
                                        height: '100%',
                                        objectFit: 'cover'
                                    }}
                                />
                            </div>
                        </Col>
                        <Col md={8}>
                            <Table striped bordered hover>
                                <tbody>
                                    <tr>
                                        <td>Họ và tên</td>
                                        <td>{userDetails.ho} {userDetails.ten}</td>
                                    </tr>
                                    <tr>
                                        <td>Tên đăng nhập</td>
                                        <td>{userDetails.username}</td>
                                    </tr>
                                    <tr>
                                        <td>Email</td>
                                        <td>{userDetails.email}</td>
                                    </tr>
                                    <tr>
                                        <td>Giới tính</td>
                                        <td>{userDetails.gioiTinh}</td>
                                    </tr>
                                    <tr>
                                        <td>Ngày sinh</td>
                                        <td>{moment(userDetails.ngaysinh).format("DD/MM/YYYY")}</td>
                                    </tr>
                                    
                                </tbody>
                            </Table>
                        </Col>
                    </Row>
                </Card.Body>
            </Card>
        </Container>
    );
}

export default UserDetails;
