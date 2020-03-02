import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Global {
    public static HashMap<String, Integer> config = new HashMap<>();
    private static HashMap<String, Integer> duplicatehashmap = new HashMap<>();
    private static LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>();
    private static AtomicBoolean isRunnable=new AtomicBoolean(false);


    public static void QueueAdd(String s) {
        linkedBlockingQueue.add(s);
    }

    public static String QueueTake() {
        String string="";
        try {
            string=linkedBlockingQueue.take();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return string;
    }

    public static boolean GetState(){
        return isRunnable.get();
    }
    public static void  SetState(boolean state){
        isRunnable.set(state);
    }

    public static void Fetch() throws IOException {
        try {
            Yaml yaml = new Yaml();
            config = yaml.load(new FileInputStream(new File("/home/satvik/Downloads/multi.yaml")));
            duplicatehashmap.putAll(config);
        }catch(Exception e){e.printStackTrace();}
    }
    public static Integer IteratorGetValue(HashMap.Entry<String,Integer> iterator){
         return iterator.getValue();
    }
    public static String IteratorGetKey(HashMap.Entry<String,Integer> iterator){
        return iterator.getKey();

    }
    public static Integer DupliHashMapGet(String key){
        return duplicatehashmap.get(key);
    }
//    public static void IteratorSetValue(HashMap.Entry<String,Integer> iterator){
//        iterator.setValue();
//    }
}
