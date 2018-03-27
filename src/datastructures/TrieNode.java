package datastructures;
public class TrieNode {
    
    Boolean visited;
    Boolean isCompleteWord;
    TrieNode[] offspring = new TrieNode[26];
    char ch;
    
    public TrieNode(){
        this.isCompleteWord = false;
        this.visited = false;
        for(int i  = 0; i < offspring.length; i++){
            offspring[i] = null;
        }
    }
    public static TrieNode makeNode(char c){
        TrieNode newn = new TrieNode();
        newn.isCompleteWord = false;
        newn.visited = false;
        newn.ch = c;
        for (int i = 0; i < newn.offspring.length; i++){
            newn.offspring[i] = null;
        }
        return newn;
    }
    
    public TrieNode getOffspring(char c){
        for(int i = 0; i < offspring.length; i++){
            if (offspring[i] != null && offspring[i].ch == c){
                return offspring[i];
            }
        }
        return null;
    }
    
    public void addNode(TrieNode next){
        int index = (int)next.ch-97;
        offspring[index] = next;  
    }

}
