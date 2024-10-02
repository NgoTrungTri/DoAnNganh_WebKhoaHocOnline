/////Nơi Này hiển thị bài viết khi người dùng click vào xem
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom'; 
import APIs, { authApi, endpoints } from '../../configs/APIs';

const ViewBlog = () => {
    const { id } = useParams();  
    const [blog, setBlog] = useState(null);

    useEffect(() => {
        const fetchBlog = async () => {
            try {
                const response = await APIs.get(`${endpoints['demo']}/${id}`);  
                setBlog(response.data);  
            } catch (error) {
                console.error('Error fetching blog:', error);
            }
        };

        fetchBlog();
    }, [id]);

    if (!blog) {
        return <div>Loading...</div>;
    }

    return (
        <div className="container mt-5">
            <h1>{blog.tieuDe}</h1>
            <p>{new Date(blog.ngayDang).toLocaleDateString('vi-VN')}</p>

            <div dangerouslySetInnerHTML={{ __html: blog.noiDung }} />
        </div>
    );
}

export default ViewBlog;
