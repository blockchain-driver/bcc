package bcc.blockchain.bc.ethereum.classic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import bcc.blockchain.bc.BCTransaction;
import bcc.blockchain.bc.LoggingRequestInterceptor;
import bcc.blockchain.bc.ethereum.EthRequest;
import bcc.blockchain.bc.ethereum.EthResponse;
import bcc.blockchain.bc.ethereum.EthResponseStrings;
import bcc.blockchain.bc.ethereum.EthereumBlockchain;

/**
 * Ethereum Classic specific methods
 * 
 * @author Paul
 */
public class EthereumClassicBlockchain extends EthereumBlockchain {
	private static final Logger log = LoggerFactory.getLogger(EthereumClassicBlockchain.class);

	
	//https://github.com/ethereumproject/go-ethereum/wiki/JSON-RPC#geth_getaddresstransactions
	/**
	 * 
	 * Usage requires address-transaction indexes using <code>geth --atxi</code> to enable and create indexes 
	 * during chain sync/import, optionally using <code>geth atxi-build</code> to index pre-existing chain data.
	 * 
	 * @param account
	 * @param earliestBlock
	 * @param latestBlock
	 * @return List of transaction hashes, or an empty list if no transactions found
	 * 
	 * @see https://github.com/ethereumproject/go-ethereum/wiki/JSON-RPC#geth_getaddresstransactions
	 */
	public List<String> geth_getAddressTransactions(String account, int earliestBlock, int latestBlock){
		EthRequest req = new EthRequest();
		req.setMethod("geth_getAddressTransactions");
		req.addParam(account);
		req.addParam(earliestBlock);
		req.addParam(latestBlock);
		req.addParam("f"); //[t|f|tf|], use t for transactions to the address, f for from, or tf/'' for both
		req.addParam("s"); //[s|c|sc|], use s for standard transactions, c for contracts, or sc/``''` for both
		req.addParam(-1); //paginationIndexBegin
		req.addParam(-1); //paginationIndexEnd
		req.addParam(false); //whether to return transactions in order of oldest first. By default false returns transaction hashes ordered by newest transactions first.

		HttpEntity<EthRequest> entity = new HttpEntity<EthRequest>(req, headers);

		EthResponseStrings response;
		try {
			response = restTemplate.postForObject(url, entity, EthResponseStrings.class);
		} catch (RestClientException e) {		
			log.error("RestClientException: {}", e.getMessage());
			return null;
		}
		log.debug("{}", response);
		List<String> result = response.getResult();
		log.debug("result {}", result);
		
		return result;
	}


}
