package bcc.blockchain.bc.ethereum;

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

public class EthereumBlockchain extends bcc.blockchain.bc.AbsractBlockchain {
	private static final Logger log = LoggerFactory.getLogger(EthereumBlockchain.class);
	
	List<String> nodes = new LinkedList<>();
	List<String> accounts = new LinkedList<>();

	protected String url;
	protected RestTemplate restTemplate = new RestTemplate( //);
			new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
	protected HttpHeaders headers = new HttpHeaders();
	//RPC rpc = new RPC();
	
	public EthereumBlockchain() {
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		//restTemplate.setMessageConverters(getMessageConverters());
	}

	public EthereumBlockchain(List<String> nodes, List<String> accounts) {
		this();
		if (nodes == null || nodes.size()<1) {
			throw new RuntimeException("nodes list must have at least one node URL, to be used as default!");
		}
		this.url=nodes.get(0);
		this.nodes=nodes;
		this.accounts=accounts;
	}
	
	@Override
	public void setTraceHttpRequestResponce(boolean trace) {
		// for low level request/response logging
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		if (trace) {
			interceptors.add(new LoggingRequestInterceptor());
		}
		restTemplate.setInterceptors(interceptors);
	}



	@Override
	public String saveTransaction(BCTransaction tr) {
		//transaction = new BCTransactionSend(fromAccount, toAccount, value, tData, DEFAULT_GAS);
		EthTransaction ethTr = new EthTransaction(tr);
//		EthTransaction[] array = new EthTransaction[1];
//		array[0] = ethTr;
		return eth_sendTransaction(ethTr);
	}

	@Override
	public BCTransaction readTransaction(String trid) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private HttpEntity<String> getRequestEntity(RestTemplate restTemplate, String request) {
		log.debug("request {}", request);
		return new HttpEntity<String>(request, headers);
	}
	
	//class RPC{

//	// https://github.com/ethereum/go-ethereum/wiki/Management-APIs#personal_unlockaccount
//	private BCUnlockAccout personal_unlockAccount(BCUnlockAccout account) {
//		HttpEntity<String> entity = getRequestEntity(restTemplate, account.Request());
//
//		try {
//			BCUnlockAccout tx = restTemplate.postForObject(url, entity, BCUnlockAccout.class);
//			return tx;
//		} catch (RestClientException e) {		
//			log.error("RestClientException: {}", e.getMessage());
//			return null;
//		}
//	}

	// https://github.com/ethereum/go-ethereum/wiki/Management-APIs#personal_sendtransaction
	//or
	// https://github.com/ethereum/wiki/wiki/JSON-RPC#eth_sendtransaction
	/**
	 * @param ethTr
	 * @return transaction hash
	 */
	//TODO test response for 2+ transactions
	private String eth_sendTransaction(EthTransaction ethTr) {
		//HttpEntity<String> entity = getRequestEntity(restTemplate, transaction.request());
		EthRequest req = new EthRequest();
		req.setMethod("eth_sendTransaction");
		req.addParam(ethTr);

		HttpEntity<EthRequest> entity = new HttpEntity<EthRequest>(req, headers);

		EthResponse response;
		try {
			response = restTemplate.postForObject(url, entity, EthResponse.class);
		} catch (RestClientException e) {		
			log.error("RestClientException: {}", e.getMessage());
			return null;
		}
		log.debug("{}", response);
		String result = response.getResult();
		log.debug("result {}", result);
		
		return result;

	}

//	// https://github.com/ethereum/wiki/wiki/JSON-RPC#eth_gettransactionbyhash
//	private BCTransactionGet eth_getTransactionByHash(BCTransactionGet transaction) {
//		HttpEntity<String> entity = getRequestEntity(restTemplate, transaction.toRequestString());
//		BCRequest req = new BCRequest();
//		req.setMethod("eth_getTransactionCount");
//		req.addParam(account);
//		// https://github.com/ethereum/wiki/wiki/JSON-RPC#the-default-block-parameter
//		req.addParam("latest");
//
//		HttpEntity<BCRequest> entity = new HttpEntity<BCRequest>(req, headers);
//
//		try {
//			BCTransactionGet tx = restTemplate.postForObject(url, entity, BCTransactionGet.class);
//			return tx;
//		} catch (RestClientException e) {		
//			log.error("RestClientException: {}", e.getMessage());
//			return null;
//		}
//	}
	
	//https://github.com/ethereum/wiki/wiki/JSON-RPC#eth_gettransactioncount
	/** Returns the number of transactions sent from an address(account). */
	// Note: that will be nonce value for next transaction, as nonce start from 0
	public int eth_getTransactionCount(String account) {
		EthRequest req = new EthRequest();
		req.setMethod("eth_getTransactionCount");
		req.addParam(account);
		// https://github.com/ethereum/wiki/wiki/JSON-RPC#the-default-block-parameter
		req.addParam("latest");

		HttpEntity<EthRequest> entity = new HttpEntity<EthRequest>(req, headers);
		EthResponse response;
		try {
			response = restTemplate.postForObject(url, entity, EthResponse.class);
			log.debug("response {}", response.toString());
		} catch (RestClientException e) {		
			log.error("RestClientException: {}", e.getMessage());
			return -1;
		}
		log.debug("{}", response);
		if (response.getError()!=null) {
			log.warn("{}", response.getError() );
			return -2;
		}
		String result = response.getResult();
		log.debug("result {}", result);
		return Integer.decode(result);
	}
	
	//}
	

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(300);
		sb.append("Blochain(");
		sb.append(" nodes:").append(Arrays.toString(nodes.toArray()));
		sb.append(", accounts:").append(Arrays.toString(accounts.toArray()));
		sb.append(" )");
		return sb.toString();
	}

}
