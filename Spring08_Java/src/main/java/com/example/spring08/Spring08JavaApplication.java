package com.example.spring08;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Spring08JavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(Spring08JavaApplication.class, args);
		
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
