import React from "react";
import './Footer.css';

const Footer = () => {
    return (
        <footer className="footer-section pb-0 mb-0">
            <div className="footer-widget-section">
                <div className="container ms-1 me-1 ps-2">
                    <div className="row">
                        <div className="col-md-3">
                            <div className="footer-widget">
                                <div className="widget-logo" style={{ display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
                                    <a href="https://amzuni.com/">
                                        <img className="ft-logo" src="https://res.cloudinary.com/dhrkxbsmh/image/upload/v1724748091/BannerDAN_uidhrp.jpg" alt="EJ Group" loading="eager" />
                                    </a>
                                </div>
                                <ul className="widget-address">
                                    <li>
                                        <h3 className="footer-widget-title me-3">Trung Tâm Ngoại Ngữ & Tin Học EJ Group</h3>
                                    </li>
                                    <li>Liên Hệ chúng tôi: EJGroup@gmail.com</li>
                                    <li>
                                        <ul className="widget-social">
                                            <li><i className="fab fa-facebook"></i></li>
                                            <li><i className="fab fa-twitter"></i></li>
                                            <li><i className="fab fa-youtube"></i></li>
                                        </ul>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div className="col-md-6 ps-4">
                            <div className="footer-widget-link">
                                <ul className="footer-widget">
                                    <li id="block-9" className="widget widget_block">
                                        <h3 className="mt-0 pt-0" style={{ fontWeight: 'bold' }}>Về Chúng Tôi!</h3>
                                        <p>Trung Tâm Ngoại Ngữ & Tin Học EJ GROUP, thành lập vào năm 2010, là một trong những trung tâm đào tạo hàng đầu tại Việt Nam chuyên cung cấp các khóa học chất lượng về ngoại ngữ và tin học. Với sứ mệnh trang bị cho học viên những kỹ năng thiết yếu để thành công trong môi trường toàn cầu hóa, chúng tôi cam kết mang đến chương trình học hiện đại, chuyên sâu và hiệu quả.</p>
                                        <p style={{ fontWeight: 'bold' }}>Chúng tôi cung cấp</p>
                                        <ul>
                                            <li>Khóa Học Ngoại Ngữ: Từ các khóa học tiếng Anh giao tiếp đến các khóa học luyện thi chứng chỉ quốc tế như TOEFL, IELTS, chúng tôi cung cấp chương trình học đa dạng phù hợp với nhu cầu và mục tiêu của từng học viên.</li>
                                            <li>Khóa Học Tin Học: Chúng tôi cung cấp các khóa học từ cơ bản đến nâng cao về tin học văn phòng, lập trình, thiết kế web, và quản trị mạng. Đội ngũ giảng viên của chúng tôi là các chuyên gia hàng đầu trong ngành, sẵn sàng chia sẻ kiến thức và kinh nghiệm thực tiễn.</li>
                                        </ul>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div className="col-md-3" style={{ paddingLeft: '70px' }}>
                            <div className="footer-widget">
                                <div className="widget-subscribe">
                                    <div className="widget-form">
                                        <form action="#">
                                            <input type="text" className="subscribe-input mb-4" style={{ width: '250px' }} placeholder="Email here" />
                                            <button className="btn btn-primary btn-hover-dark" style={{ width: '250px' }}>Subscribe Now</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
    );
};

export default Footer;
