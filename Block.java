import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Block {
	
	int num;
	int amount;
	Hash prevHash;
	long nonce;
	Hash curHash;
	
	public Block(int num, int amount, Hash prevHash){
		this.num = num;
		this.amount = amount;
		this.prevHash = prevHash;
		
		
		
	}
	
	public Block(int num, int amount, Hash prevHash, long nonce){
		this.num = num;
		this.amount = amount;
		this.prevHash = prevHash;
		this.nonce = nonce;
		
	}
	
	public int getNum(){
		return this.num;
	}
	
	public int getAmount(){
		return this.amount;
	}
	
	public long getNonce(){
		return this.nonce;
		
	}
	
	public Hash getPrevHash(){
		return this.prevHash;
		
	}
	
	public Hash getHash(){
		return this.curHash;
	}
	
	public String toString(){
		return "BBlock " + Integer.toString(this.num) + " (Amount: " + Integer.toString(this.amount) + ", Nonce: " + Long.toString(this.nonce) + ", prevHash: " + this.prevHash.toString() + ", hash: " + this.curHash.toString() + ")"; 
	}
	
	public static byte[] calculateHash(String msg) throws NoSuchAlgorithmException {
	    MessageDigest md = MessageDigest.getInstance("sha-256");
        return null;
	    
	}
}
