package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

public class OI
{
    private static OI m_instance;
    
    private final Joystick m_leftStick;
    private final Joystick m_rightStick;    
    private final Joystick m_operatorStick;
    
    private final AutonController m_autonController;
    private final Drivebase m_drivebase;
    private final Pickup m_pickup;
    private final Catcher m_catcher;
    
    private boolean m_spitOutSequenceActive;
    private final Timer m_pickupTimer;
        
    private OI()
    {
      m_leftStick = new Joystick(Constants.LEFT_JOYSTICK_PORT);
      m_rightStick = new Joystick(Constants.RIGHT_JOYSTICK_PORT);
      m_operatorStick = new Joystick(Constants.OPERATOR_JOYSTICK_PORT);
      
      m_autonController = AutonController.getInstance();
      m_drivebase = Drivebase.getInstance();
      m_pickup = Pickup.getInstance();
      m_catcher = Catcher.getInstance();
      
      m_pickupTimer = new Timer();
      m_spitOutSequenceActive = false;
    }
    
    public static OI getInstance() {
        if (m_instance == null) {
            m_instance = new OI();
        }
        return m_instance;
    }
    
    public void enableTeleopControls() {
        
        // TODO: Add button for driver to disable compressor for 5 seconds.
        
        // ---------- RESET AUTON ----------
        if (getLeftButton(7)) {
            m_autonController.reset();
        }
        
        // ---------- DRIVE TRAIN ----------
        
        // Brake Code
        if (getLeftTrigger()) {
            m_drivebase.brakesOn();
        }
        else if (getLeftButton(2)) {
            m_drivebase.brakesOff();
        }
        
        if (!m_drivebase.brakesAreOn()) {
            m_drivebase.brakesOff();
            m_drivebase.setSpeed(getLeftY(), getRightY());
        }
        else {
            m_drivebase.setSpeed(0.0);
        }       
        
        // Encoders
        if (getRightButton(2)) {
            m_drivebase.resetEncoders();
        }
        
        // ---------- CATCHER ----------
        
        // Enable/Disable Auto-Catcher
        //if(m_catcher.getRightButton(4)) {
        //    enableAutoCatcher();
        //}
        //else if(getRightButton(5)) {
            m_catcher.disableAutoCatcher();
        //}
        
        // Auto Catcher Logic
        if(m_catcher.isOpen() && m_catcher.autoCatcherEnabled()) {
            System.out.println("Auto Catcher is enabled and catcher is open");
            if(m_catcher.getUltrasonicValue() <= 80) {
                m_catcher.closeCatcher();
                System.out.println("Sonic is less than 45");
            }
        }
        // If auto catcher is not enabled, use default controls
        else {
            if (getRightTrigger()) {
                m_catcher.openCatcher();
            } else if (getRightButton(2)) {
                m_catcher.closeCatcher();
            }
        }
        
        // ---------- INTAKE ----------
        
        // TODO: Add a method that once the catcher closes, run the wheels for one second.

        if (getOperatorButton(3)) {
            m_pickup.openPickup();
        }
        else if (getOperatorButton(2)) {
            m_pickup.closePickup();
        }
        
        // Kicker Logic
        /*if (m_pickup.isOpen()) {
            if(getOperatorTrigger()) {
                m_pickup.openKicker();
            }
            else {
                m_pickup.closeKicker();
            }
        } else {
            
        }*/
        
        if (getOperatorTrigger()) {
            m_spitOutSequenceActive = true;
            m_pickupTimer.start();
            m_pickupTimer.reset();
        }
        
        if (m_spitOutSequenceActive) {
            
            m_pickup.openPickup();
            
            if (m_pickupTimer.get() > 0.2 && m_pickupTimer.get() <= 1.0) {
                m_pickup.runIntakeWheelsOut();
                m_pickup.openKicker();
            } else if (m_pickupTimer.get() > 1.0) {
                m_pickup.closeKicker();
                m_spitOutSequenceActive = false;
                m_pickupTimer.stop();
            }
        }
        else if (getOperatorButton(7)) {
            m_pickup.runIntakeWheelsIn();
        }
        else if (getOperatorButton(6)){
            m_pickup.runIntakeWheelsOut();      
        }
        else {
            m_pickup.stopIntakeWheels();
        }
    }
    
    public double getLeftY() {
        double yval = m_leftStick.getY();
        if (yval > -0.02 && yval < 0.02) {
            return 0.0;
        }
        else {
            return yval;
        }
    }
    
    public double getRightY() {
        double yval = m_rightStick.getY();
        if (yval > -0.02 && yval < 0.02) {
            return 0.0;
        }
        else {
            return yval;
        }
    }    
    
    public boolean getLeftTrigger(){
        return m_leftStick.getTrigger();
    }
    
    public boolean getRightTrigger(){
        return m_rightStick.getTrigger();
    }
    
    public boolean getLeftButton(int button){
        return m_leftStick.getRawButton(button);
    }
    
    public boolean getRightButton(int button){
        return m_rightStick.getRawButton(button);
    }
    
    public boolean getOperatorButton(int button) {
        return m_operatorStick.getRawButton(button);
    }
    
    public boolean getOperatorTrigger() {
        return m_operatorStick.getTrigger();
    }
}