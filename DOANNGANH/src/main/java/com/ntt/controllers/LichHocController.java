/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.controllers;

import com.ntt.pojo.Khoahoc;
import com.ntt.pojo.Ngayhocbu;
import com.ntt.pojo.ThoiGianTrongTuanForm;
import com.ntt.pojo.Thoigiantrongtuan;
import com.ntt.services.KhoaHocServices;
import com.ntt.services.NgayHocBuServices;
import com.ntt.services.ThoiGianTrongTuanServices;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LichHocController {

    @Autowired
    private ThoiGianTrongTuanServices thoiGianTrongTuanService;

    @Autowired
    private KhoaHocServices khoahocService;

    @Autowired
    private NgayHocBuServices ngayHocBuService;

    // Phương thức để thêm thông tin chung vào model
    private void populateScheduleModel(Model model, int courseId) {
        Khoahoc course = khoahocService.getCourseById(courseId);
        List<Thoigiantrongtuan> scheduleList = thoiGianTrongTuanService.findByKhoaHocId(courseId);

        // Lấy thông tin ngày bắt đầu và kết thúc của khóa học
        java.sql.Date ngayBatDau = (java.sql.Date) course.getNgayBatDau();
        java.sql.Date ngayKetThuc = (java.sql.Date) course.getNgayKetThuc();

        Calendar cal = Calendar.getInstance();
        cal.setTime(ngayBatDau);
        LocalDate startDate = LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));

        cal.setTime(ngayKetThuc);
        LocalDate endDate = LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));

        long soTuan = ChronoUnit.WEEKS.between(startDate, endDate);

        // Đưa thông tin khóa học và lịch học vào model
        model.addAttribute("course", course);
        model.addAttribute("scheduleList", scheduleList);
        model.addAttribute("startdate", startDate);
        model.addAttribute("enddate", endDate);
        model.addAttribute("soTuan", soTuan);
        model.addAttribute("courseId", courseId);
    }

    @GetMapping("/view-schedule/{courseId}")
    public String xemLichHoc(Model model, @PathVariable(value = "courseId") int id) {
        populateScheduleModel(model, id);
        return "course-schedule";
    }

    @GetMapping("/schedule/{courseId}")
    public String LichHoc(Model model, @PathVariable(value = "courseId") int id) {
        // Gọi phương thức thêm thông tin vào model
        populateScheduleModel(model, id);

        // Chuẩn bị đối tượng buoiHocList để sử dụng trong form
        ThoiGianTrongTuanForm buoiHocList = new ThoiGianTrongTuanForm();
        buoiHocList.setBuoiHocList(new ArrayList<Thoigiantrongtuan>());
        model.addAttribute("buoiHocList", buoiHocList);

        return "create-schedule";
    }

    @PostMapping("/schedule/{courseId}")
    public String taoFormBuoiHoc(
            @PathVariable("courseId") int courseId,
            @RequestParam("soBuoi") int soBuoi,
            Model model) {

        // Gọi phương thức thêm thông tin vào model
        populateScheduleModel(model, courseId);

        // Tạo danh sách các buổi học trống cho form
        ThoiGianTrongTuanForm buoiHocListForm = new ThoiGianTrongTuanForm();
        List<Thoigiantrongtuan> buoiHocList = new ArrayList<>();
        for (int i = 0; i < soBuoi; i++) {
            buoiHocList.add(new Thoigiantrongtuan()); // Thêm buổi học rỗng để hiển thị trên form
        }
        buoiHocListForm.setBuoiHocList(buoiHocList);

        // Đưa danh sách buổi học vào model
        model.addAttribute("buoiHocList", buoiHocListForm);

        return "create-schedule";
    }

    @PostMapping("/schedule/preview/{courseId}")
    public String previewAndSaveSchedule(
            @ModelAttribute("buoiHocList") ThoiGianTrongTuanForm buoiHocList,
            @RequestParam(value = "action", required = false) String action,
            @PathVariable("courseId") int courseId,
            Model model) {

        // Gọi phương thức thêm thông tin vào model
        populateScheduleModel(model, courseId);

        Khoahoc course = khoahocService.getCourseById(courseId);
        List<Thoigiantrongtuan> previewSchedule = new ArrayList<>();

        // Tạo một Set để lưu trữ các ngày học đã được thêm
        Set<LocalDate> uniqueDaysSet = new HashSet<>();

        // Tính danh sách lịch học dự kiến
        for (Thoigiantrongtuan buoiHoc : buoiHocList.getBuoiHocList()) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(course.getNgayBatDau());
            LocalDate startDate = LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));

            cal.setTime(course.getNgayKetThuc());
            LocalDate endDate = LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));

            List<LocalDate> ngayHocList = tinhDanhSachNgayHoc(startDate, endDate, buoiHoc.getTenThuTrongTuan());

            // Kiểm tra từng ngày học xem có bị trùng không
            for (LocalDate ngayHoc : ngayHocList) {
                if (!uniqueDaysSet.contains(ngayHoc)) { // Nếu ngày học chưa có trong Set
                    // Thêm ngày học vào Set để đánh dấu đã xử lý
                    uniqueDaysSet.add(ngayHoc);

                    // Tạo buổi học mới
                    Thoigiantrongtuan tgtt = new Thoigiantrongtuan();
                    tgtt.setNgayHoc(java.sql.Date.valueOf(ngayHoc));
                    tgtt.setThoiGianBatDau(buoiHoc.getThoiGianBatDau());
                    tgtt.setThoiGianKetThuc(buoiHoc.getThoiGianKetThuc());
                    tgtt.setTenThuTrongTuan(buoiHoc.getTenThuTrongTuan());
                    tgtt.setKhoaHocId(course);
                    previewSchedule.add(tgtt);
                }
            }
        }

        // Khi người dùng nhấn "Lưu Lịch Học", thực hiện việc lưu vào database
        if ("save".equals(action)) {
            for (Thoigiantrongtuan buoiHoc : previewSchedule) {
                tinhThoiLuong(buoiHoc);
                buoiHoc.setKhoaHocId(course);
                thoiGianTrongTuanService.taoBuoiHoc(buoiHoc);
            }
            return "redirect:/view-schedule/" + courseId;
        }

        if ("preview".equals(action)) {
            model.addAttribute("previewSchedule", previewSchedule);
            buoiHocList.setBuoiHocList(previewSchedule);
            model.addAttribute("buoiHocList", buoiHocList);
        }

        return "create-schedule";
    }

    /////Ngày học bù 
    @GetMapping("/chonngayhocbu/{ngayBuId}")
    public String showNgayHocBuForm(@PathVariable("ngayBuId") int ngayBuId, Model model) {
        Ngayhocbu ngayhocbu = new Ngayhocbu();

        model.addAttribute("ngayhocbu", ngayhocbu);
        model.addAttribute("ngayBuId", ngayBuId);

        return "chondayhocbuForm";
    }

    @PostMapping("/chonngayhocbu/{ngayBuId}")
    public String saveNgayHocBu(
            @ModelAttribute("ngayhocbu") Ngayhocbu ngayhocbu,
            @ModelAttribute("ngayBuId") int ngayBuId,
            Model model) {

        boolean trungLap;

        Thoigiantrongtuan buoihoc = thoiGianTrongTuanService.findById(ngayBuId);
        Khoahoc kh = this.khoahocService.getKhoaHocByBuoiHoc(buoihoc);

        trungLap = ngayHocBuService.checkNgayHocBuHopLe(ngayhocbu, kh.getIdGVPhuTrach().getId());

        if (trungLap == false) {
            ngayhocbu.setKhoaHocId(kh);
            ngayhocbu.setNgayBu(buoihoc.getNgayHoc());

            ngayHocBuService.TaoNgayHocBu(ngayhocbu);
            model.addAttribute("khoaHocId", kh.getId());
            return "redirect:/list-ngayhocbu/" + kh.getId();
        } else {
            model.addAttribute("errorMessage", "Trùng Lịch Dạy Của Giảng Viên. Vui lòng chọn ngày khác.");
            return "chondayhocbuForm";
        }
    }

    @GetMapping("/list-ngayhocbu/{khoaHocId}")
    public String listNgayHocBu(@PathVariable("khoaHocId") int khoaHocId, Model model) {
        // Lấy danh sách các ngày học bù dựa trên khoaHocId
        List<Ngayhocbu> ngayhocbuList = ngayHocBuService.findByKhoaHoc(khoaHocId);

        // Thêm danh sách này vào model để truyền đến view
        model.addAttribute("ngayhocbuList", ngayhocbuList);
        model.addAttribute("khoaHocId", khoaHocId);

        return "list-ngayhocbu";
    }

    // Hàm tính ra danh sách ngày học
    private List<LocalDate> tinhDanhSachNgayHoc(LocalDate startDate, LocalDate endDate, String tenThuTrongTuan) {
        List<LocalDate> danhSachNgayHoc = new ArrayList<>();
        DayOfWeek dayOfWeek = DayOfWeek.valueOf(tenThuTrongTuan.toUpperCase());

        LocalDate current = startDate;
        while (!current.isAfter(endDate)) {
            if (current.getDayOfWeek() == dayOfWeek) {
                danhSachNgayHoc.add(current);
            }
            current = current.plusDays(1);
        }

        return danhSachNgayHoc;
    }

    // Hàm tính thời lượng của 1 buổi học
    private void tinhThoiLuong(Thoigiantrongtuan buoiHoc) {
        if (buoiHoc.getThoiGianBatDau() != null && buoiHoc.getThoiGianKetThuc() != null) {
            LocalTime startTime = buoiHoc.getThoiGianBatDau().toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
            LocalTime endTime = buoiHoc.getThoiGianKetThuc().toInstant().atZone(ZoneId.systemDefault()).toLocalTime();

            Duration duration = Duration.between(startTime, endTime);

            long hours = duration.toMinutes() / 60;
            long minutes = duration.toMinutes() % 60;

            buoiHoc.setThoiLuong(minutes > 0 ? hours + 1 : hours);
        }
    }
    
    
}
