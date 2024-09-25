import axios from "axios";
import cookie from "react-cookies";

const BASE_URL = 'http://localhost:8080/doannganh/';

export const endpoints = {

    ///Người Dùng
    'register': '/api/users/',
    'login': '/api/login/',
    'current-user': '/api/current-user/',
    'userbyusername': '/api/userinfo/',
    'postBlog': '/api/dangBai/',
    

}   

console.log(cookie.load('token'));

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