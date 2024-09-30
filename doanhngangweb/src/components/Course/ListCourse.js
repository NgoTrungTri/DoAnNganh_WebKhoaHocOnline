import React, { useEffect, useState } from "react";
import './ListCourse.css';
import APIs, { authApi, endpoints } from "../../configs/APIs";

const ListCourse = () => {
    const [coursesMoiNhat, setCoursesMoiNhat] = useState([]);
    const [currentPageNgoaiNgu, setCurrentPageNgoaiNgu] = useState(0);
    const [currentPageTinHoc, setCurrentPageTinHoc] = useState(0);
    const [coursesNgoaiNgu, setCoursesNgoaiNgu] = useState([]);
    const [coursesTinHoc, setCoursesTinHoc] = useState([]);

    // Load khóa học Ngoại Ngữ
    useEffect(() => {
        async function fetchCoursesNgoaiNgu() {
            try {
                const response = await APIs.get(`${endpoints.loadkhoahocngoaingu}?page=${currentPageNgoaiNgu}`);
                setCoursesNgoaiNgu(response.data);
            } catch (error) {
                console.error('Error fetching Ngoai Ngu courses:', error);
            }
        }
        fetchCoursesNgoaiNgu();
    }, [currentPageNgoaiNgu]);

    // Load khóa học Tin Học
    useEffect(() => {
        async function fetchCoursesTinHoc() {
            try {
                const response = await APIs.get(`${endpoints.loadkhoahoctinhoc}?page=${currentPageTinHoc}`);
                setCoursesTinHoc(response.data);
            } catch (error) {
                console.error('Error fetching Tin Hoc courses:', error);
            }
        }
        fetchCoursesTinHoc();
    }, [currentPageTinHoc]);

    // Hàm để xử lý nút "Prev" (Trang trước) cho Ngoại Ngữ
    const handlePrevPageNgoaiNgu = () => {
        if (currentPageNgoaiNgu > 0) {
            setCurrentPageNgoaiNgu(currentPageNgoaiNgu - 1);
        }
    };

    // Hàm để xử lý nút "Next" (Trang sau) cho Ngoại Ngữ
    const handleNextPageNgoaiNgu = () => {
        setCurrentPageNgoaiNgu(currentPageNgoaiNgu + 1);
    };

    // Hàm để xử lý nút "Prev" (Trang trước) cho Tin Học
    const handlePrevPageTinHoc = () => {
        if (currentPageTinHoc > 0) {
            setCurrentPageTinHoc(currentPageTinHoc - 1);
        }
    };

    // Hàm để xử lý nút "Next" (Trang sau) cho Tin Học
    const handleNextPageTinHoc = () => {
        setCurrentPageTinHoc(currentPageTinHoc + 1);
    };

    useEffect(() => {
        async function fetchCoursesMoiNhat() {
            try {
                const response = await APIs.get(endpoints.load4khoahocmoinhat);
                console.log(response.data);
                setCoursesMoiNhat(response.data);
            } catch (error) {
                console.error('Error fetching courses:', error);
            }
        }
        fetchCoursesMoiNhat();
    }, []);

    const getRandomImage = () => {
        const randomIndex = Math.floor(Math.random() * 19) + 1;
        return `/images/blog-thumbnails/image${randomIndex}.jpg`;
    };

    const handlePayment = async (khoaHocId) => {
        try {
            const api = authApi();
            const response = await api.post(`${endpoints['muakhoahoc']}/${khoaHocId}`);
            const data = response.data;

            if (response.status === 200) {
                const tabB = window.open(data.paymentUrl, '_blank');

                // Theo dõi trạng thái
                const checkTabStatusInterval = setInterval(async () => {
                    if (tabB && tabB.closed) {
                        clearInterval(checkTabStatusInterval);
                    } else if (tabB) {
                        const currentOrigin = window.location.origin;
                        try {
                            const tabUrl = tabB.location.href;
                            if (tabUrl.startsWith(currentOrigin)) {
                                await api.post(`${endpoints['taodonhang']}/${khoaHocId}`);
                                console.log("Tạo đơn hàng thành công");
                                clearInterval(checkTabStatusInterval);
                            }
                        } catch (e) {
                            console.warn('Đang chờ để thanh toán thành công');
                        }
                    }
                }, 10000);

            } else {
                console.error(data);
                alert(data);
            }
        } catch (error) {
            console.error('Lỗi khi mua khóa học:', error);
            alert('Đã xảy ra lỗi khi mua khóa học. Vui lòng thử lại.');
        }
    }

    return (
        <div className="list-course-container">
            <div className="list-course-wrapper">
                <h2 className="newLine">CÁC KHÓA HỌC MỚI NHẤT</h2>
                <div className="list-course-grid">
                    {coursesMoiNhat.map(course => (
                        <div key={course.id} className="list-course-card">
                            <img src={getRandomImage()} alt="" className="list-course-image" />
                            <h3>{course.tenKhoaHoc}</h3>
                            <p>Start: {new Date(course.ngayBatDau).toLocaleDateString('vi-VN')}</p>
                            <p>End: {new Date(course.ngayKetThuc).toLocaleDateString('vi-VN')}</p>
                            <p className="list-course-price">{course.giaTien}.VND</p>
                            <button className="list-course-buy-button"
                                onClick={() => handlePayment(course.id)}
                            >
                                Mua ngay
                            </button>
                        </div>
                    ))}
                </div>
            </div>

            {/* Phần hiển thị khóa học Ngoại Ngữ */}
            <div className="courses-wrapper">
                <h2 className="newLine">CÁC KHÓA HỌC NGOẠI NGỮ</h2>
                <div className="courses-grid">

                    <button className="prev-button" onClick={handlePrevPageNgoaiNgu} disabled={currentPageNgoaiNgu === 0}>
                        &lt;
                    </button>

                    {coursesNgoaiNgu.map(course => (
                        <div key={course.id} className="course-card">
                            <img src={getRandomImage()} alt={course.tenKhoaHoc} className="course-image" />
                            <h3>{course.tenKhoaHoc}</h3>
                            <p>Start: {new Date(course.ngayBatDau).toLocaleDateString('vi-VN')}</p>
                            <p>End: {new Date(course.ngayKetThuc).toLocaleDateString('vi-VN')}</p>
                            <p className="course-price">{course.giaTien}.VND</p>
                            <button className="buy-button"
                                onClick={() => handlePayment(course.id)}
                            >
                                Mua ngay
                            </button>
                        </div>
                    ))}

                    <button className="next-button" onClick={handleNextPageNgoaiNgu} disabled={coursesTinHoc.length < 4}>
                        &gt;
                    </button>

                </div>
            </div>

            {/* Phần hiển thị khóa học Tin Học */}
            <div className="courses-wrapper">
                <h2 className="newLine">CÁC KHÓA HỌC TIN HỌC</h2>
                <div className="courses-grid">

                    <button className="prev-button" onClick={handlePrevPageTinHoc} disabled={currentPageTinHoc === 0}>
                        &lt;
                    </button>

                    {coursesTinHoc.map(course => (
                        <div key={course.id} className="course-card">
                            <img src={getRandomImage()} alt={course.tenKhoaHoc} className="course-image" />
                            <h3>{course.tenKhoaHoc}</h3>
                            <p>Start: {new Date(course.ngayBatDau).toLocaleDateString('vi-VN')}</p>
                            <p>End: {new Date(course.ngayKetThuc).toLocaleDateString('vi-VN')}</p>
                            <p className="course-price">{course.giaTien}.VND</p>
                            <button className="buy-button"
                                onClick={() => handlePayment(course.id)}
                            >
                                Mua ngay
                            </button>
                        </div>
                    ))}

                    <button className="next-button" onClick={handleNextPageTinHoc} disabled={coursesTinHoc.length < 4}>
                        &gt;
                    </button>

                </div>
            </div>
        </div>
    );
}

export default ListCourse;
