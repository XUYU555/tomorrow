package com.xxyy.quandaiban1.infrastructure;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author xy
 * @date 2023-09-13 18:59
 */
public abstract class PasswordUtil {
    public static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    /**
     * 密码加密
     *
     * @param password 需要加密的内容
     */
    public static String encode(String password) {
        return encoder.encode(password);
    }

    /**
     * 验证密码是否匹配
     *
     * @param source 需要验证的内容
     * @param target 数据库存储的值
     */
    public static boolean isMatch(String source, String target) {
        return encoder.matches(source, target);
    }
}