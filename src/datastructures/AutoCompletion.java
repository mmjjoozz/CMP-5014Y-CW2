package datastructures;

import static datastructures.DictionaryFinder.readWordsFromCSV;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class AutoCompletion {
    
    
    public static void main(String[] args) throws Exception {
        
        DictionaryFinder df = new DictionaryFinder();
        
        df.formDictionary();
        
        AutoCompletionTrie trie = df.formTrie();
        
        
        List<String> queries = readWordsFromCSV("testQueries.txt");
        
        
        try {
            PrintWriter writer = new PrintWriter("lotrMatches.csv");
            
            for(String q : queries){
                AutoCompletionTrie subTrie = trie.getSubTrie(q);
                if(subTrie != null){
                    writer.println(subTrie.getAllWords(q));
                    System.out.println(subTrie.getAllWords(q));
                } 
            }
            
            writer.close();
                 
        } catch (IOException e){
            e.printStackTrace();
        }
        
        
    }
    
}
