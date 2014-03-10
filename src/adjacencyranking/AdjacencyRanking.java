/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adjacencyranking;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rachelmills
 */
public class AdjacencyRanking {

//    final static String INPUT_FILE = "/Users/rachelmills/Desktop/ClueWeb/subset_clusters.txt";
    final static String INPUT_FILE = "/home/wikiprep/LMW-tree/subset_clusters.txt";
    final static String ADJACENCY_FILE = "/home/wikiprep/wikiprep/work/ShortestPath/ID_Categories_Updated_new.txt";
    final static String CATEGORY_TITLE_MAPPING = "/home/wikiprep/wikiprep/work/ID_Title.txt";
//    final static String ADJACENCY_FILE = "/Users/rachelmills/Desktop/Clueweb/ShortestPath/ID_Categories_Updated.txt";
    //final static String MAPPING_FILE = "/home/wikiprep/wikiprep/work/ID_Title.txt";
//    final static String CATEGORY_TITLE_MAPPING = "/Volumes/Untitled/wikiprep/WikiOutput/ID_Title.txt";
//    final static String MAPPING_FILE = "/Users/rachelmills/Desktop/ClueWeb/Wikiparser/ID_Title.txt";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        AdjacencyRanking ar = new AdjacencyRanking();
        
        ar.startProcessing();
    }

    private void startProcessing() {
        ReadFile rf = new ReadFile(INPUT_FILE);
        Path filePath = rf.getFilePath();

        ReadFile adjacencies = new ReadFile(ADJACENCY_FILE);
        Path adjacencyFilePath = adjacencies.getFilePath();
        
        ReadFile titleMapping = new ReadFile(CATEGORY_TITLE_MAPPING);
        Path titleMappingPath = titleMapping.getFilePath();

        AdjacencyFile adjacencyFile = new AdjacencyFile(adjacencyFilePath);
        Map<Integer, List<Integer>> adjacencyMap = adjacencyFile.getAdjacencyMap();
        
        System.out.println("Files ready for processing.");

        ProcessFile pf = new ProcessFile(filePath, adjacencyMap);
        
        System.out.println("Set up finished");
        
        Map<Integer, List<Integer>> clusters = pf.processLineByLine();
        
        System.out.println("Processing finished");
        
        Map<Integer, List<Integer>> ranking = pf.calculateAdjacencyRank(clusters);
        
        MappingFile mappingFile = new MappingFile(titleMappingPath);
        Map<Integer, String> map = mappingFile.getMapping();
        
        Map<Integer, List<CategoryTitle>> clustersWithTitles = pf.addTitlesToRanking(ranking, map);                
    }
    
    

}
