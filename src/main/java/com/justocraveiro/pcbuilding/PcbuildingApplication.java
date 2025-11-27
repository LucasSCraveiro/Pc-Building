package com.justocraveiro.pcbuilding;

import com.justocraveiro.pcbuilding.model.Computador;
import com.justocraveiro.pcbuilding.repository.ComputadorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;import org.springframework.context.annotation.Bean;
import java.math.BigDecimal;
@SpringBootApplication
public class PcbuildingApplication {

	public static void main(String[] args) {
		SpringApplication.run(PcbuildingApplication.class, args);
	}
	@Bean
	public CommandLineRunner seed(ComputadorRepository repository) {
		return args -> {
			if (repository.count() == 0) {
				repository.save(new Computador("Gaming Beast 2025", "PC potente para jogos AAA", "AMD Ryzen 9", "32GB", "1TB NVMe", new BigDecimal("11999.90"), "gaming"));
				repository.save(new Computador("Office Pro", "Equipamento confiável para produtividade", "Intel i5", "16GB", "512GB SSD", new BigDecimal("3599.00"), "office"));
				repository.save(new Computador("Servidor Rack 2U", "Servidor para pequenas empresas", "Intel Xeon", "64GB", "4TB RAID", new BigDecimal("22000.00"), "servidor"));
			}
		};
	}
}
