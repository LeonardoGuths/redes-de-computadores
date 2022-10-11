package stopclient;

import java.time.Duration;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

public class Cronometro extends Thread{
    
    private Instant end;
    private Instant start;
    private Integer targetTime;
    private Duration timeElapsed;
    private JLabel tempo;
    private boolean status = true;
    private long seconds;
    private long minutes;
    private long actual;
    
    
    public Cronometro (JLabel tempo, Integer targetTime)
    {
        this.tempo = tempo;
        this.targetTime = targetTime;
        
    }
    
    @Override
    public void run()
    {
        start = Instant.now();
        while(status)
        {
            try {
                end = Instant.now();
                
                timeElapsed = Duration.between(start, end);
                actual = targetTime - timeElapsed.getSeconds();
                seconds = actual % 60;
                minutes = actual / 60;
                
                if(actual>targetTime)
                	tempo.setText("--:--");
                else
                	tempo.setText(String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
                
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Cronometro.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public long getSeconds() {
        return seconds;
    }
    
    public long getTempo()
    {
        return timeElapsed.getSeconds();
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

    
