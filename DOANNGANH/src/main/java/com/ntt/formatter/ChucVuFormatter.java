/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.formatter;

import com.ntt.pojo.Chucvu;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author PC
 */
public class ChucVuFormatter implements Formatter<Chucvu>{

    @Override
    public Chucvu parse(String id, Locale locale) throws ParseException {
        Chucvu c = new Chucvu();
        c.setId(Integer.parseInt(id));
        
        return c;
    }

    @Override
    public String print(Chucvu object, Locale locale) {
       return String.valueOf(object.getId());
    }
    
}
