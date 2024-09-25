import { useEffect } from "react";
import cookie from "react-cookies";

const ClearCookieOnMount = () => {
  useEffect(() => {
    // Kiểm tra xem cookie 'token' có tồn tại không
    if (cookie.load('token')) {
      // Nếu tồn tại, xóa cookie 'token'
      cookie.remove('token');
      console.log("Đã xóa cookie 'token' khi vào trang web");
    }
  }, []); 
 // Không cần render
  return null;
};

export default ClearCookieOnMount;
