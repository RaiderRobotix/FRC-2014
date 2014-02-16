package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;


 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 25-DS
 */
public class Pickup {
    private static Pickup m_instance;
    private final Solenoid m_pickupExtended;
    private final Solenoid m_pickupRetracted;
    private static Talon m_pickupLeft;
    private static Talon m_pickupRight;
    private static Solenoid m_kicker;
    
    // Always close pickup by default
    boolean m_isOpen = false;
  
    private Pickup () {
        m_pickupExtended = new Solenoid(Constants.PICKUP_SOLENOID_OUT);
        m_pickupRetracted = new Solenoid(Constants.PICKUP_SOLENOID_IN);
        m_pickupLeft = new Talon(Constants.LEFT_PICKUP_PWM);
        m_pickupRight = new Talon(Constants.RIGHT_PICKUP_PWM);
        m_kicker = new Solenoid(Constants.KICKER_SOLENOID);
    }

    public static Pickup getInstance() {
        if (m_instance == null) {
            m_instance = new Pickup();
        }
        return m_instance;
    }
    
    public void closePickup(){
        m_pickupExtended.set(false); 
        m_pickupRetracted.set(true);
        m_isOpen = false;
    }
    
    public void openPickup(){
        m_pickupExtended.set(true); 
        m_pickupRetracted.set(false);
        m_isOpen = true;
    }
    
    public void openKicker() {
        m_kicker.set(true);
    }
    
    public void closeKicker() {
        m_kicker.set(false);
    }
    
    public void runIntakeWheelsOut() {
        m_pickupRight.set(-1.0);
        m_pickupLeft.set(-1.0);     
    }
    
    public void runIntakeWheelsIn() {
        m_pickupRight.set(1.0);
        m_pickupLeft.set(1.0);
    }
    
    public void stopIntakeWheels() {
        m_pickupRight.set(0.0);
        m_pickupLeft.set(0.0);
    }
    
    public boolean isOpen() {
        return m_isOpen;
    }
}
