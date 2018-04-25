package bcc.app.java;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bcc.blockchain.bc.BCTransaction;
import bcc.blockchain.bc.Blockchain;
import bcc.blockchain.bc.ethereum.EthereumBlockchain;

public class AppUsingBlockchain {
	private static final Logger log = LoggerFactory.getLogger(AppUsingBlockchain.class);

	public static void main(String[] args) {
		
		List<String> nodes = new LinkedList<>();
		// geth --datadir dev --dev --rpc --rpcapi "eth,personal,net,txpool" --rpcport 61337 --ipcdisable console
		// 65164
		nodes.add("http://localhost:61337");
		String account ="0x315C4BBbD11bA58ad0c52D25F3035ca810d9e6D1";
		List<String> accounts = new LinkedList<>();
		accounts.add(account);
		
		EthereumBlockchain ebc = new EthereumBlockchain(nodes, accounts);
		Blockchain bc = ebc;
		
		int trCount = ebc.eth_getTransactionCount(account);
		log.info("{}", trCount);
		
		BCTransaction tr = new BCTransaction();
		String trid = bc.saveTransaction(tr);

		BCTransaction tr2 = bc.readTransaction(trid);
		
	}

}

