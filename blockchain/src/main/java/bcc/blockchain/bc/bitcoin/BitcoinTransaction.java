package bcc.blockchain.bc.bitcoin;

import java.util.List;

//https://bitcoin.org/en/developer-reference#gettransaction
// Optional value should be wrapped Java primitive types, e.g. Boolean, Integer instead of boolean, int
public class BitcoinTransaction {
	// A positive number of bitcoins if this transaction increased the total wallet balance; a negative number of bitcoins if this transaction decreased the total wallet balance, or 0 if the transaction had no net effect on wallet balance
	Long amount;
	// (Optional) If an outgoing transaction, this is the fee paid by the transaction reported as negative bitcoins
	Long fee;
	//The number of confirmations the transaction has received. Will be 0 for unconfirmed and -1 for conflicted
	int confirmations;
	// (Optional) Set to true if the transaction is a coinbase. Not returned for regular transactions
	Boolean generated;
	// (Optional) 
	String blockhash;
	// (Optional) 
	Integer blockindex;
	// (Optional) 
	Integer blocktime;
	//
	String txid;
	//
	List<String> walletconflicts;
	// A Unix epoch time when the transaction was added to the wallet
	int time;
	// A Unix epoch time when the transaction was detected by the local node, or the time of the block on the local best block chain that included the transaction
	int timereceived;
	// "bip125-replaceable"
	String bip125replaceable;
	// (Optional) For transaction originating with this wallet, a locally-stored comment added to the transaction. Only returned if a comment was added
	// local comment
	String comment;
	// (Optional) For transaction originating with this wallet, a locally-stored comment added to the transaction identifying who the transaction was sent to. Only returned if a comment-to was added
	// local name of receiver
	String to;
	
	//TODO finish, that tr is too long
}
