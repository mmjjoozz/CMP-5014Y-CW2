
package datastructures;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;
import java.util.TreeMap;
/**
 *
 * @author ajb
 */
public class DictionaryFinder {
    
    ArrayList<String> in;
    TreeMap<String, Integer> dict;
    public DictionaryFinder(){
    }
/**
 * Reads all the words in a comma separated text document into an Array
 * @param f 
 */   
    public static ArrayList<String> readWordsFromCSV(String file) throws FileNotFoundException, IOException {
        Scanner sc=new Scanner(new File(file));
        
                
        Writer writer = new BufferedWriter(new OutputStreamWriter(
              new FileOutputStream("out.txt"), "utf-8"));
        
        sc.useDelimiter(" |,");
        ArrayList<String> words=new ArrayList<>();
        String str;
        while(sc.hasNext()){
            str=sc.next();
            str=str.trim();
            str=str.toLowerCase();
            words.add(str);
            writer.write(str + ",");
            
        }
        writer.close();

        
        return words;
    }
     public static void saveCollectionToFile(Collection<?> c,String file) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        PrintWriter printWriter = new PrintWriter(fileWriter);
         for(Object w: c){
            printWriter.println(w.toString());
         }
        printWriter.close();
     }
     public void formDictionary() throws Exception {
         in = readWordsFromCSV("lotr.csv");
         Collections.sort(in);
         dict = new TreeMap();
         for(String word : in){
             if(dict.containsKey(word)){
                 dict.put(word, dict.get(word)+1);
             } else {
                 dict.put(word, 1);
             }
         }
     }
     public void saveToFile() throws Exception {
          ArrayList<String> out = new ArrayList();
          File newFile = new File("output.csv");
          BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));
          for (String key : dict.keySet()){
              writer.write(key + " " + dict.get(key) + "\n");
          }
          writer.close();
     }
     
     public AutoCompletionTrie formTrie(){
         AutoCompletionTrie t = new AutoCompletionTrie();
         for(String key : dict.keySet()){
             t.add(key, dict.get(key));
         }
         return t;
     }
        
}
