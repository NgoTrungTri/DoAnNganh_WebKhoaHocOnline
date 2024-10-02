import { BrowserRouter, Route, Routes } from "react-router-dom";
import Header from "./components/commons/Header";
import Footer from "./components/commons/Footer";
import Login from "./components/User/Login";
import UserDetails from "./components/User/UserDetails";
import 'bootstrap/dist/css/bootstrap.min.css';
import { MyDispatchContext, MyUserContext } from "./configs/Context";
import { useReducer } from "react";
import { MyUserReducer } from "./configs/Reducers";
import Register from "./components/User/Register";
import ChatBox from "./components/ChatBox/ChatBox";
import { Container } from "react-bootstrap";
import HomePage from "./components/HomePage/HomePage";
import PostBlog from "./components/Blog/PostBlog";
import TeacherBlogs from "./components/Blog/TeacherBlogs";
import ViewBlog from "./components/Blog/ViewBlog";
import TeacherCourse from "./components/Course/TeacherCourse";
import CourseDetail from "./components/Course/CoruseDetail";
import PopularBlogs from "./components/Blog/PopularBlogs";
import ListCourse from "./components/Course/ListCourse";
import PaymentSuccessful from "./components/commons/PaymentSuccesful";
import ClearCookieOnMount from "./components/commons/ClearCookieOnMount";
import StudentCourse from "./components/Course/StudentCourse";
import OtpVerify from "./components/User/OtpVerify";
import { GoogleOAuthProvider } from "@react-oauth/google";
// import { Container } from "react-bootstrap";\


const Home = () => {

  const [user, useDispatch] = useReducer(MyUserReducer, null);

  return (
    <BrowserRouter>
      <MyUserContext.Provider value={user}>
        <MyDispatchContext.Provider value={useDispatch}>
          <ClearCookieOnMount />
            <Header />
            <Container style={{ marginTop: 100, marginBottom: 100 }}>
              <Routes>
                <Route path="/" element={<HomePage />} />
                <Route path="/login" element={<Login />} />
                <Route path="/postblog" element={<PostBlog />} />
                <Route path="/register" element={<Register />} />
                <Route path="/otpveryfy" element={<OtpVerify />} />
                <Route path="/userinfo" element={<UserDetails />} />
                <Route path="/teacherblogs" element={<TeacherBlogs />} />
                <Route path="/listcourse" element={<ListCourse />} />
                <Route path="/teachercourses" element={<TeacherCourse />} />
                <Route path="/studentcourses" element={<StudentCourse />} />
                <Route path="/coursedetail/:courseId" element={<CourseDetail />} />
                <Route path="/allblogs" element={<PopularBlogs />} />
                <Route path="/demo/:id" element={<ViewBlog />} />
                <Route path="/paymentsuccess" element={<PaymentSuccessful />} />
                <Route path="/chat" element={user ? <ChatBox user={user} /> : <Login />} />
              </Routes>
            </Container>
            <Footer />
        </MyDispatchContext.Provider>
      </MyUserContext.Provider>
    </BrowserRouter>
  );
}

export default Home;