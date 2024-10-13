import React, { useEffect, useState } from 'react';
import { authApi, endpoints } from '../../configs/APIs';
import './ListStudent.css'; // Tạo file CSS nếu cần
import { useParams } from 'react-router-dom';

const ListStudent = () => {
    const [students, setStudents] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const { courseId } = useParams();

    // Hàm gọi API để lấy danh sách học viên
    useEffect(() => {
        const fetchStudents = async () => {
            try {
                const api = authApi();
                const response = await api.get(`${endpoints['liststudent']}/${courseId}`);
                const data = response.data;
                setStudents(data);
                setLoading(false);
            } catch (error) {
                console.error('Lỗi khi lấy danh sách học viên:', error);
                setError('Không thể tải danh sách học viên.');
                setLoading(false);
            }
        };

        fetchStudents();
    }, []);

    // Hiển thị thông tin trong quá trình tải dữ liệu
    if (loading) {
        return <p className="loading-text">Đang tải dữ liệu...</p>;
    }

    // Nếu có lỗi khi tải dữ liệu
    if (error) {
        return <p className="error-text">{error}</p>;
    }

    // Nếu không có học viên nào
    if (students.length === 0) {
        return <p className="no-data-text">Không có học viên nào.</p>;
    }

    return (
        <div className="list-student-container container mt-5">
            <h2 className="section-title">Danh Sách Học Viên</h2>
            <ul className="student-list">
                {students.map((student) => (
                    <li className="student-item" key={student.id}>
                        <div className="student-info">
                            <p><strong>Tên:</strong> {student.ten}</p>
                            <p><strong>Email:</strong> {student.email}</p>
                            <p><strong>Số điện thoại:</strong> {student.soDienThoai}</p>
                        </div>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default ListStudent;
