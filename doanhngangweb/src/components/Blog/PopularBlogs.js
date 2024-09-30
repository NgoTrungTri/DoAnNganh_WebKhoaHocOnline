import React, { useEffect, useState } from 'react';
import APIs, { endpoints } from '../../configs/APIs';
import './PopularBlogs.css';

const PopularBlogs = () => {
    const [blogs, setBlogs] = useState([]);
    const [NgoaiNgublogs, setNgoaiNguBlogs] = useState([]);
    const [TinHocblogs, setTinHocBlogs] = useState([]);

    useEffect(() => {
        const fetchBlogs = async () => {
            try {
                const response = await APIs.get(endpoints.baidangmoinhat);
                setBlogs(response.data);
            } catch (error) {
                console.error('Error fetching popular blogs:', error);
            }
        };

        const fetchNgoaiNguBlogs = async () => {
            try {
                const response = await APIs.get(endpoints.baidangngoaingu);
                setNgoaiNguBlogs(response.data);
            } catch (error) {
                console.error('Error fetching popular blogs:', error);
            }
        };

        const fetchTinHocBlogs = async () => {
            try {
                const response = await APIs.get(endpoints.baidangtinhoc);
                setTinHocBlogs(response.data);
            } catch (error) {
                console.error('Error fetching popular blogs:', error);
            }
        };

        fetchBlogs();
        fetchNgoaiNguBlogs();
        fetchTinHocBlogs();
    }, []);

    // Hàm để chọn ảnh ngẫu nhiên từ thư mục
    const getRandomImage = () => {
        const randomIndex = Math.floor(Math.random() * 19) + 1;
        return `/images/blog-thumbnails/image${randomIndex}.jpg`;
    };

    return (
        <div className="container mt-4">
            <h1 className='line' style={{ paddingTop: 20, paddingBottom: 30}}>Bài Viết Mới Nhất</h1>
            <div className="row">
                {blogs.map((blog) => (
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

            {/* Phần bài viết ngoại ngữ */}
            <h1 className='line' style={{ paddingTop: 20, paddingBottom: 30}}>Bài Viết Ngoại Ngữ Bổ Ích</h1>
            <div className="row">
                {NgoaiNgublogs.map((blog) => (
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

            {/* Bài vietes về tin học */}
            <h1 className='newLine' style={{ paddingTop: 20, paddingBottom: 30}}>Bài Viết Tin Học Bổ Ích</h1>
            <div className="row">
                {TinHocblogs.map((blog) => (
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
        </div>
    );
};

export default PopularBlogs;
