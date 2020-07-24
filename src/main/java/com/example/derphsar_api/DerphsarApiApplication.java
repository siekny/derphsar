package com.example.derphsar_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.*")
public class DerphsarApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DerphsarApiApplication.class, args);
		System.out.println("Hello chiva");



//		String addressDetail = "[\"red\",\"blue\",\"gray\"]";
//		addressDetail = addressDetail.replace(' ',' ');
//		String[] addressDetailElems = addressDetail.split(",");
//		System.out.printf("proColor : ");
//		for(String elem : addressDetailElems) {
//			System.out.print( elem+",");
//		}


		//String test = "{\"Adr1\":\"123 Avenue\", \"Adr2\":\"Apt-121\", \"City\":\"Phoenix\", \"State\":\"AZ\"}";
		//String test = "{\"S\",\"M\",\"L\",\"XL\"}";
//		String test = "{\"Adr1\":\"123 Avenue\"}";
//
//
//		Gson gson = new Gson();
//		Address addressObj = gson.fromJson(test, Address.class);//new TypeToken<List<Address>>() {}.getType());
//		System.out.println(addressObj);

	}

}
