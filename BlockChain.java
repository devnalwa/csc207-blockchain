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
    Node first;
    Node last;

    public BlockChain(int initial) throws NoSuchAlgorithmException{
        Block c = new Block(0, initial, null);
        this.first = new Node(c,null); 
        this.last = this.first;
    }
    public Block mine(int amount) throws NoSuchAlgorithmException{
        Block b = new Block(last.block.num + 1, amount, last.block.prevHash);
        return b;
    }
    public int getSize(){
        return last.block.num + 1;
    }
    void append(Block blk){
        if(blk.prevHash != last.block.curHash){
            throw new IllegalArgumentException("not a valid block"); 
        }
        else{
            Node add = new Node(blk, null);
            this.last.next = add; 

        }
    }
    public boolean removeLast(){
        if(getSize() ==1) return false;
        Node temp = this.first;
        do{
            temp = temp.next; 
        }while(temp.next != null);
        temp.next = null;
        this.last = temp;
        return true;
    }
    public Hash getHash(){
        return last.block.curHash;
    }
    public boolean isValidBlockChain(){
        Node temp = this.first;
        while(temp.next!=null){
            if(temp.block.curHash != temp.next.block.prevHash){
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
        return true;
    }
    public void printBalances(){
        int p1 = this.first.block.amount;
        int p2 = 0;
        Node temp = this.first.next;
        do{
            if(temp.block.amount > 0){
                p1 += temp.block.amount;
                p2 -= temp.block.amount;
            }else if(temp.block.amount <0){
                p1 -= temp.block.amount;
                p2 += temp.block.amount;
            }else{}
            temp = temp.next;
        }while(temp != null);
        System.out.println("Alice: " + p1 + ", Bob: " + p2);
    }
    public String toString(){
        String str = "";
        Node temp = this.first;
        for(int i = 0; i < getSize(); i++){
            str += temp.block.toString();
            str += "\n";
        }
        return str;
    }

}
