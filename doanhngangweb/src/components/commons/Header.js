import { useContext, useEffect, useState } from "react";
import { Container, Nav, Navbar, NavDropdown } from "react-bootstrap";
import { Link } from "react-router-dom";
import { MyDispatchContext, MyUserContext } from "../../configs/Context";
import APIs, { endpoints } from "../../configs/APIs";

const Header = () => {
    const user = useContext(MyUserContext);
    const dispatch = useContext(MyDispatchContext);

    return (
        <nav className="navbar navbar-expand-sm bg-white navbar-whitesmoke fixed-top shadow-navbar shadow" style={{ padding: 0, margin: 0, height: 80 }}>
            <div className="container-fluid">
                <a className="navbar-brand me-0" href="/">
                    <img className="ft-logo" src="https://res.cloudinary.com/dhrkxbsmh/image/upload/v1724748091/BannerDAN_uidhrp.jpg" alt="EJ Group" style={{ width: 50, height: 50 }} />
                </a>
                <div className="ms-1" style={{ width: 200, height: 60 }}>
                    <h6 style={{ textAlign: 'center', fontWeight: 'bold', marginTop: "10px" }}>TRUNG TÂM NGOẠI NGỮ & TIN HỌC EJ GROUP</h6>
                </div>
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div id="Login" className="ms-auto">
                    {user ? (
                        <ul className="navbar-nav">
                            <Link className="btn btn-primary" to="/userinfo">Thông Tin</Link>

                            <li className="nav-item">
                                <span>Xin Chào {user.name}!</span>
                                <Link className="btn btn-primary" onClick={() => dispatch({ type: "logout" })} >Đăng xuất</Link>
                            </li>
                        </ul>
                    ) : (
                        <ul className="navbar-nav">
                            <li className="nav-item">
                                <Link className="btn btn-primary" to="/login">Đăng nhập</Link>
                            </li>
                        </ul>
                    )}
                </div>
            </div>
        </nav>
    );
}

export default Header;
