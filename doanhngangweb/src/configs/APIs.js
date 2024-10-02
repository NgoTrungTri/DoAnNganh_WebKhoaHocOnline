import axios from "axios";
import cookie from "react-cookies";

const BASE_URL = 'http://localhost:8080/doannganh/';

export const endpoints = {

    ///Người Dùng
    'register': '/api/users/',
    'login': '/api/login/',
    'google-signin': 'api/firebase-login/',
    'current-user': '/api/current-user/',
    'verifyOtp': 'api/users/verifyOtp',
    'userbyusername': '/api/userinfo/',
    'getUserRole': '/api/useRole/',

    ////Liên quan tới bài đăng
    'postBlog': '/api/gv/dangBai/',
    'categories': '/api/danhMuc/',
    'baidangmoinhat': '/api/bai-dang-moi-nhat/',
    'baidangngoaingu': 'api/bai-dang-ngoai-ngu/',
    'baidangtinhoc': 'api/bai-dang-tin-hoc/',
    'teacherblogs': '/api/bai-dang-chinh-sua',
    'myblogs': '/api/bai-dang-public-giao-vien',
    'demo': '/api/demo',
    'edit': '/api/blogs',
    'delete': '/api/xoabaidang',

    ////Liên quan tới khóa học
    'teacherCourse': '/api/khoahoc/giaovien/',
    'course-detail': 'api/course-detail',
    'loadkhoahoctinhoc': '/api/khoahoc/tinhoc/',
    'loadkhoahocngoaingu': '/api/khoahoc/ngoaingu/',
    'load4khoahocmoinhat': '/api/khoahoc/moi-nhat/',
    ///mua khoa hoc
    'muakhoahoc': '/api/muaKhoaHoc',    
    'taodonhang': '/api/taoDonHang',

    ///Khóa Học Học Viên
    'khoaHocDangHoc': '/api/khoahocList/dang-hoc',
    'khoaHocSapToi': '/api/khoahocList/sap-toi',
    'khoaHocDaMua': '/api/khoahocList/da-mua',
    
}   

export const authApi = () => {
    return axios.create({
        baseURL: BASE_URL,
        headers: {
            'Authorization': cookie.load('token')
        }
    })
}

export default axios.create({
    baseURL: 'http://localhost:8080/doannganh/'
});