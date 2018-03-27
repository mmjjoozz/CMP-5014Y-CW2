package datastructures;
import java.util.ArrayList;
import java.util.*;

public class Trie {
    
    TrieNode root = new TrieNode();
    
    public void add(String key){
        TrieNode temp = root;
        for(int i = 0; i < key.length(); i++){
            TrieNode next = temp.getOffspring(key.charAt(i));
            if(next == null){
                next = TrieNode.makeNode(key.charAt(i));
                temp.addNode(next);
            }
            temp = next;
        }
        temp.isCompleteWord = true;
    }
    public boolean contains(String key){
        TrieNode temp = root;
        for (int i = 0; i < key.length(); i++){
            TrieNode next = temp.getOffspring(key.charAt(i)); //problem here
            if(next == null){
                 return false;
            }else {
                temp = next;
            }
        }
        return true;
        }
    
    public String breadthFirstSearch(){

        Queue<TrieNode> q = new LinkedList();
        ArrayList<Character> c = new ArrayList();
        q.add(root);
        while(!q.isEmpty()){
            TrieNode next = q.remove();
            if (next.offspring != null){
                for(int i = 0; i < next.offspring.length; i++){
                    if(next.offspring[i] != null){
                        q.add(next.offspring[i]);
                    }
                }
                c.add(next.ch);
            }
        }
        StringBuilder str = new StringBuilder(c.size());
        for(Character ch : c){
            str.append(ch);
        }
        return str.toString();
    }
    
    public String outputDepthFirstSearch(){
        StringBuilder builder = new StringBuilder();
        outputDepthFirstSearch(root, builder);
        return builder.toString();
    }
    
    private static void outputDepthFirstSearch(TrieNode next, StringBuilder str){
        next.visited = true;
        for(int i = 0; i < next.offspring.length-1; i++){
            if(next.offspring[i] != null && !next.offspring[i].visited){
                outputDepthFirstSearch(next.offspring[i],str);
            }
        }
        str.append(next.ch);
    }
    
    
    public Trie getSubTrie(String prefix){
        TrieNode next = root;
        Trie newTrie = new Trie();
        for(int i = 0; i < prefix.length(); i++){
            int index = (int)prefix.charAt(i)-97;
            if(next == null){
                return null;
            }
            if(next.getOffspring(prefix.charAt(i)) != null){
                newTrie.root = next.getOffspring(prefix.charAt(i));
            } 
            next = next.offspring[index];
        }
        return newTrie;
    }
    
    public List<String> getAllWords(){
        
        List<String> list = new ArrayList();
        StringBuilder builder = new StringBuilder();
        getAllWords(root, builder, list);
        return list;
    }
    
    private void getAllWords(TrieNode next, StringBuilder builder, List list){
            if(next == null) {
                return; 
            }                
            if(next.isCompleteWord){
                System.out.println(builder.toString());
                list.add(builder.toString());
            }
            for(int i = 0; i < next.offspring.length; i++){
                if(next.offspring[i] != null) {
                    builder.append(next.ch);
                    getAllWords(next.offspring[i], builder, list);
                    builder.setLength(builder.length() - 1);
                }
            }
        }
    
}

    

