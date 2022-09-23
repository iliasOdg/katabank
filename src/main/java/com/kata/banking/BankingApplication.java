package com.kata.banking;

import com.kata.banking.entity.Account;
import com.kata.banking.entity.Client;
import com.kata.banking.repository.AccountRepository;
import com.kata.banking.repository.ClientRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BankingApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankingApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(AccountRepository accountRepository, ClientRepository clientRepository) {
        return args -> {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode("Password");
            Client client = Client.builder()
                    .surname("James")
                    .firstname("Bond")
                    .email("james@email.com")
                    .clientNumber(2543)
                    .password(encodedPassword)
                    .role("USER")
                    .build();
            client = clientRepository.saveAndFlush(client);

            Account ac1 = Account.builder()
                    .accountPosition(2000d)
                    .accountNumber("ACC01")
                    .client(client)
                    .build();
            Account ac2 = Account.builder()
                    .accountPosition(0d)
                    .accountNumber("ACC02")
                    .client(client)
                    .build();
            accountRepository.save(ac1);
            accountRepository.save(ac2);
        };
    }


    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }
}
