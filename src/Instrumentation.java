import java.io.IOException;
import java.util.*;
import java.text.SimpleDateFormat;
import java.nio.file.*;
import java.nio.charset.*;

public class Instrumentation{
    // Declaring global variables
    private Boolean isActive;
    private Stack<Long> startTimes = new Stack<Long>();
    private String buffer;

    private static Instrumentation instance = new Instrumentation();

    // Creating a constructor
    private Instrumentation(){
        isActive=false;
        buffer="";
    }

    public static Instrumentation getInstance(){
        return instance;
    }
    /***********************************************/
    
    // Start the timer
    public void startTiming(String comment){
        if(!isActive){
            return;
        }
        long start = System.nanoTime();
        buffer += indentation()+"STARTTIMING: "+ comment+"\n";
        startTimes.push(start);
    }
    // End the timer 
    public void stopTiming(String comment){
        if(!isActive){
            return;
        }
        long elapsedTime = (System.nanoTime()-startTimes.pop())/1000000;
        buffer+=indentation()+"STOPTIMING: " + comment +" "+elapsedTime+"ms\n";
    }

    // Adds a comment output
    public void comment (String comment){
        if(!isActive){
            return;
        }
        
        buffer+=indentation()+"COMMENT: "+comment+"\n";
    }

    // Dump the info into a .log file
    public void dump (String filename){
        if(!isActive){
            return;
        }
        Path path;
        if (filename == null){
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("ddyyMMhhmmss");
            String strDate=formatter.format(date);
            path = Paths.get("instrumentation"+strDate+".log");
        }
        else{
            path = Paths.get(filename);
        }
        List<String> lines = Arrays.asList(buffer.split("\n"));

        try{
            Files.write(path, lines, Charset.forName("UTF-8"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    // Activates the program, only run at the beginning
    public void activate (Boolean onoff){
        this.isActive = onoff;
    }

    private String indentation(){
        String indent ="";
        for(int i=0;i<startTimes.size();i++){
            indent +="|  ";
        }
        return indent;
    }
}