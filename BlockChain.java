import java.security.NoSuchAlgorithmException;


public class BlockChain {
    static class Node{
        //Declare class variables
        Node next;
        Block block;

        Node(Block b, Node n)
        {
            this.block = b;
            this.next = n;
        }
    }
    // decleration of first and last node
    Node first;
    Node last;

    public BlockChain(int initial) throws NoSuchAlgorithmException{ // creation of new blockchain whose block starts with a given initial amount
        Block c = new Block(0, initial, null); 
        this.first = new Node(c,null); 
        this.last = this.first;
    }
    public Block mine(int amount) throws NoSuchAlgorithmException{ // creates a new block that can be added to the list
        Block b = new Block(last.block.num + 1, amount, last.block.prevHash); 
        return b;
    }
    public int getSize(){ // returns size of the blockchain
        return last.block.num + 1;
    }
    void append(Block blk){ // adds block blk to the chain if prompted by the user
        int p1 = this.first.block.amount;
        int p2 = 0;
        Node temp = this.first.next;
        while(temp != null){ // checks to see if temp is null
            if(temp.block.amount > 0){
                p1 += temp.block.amount;
                p2 -= temp.block.amount;
            }else if(temp.block.amount <0){
                p1 -= temp.block.amount;
                p2 += temp.block.amount;
            }else{}
            temp = temp.next;
        }
        if(blk.amount < 0 && p1 -blk.amount < 0){
            throw new IllegalArgumentException("not a valid block"); // if block is not valid, it throws and illegal argument exception
        }
        if(blk.amount > 0 && p2 -blk.amount < 0){
            throw new IllegalArgumentException("not a valid block"); // if block is not valid, it throws and illegal argument exception
        }
        else{
            blk.prevHash = this.last.block.curHash;
            Node add = new Node(blk, null);
            this.last.next = add; 
        }
    }
    public boolean removeLast(){ // removes the last block from the chain and returns true
        if(getSize() ==1) return false; // if there is only one block on chain, returns flase
        Node temp = this.first;
        while(temp.next.next != null){ 
           System.out.println("check");
            temp = temp.next;
        }
        temp.next = null;
        this.last = temp;
        return true;
    }
    public Hash getHash(){ // returns the hash of the last block in the chain
        return last.block.curHash;
    }
    public boolean isValidBlockChain(){ // goes through the chain and checks to see if the blocks are consistent and valid
        Node temp = this.first;
        while(temp.next!=null){
            if(temp.block.curHash != temp.next.block.prevHash){ // if they are not, it returns false
                return false;
            }
        }
        int p1 = this.first.block.amount;
        int p2 = 0;
        Node temp2 = this.first.next;
        while (temp2 != null){
            if(temp2.block.amount > 0){
                p1 += temp.block.amount;
            }else if(temp.block.amount <0){
                p1 -= temp.block.amount;
                p2 += temp.block.amount;
            }else{}
            temp2 = temp2.next;
        }
        if(p1 < 0 || p2 < 0) return false;
        return true; // returns true if valid
    }
    public void printBalances(){ // prints Alice’s and Bob’s respective balances in the form
        int p1 = this.first.block.amount;
        int p2 = 0;
        Node temp = this.first.next;
        while(temp != null){
            if(temp.block.amount > 0){
                p1 += temp.block.amount;
                p2 -= temp.block.amount;
            }else if(temp.block.amount <0){
                p1 -= temp.block.amount;
                p2 += temp.block.amount;
            }else{}
            temp = temp.next;
        }
        System.out.println("Alice: " + p1 + ", Bob: " + p2);
    }
    public String toString2(){ // returns a string representation of the block chain
        String str = "";
        Node temp = this.first;
        while(temp != null){
            str += temp.block.toString();
            str += "\n";
            temp = temp.next;
        }
        return str;
    }

}
