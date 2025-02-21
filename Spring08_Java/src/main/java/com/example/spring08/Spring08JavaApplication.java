package com.example.spring08;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.spring08.util.Messenger;
import com.example.spring08.util.WritingUtil;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class Spring08JavaApplication {

	@Autowired private WritingUtil util; 
	@Autowired private Messenger messenger;
	
	@PostConstruct
	public void testAop() {
		messenger.sendGreeting("안녕하세요!");
		messenger.sendGreeting("안녕 바보야!");
		
		String result = messenger.getMessage();
		System.out.println("result:" + result);
		
		util.writeLetter();
		util.writeReport();
		util.writeDiary();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Spring08JavaApplication.class, args);
		
		String pwd = "1234";
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPwd = encoder.encode(pwd);
		System.out.println(encodedPwd);
		
		boolean same = BCrypt.checkpw(pwd, encodedPwd);
		System.out.println(same);
		
		
		List<String> names = List.of("김구라", "해골", "원숭이");
		Stream<String> stream = names.stream();
		
//		Function<String, String> f = (item) -> item + "님";
		
//		List<String> names2 = stream.map(f).toList();
//		System.out.println(names2);
		
		List<String> names3 = stream.map((item)->item+"님").toList();
		System.out.println(names3);
		
		List<String> names4 = names.stream().map((item)->item+"님").toList();
		System.out.println(names4);
		
		List<Integer> nums = List.of(-10, 20, 30, -5, 25, -30);
		List<Integer> newNums = nums.stream().filter(item -> item>0).sorted().toList();
		System.out.println(newNums);
		List<Integer> newNums2 = nums.stream().filter(item -> item>0).map(item -> item*2).sorted().toList();
		System.out.println(newNums2);
		
		nums.stream().filter(item -> item>0).map(item -> item*2).forEach(item -> {
			System.out.println(item);
		});
		
		List<String> strNums = List.of("10", "20", "30", "40", "50");
		List<Integer> intNums = strNums.stream().map(item -> Integer.parseInt(item)).toList();
		System.out.println(intNums);
		
		List<Integer> intNums2 = strNums.stream().map(Integer::parseInt).toList();
	}

}
