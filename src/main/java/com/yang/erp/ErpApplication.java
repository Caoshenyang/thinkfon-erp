package com.yang.erp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * <p>
 * 启动类
 * 需要启动 MySQL
 * api 文档地址： http://127.0.0.1:8000/itshare/doc.html
 * </p>
 *
 * @author 曹申阳
 * @since 2023-01-07 11:05:31
 */
@SpringBootApplication
public class ErpApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErpApplication.class, args);
        try {
            InetAddress addr = InetAddress.getLocalHost();
            System.out.println("接口文档地址：http://" + addr.getHostAddress() + ":8000/doc.html");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
