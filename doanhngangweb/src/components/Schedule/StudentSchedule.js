import React, { useEffect, useState } from 'react';
import { Calendar, momentLocalizer } from 'react-big-calendar';
import moment from 'moment';
import 'react-big-calendar/lib/css/react-big-calendar.css';
import { authApi, endpoints } from '../../configs/APIs';

const localizer = momentLocalizer(moment);

const StudentSchedule = () => {
  const [events, setEvents] = useState([]);

  // Hàm fetch dữ liệu từ API
  useEffect(() => {
    const fetchSchedule = async () => {
      try {
        const api = authApi();
        const response = await api.get(endpoints['thoiKhoaBieu']);

        // Kiểm tra cấu trúc của response
        console.log('Response từ API:', response);

        // Nếu response có chứa 'data', ta lấy nó ra
        if (response && response.data) {
          console.log('Dữ liệu từ response.data:', response.data);

          // Xử lý và định dạng dữ liệu để hiển thị
          const formattedEvents = response.data.map(item => {
            const { thoiGianTrongTuan } = item;

            // Kiểm tra xem thoiGianTrongTuan có tồn tại và có chứa thời gian không
            if (!thoiGianTrongTuan || !thoiGianTrongTuan.thoiGianBatDau || !thoiGianTrongTuan.thoiGianKetThuc) {
              console.warn(`Thiếu dữ liệu thời gian cho khóa học: ${item.tenKhoaHoc}`);
              return null; // Bỏ qua mục này nếu thiếu dữ liệu
            }

            const start = new Date(thoiGianTrongTuan.ngayHoc);  // Ngày học

            // Thời gian bắt đầu và kết thúc (giả sử định dạng HH:mm)
            const [startHours, startMinutes] = thoiGianTrongTuan.thoiGianBatDau.split(':').map(Number);
            const [endHours, endMinutes] = thoiGianTrongTuan.thoiGianKetThuc.split(':').map(Number);

            // Tạo thời gian bắt đầu và kết thúc cho sự kiện
            const startEvent = new Date(start);
            startEvent.setHours(startHours, startMinutes);

            const endEvent = new Date(start);
            endEvent.setHours(endHours, endMinutes);

            return {
              title: item.tenKhoaHoc, // Lấy tên khóa học để hiển thị trong sự kiện
              start: startEvent,       // Thời gian bắt đầu
              end: endEvent,           // Thời gian kết thúc
            };
          }).filter(event => event !== null); // Lọc bỏ những mục null

          // Cập nhật state events
          setEvents(formattedEvents);
        } else {
          console.error('Response không chứa dữ liệu data');
        }
      } catch (error) {
        console.error('Lỗi khi lấy thời khóa biểu:', error);
      }
    };

    fetchSchedule();
  }, []);

  return (
    <div style={{ height: '700px' }}>
      <h1 className='mt-4 mb-4'>THỜI KHÓA BIỂU HỌC VIÊN</h1>
      <Calendar
        localizer={localizer}
        events={events}
        startAccessor="start"
        endAccessor="end"
        defaultView="week"
        views={['month', 'week', 'day']}
        step={30}  // Khoảng thời gian mỗi bước (30 phút)
        timeslots={2}  // Số slot trong 1 giờ (2 slot = mỗi slot 30 phút)
        min={new Date(2024, 9, 2, 6, 0)}  // Bắt đầu hiển thị từ 6h sáng
        max={new Date(2024, 9, 2, 23, 0)}  // Kết thúc hiển thị lúc 10h tối
        style={{ height: 500 }}
      />
    </div>
  );
};

export default StudentSchedule;
