import React, { useState } from "react";
import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import { authApi, endpoints } from "../../configs/APIs";

const PostBlog = () => {
    const [tieuDe, setTieuDe] = useState("");
    const [noiDung, setNoiDung] = useState("");
    const [videoUrl, setVideoUrl] = useState("");
    const [moTa, setMoTa] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();
        const data = {
            tieuDe,
            noiDung,
            videoUrl,
            moTa
        };

        try {
            const api = authApi();  

            const response = await api.post(endpoints['postBlog'], data);
            console.log("Bài đăng thành công:", response.data);
        } catch (error) {
            console.error("Lỗi khi đăng bài:", error.response ? error.response.data : error.message);
        }
    };

    return (
        <div className="post-blog">
            <h1>Đăng Bài</h1>
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

                <button type="submit" className="btn btn-primary">Đăng Bài</button>
            </form>
        </div>
    );
};

export default PostBlog;
