package adjacencyranking;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rachelmills
 */
public class ProcessFile {

    private final static Charset ENCODING = StandardCharsets.UTF_8;

    private FileOutputStream out;
    private BufferedWriter bufferedWriter;

    private final Path path;
    private final Map<Integer, List<Integer>> clusterMap;
    private Map<Integer, List<Integer>> clusters;
    private final Map<Integer, List<Integer>> ranking;

    private final static String OUTPUT_FILE_PATH = "/home/wikiprep/LMW-tree/";
//    private final static String OUTPUT_FILE_PATH = "/Users/rachelmills/Desktop/Clueweb/";

    public ProcessFile(Path path, Map<Integer, List<Integer>> clusterMap) {
        try {
            out = new FileOutputStream(OUTPUT_FILE_PATH + "wiki_ranking.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProcessFile.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(out, "UTF8"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ProcessFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.path = path;
        this.clusterMap = clusterMap;
        this.clusters = new HashMap<>();
        this.ranking = new HashMap<>();
    }

    public Map<Integer, List<Integer>> processLineByLine() {
        try (Scanner scanner = new Scanner(path, ENCODING.name())) {
            while (scanner.hasNextLine()) {
                clusters = processLine(scanner.nextLine());
            }
        } catch (IOException ex) {
            Logger.getLogger(ProcessFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clusters;
    }

    private Map<Integer, List<Integer>> processLine(String nextLine) {

        //use a second Scanner to parse the content of each line 
        Scanner sc = new Scanner(nextLine);

        // extract and remove id from line
        int id = Integer.parseInt(sc.next().replaceAll("[^\\d]", ""));

        // extract cluster number
        int cluster = sc.nextInt();

        // search map for id
        List<Integer> categoryList = clusterMap.get(id);

        if (null != categoryList) {
            if (clusters != null && !clusters.isEmpty() && null != clusters.get(cluster)) {
                clusters.get(cluster).addAll(categoryList);
            } else {
                clusters.put(cluster, categoryList);
            }
        }
        return clusters;
    }

    public Map<Integer, List<Integer>> calculateAdjacencyRank(Map<Integer, List<Integer>> clusters) {
        
        for (int i = 0; i < clusters.size(); i++) {
            
            List<Integer> rankingList = new ArrayList<>();
            Set<Integer> uniqueSet = new HashSet<>();
            uniqueSet.addAll(clusters.get(i));
            List<CategoryFrequency> frequencies = new ArrayList<>();
            for (Integer temp :uniqueSet) {
                CategoryFrequency cf = new CategoryFrequency(temp, Collections.frequency(clusters.get(i), temp));
                frequencies.add(cf);
            }
            if (frequencies.size() < 10) {
                System.out.println("Cluster number = " + i );
                for (CategoryFrequency l : frequencies) {
                    System.out.println("category = " + l.getCategory());
                    System.out.println("frequency = " + l.getFrequency());
                }
            }
            Collections.sort(frequencies);
            for (int j = 0; j < Math.min(10, frequencies.size()); j++) {
                rankingList.add(frequencies.get(i).getCategory());
            }       
            try {
                bufferedWriter.write(i + ":  " + rankingList.toString() + "\n");
                bufferedWriter.flush();
            } catch (IOException ex) {
                Logger.getLogger(ProcessFile.class.getName()).log(Level.SEVERE, null, ex);
            }
            ranking.put(i, rankingList);
        }
        try {
            bufferedWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(ProcessFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ranking;
    }
}