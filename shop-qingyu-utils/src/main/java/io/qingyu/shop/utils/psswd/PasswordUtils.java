package io.qingyu.shop.utils.psswd;

import io.qingyu.shop.utils.md5.MD5Hash;

/**
 * @author qingYu
 * @version 1.0.0
 * @Description 密码加密
 */
public class PasswordUtils {
    public static String getPassword(String password){
        if (password == null || password.trim().isEmpty()) return password;
        for (int i = 0; i < 5; i++){
            password = MD5Hash.md5Java(password);
        }
        return password;
    }
}
