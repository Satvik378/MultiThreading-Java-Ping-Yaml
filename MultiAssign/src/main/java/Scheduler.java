import java.util.HashMap;

public class Scheduler implements Runnable {
    public void run(){
        try{
        while(!Global.GetState()){
                try{
                Thread.sleep(10);
                for (HashMap.Entry<String,Integer> iterator : Global.config.entrySet()) {
                    if (Global.IteratorGetValue(iterator) == 0) {
                        Global.QueueAdd(Global.IteratorGetKey(iterator));
                        iterator.setValue(Global.DupliHashMapGet(Global.IteratorGetKey(iterator)));
                    }
                    if (Global.IteratorGetValue(iterator) > 0) {
                        iterator.setValue(Global.IteratorGetValue(iterator) - 10);
                    }

                }
                }catch(Exception e){e.printStackTrace();}
            }
        }catch(Exception e){
            Global.SetState(true);
        }
        finally {
            if(Global.GetState())
            {
                Global.SetState(false);
                run();
            }


        }


    }
}
