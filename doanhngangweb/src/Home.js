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
import ClearCookieOnMount from "./components/commons/ClearCookieOnMount";
import ChatBox from "./components/ChatBox/ChatBox";
import { Container } from "react-bootstrap";
import HomePage from "./components/HomePage/HomePage";
import PostBlog from "./components/Blog/PostBlog";
// import { Container } from "react-bootstrap";


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
              <Route path="/userinfo" element={<UserDetails />} />
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