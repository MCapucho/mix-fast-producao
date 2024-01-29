package br.com.postech.mixfastproducao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MixFastProducaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MixFastProducaoApplication.class, args);
	}

}
