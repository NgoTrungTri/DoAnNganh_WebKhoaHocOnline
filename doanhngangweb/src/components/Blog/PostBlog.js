// import React, { useState, useEffect } from "react";
// import { CKEditor } from '@ckeditor/ckeditor5-react';
// import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
// import { authApi, endpoints } from "../../configs/APIs";
// import { useNavigate } from 'react-router-dom';

// const PostBlog = () => {
//     const [tieuDe, setTieuDe] = useState("");
//     const [noiDung, setNoiDung] = useState("");
//     const [videoUrl, setVideoUrl] = useState("");
//     const [moTa, setMoTa] = useState("");
//     const [danhMucId, setDanhMucId] = useState("");
//     const [danhMucList, setDanhMucList] = useState([]);
//     const navigate = useNavigate();

//     const fetchCategories = async () => {
//         try {
//             const response = await authApi().get(endpoints['categories']);
//             setDanhMucList(response.data);
//         } catch (error) {
//             if (error.response) {
//                 console.error("Lỗi phản hồi từ server:", error.response.data);
//                 console.error("Status code:", error.response.status);
//             } else {
//                 console.error("Lỗi kết nối hoặc cài đặt:", error.message);
//             }
//         }
//     };

//     useEffect(() => {
//         fetchCategories(); 
//     }, []);

//     const handleSubmit = async (e) => {
//         e.preventDefault();
//         const data = {
//             tieuDe,
//             noiDung,
//             videoUrl,
//             moTa,
//             danhMucId 
//         };

//         try {
//             const api = authApi();      
//             const response = await api.post(endpoints['postBlog'], data);
//             console.log("Bài đăng thành công:", response.data);
//             // Chuyển hướng đến trang chủ
//             navigate(`/teacherblogs`);
//         } catch (error) {
//             console.error("Lỗi khi đăng bài:", error.response ? error.response.data : error.message);
//         }
//     };

//     return (
//         <div className="post-blog">
//             <h1>Đăng Bài</h1>
//             <form onSubmit={handleSubmit}>
//                 <div className="form-group">
//                     <label htmlFor="tieuDe">Tiêu đề:</label>
//                     <input
//                         type="text"
//                         id="tieuDe"
//                         value={tieuDe}
//                         onChange={(e) => setTieuDe(e.target.value)}
//                         className="form-control"
//                         required
//                     />
//                 </div>

//                 <div className="form-group">
//                     <label htmlFor="noiDung">Nội dung:</label>
//                     <CKEditor
//                         editor={ClassicEditor}
//                         data={noiDung}
//                         onChange={(event, editor) => {
//                             const data = editor.getData();
//                             setNoiDung(data);
//                         }}
//                     />
//                 </div>

//                 <div className="form-group">
//                     <label htmlFor="videoUrl">Link Video (YouTube):</label>
//                     <input
//                         type="text"
//                         id="videoUrl"
//                         value={videoUrl}
//                         onChange={(e) => setVideoUrl(e.target.value)}
//                         className="form-control"
//                     />
//                 </div>

//                 <div className="form-group">
//                     <label htmlFor="moTa">Mô tả Video:</label>
//                     <textarea
//                         id="moTa"
//                         value={moTa}
//                         onChange={(e) => setMoTa(e.target.value)}
//                         className="form-control"
//                     />
//                 </div>

//                 <div className="form-group">
//                     <label htmlFor="danhMuc">Danh mục:</label>
//                     <select
//                         id="danhMuc"
//                         value={danhMucId}
//                         onChange={(e) => setDanhMucId(e.target.value)}
//                         className="form-control"
//                         required
//                     >
//                         <option value="">Chọn danh mục</option>
//                         {danhMucList.length > 0 ? (
//                             danhMucList.map((danhMuc) => (
//                                 <option key={danhMuc.id} value={danhMuc.id}>
//                                     {danhMuc.tenDanhMuc}
//                                 </option>
//                             ))
//                         ) : (
//                             <option value="">Không có danh mục nào</option>
//                         )}
//                     </select>
//                 </div>

//                 <button type="submit" className="btn btn-primary">Đăng Bài</button>
//             </form>
//         </div>
//     );
// };

// export default PostBlog;



import React, { useState, useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom"; // Import useLocation và useNavigate
import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import { authApi, endpoints } from "../../configs/APIs";

const PostBlog = () => {
    const location = useLocation(); // Lấy location
    const navigate = useNavigate(); // Khởi tạo navigate
    const blog = location.state?.blog; // Lấy dữ liệu bài viết từ state

    // Khởi tạo các state
    const [tieuDe, setTieuDe] = useState(blog ? blog.tieuDe : ""); // Nếu có blog, gán giá trị
    const [noiDung, setNoiDung] = useState(blog ? blog.noiDung : ""); // Tương tự với nội dung
    const [videoUrl, setVideoUrl] = useState(blog ? blog.videoUrl : ""); // Gán videoUrl nếu có
    const [moTa, setMoTa] = useState(blog ? blog.moTa : ""); // Gán mô tả nếu có
    const [danhMucId, setDanhMucId] = useState(blog ? blog.danhMucId : ""); // Gán danh mục nếu có
    const [danhMucList, setDanhMucList] = useState([]);

    // Fetch danh mục
    const fetchCategories = async () => {
        try {
            const response = await authApi().get(endpoints['categories']);
            setDanhMucList(response.data);
        } catch (error) {
            if (error.response) {
                console.error("Lỗi phản hồi từ server:", error.response.data);
                console.error("Status code:", error.response.status);
            } else {
                console.error("Lỗi kết nối hoặc cài đặt:", error.message);
            }
        }
    };

    useEffect(() => {
        fetchCategories(); 
    }, []);

    // Xử lý gửi form
    const handleSubmit = async (e) => {
        e.preventDefault();
        const data = {
            tieuDe,
            noiDung,
            videoUrl,
            moTa,
            danhMucId 
        };

        try {
            const api = authApi();
            if (blog) {
                // Nếu có blog, thực hiện cập nhật
                await api.put(`${endpoints['edit']}/${blog.id}`, data);
                console.log("Cập nhật bài đăng thành công");
            } else {
                // Nếu không có blog, thực hiện đăng bài mới
                await api.post(endpoints['postBlog'], data);
                console.log("Bài đăng thành công");
            }
            // Chuyển hướng đến trang chủ
            navigate(`/teacherblogs`);
        } catch (error) {
            console.error("Lỗi khi đăng bài:", error.response ? error.response.data : error.message);
        }
    };

    return (
        <div className="post-blog">
            <h1>{blog ? "Chỉnh Sửa Bài Đăng" : "Đăng Bài"}</h1>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label htmlFor="tieuDe">Tiêu đề:</label>
                    <input
                        type="text"
                        id="tieuDe"
                        value={tieuDe}
                        onChange={(e) => setTieuDe(e.target.value)}
                        className="form-control"
                        required
                    />
                </div>

                <div className="form-group">
                    <label htmlFor="noiDung">Nội dung:</label>
                    <CKEditor
                        editor={ClassicEditor}
                        data={noiDung}
                        onChange={(event, editor) => {
                            const data = editor.getData();
                            setNoiDung(data);
                        }}
                    />
                </div>

                <div className="form-group">
                    <label htmlFor="videoUrl">Link Video (YouTube):</label>
                    <input
                        type="text"
                        id="videoUrl"
                        value={videoUrl}
                        onChange={(e) => setVideoUrl(e.target.value)}
                        className="form-control"
                    />
                </div>

                <div className="form-group">
                    <label htmlFor="moTa">Mô tả Video:</label>
                    <textarea
                        id="moTa"
                        value={moTa}
                        onChange={(e) => setMoTa(e.target.value)}
                        className="form-control"
                    />
                </div>

                <div className="form-group">
                    <label htmlFor="danhMuc">Danh mục:</label>
                    <select
                        id="danhMuc"
                        value={danhMucId}
                        onChange={(e) => setDanhMucId(e.target.value)}
                        className="form-control"
                        required
                    >
                        <option value="">Chọn danh mục</option>
                        {danhMucList.length > 0 ? (
                            danhMucList.map((danhMuc) => (
                                <option key={danhMuc.id} value={danhMuc.id}>
                                    {danhMuc.tenDanhMuc}
                                </option>
                            ))
                        ) : (
                            <option value="">Không có danh mục nào</option>
                        )}
                    </select>
                </div>

                <button type="submit" className="btn btn-primary">{blog ? "Cập Nhật" : "Đăng Bài"}</button>
            </form>
        </div>
    );
};

export default PostBlog;
