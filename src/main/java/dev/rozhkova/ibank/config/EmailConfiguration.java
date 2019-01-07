package dev.rozhkova.ibank.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class EmailConfiguration {

    @Value("${email.util.host}")
    private String host;

    @Value("${email.util.port}")
    private String port;

    @Value("${email.util.user}")
    private String user;

    @Value("${email.util.pass}")
    private String pass;
}
