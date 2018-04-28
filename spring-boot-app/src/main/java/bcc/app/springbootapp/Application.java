package bcc.app.springbootapp;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import bcc.blockchain.bc.BCTransaction;
import bcc.blockchain.bc.Blockchain;
import bcc.blockchain.bc.ethereum.EthereumBlockchain;

@SpringBootApplication
//@Configuration
//@ComponentScan("bcc.blockchain.bc.ethereum")
public class Application {
	
	//@Autowired
	EthereumBlockchain ebc;
	
	//@Autowired
	Blockchain blockchain;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@PostConstruct
    public void init(){
		
		BCTransaction tr = new BCTransaction();
		blockchain.saveTransaction(tr );
		
    }
}
