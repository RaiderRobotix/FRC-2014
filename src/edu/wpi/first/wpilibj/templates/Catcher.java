package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Solenoid;


public class Catcher {
    
    private static Catcher m_instance;
    
    private final OI m_oi;
        
    private final Solenoid m_leftNetExtended;
    private final Solenoid m_leftNetRetracted;
    private final Solenoid m_rightNetExtended;
    private final Solenoid m_rightNetRetracted;
    private final AnalogChannel m_catcherSonic;
    
    private boolean enableAutoCatcher;
    private boolean catcherIsOpen;
    
    
   private Catcher() {
      m_oi = OI.getInstance();
        
      m_leftNetExtended = new Solenoid(Constants.LEFT_SOLENOID_OUT);
      m_leftNetRetracted = new Solenoid(Constants.LEFT_SOLENOID_IN);
      m_rightNetExtended = new Solenoid(Constants.RIGHT_SOLENOID_OUT);
      m_rightNetRetracted = new Solenoid(Constants.RIGHT_SOLENOID_IN);
      
      m_catcherSonic = new AnalogChannel(Constants.CATCHER_SONIC);

      enableAutoCatcher = true; 
      catcherIsOpen = false;
    }
    
    public static Catcher getInstance() {
        if (m_instance == null) {
            m_instance = new Catcher();
        }
        return m_instance;
    }
    
    public void enableTeleopControls() {

        /* 
        * Enable/Disable Auto-Catcher
        */
        if(m_oi.getRightButton(4)) {
            enableAutoCatcher = true;
        }
        else if(m_oi.getRightButton(5)) {
            enableAutoCatcher = false;
        }
        
        /*
        * Auto Catcher Logic
        */
        // catch the ball automaticaly
        if(catcherIsOpen == true && enableAutoCatcher) {
            System.out.println("Auto Catcher is enabled and catcher is open");
            if(m_catcherSonic.getValue() <= 80) {
                catcherIsOpen = false;
                System.out.println("Sonic is less than 45");
            }
            else {
                catcherIsOpen = true;
            }
        }
        //if auto catcher is not enabled, use default controls
        else {
            if (m_oi.getRightTrigger()) {
                catcherIsOpen = true;
            } else if(m_oi.getRightButton(2)) {
                catcherIsOpen = false;
            }
        }
        
        // Actually close or open the catcher based on flag
        if(catcherIsOpen) {
            openLeftCatcher();
            openRightCatcher();
        } 
        else if(!catcherIsOpen) {
            closeLeftCatcher();
            closeRightCatcher();
        }
               
        System.out.println(m_catcherSonic.getValue());
    }
    
    public void closeLeftCatcher() {
        m_leftNetExtended.set(true);
        m_leftNetRetracted.set(false);
    }
    
    public void openLeftCatcher() {
        m_leftNetExtended.set(false);
        m_leftNetRetracted.set(true);
        
    }
     
    public void closeRightCatcher() {
        m_rightNetExtended.set(true);
        m_rightNetRetracted.set(false);
    }
    
    public void openRightCatcher() {
        m_rightNetExtended.set(false);
        m_rightNetRetracted.set(true);
    }
    
    
}

