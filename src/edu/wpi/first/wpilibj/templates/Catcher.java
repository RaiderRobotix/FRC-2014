package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Solenoid;

public class Catcher {
    
    private static Catcher m_instance;
    
    private final Solenoid m_leftNetExtended;
    private final Solenoid m_leftNetRetracted;
    private final Solenoid m_rightNetExtended;
    private final Solenoid m_rightNetRetracted;
    
    private final AnalogChannel m_catcherSonic;
    
    private boolean m_autoCatcherEnabled;
    private boolean m_isOpen;
    
    
   private Catcher() {
      m_leftNetExtended = new Solenoid(Constants.LEFT_SOLENOID_OUT);
      m_leftNetRetracted = new Solenoid(Constants.LEFT_SOLENOID_IN);
      m_rightNetExtended = new Solenoid(Constants.RIGHT_SOLENOID_OUT);
      m_rightNetRetracted = new Solenoid(Constants.RIGHT_SOLENOID_IN);
      
      m_catcherSonic = new AnalogChannel(Constants.CATCHER_SONIC);

      m_autoCatcherEnabled = true; 
      m_isOpen = false;
    }
    
    public static Catcher getInstance() {
        if (m_instance == null) {
            m_instance = new Catcher();
        }
        return m_instance;
    }
    
    public void closeLeftCatcher() {
        m_leftNetExtended.set(false);
        m_leftNetRetracted.set(true);
    }
    
    public void openLeftCatcher() {
        m_leftNetExtended.set(true);
        m_leftNetRetracted.set(false);
    }
     
    public void closeRightCatcher() {
        m_rightNetExtended.set(false);
        m_rightNetRetracted.set(true);
    }

    public void openRightCatcher() {
        m_rightNetExtended.set(true);
        m_rightNetRetracted.set(false);
    }
    
    public void openCatcher() {
        openLeftCatcher();
        openRightCatcher();
        m_isOpen = true;
    }
    
    public void closeCatcher() {
        closeLeftCatcher();
        closeRightCatcher();
        m_isOpen = false;
    }
    
    public boolean isOpen() {
        return m_isOpen;
    }
    
    public boolean autoCatcherEnabled() {
        return m_autoCatcherEnabled;
    }
    
    public void enableAutoCatcher() {
        m_autoCatcherEnabled = true;
    }
    
    public void disableAutoCatcher() {
        m_autoCatcherEnabled = false;
    }
    
    public int getUltrasonicValue() {
        return m_catcherSonic.getValue();
    }
}

