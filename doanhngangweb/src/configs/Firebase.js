// Import các hàm cần thiết từ SDK Firebase
import { initializeApp } from "firebase/app";
import { getFirestore } from "firebase/firestore"; // Đảm bảo import đúng hàm getFirestore
import { getAuth, GoogleAuthProvider, signInWithPopup, signOut } from "firebase/auth";

const firebaseConfig = {
  apiKey: "AIzaSyAhGsjp5RTMi2YKz__jJi9zkZRUHzxGcUA",
  authDomain: "drlweb-284cf.firebaseapp.com",
  databaseURL: "https://drlweb-284cf-default-rtdb.firebaseio.com",
  projectId: "drlweb-284cf",
  storageBucket: "drlweb-284cf.appspot.com",
  messagingSenderId: "967948141715",
  appId: "1:967948141715:web:061166dae05ab4e87d3966",
  measurementId: "G-EB7DCN6VBD"
};

// Khởi tạo Firebase
const app = initializeApp(firebaseConfig);

// Khởi tạo Firestore
const db = getFirestore(app);

const auth = getAuth(app);
const provider = new GoogleAuthProvider();

export { auth, provider, signInWithPopup, signOut };

export { db };