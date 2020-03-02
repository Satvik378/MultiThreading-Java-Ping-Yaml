import java.io.*;
import java.net.*;
public class CollectorService implements Runnable {
    private BufferedReader input;
    private BufferedReader Error;

    public void run() {

        try {
        while (!Global.GetState()) {

                String ip = Global.QueueTake();
                System.out.println("Pinging " + ip);
                Ping(ip);
            }

        }catch (Exception e) {
            Global.SetState(true);
        }
    }

    public void Ping(String ip) throws UnknownHostException, IOException {
        try {
            String[] command = {"/bin/bash", "-c", "ping -i 2 -W 2 -t 2 -c 3 " + ip};
            ProcessBuilder build = new ProcessBuilder(command);
            Process process = build.start();
            // to read the output
            input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            Error = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            String Ipaddress = null;
            System.out.println("Standard output: ");
            while ((Ipaddress = input.readLine()) != null) {
                System.out.println(Ipaddress);
            }
            if ((Ipaddress = Error.readLine()) != null) {
                while ((Ipaddress = Error.readLine()) != null) {
                    System.out.println(Ipaddress);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            Error.close();
            input.close();
        }

    }

}
