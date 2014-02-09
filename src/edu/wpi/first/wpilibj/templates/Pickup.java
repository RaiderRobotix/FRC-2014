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
    private final Talon m_pickupTalonLeft;
    private final Talon m_pickupTalonRight;
    private final Solenoid m_kicker;
  
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
    
    public void openKicker() {
        m_kicker.set(true);
    }
    
    public void closeKicker() {
        m_kicker.set(false);
    }
    
    
    public void enableTeleopControls(){
        if(m_oi.getOperatorButton(3)){
            openPickup();
        }
        if(m_oi.getOperatorButton(2)){
            closePickup();
        }
        
        if(m_oi.getOperatorButton(7))
        {
            m_pickupTalonRight.set(1);
            m_pickupTalonLeft.set(1);
        }
        else if(m_oi.getOperatorButton(6)){
    
            m_pickupTalonRight.set(-1);
            m_pickupTalonLeft.set(-1);        
        }
        else {
            m_pickupTalonRight.set(0.0);
            m_pickupTalonLeft.set(0.0);
        }
        
        if(m_oi.getOperatorTrigger()) {
            openKicker();
        }
        else {
            closeKicker();
        }
    }
}
