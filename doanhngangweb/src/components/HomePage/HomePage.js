import React, { useState } from 'react';
import './HomePage.css'; // Import file CSS cho trang

const courses = [
  {
    id: 1,
    title: 'Khóa học tiếng Anh cơ bản',
    description: 'Khóa học giúp bạn nắm vững ngữ pháp và từ vựng cơ bản.',
    imageUrl: 'https://via.placeholder.com/150',
    price: '500.000đ'
  },
  {
    id: 2,
    title: 'Khóa học tiếng Anh giao tiếp',
    description: 'Rèn luyện kỹ năng giao tiếp tiếng Anh hàng ngày.',
    imageUrl: 'https://via.placeholder.com/150',
    price: '1.000.000đ'
  },
  {
    id: 3,
    title: 'Khóa học luyện thi IELTS',
    description: 'Luyện thi IELTS với giáo viên bản xứ.',
    imageUrl: 'https://via.placeholder.com/150',
    price: '2.000.000đ'
  },
  {
    id: 4,
    title: 'Khóa học luyện thi IELTS',
    description: 'Luyện thi IELTS với giáo viên bản xứ.',
    imageUrl: 'https://via.placeholder.com/150',
    price: '2.000.000đ'
  },
  {
    id: 5,
    title: 'Khóa học tiếng Anh cơ bản 1111',
    description: 'Khóa học giúp bạn nắm vững ngữ pháp và từ vựng cơ bản.',
    imageUrl: 'https://via.placeholder.com/150',
    price: '500.000đ'
  },
  {
    id: 6,
    title: 'Khóa học tiếng Anh giao tiếp 11111',
    description: 'Rèn luyện kỹ năng giao tiếp tiếng Anh hàng ngày.',
    imageUrl: 'https://via.placeholder.com/150',
    price: '1.000.000đ'
  },
  {
    id: 7,
    title: 'Khóa học luyện thi IELTS 11111',
    description: 'Luyện thi IELTS với giáo viên bản xứ',
    imageUrl: 'https://via.placeholder.com/150',
    price: '2.000.000đ'
  },
  {
    id: 8,
    title: 'Khóa học luyện thi IELTS',
    description: 'Luyện thi IELTS với giáo viên bản xứ. 111111',
    imageUrl: 'https://via.placeholder.com/150',
    price: '2.000.000đ'
  }
];

// const HomePage = () => {
//   return (
//     <div className="homepage-container">
//       {/* Banner */}
//       <div className="banner-container">
//         <img 
//           src="https://flic500.edu.vn/wp-content/uploads/2023/03/NNTH-Banner_fixed-1-1024x406.jpg" 
//           alt="Banner" 
//           className="banner-image"
//         />
//       </div>

//       {/* Phần hiển thị khóa học ngoại ngữ */}
//       <div className="courses-container">
//         <h1 className="newLine">CÁC KHÓA HỌC NGOẠI NGỮ</h1>
//         <div className="courses-grid">
//           {courses.map(course => (
//             <div key={course.id} className="course-card">
//               <img src={course.imageUrl} alt={course.title} className="course-image" />
//               <h3>{course.title}</h3>
//               <p>{course.description}</p>
//               <p className="course-price">{course.price}</p>
//               <button className="buy-button">Mua ngay</button>
//             </div>
//           ))}
//         </div>
//       </div>

//       {/* Phần hiển thị khóa học tin học */}
//       <div className="courses-container">
//         <h1 className="newLine">CÁC KHÓA HỌC TIN HỌC</h1>
//         <div className="courses-grid">
//           {courses.map(course => (
//             <div key={course.id} className="course-card">
//               <img src={course.imageUrl} alt={course.title} className="course-image" />
//               <h3>{course.title}</h3>
//               <p>{course.description}</p>
//               <p className="course-price">{course.price}</p>
//               <button className="buy-button">Mua ngay</button>
//             </div>
//           ))}
//         </div>
//       </div>
//     </div>
//   );
// };

// export default HomePage;
const HomePage = () => {
  const [currentPage, setCurrentPage] = useState(1);
  const coursesPerPage = 4;

  const indexOfLastCourse = currentPage * coursesPerPage;
  const indexOfFirstCourse = indexOfLastCourse - coursesPerPage;
  const currentCourses = courses.slice(indexOfFirstCourse, indexOfLastCourse);

  // Hàm để xử lý nút "Prev" (Trang trước)
  const handlePrevPage = () => {
    if (currentPage > 1) {
      setCurrentPage(currentPage - 1);
    }
  };

  // Hàm để xử lý nút "Next" (Trang sau)
  const handleNextPage = () => {
    if (indexOfLastCourse < courses.length) {
      setCurrentPage(currentPage + 1);
    }
  };

  return (
    <div className="homepage-container">
      {/* Banner */}
      <div className="banner-container">
        <img
          src="https://flic500.edu.vn/wp-content/uploads/2023/03/NNTH-Banner_fixed-1-1024x406.jpg"
          alt="Banner"
          className="banner-image"
        />
      </div>

      {/* Phần hiển thị khóa học */}
      <div className="courses-wrapper">
        <h2 className="newLine">CÁC KHÓA HỌC NGOẠI NGỮ</h2>
        <div className="courses-grid">

          <button className="prev-button" onClick={handlePrevPage} disabled={currentPage === 1}>
            &lt;
          </button>

          {currentCourses.map(course => (
            <div key={course.id} className="course-card">
              <img src={course.imageUrl} alt={course.title} className="course-image" />
              <h3>{course.title}</h3>
              <p>{course.description}</p>
              <p className="course-price">{course.price}</p>
              <button className="buy-button">Mua ngay</button>
            </div>
          ))}

          <button className="next-button" onClick={handleNextPage} disabled={indexOfLastCourse >= courses.length}>
            &gt;
          </button>

        </div>
      </div>


      {/* Phần hiển thị khóa học */}
      <div className="courses-wrapper">
        <h2 className="newLine">CÁC KHÓA HỌC TIN HỌC</h2>
        <div className="courses-grid">

          <button className="prev-button" onClick={handlePrevPage} disabled={currentPage === 1}>
            &lt;
          </button>

          {currentCourses.map(course => (
            <div key={course.id} className="course-card">
              <img src={course.imageUrl} alt={course.title} className="course-image" />
              <h3>{course.title}</h3>
              <p>{course.description}</p>
              <p className="course-price">{course.price}</p>
              <button className="buy-button">Mua ngay</button>
            </div>
          ))}

          <button className="next-button" onClick={handleNextPage} disabled={indexOfLastCourse >= courses.length}>
            &gt;
          </button>

        </div>
      </div>
    </div>
  );
};

export default HomePage;