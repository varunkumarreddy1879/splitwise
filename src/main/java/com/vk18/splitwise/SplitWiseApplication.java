package com.vk18.splitwise;

import com.vk18.splitwise.Command.CommandRegistery;
import com.vk18.splitwise.Command.RegisterUserCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SplitWiseApplication implements CommandLineRunner {

    CommandRegistery commandRegistery;

    public SplitWiseApplication(CommandRegistery commandRegistery){
        this.commandRegistery=commandRegistery;
    }
    public static void main(String[] args) {
        SpringApplication.run(SplitWiseApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String input="018 AddGroup roomamtes";
        commandRegistery.execute(input);
    }
}
