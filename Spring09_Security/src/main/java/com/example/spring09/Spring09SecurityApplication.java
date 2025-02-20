package com.example.spring09;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class Spring09SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(Spring09SecurityApplication.class, args);
	}
	@EventListener(ApplicationReadyEvent.class)
	public void openChrome() {
		String url = "http://localhost:9000/spring09/";
		String os = System.getProperty("os.name").toLowerCase();
		ProcessBuilder builder = null;
		try {
			if (os.contains("window")) {
				builder = new ProcessBuilder("cmd.exe", "/c", "start chrome " + url);
			} else if (os.contains("mac")) {
				builder = new ProcessBuilder("/usr/bin/open", "-a", "Google Chrome", url);
			} else {
				System.out.println("지원하지 않는 운영체제 입니다.");
				return;
			}
			builder.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
