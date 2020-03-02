import org.w3c.dom.ls.LSOutput;
import org.yaml.snakeyaml.Yaml;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.io.FileInputStream;


public class BootManager{
    public static void main(String[] args)throws IOException {
        try {

           Global.Fetch();

            Scheduler scheduler = new Scheduler();
            CollectorService collectorService = new CollectorService();

            Thread t1 = new Thread(scheduler);
            Thread t2 = new Thread(collectorService);

            t1.start();
            t2.start();
        }catch(Exception e){
            e.printStackTrace();
        }


    }
}
