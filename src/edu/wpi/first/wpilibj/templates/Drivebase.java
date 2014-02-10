package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Talon;


public class Drivebase {
    
    private static Drivebase m_instance;
    
    private final OI m_oi;
    
    private final Talon m_leftDrive1;
    private final Talon m_leftDrive2;
    private final Talon m_leftDrive3;
    
    private final Talon m_rightDrive1;
    private final Talon m_rightDrive2;
    private final Talon m_rightDrive3;
    
    private final Servo m_leftBrake;
    private final Servo m_rightBrake;
    
    private boolean m_brakesOn;
        
    private Drivebase(){
        
        m_oi = OI.getInstance();
        
        m_leftDrive1 = new Talon(Constants.LEFT_DRIVE_PWM1);
        m_leftDrive2 = new Talon(Constants.LEFT_DRIVE_PWM2);
        m_leftDrive3 = new Talon(Constants.LEFT_DRIVE_PWM3);
        
        m_rightDrive1 = new Talon(Constants.RIGHT_DRIVE_PWM1);
        m_rightDrive2 = new Talon(Constants.RIGHT_DRIVE_PWM2);
        m_rightDrive3 = new Talon(Constants.RIGHT_DRIVE_PWM3);
        
        m_leftBrake = new Servo(Constants.LEFT_BRAKE_PWM);
        m_rightBrake = new Servo(Constants.RIGHT_BRAKE_PWM);
        m_brakesOn = false;
    }
    
    public static Drivebase getInstance() {
        if (m_instance == null) {
            m_instance = new Drivebase(); 
        }
        return m_instance;
    }
    
    public void setSpeed(double leftSpeed, double rightSpeed){
        m_leftDrive1.set(leftSpeed);
        m_leftDrive2.set(leftSpeed);
        m_leftDrive3.set(leftSpeed);
        
        m_rightDrive1.set(rightSpeed);
        m_rightDrive2.set(rightSpeed);
        m_rightDrive3.set(rightSpeed);
    }
    
    public void enableTeleopControls(){         
        if (m_oi.getLeftTrigger()) {
            brakesOn();
        } else if (m_oi.getLeftButton(2)) { // Right top button
            brakesOff();
        }
        
        if (!m_brakesOn) {
            brakesOff();
            setSpeed(m_oi.getLeftY(), m_oi.getRightY());
        } else {
            setSpeed(0.0, 0.0);
        }       
    }
    
    public void brakesOn() {
        m_leftBrake.set(0.6);
        m_rightBrake.set(0.37);
        m_brakesOn = true;
    }
    
    public void brakesOff() {
        m_leftBrake.set(0.4);
        m_rightBrake.set(0.57);
        m_brakesOn = false;
    }
}