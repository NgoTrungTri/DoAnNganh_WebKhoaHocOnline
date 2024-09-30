import React, { useEffect, useState } from 'react';
import { authApi, endpoints } from '../../configs/APIs';
import { useNavigate } from 'react-router-dom';
import './TeacherCourse.css';

const TeacherCourse = () => {
    const [courses, setCourses] = useState([]); 
    const navigate = useNavigate();

    useEffect(() => {
        const fetchCourses = async () => {
            try {
                const api = authApi();
                const response = await api.get(endpoints['teacherCourse']);
                setCourses(response.data);
            } catch (error) {
                console.error('Error fetching courses:', error);
            }
        };

        fetchCourses();
    }, []);

    return (
        <div className="container mt-4">
            <h1 className='text-center mt-4 mb-4' style={{ paddingTop: 20, paddingBottom: 30 }}>Danh Sách Khóa Học</h1>
            <div className="row">
                {courses.map((course) => (
                    <div className="col-md-4" key={course.id}>
                        <div className="card blog-card mb-4 shadow-sm">
                            <img
                                src={getRandomImage()} // Thay thế hình ảnh nếu cần
                                className="card-img-top"
                                alt="Course thumbnail"
                            />
                            <div className="card-body">
                                <h5 className="card-title text-primary">{course.tenKhoaHoc}</h5>
                                <p className="card-text text-muted">{`Ngày bắt đầu: ${new Date(course.ngayBatDau).toLocaleDateString('vi-VN')}`}</p>
                                <p className="card-text text-muted">{`Ngày kết thúc: ${new Date(course.ngayKetThuc).toLocaleDateString('vi-VN')}`}</p>
                                <p className="card-text text-muted">{`Giá: ${course.giaTien.toLocaleString()} VNĐ`}</p>
                                <button
                                    className="btn btn-outline-primary w-100"
                                    onClick={() => {
                                        navigate(`/coursedetail/${course.id}`);
                                    }}
                                >
                                    Xem Khóa Học
                                </button>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
}

// Hàm để chọn ảnh ngẫu nhiên từ thư mục
const getRandomImage = () => {
    const randomIndex = Math.floor(Math.random() * 8) + 1;
    return `/images/course-thumbnails/image${randomIndex}.jpg`; 
};

export default TeacherCourse;
