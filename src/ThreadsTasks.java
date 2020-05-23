import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ThreadsTasks {
    public static void main(String[] args) throws IOException {
        HashMap<String, Integer> words = new HashMap<>();
        File file = new File("/Users/inna/java files/топ100слов/топ100слов.txt");

      //  Thread thread = new Thread(new Top100Words(words)).start();



        for (int i = 0; i < Runtime.getRuntime().availableProcessors() ; i++) {
            new Thread(new AddToMap(file, words)).start();
        }




    }
}

class AddToMap implements Runnable {
    private File file;
    HashMap<String, Integer> allWords = new HashMap<>();

    public AddToMap(File file, HashMap<String, Integer> allWords) {
        this.file = file;
        this.allWords = allWords;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        HashMap<String, Integer> countword = new HashMap<>();
        try {
            try (FileInputStream fileInput = new FileInputStream("/Users/inna/java files/топ100слов/топ100слов.txt");
                 ByteArrayOutputStream bout = new ByteArrayOutputStream()) {

                byte[] bytes = new byte[fileInput.available()];
                fileInput.read(bytes, 0 , bytes.length / Runtime.getRuntime().availableProcessors());
                bout.write(bytes, 0 , bytes.length / Runtime.getRuntime().availableProcessors());

                String str = bout.toString();
                str = str.replaceAll("[^a-zA-Zа-яА-Я]]", " ");
                System.out.println(str);
                String[] words = str.split("\\s+");
                for (String word : words) {
                    if (!countword.containsKey(word)) {
                        countword.put(word, 0);
                    }
                    countword.put(word, countword.get(word) + 1);
                }
                System.out.println(countword);
                allWords.putAll(countword);
                System.out.println(allWords);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class Top100Words implements Runnable{
    private HashMap<String, Integer> words;

    public Top100Words(HashMap<String, Integer> words) {
        this.words = words;
    }

    @Override
    public void run() {
            ArrayList<Map.Entry<String, Integer>> arr = new ArrayList<>();
            LinkedHashMap<String, Integer> linkedMap = new LinkedHashMap<>();
        ArrayList<String> maxCount = new ArrayList<>();
            for (Map.Entry<String, Integer> e : words.entrySet()) {
                arr.add(e);
            }

            Comparator<Map.Entry<String, Integer>> valueComparator = new Comparator<Map.Entry<String, Integer>>() {

                @Override
                public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                    Integer v1 = e1.getValue();
                    Integer v2 = e2.getValue();
                    return v2.compareTo(v1);
                }
            };
        Collections.sort(arr, valueComparator);

        for(Map.Entry<String, Integer> e: arr) {
            linkedMap.put(e.getKey(), e.getValue());
        }

        for(Map.Entry<String, Integer> e : linkedMap.entrySet()){
            maxCount.add(e.getKey());
        }
        List<String> maxCount1 = maxCount.subList(0, 100);
        System.out.println(maxCount);
    }
}

