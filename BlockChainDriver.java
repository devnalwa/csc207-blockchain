import java.security.NoSuchAlgorithmException;
import java.util.*;


public class BlockChainDriver { // this class interacts with all the other classes and holds the main method


    public static void main(String[] args) throws NumberFormatException, NoSuchAlgorithmException{
        BlockChain c = new BlockChain(Integer.parseInt(args[0]));

        Scanner in = new Scanner(System.in); // to take in user input
        String cmd = ""; // the command the user will be inputing
        boolean q = false;
        while(q == false){ 
            System.out.println(c.toString2());
            System.out.println("Command?  ");
            cmd = in.next();
            if(cmd.equals("mine")){ 
                System.out.println("Amount transferred? ");
                cmd = in.next();
                c.mine(Integer.parseInt(cmd)); // calls the mine function
                System.out.println("amount = " + cmd + ", nonce = " + c.first.block.getNonce());
            }else if(cmd.equals("append")){ 
                System.out.println("Amount transferred? ");
                cmd = in.next();
                c.append(c.mine(Integer.parseInt(cmd))); // calls the append function
                //System.out.println("amount = " + cmd + ", nonce = " + c.first.block.getNonce());
            }else if(cmd.equals("remove")){
                c.removeLast(); // calls the remove function
            }else if(cmd.equals("check")){ 
                if(c.isValidBlockChain()) System.out.println("Chain is valid!"); // calls the isValid function
                else System.out.println("Chain is not valid!");
            }else if(cmd.equals("report")){
                c.printBalances(); // calls the printBalances function
            }else if(cmd.equals("help")){ // if user types help, it gives the details on all the functions
                System.out.println("Valid commands:");
                System.out.println("    mine: discovers the nonce for a given transaction");
                System.out.println("    append: appends a new block onto the end of the chain");
                System.out.println("    remove: removes the last block from the end of the chain");  
                System.out.println("    check: checks that the block chain is valid");   
                System.out.println("    report: reports the balances of Alice and Bob"); 
                System.out.println("    help: prints this list of commands"); 
                System.out.println("    quit: quits the program");  
            }else if(cmd.equals("quit")){ 
                q = true; // lets the user quit the program
            }
            
        }

    } 
}
