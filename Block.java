
import java.util.*;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Block {

    int num;//the number of the block in the chain
    int amount;//amount transferred between two parties
    Hash prevHash;
    long nonce;
    Hash curHash;
    
    
    
    public static Hash calculateHash(int num, int amount, Hash prevHash, long nonce) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("sha-256");
        ByteBuffer byte1 = ByteBuffer.allocate(8);
        ByteBuffer byte2 = ByteBuffer.allocate(8);
        byte1.putInt(num).putInt(amount);
        byte2.putLong(nonce);
        md.update(byte1.array());
        if (prevHash != null) {
            md.update(prevHash.getData());
        }
        md.update(byte2.array());
        byte[] hash = md.digest();
        Hash ret = new Hash(hash);
        return ret;
    }

    public Block(int num, int amount, Hash prevHash) throws NoSuchAlgorithmException{
        Random rand = new Random();
        this.num = num;
        this.amount = amount;
        this.prevHash = prevHash;
        this.nonce = rand.nextLong();
        this.curHash = calculateHash(this.num, this.amount, this.prevHash, this.nonce);
        while (!this.curHash.isValid()){
            this.curHash = calculateHash(this.num, this.amount, this.prevHash, this.nonce);
            this.nonce++;
        }
    }

    public Block(int num, int amount, Hash prevHash, long nonce) throws NoSuchAlgorithmException{
        this.num = num;
        this.amount = amount;
        this.prevHash = prevHash;
        this.nonce = nonce;
        this.curHash= calculateHash(this.num, this.amount, this.prevHash, this.nonce);

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
}
