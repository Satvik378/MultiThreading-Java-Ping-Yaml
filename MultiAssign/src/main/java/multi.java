import org.w3c.dom.ls.LSOutput;
import org.yaml.snakeyaml.Yaml;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.io.FileInputStream;
import java.io.*;
import java.net.*;
class pinging{
    public void run(){

    }
}
public class multi {
    static HashMap<String, Integer> hm = new HashMap<>();
    static Queue<String> q=new LinkedList<>();
    static int counter=0;
    public static void main(String[] args) throws IOException {

        Yaml yaml = new Yaml();
        hm = yaml.load(new FileInputStream(new File("/home/satvik/Downloads/multi.yaml")));

//        for (HashMap.Entry<String,Integer> jk : hm.entrySet()) {
//            System.out.println(jk.getKey() +" " +jk.getValue());
//        }
            //anonymous class for counting main thread

            TimerTask timerTask = new TimerTask() {
                public void run() {
//                    System.out.println("TimerTask executing counter is: " + counter);
                    counter++;
                }
            };
//        new thread for operations.
        Thread t1=new Thread(new Runnable(){
            public void run(){
                while(true){
                    if(counter==1000){
                        timerTask.cancel();
                        break;
                    }
                    for (HashMap.Entry<String,Integer> jk : hm.entrySet()) {
                        if(jk.getValue()==counter){
//                            System.out.println(jk.getKey()+" "+jk.getValue());
                            q.add(jk.getKey());
                            jk.setValue(jk.getValue()*2);
                        }
                    }
//                    System.out.println(q);
                }
            }

            });

     Thread t2=new Thread(new Runnable(){
           public void run(){
            while(true){
               System.out.println("Hi");
            try{
              for(int i=0;i<q.size();i++){
                   InetAddress geek = InetAddress.getByName(q.poll());
                   System.out.println("Sending Ping Request to " + q.poll());
                    if (geek.isReachable(5000))
                        System.out.println("Host is reachable");
                    else
                       System.out.println("Sorry ! We can't reach to this host");
                }

            }catch(Exception e){System.out.println(e);}
            }
            }
            });


        Timer timer = new Timer("timmy");//create a new Timer
        timer.schedule(timerTask,0,1);//delay is starting time and period is time between successive tasks.
        t1.start();
        t2.start();



    }
}
