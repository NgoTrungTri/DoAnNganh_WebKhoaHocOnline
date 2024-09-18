/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.formatter;

import com.ntt.pojo.User;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author PC
 */
public class UserFormatter implements Formatter<User>{

    @Override
    public String print(User u, Locale locale) {
        return String.valueOf(u.getId());
    }

    @Override
    public User parse(String id, Locale locale) throws ParseException {
        User u = new User();
        u.setId(Integer.parseInt(id));
        
        return u;
    }
    
}
