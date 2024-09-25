/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.controllers;

import com.itextpdf.io.font.PdfEncodings;
import com.ntt.pojo.Luonggiaovien;
import com.ntt.services.LuongGiaoVienServices;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.ntt.pojo.User;
import com.ntt.services.UserServices;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author DELL
 */
@Controller
public class LuongGiaoVienController {

    @Autowired
    private LuongGiaoVienServices luong;

    @Autowired
    private UserServices userServices;

    @GetMapping("/hesoluong")
    public String listHeSo(Model model, @RequestParam(name = "LoaiGV", required = false) String loaiGV) {
        List<Luonggiaovien> heSo = null;

        // Nếu không có lựa chọn cụ thể, mặc định là "Cơ Hữu"
        if (loaiGV == null || loaiGV.isEmpty()) {
            loaiGV = "Cơ Hữu";
        }

        switch (loaiGV) {
            case "Cơ Hữu":
                heSo = this.luong.findByGVCoHuu();
                break;
            case "Thỉnh Giảng":
                heSo = this.luong.findByGVThinhGiang();
                break;
            default:
                heSo = this.luong.findByGVCoHuu();
                break;
        }

        model.addAttribute("heSo", heSo);
        model.addAttribute("LoaiGV", loaiGV);  // Truyền giá trị loại giáo viên để sử dụng trong JSP
        return "list-hesoluonggiaovien";
    }

    @PostMapping("/updateLuongGiaoVien")
    public String updateLuongGiaoVien(@RequestParam("id") int id,
            @RequestParam("heSo") float heSo,
            @RequestParam("tienLuongThemTheoGio") double tienLuongThemTheoGio) {
        // Tìm giáo viên theo ID
        Luonggiaovien luongGiaoVien = luong.findById(id);

        // Cập nhật giá trị
        luongGiaoVien.setHeSo(heSo);
        luongGiaoVien.setTienLuongThemTheoGio(tienLuongThemTheoGio);

        // Lưu thay đổi vào cơ sở dữ liệu
        luong.update(luongGiaoVien);

        // Redirect về trang danh sách hệ số lương
        return "redirect:/hesoluong";
    }

    @GetMapping("/bang-luong")
    public String showBangLuongForm(Model model) {
        List<User> teachers = userServices.getUsersByUserRole("ROLE_GV");
        model.addAttribute("teachers", teachers);
        return "xuatbangluongpdf";
    }

    @GetMapping("/bang-luong/thongtin")
    public String showThongTin(@RequestParam("teacherId") int userId, Model model) {
        User teacher = userServices.getUserById(userId);
        model.addAttribute("teacher", teacher);

        double heSoLuong = luong.findByGiaoVienId(userId).getHeSo();
        double luongTheoGio = luong.findByGiaoVienId(userId).getTienLuongThemTheoGio();
        double luongCoSo = 2340000;
        int tongGioDay = luong.countGioDayTrongThang(userId);
        double tongLuong = 0;
        double luongThuong = 0;
        double luongTru = 0;

        if (luong.isGiaoVienCoHuu(userId)) {
            if (tongGioDay > 128) {
                int gioLamThem = tongGioDay - 128;
                luongThuong = gioLamThem * luong.findByGiaoVienId(userId).getTienLuongThemTheoGio();
                tongLuong = heSoLuong * luongCoSo + luongThuong;
            } else if (tongGioDay == 128) {
                tongLuong = heSoLuong * luongCoSo;
            } else {
                luongTru = 500000;
                tongLuong = heSoLuong * luongCoSo - luongTru;
            }
            NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
            String formattedLuong = formatter.format(tongLuong);
            String formattedLuongTheoGio = formatter.format(luongTheoGio);
            String formattedLuongThuong = formatter.format(luongThuong);
            String formattedLuongTru = formatter.format(luongTru);
            model.addAttribute("tongGioDay", tongGioDay);
            model.addAttribute("heSoLuong", heSoLuong);
            model.addAttribute("luongTheoGio", formattedLuongTheoGio);
            model.addAttribute("luongCoSo", luongCoSo);
            model.addAttribute("luongThuong", formattedLuongThuong);
            model.addAttribute("luongTru", formattedLuongTru);
            model.addAttribute("tongLuong", formattedLuong);
        } else {
            tongLuong = tongGioDay * luong.findByGiaoVienId(userId).getTienLuongThemTheoGio();
            NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
            String formattedLuong = formatter.format(tongLuong);
            String formattedLuongThuong = formatter.format(luongThuong);
            String formattedLuongTru = formatter.format(luongTru);
            model.addAttribute("tongGioDay", tongGioDay);
            model.addAttribute("luongTheoGio", luongTheoGio);
            model.addAttribute("heSoLuong", 0);
            model.addAttribute("luongCoSo", 0);
            model.addAttribute("luongThuong", formattedLuongThuong);
            model.addAttribute("luongTru", formattedLuongTru);
            model.addAttribute("tongLuong", formattedLuong);
        }

        List<User> teachers = userServices.getUsersByUserRole("ROLE_GV");
        model.addAttribute("teachers", teachers);
        return "xuatbangluongpdf";
    }

    @GetMapping("api/bang-luong/export-pdf/{userId}")
    @CrossOrigin
    public void exportBangLuongPdf(Model model, HttpServletResponse response, @PathVariable("userId") int idGiaoVien) throws IOException {
        User teacher = userServices.getUserById(idGiaoVien);
        model.addAttribute("teacher", teacher);
        int tongGioDay = luong.countGioDayTrongThang(idGiaoVien);
        double luongTheoGio = luong.findByGiaoVienId(idGiaoVien).getTienLuongThemTheoGio();
        double heSoLuong = luong.findByGiaoVienId(idGiaoVien).getHeSo();
        double tongLuong = 0;
        double luongCoSo = 2340000;
        double luongThuong = 0;
        double luongTru = 500000;

        if (luong.isGiaoVienCoHuu(idGiaoVien)) {

            if (tongGioDay > 128) {
                int gioLamThem = tongGioDay - 128;
                luongThuong = gioLamThem * luong.findByGiaoVienId(idGiaoVien).getTienLuongThemTheoGio();
                tongLuong = heSoLuong * luongCoSo + luongThuong;
            } else if (tongGioDay == 128) {
                tongLuong = heSoLuong * luongCoSo;
            } else {
                luongTru = 500000;
                tongLuong = heSoLuong * luongCoSo - luongTru;
            }
        } else {
            heSoLuong = 0;
            luongCoSo = 0;
            luongThuong = 0;
            luongTru = 0;
            tongLuong = tongGioDay * luong.findByGiaoVienId(idGiaoVien).getTienLuongThemTheoGio();
        }

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=bang_luong_giao_vien.pdf");

        PdfWriter writer = new PdfWriter(response.getOutputStream());
        PdfDocument pdf = new PdfDocument(writer);
        try (Document document = new Document(pdf)) {
            PdfFont font = PdfFontFactory.createFont("C:/Users/DELL/Desktop/arial-font/arial.ttf", PdfEncodings.IDENTITY_H);

            // Title
            Paragraph title = new Paragraph("BẢNG LƯƠNG GIÁO VIÊN")
                    .setFont(font)
                    .setFontSize(18)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER);
            document.add(title);

            document.add(new Paragraph("\n"));

            User giaoVien = this.userServices.getUserById(idGiaoVien);

            // Create a table with 2 columns for teacher details
            float[] columnWidths = {200f, 400f};
            Table table = new Table(columnWidths);
            table.setWidth(UnitValue.createPercentValue(100));

            Paragraph fullName = new Paragraph()
                    .add(new Text("GV. " + giaoVien.getHo() + " ").setFont(font))
                    .add(new Text(giaoVien.getTen()).setFont(font));

            // Add rows to the table
            table.addCell(new Cell().add(new Paragraph("Tên Giáo Viên:").setFont(font).setBold()));
            table.addCell(new Cell().add(fullName));

            table.addCell(new Cell().add(new Paragraph("Mã Giáo Viên:").setFont(font).setBold()));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(giaoVien.getId())).setFont(font)));

            table.addCell(new Cell().add(new Paragraph("Giới Tính:").setFont(font).setBold()));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(giaoVien.getGioiTinh())).setFont(font)));

            table.addCell(new Cell().add(new Paragraph("Ngày Sinh").setFont(font).setBold()));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(giaoVien.getNgaySinh())).setFont(font)));

            table.addCell(new Cell().add(new Paragraph("Email:").setFont(font).setBold()));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(giaoVien.getEmail())).setFont(font)));

            table.addCell(new Cell().add(new Paragraph("Quê quán:").setFont(font).setBold()));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(giaoVien.getQueQuan())).setFont(font)));

            table.addCell(new Cell().add(new Paragraph("Tổng Số Giờ Dạy:").setFont(font).setBold()));
            table.addCell(new Cell().add(new Paragraph(tongGioDay + " giờ").setFont(font)));

            table.addCell(new Cell().add(new Paragraph("Hệ Số Lương:").setFont(font).setBold()));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(heSoLuong)).setFont(font)));

            table.addCell(new Cell().add(new Paragraph("Tiền Lương Theo Giờ:").setFont(font).setBold()));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(luongTheoGio)).setFont(font)));

            table.addCell(new Cell().add(new Paragraph("Tiền Lương Thưởng:").setFont(font).setBold()));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(luongThuong)).setFont(font)));

            table.addCell(new Cell().add(new Paragraph("Tiền Lương Trừ:").setFont(font).setBold()));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(luongTru)).setFont(font)));

            table.addCell(new Cell().add(new Paragraph("Lương Cơ Sở:").setFont(font).setBold()));
            table.addCell(new Cell().add(new Paragraph(String.format("%,.0f VND", luongCoSo)).setFont(font)));

            document.add(table);

            document.add(new Paragraph("\n"));

            // Formatting the total salary
            NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
            String formattedLuong = formatter.format(tongLuong);

            // Total salary in a separate table
            Table salaryTable = new Table(1);
            salaryTable.setWidth(UnitValue.createPercentValue(100));
            salaryTable.addCell(new Cell()
                    .add(new Paragraph("TỔNG LƯƠNG: " + formattedLuong)
                            .setFont(font)
                            .setFontSize(14)
                            .setBold()
                            .setTextAlignment(TextAlignment.CENTER))
                    .setBorder(null));

            document.add(salaryTable);
        }
    }

}
