import java.util.*;
import java.lang.*;

public class Hash {
	public byte[] hash;
	public Hash(byte[] data){ // constructs a new Hash object that contains the given hash
		this.hash = data;
	}
	
	public byte[] getData(){ // returns the hash contained in this object
		return this.hash;
	}
	
	public boolean isValid(){ // returns true if this hash meets the criteria for validity 
		if(this.hash[0] == 0 && this.hash[1] == 0 && this.hash[2] == 0) {return true;} // checks first three indices
		return false;
	}
	
	public String toString(){ // returns the string representation of the hash as a string of hexadecimal digits
		String h = ""; 
		for(int i = 0; i < this.hash.length; i++){ // a loop that goes through the length of the hash
			 h+= String.format("%x", Byte.toUnsignedInt(this.hash[i]));
		}
		return h;
	}
	
	public boolean equals(Object other){ // returns true if this hash is structurally equal to the argument
		if (other instanceof Hash){
			Hash o = (Hash) other;
			return Arrays.equals(o.hash, this.hash);
		}
		else return false;
	}
}
