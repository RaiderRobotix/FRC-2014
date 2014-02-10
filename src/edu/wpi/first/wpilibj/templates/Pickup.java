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
    private final OI m_oi;
    private final Solenoid m_pickupExtended;
    private final Solenoid m_pickupRetracted;
    private static Talon m_pickupTalonLeft;
    private static Talon m_pickupTalonRight;
    private static Solenoid m_kicker;
    
    // Always close pickup by default
    boolean PickUpIsOpen = false;
  
    private Pickup () {
        m_oi = OI.getInstance();
        m_pickupExtended= new Solenoid(Constants.PICKUP_SOLENOID_OUT);
        m_pickupRetracted= new Solenoid(Constants.PICKUP_SOLENOID_IN);
        m_pickupTalonLeft= new Talon(Constants.LEFT_PICKUP_PWM);
        m_pickupTalonRight= new Talon(Constants.RIGHT_PICKUP_PWM);
        m_kicker = new Solenoid(Constants.KICKER_SOLENOID);
    }
    public static Pickup getInstance() {
        if (m_instance == null) {
            m_instance = new Pickup();
        }
        return m_instance;
    }
    
    public void closePickup(){
        m_pickupExtended.set(true); 
        m_pickupRetracted.set(false);
    }
    
    public void openPickup(){
        m_pickupExtended.set(false); 
        m_pickupRetracted.set(true);    
    }
    
    public static void openKicker() {
        m_kicker.set(true);
    }
    
    public static void closeKicker() {
        m_kicker.set(false);
    }
    
    public static void runIntakeWheelsOut() {
        m_pickupTalonRight.set(-1);
        m_pickupTalonLeft.set(-1);     
    }
    
    public static void runIntakeWheelsIn() {
        m_pickupTalonRight.set(1);
        m_pickupTalonLeft.set(1);
    }
    
    public static void stopIntakeWheels() {
        m_pickupTalonRight.set(0.0);
        m_pickupTalonLeft.set(0.0);
    }
    
    public void enableTeleopControls(){
        
        /* TODO
        *  
        *  Task 1: Add a method that once the catcher closes, run the wheels for one second.
        */
        
        /*
        *   Pickup Open / close logic
        */
        
        // Set flag to open the pickup
        if (m_oi.getOperatorButton(3)) {
            PickUpIsOpen = true;
        }
        // Set flag to close the pickup
        else if (m_oi.getOperatorButton(2)) {
            PickUpIsOpen = false;
        }
        
        // Actually open the pickup
        if(PickUpIsOpen) {
            openPickup();
        }
        // Actually close the pickup
        else if(!PickUpIsOpen){
            closePickup();
        }
        
        /*
        * Kicker Logic
        */
        
        if(PickUpIsOpen) {
            // If pickup is open, we can kick
            if(m_oi.getOperatorTrigger()) {
                openKicker();
            }
            // Keep kicker closed by default
            else {
                closeKicker();
            }
        }
        
        /*
        * Pickup Wheels Logic
        */
        if(m_oi.getOperatorButton(7))
        {
            runIntakeWheelsIn();
        }
        else if(m_oi.getOperatorButton(6)){
    
            runIntakeWheelsOut();      
        }
        else
        {stopIntakeWheels();}
        
    }
}