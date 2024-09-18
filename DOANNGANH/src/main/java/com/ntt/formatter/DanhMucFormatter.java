/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.formatter;

import com.ntt.pojo.Danhmuc;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author DELL
 */
public class DanhMucFormatter implements Formatter<Danhmuc> {
    @Override
    public Danhmuc parse(String id, Locale locale) throws ParseException {
        Danhmuc category = new Danhmuc();
        category.setId(Integer.parseInt(id));
        
        return category;
    }

    @Override
    public String print(Danhmuc object, Locale locale) {
       return String.valueOf(object.getId());
    }
}



