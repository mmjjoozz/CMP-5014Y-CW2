package datastructures;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class AutoCompletionTrie {

    
    AutoCompletionTrieNode root = new AutoCompletionTrieNode();
    double trieFrequency = 0;
    
    public void add(String key, int frequency){
        AutoCompletionTrieNode temp = root;
        for(int i = 0; i < key.length(); i++){
            AutoCompletionTrieNode next = temp.getOffspring(key.charAt(i));
            if(next == null){
                next = AutoCompletionTrieNode.makeNode(key.charAt(i));
                temp.addNode(next);
            }
            temp = next;
        }
        temp.isCompleteWord = true;
        temp.frequency = frequency;
        
    }
    public boolean contains(String key){
        AutoCompletionTrieNode temp = root;
        for (int i = 0; i < key.length(); i++){
            AutoCompletionTrieNode next = temp.getOffspring(key.charAt(i)); 
            if(next == null){
                 return false;
            }else {
                temp = next;
            }
        }
        return true;
        }
    
    public String breadthFirstSearch(){
        Queue<AutoCompletionTrieNode> q = new LinkedList();
        ArrayList<Character> c = new ArrayList();
        q.add(root);
        while(!q.isEmpty()){
            AutoCompletionTrieNode next = q.remove();
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
    
    private static void outputDepthFirstSearch(AutoCompletionTrieNode next, StringBuilder str){
        next.visited = true;
        for(int i = 0; i < next.offspring.length-1; i++){
            if(next.offspring[i] != null && !next.offspring[i].visited){
                outputDepthFirstSearch(next.offspring[i],str);
            }
        }
        str.append(next.ch);
    }
    
    public AutoCompletionTrie getSubTrie(String prefix){
        AutoCompletionTrieNode next = root;
        AutoCompletionTrie newTrie = new AutoCompletionTrie();
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
    
    public String getAllWords(String query){
        
        Map<String, Double> map = new TreeMap();
        StringBuilder builder = new StringBuilder(query);
        getAllWords(root, builder, map); 

        map = map.entrySet().stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .collect(Collectors.toMap(
                Map.Entry::getKey, 
                Map.Entry::getValue, 
                (x,y)-> {throw new AssertionError();},
                LinkedHashMap::new
        ));
        
        builder = new StringBuilder(query + ",");
       int counter = 0;
       for(Object key : map.keySet()){
           if(counter < 3){
              builder.append((String) key + "," + map.get(key)/trieFrequency + ",");
           }
            counter++;
        }   
        
        if (builder.toString().isEmpty()){
            return null;
        }
        return builder.toString();

    }
    
    private void getAllWords(AutoCompletionTrieNode next, StringBuilder builder,
                            Map map){
            if(next == null) {
                return; 
            } 
            
            if(next.isCompleteWord){
                map.put(builder.toString(), next.frequency);
                this.trieFrequency = this.trieFrequency + next.frequency;
            }
            for(int i = 0; i < next.offspring.length; i++){
                if(next.offspring[i] != null) {
                    builder.append(next.offspring[i].ch);
                    getAllWords(next.offspring[i], builder, map);
                    builder.setLength(builder.length()-1);
                }
            }
        }
    
}

