import java.util.*;
import java.lang.*;

public class Hash {
	public byte[] hash;
	public Hash(byte[] data){
		this.hash = data;
	}
	
	public byte[] getData(){
		return this.hash;
	}
	
	public boolean isValid(){
		if(this.hash[0] == 0 && this.hash[1] == 0 && this.hash[2] == 0) {return true;}
		return false;
	}
	
	public String toString(){
		String h = ""; 
		for(int i = 0; i < this.hash.length; i++){
			 h+= String.format("%x", Byte.toUnsignedInt(this.hash[i]));
		}
		return h;
	}
	
	public boolean equals(Object other){
		if (other instanceof Hash){
			Hash o = (Hash) other;
			return Arrays.equals(o.hash, this.hash);
		}
		else return false;
	}
}
