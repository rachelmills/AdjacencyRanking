/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package adjacencyranking;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rachelmills
 */
public class AdjacencyFile {
    
    Path path;
    private Map<Integer, List<Integer>> adjacencyMap;
    
    private final static Charset ENCODING = StandardCharsets.UTF_8;
    
    public AdjacencyFile(Path path) {
        adjacencyMap = new HashMap<>();
        
        this.path = path;
        try {
            Scanner s = new Scanner(path, ENCODING.name());
            while (s.hasNextLine()) {
                Scanner s1 = new Scanner(s.nextLine());
                List<Integer> cats = new ArrayList<>();
                Integer i = s1.nextInt();
                while (s1.hasNext()) {
                    Integer j = s1.nextInt();
                    cats.add(j);
                }
                adjacencyMap.put(i, cats);
            }
        } catch (IOException ex) {
            Logger.getLogger(ProcessFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the adjacencyMap
     */
    public Map<Integer, List<Integer>> getAdjacencyMap() {
        return adjacencyMap;
    }

    /**
     * @param adjacencyMap the adjacencyMap to set
     */
    public void setAdjacencyMap(Map<Integer, List<Integer>> adjacencyMap) {
        this.adjacencyMap = adjacencyMap;
    }

}
