package dev.rozhkova.ibank.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Main {
    public static void main(final String[] args) {
        final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("user1"));
        System.out.println(encoder.encode("admin"));
    }
}