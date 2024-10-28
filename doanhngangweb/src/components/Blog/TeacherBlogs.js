import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { authApi, endpoints } from '../../configs/APIs';
import Spinner from '../commons/MySpinner';
import './TeacherBlogs.css';

const TeacherBlogs = () => {
    const [blogs, setBlogs] = useState([]);
    const [myBlogs, setMyBlogs] = useState([]);
    const [loading, setLoading] = useState(true);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchBlogs = async () => {
            try {
                const api = authApi();
                const response = await api.get(endpoints['teacherblogs']);
                setBlogs(response.data);
            } catch (error) {
                console.error('Error fetching blogs:', error);
            }
        };

        const fetchMyBlogs = async () => {
            try {
                const api = authApi();
                const response = await api.get(endpoints['myblogs']);
                setMyBlogs(response.data);
            } catch (error) {
                console.error('Error fetching blogs:', error);
            }
        };

        const fetchData = async () => {
            setLoading(true); // Bắt đầu tải
            await Promise.all([fetchBlogs(), fetchMyBlogs()]); // Chờ cả hai fetch hoàn thành
            setLoading(false); // Kết thúc tải
        };

        fetchData();
    }, []);

    // Hàm để xóa bài đăng
    const handleDelete = async (id) => {
        const confirmDelete = window.confirm('Bạn có chắc chắn muốn xóa bài đăng này không?');
        if (confirmDelete) {
            try {
                const api = authApi();
                const response = await api.delete(`${endpoints['delete']}/${id}`);

                if (response.status === 204) {
                    setBlogs(blogs.filter(blog => blog.id !== id));
                    alert('Xóa bài đăng thành công!');
                } else {
                    throw new Error('Có lỗi xảy ra khi xóa bài đăng.');
                }
            } catch (error) {
                console.error('Lỗi:', error);
                alert('Có lỗi xảy ra khi xóa bài đăng.');
            }
        }
    };

    // Hàm để chọn ảnh ngẫu nhiên từ thư mục
    const getRandomImage = () => {
        const randomIndex = Math.floor(Math.random() * 19) + 1;
        return `/images/blog-thumbnails/image${randomIndex}.jpg`;
    };

    return (
        <div className="container mt-4">
            {/* Hiển thị spinner nếu đang tải dữ liệu */}
            {loading ? (
                <div className="text-center">
                    <Spinner />
                </div>
            ) : (
                <>
                    {/* Các bài đăng cần chỉnh sửa của giáo viên */}
                    <h1 className='line' style={{ paddingTop: 20, paddingBottom: 30 }}>Danh Sách Bài Đăng Chỉnh Sửa</h1>
                    <div className="row">
                        {blogs.map((blog) => (
                            <div className="col-md-4" key={blog.id}>
                                <div className="card blog-card mb-4 shadow-sm">
                                    <img
                                        src={getRandomImage()}
                                        className="card-img-top"
                                        alt="Blog thumbnail"
                                    />
                                    <div className="card-body">
                                        <h5 className="card-title text-primary">{blog.tieuDe}</h5>
                                        <p className="card-text text-muted">{new Date(blog.ngayDang).toLocaleDateString('vi-VN')}</p>
                                        <p className="card-text text-muted">{blog.noiDungPhanHoi}</p>
                                        <button
                                            className="btn btn-outline-primary w-100"
                                            onClick={() => {
                                                const url = `/demo/${blog.id}`;
                                                window.open(url, '_blank');
                                            }}
                                        >
                                            Xem Bài Đăng
                                        </button>
                                        <button
                                            className="btn btn-outline-warning w-100 mt-1"
                                            onClick={() => navigate('/postblog', { state: { blog } })}
                                        >
                                            Chỉnh Sửa
                                        </button>
                                        <button
                                            className="btn btn-outline-danger w-100 mt-1"
                                            onClick={() => handleDelete(blog.id)}
                                        >
                                            Xóa
                                        </button>
                                    </div>
                                </div>
                            </div>
                        ))}
                    </div>

                    {/* Các bài đăng public của giáo viên */}
                    <h1 className='line' style={{ paddingTop: 20, paddingBottom: 30 }}>Danh Sách Bài Đăng Của Bạn</h1>
                    <div className="row">
                        {myBlogs.map((blog) => (
                            <div className="col-md-4" key={blog.id}>
                                <div className="card blog-card-1 mb-4 shadow-sm">
                                    <img
                                        src={getRandomImage()}
                                        className="card-img-top"
                                        alt="Blog thumbnail"
                                    />
                                    <div className="card-body">
                                        <h5 className="card-title text-primary">{blog.tieuDe}</h5>
                                        <p className="card-text text-muted">{new Date(blog.ngayDang).toLocaleDateString('vi-VN')}</p>
                                        <button
                                            className="btn btn-outline-primary w-100"
                                            onClick={() => {
                                                const url = `/demo/${blog.id}`;
                                                window.open(url, '_blank');
                                            }}
                                        >
                                            Xem Bài Đăng
                                        </button>
                                    </div>
                                </div>
                            </div>
                        ))}
                    </div>
                </>
            )}
        </div>
    );
}

export default TeacherBlogs;
