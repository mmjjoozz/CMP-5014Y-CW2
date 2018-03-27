package datastructures;
public class AutoCompletionTrieNode {
    
    Boolean visited;
    Boolean isCompleteWord;
    AutoCompletionTrieNode[] offspring = new AutoCompletionTrieNode[26];
    char ch;
    double frequency;
    
    public AutoCompletionTrieNode(){
        this.isCompleteWord = false;
        this.visited = false;
        for(int i  = 0; i < offspring.length; i++){
            offspring[i] = null;
        }
    }
    public static AutoCompletionTrieNode makeNode(char c){
        AutoCompletionTrieNode newn = new AutoCompletionTrieNode();
        newn.isCompleteWord = false;
        newn.visited = false;
        newn.ch = c;
        for (int i = 0; i < newn.offspring.length; i++){
            newn.offspring[i] = null;
        }
        return newn;
    }
    
    public AutoCompletionTrieNode getOffspring(char c){
        for(int i = 0; i < offspring.length; i++){
            if (offspring[i] != null && offspring[i].ch == c){
                return offspring[i];
            }
        }
        return null;
    }
    
    public void addNode(AutoCompletionTrieNode next){
        int index = (int)next.ch-97;
        offspring[index] = next;  
    }

}
