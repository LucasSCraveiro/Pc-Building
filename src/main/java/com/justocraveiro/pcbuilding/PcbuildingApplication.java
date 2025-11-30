package com.justocraveiro.pcbuilding;

import com.justocraveiro.pcbuilding.model.Computador;
import com.justocraveiro.pcbuilding.model.Peca;
import com.justocraveiro.pcbuilding.model.TipoPeca;
import com.justocraveiro.pcbuilding.repository.ComputadorRepository;
import com.justocraveiro.pcbuilding.repository.PecaRepository;
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
	public CommandLineRunner seed(ComputadorRepository computadorRepository, PecaRepository pecaRepository) {
		return args -> {
			// popular catálogo de peças se vazio
			if (pecaRepository.count() == 0) {
				pecaRepository.save(new Peca("Intel i5 12400", TipoPeca.PROCESSADOR, new BigDecimal("900")));
				pecaRepository.save(new Peca("Intel i7 12700", TipoPeca.PROCESSADOR, new BigDecimal("1400")));
				pecaRepository.save(new Peca("AMD Ryzen 5 5600X", TipoPeca.PROCESSADOR, new BigDecimal("800")));

				pecaRepository.save(new Peca("8GB DDR4", TipoPeca.RAM, new BigDecimal("150")));
				pecaRepository.save(new Peca("16GB DDR4", TipoPeca.RAM, new BigDecimal("250")));
				pecaRepository.save(new Peca("32GB DDR4", TipoPeca.RAM, new BigDecimal("450")));

				pecaRepository.save(new Peca("256GB SSD", TipoPeca.ARMAZENAMENTO, new BigDecimal("120")));
				pecaRepository.save(new Peca("512GB SSD", TipoPeca.ARMAZENAMENTO, new BigDecimal("200")));
				pecaRepository.save(new Peca("1TB SSD", TipoPeca.ARMAZENAMENTO, new BigDecimal("350")));
			}

			// popular alguns computadores exemplo se não existirem
			if (computadorRepository.count() == 0) {
				computadorRepository.save(new Computador("Gaming Beast 2025", "PC potente para jogos AAA", "AMD Ryzen 9", "32GB", "1TB NVMe", new BigDecimal("11999.90"), "gaming"));
				computadorRepository.save(new Computador("Office Pro", "Equipamento confiável para produtividade", "Intel i5", "16GB", "512GB SSD", new BigDecimal("3599.00"), "office"));
				computadorRepository.save(new Computador("Servidor Rack 2U", "Servidor para pequenas empresas", "Intel Xeon", "64GB", "4TB RAID", new BigDecimal("22000.00"), "servidor"));
			}
		};
	}
}
