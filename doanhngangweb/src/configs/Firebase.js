  // Import các hàm cần thiết từ SDK Firebase
  import { initializeApp } from "firebase/app";
  import { getDatabase } from "firebase/database";
  import { getAuth, GoogleAuthProvider, signInWithPopup, signOut } from "firebase/auth";

  // Cấu hình Firebase
  const firebaseConfig = {
    apiKey: "AIzaSyC9bEmtczSh6Dr1GiKJUqfrG4E4mW-fkGU",
    authDomain: "doannganh-d5551.firebaseapp.com",
    projectId: "doannganh-d5551",
    storageBucket: "doannganh-d5551.appspot.com",
    messagingSenderId: "85037057535",
    appId: "1:85037057535:web:2c43f868bc98c1cf7cd3ca",
    measurementId: "G-MJ95L5CTD6"
  };

  // Khởi tạo Firebase
  const app = initializeApp(firebaseConfig);

  // Khởi tạo Realtime Database và Authentication
  const db = getDatabase(app);
  const auth = getAuth(app);

  // Cấu hình Google Auth Provider
  const provider = new GoogleAuthProvider();

  // Xuất khẩu các đối tượng và hàm cần thiết
  export { auth, provider, signInWithPopup, signOut, db };
