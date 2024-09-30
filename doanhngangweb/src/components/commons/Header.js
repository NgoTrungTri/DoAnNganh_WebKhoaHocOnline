import { useContext } from "react";
import { Link } from "react-router-dom";
import { MyDispatchContext, MyUserContext } from "../../configs/Context";

const Header = () => {
    const user = useContext(MyUserContext);
    const dispatch = useContext(MyDispatchContext);

    return (
        <nav className="navbar navbar-expand-sm bg-white fixed-top shadow-navbar shadow" style={{ padding: 0, margin: 0, height: 80 }}>
            <div className="container-fluid d-flex justify-content-between align-items-center">
                <div className="d-flex align-items-center">
                    <Link className="navbar-brand me-0" to="/">
                        <img className="ft-logo" src="https://res.cloudinary.com/dhrkxbsmh/image/upload/v1724748091/BannerDAN_uidhrp.jpg" alt="EJ Group" style={{ width: 50, height: 50 }} />
                    </Link>
                    <div className="ms-2" style={{ width: 200, height: 60 }}>
                        <h6 style={{ textAlign: 'center', fontWeight: 'bold', marginTop: "10px" }}>TRUNG TÂM NGOẠI NGỮ & TIN HỌC EJ GROUP</h6>
                    </div>
                    <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    <div className="collapse navbar-collapse" id="collapsibleNavbar">
                        <ul className="navbar-nav">
                            <li className="nav-item">
                                <Link className="nav-link ms-3" to="/">Trang Chủ</Link>
                            </li>
                            {user ? (
                                <>
                                    {user.userRole === 'ROLE_GV' ? (
                                        <>
                                            <li className="nav-item">
                                                <Link className="nav-link" to="/userinfo">Thông Tin</Link>
                                            </li>
                                            <li className="nav-item">
                                                <Link className="nav-link" to="/allblogs">Bài Đăng Kiến Thức</Link>
                                            </li>
                                            <li className="nav-item">
                                                <Link className="nav-link" to="/postblog">Đăng Bài</Link>
                                            </li>
                                            <li className="nav-item">
                                                <Link className="nav-link" to="/teacherblogs">Quản Lý Bài Viết</Link>
                                            </li>
                                            <li className="nav-item">
                                                <Link className="nav-link" to="/teachercourses">Khóa Học Phụ Trách</Link>
                                            </li>
                                        </>
                                    ) : (
                                        <>
                                            <li className="nav-item">
                                                <Link className="nav-link" to="/userinfo">Thông Tin</Link>
                                            </li>
                                            <li className="nav-item">
                                                <Link className="nav-link" to="/listcourse">Khóa Học</Link>
                                            </li>
                                            <li className="nav-item">
                                                <Link className="nav-link" to="/studentcourses">Khóa Học Của Bạn</Link>
                                            </li>
                                            <li className="nav-item">
                                                <Link className="nav-link" to="/allblogs">Bài Đăng Kiến Thức</Link>
                                            </li>
                                        </>
                                    )}
                                </>
                            ) : (
                                <>
                                    <li className="nav-item">
                                        <Link className="nav-link" to="/listcourse">Khóa Học</Link>
                                    </li>
                                    <li className="nav-item">
                                        <Link className="nav-link" to="/allblogs">Bài Đăng Kiến Thức</Link>
                                    </li>
                                </>
                            )}
                        </ul>
                    </div>
                </div>
                <div id="Login" className="loginthe d-flex align-items-center">
                    {user ? (
                        <ul className="navbar-nav">
                            <li className="nav-item d-flex align-items-center">
                                <Link className="btn btn-outline-primary ms-3" onClick={() => dispatch({ type: "logout" })}>Đăng xuất</Link>
                            </li>
                        </ul>
                    ) : (
                        <ul className="navbar-nav">
                            <li className="nav-item me-2">
                                <Link className="btn btn-outline-primary me-2" to="/login">Đăng nhập</Link>
                            </li>
                            <li className="nav-item me-2">
                                <Link className="btn btn-outline-primary me-2" to="/register">Đăng Ký</Link>
                            </li>
                        </ul>
                    )}
                </div>
            </div>
        </nav>
    );
}

export default Header;