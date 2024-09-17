package jp.co.hoge.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
	    "jp.co.hoge.web.config", 
	    "jp.co.hoge.web.controller", 
	    "jp.co.hoge.web.model", 
	    "jp.co.hoge.web.repository", 
	    "jp.co.hoge.web.service"
	})
	public class YutoTakahashiEducureAppApplication {
	    public static void main(String[] args) {
	        SpringApplication.run(YutoTakahashiEducureAppApplication.class, args);
	    }
	}

