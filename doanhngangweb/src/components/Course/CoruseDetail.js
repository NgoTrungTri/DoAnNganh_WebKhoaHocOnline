import React, { useEffect, useState } from 'react';
import { authApi, endpoints } from '../../configs/APIs';
import { useParams } from 'react-router-dom';
import './CourseDetail.css'; // Import file CSS tùy chỉnh

const CourseDetail = () => {
    const [courseDetail, setCourseDetail] = useState(null);
    const { courseId } = useParams();
    const [loading, setLoading] = useState(true);

    // Hàm gọi API để lấy chi tiết khóa học
    useEffect(() => {
        const fetchCourseDetail = async () => {
            try {
                const api = authApi();
                const response = await api.get(`${endpoints['course-detail']}/${courseId}`);
                const data = response.data;
                setCourseDetail(data);
                setLoading(false);
            } catch (error) {
                console.error('Lỗi khi lấy chi tiết khóa học:', error);
                setLoading(false);
            }
        };

        fetchCourseDetail();
    }, [courseId]);

    // Hiển thị thông tin trong quá trình tải dữ liệu
    if (loading) {
        return <p className="loading-text">Đang tải dữ liệu...</p>;
    }

    // Nếu không có dữ liệu
    if (!courseDetail) {
        return <p className="error-text">Không tìm thấy thông tin khóa học.</p>;
    }

    return (
        <div className="course-detail-container container mt-5">
            <div className="row">
                <div className="col-lg-8">
                    {/* Danh sách đề cương */}
                    <div className="course-outline mt-3 mb-2">
                        <h2 className="section-title">Đề Cương Khóa Học</h2>
                        <ul className="file-list">
                            {courseDetail.map((decuong) => (
                                <li className="file-item" key={decuong.id}>
                                    <div className="file-container">
                                        {/* Biểu tượng tập tin */}
                                        <div className="file-icon">
                                            <i className="fas fa-file-pdf"></i>
                                        </div>
                                        {/* Thông tin về đề cương */}
                                        <div className="file-info">
                                            <h3 className="chapter-title">{decuong.tenDeCuong}</h3>
                                            <a href={decuong.urlFile} target="_blank" rel="noopener noreferrer" className="file-link">
                                                Tải về
                                            </a>
                                        </div>
                                    </div>
                                </li>
                            ))}
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default CourseDetail;
