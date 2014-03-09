package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

public class Drivebase {
    
    private static Drivebase m_instance;
    
    private final Talon m_leftDrive1;
    private final Talon m_leftDrive2;
    
    private final Talon m_rightDrive1;
    private final Talon m_rightDrive2;
    
    private final Servo m_leftBrake;
    private final Servo m_rightBrake;
    
    private boolean m_brakesOn;
    
    private final Encoder m_leftEncoder;
    private final Encoder m_rightEncoder;
    
    private final PIDController m_leftEncoderController1;
    private final PIDController m_leftEncoderController2;
    private final PIDController m_rightEncoderController1;
    private final PIDController m_rightEncoderController2;
    
    private boolean m_isDrivingForward;
    private boolean m_isDrivingStraight;
    
    private Gyro m_gyro;
    
    private Timer m_timer;
    
    private Drivebase(){
        
        m_leftDrive1 = new Talon(Constants.LEFT_DRIVE_PWM1);
        m_leftDrive2 = new Talon(Constants.LEFT_DRIVE_PWM2);
        
        m_rightDrive1 = new Talon(Constants.RIGHT_DRIVE_PWM1);
        m_rightDrive2 = new Talon(Constants.RIGHT_DRIVE_PWM2);
        
        m_leftBrake = new Servo(Constants.LEFT_BRAKE_PWM);
        m_rightBrake = new Servo(Constants.RIGHT_BRAKE_PWM);
        m_brakesOn = false;
        
        m_leftEncoder = new Encoder(Constants.LEFT_ENCODER_A, Constants.LEFT_ENCODER_B, true);
        m_rightEncoder = new Encoder(Constants.RIGHT_ENCODER_A, Constants.RIGHT_ENCODER_B);
        
        m_leftEncoder.setDistancePerPulse(Constants.INCHES_PER_COUNT);
        m_rightEncoder.setDistancePerPulse(Constants.INCHES_PER_COUNT);
        
        m_leftEncoder.start();
        m_rightEncoder.start();
        
        m_leftEncoderController1 = new PIDController(Constants.LEFT_ENCODER_P,
                                                     Constants.LEFT_ENCODER_I, 
                                                     Constants.LEFT_ENCODER_D,
                                                     m_leftEncoder, m_leftDrive1);
        m_leftEncoderController2 = new PIDController(Constants.LEFT_ENCODER_P,
                                                     Constants.LEFT_ENCODER_I, 
                                                     Constants.LEFT_ENCODER_D,
                                                     m_leftEncoder, m_leftDrive2);
        
        m_rightEncoderController1 = new PIDController(Constants.RIGHT_ENCODER_P,
                                                      Constants.RIGHT_ENCODER_I, 
                                                      Constants.RIGHT_ENCODER_D,
                                                      m_rightEncoder, m_rightDrive1);
        
        m_rightEncoderController2 = new PIDController(Constants.RIGHT_ENCODER_P,
                                                      Constants.RIGHT_ENCODER_I, 
                                                      Constants.RIGHT_ENCODER_D,
                                                      m_rightEncoder, m_rightDrive2);
        
        m_leftEncoder.setPIDSourceParameter(PIDSource.PIDSourceParameter.kDistance);
        m_rightEncoder.setPIDSourceParameter(PIDSource.PIDSourceParameter.kDistance);
        
        m_isDrivingForward = false;
        m_isDrivingStraight = false;
        
        m_gyro = new Gyro(Constants.GYRO_CHANNEL);
        m_gyro.setSensitivity(Constants.GYRO_SENSITIVITY);
        
        m_timer = new Timer();      
    }
    
    public static Drivebase getInstance() {
        if (m_instance == null) {
            m_instance = new Drivebase(); 
        }
        return m_instance;
    }
    
    public void setSpeed(double speed){
        m_leftDrive1.set(speed);
        m_leftDrive2.set(speed);
        
        m_rightDrive1.set(speed);
        m_rightDrive2.set(speed);
    }
    
    public void setSpeed(double leftSpeed, double rightSpeed){
        m_leftDrive1.set(leftSpeed);
        m_leftDrive2.set(leftSpeed);
        
        m_rightDrive1.set(rightSpeed);
        m_rightDrive2.set(rightSpeed);
    }
    
    public void setRightSpeed(double speed) {
        m_rightDrive1.set(speed);
        m_rightDrive2.set(speed);
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
    
    public boolean brakesAreOn() {
        return m_brakesOn;
    }
    
    public double getLeftEncoderDistance() {
        return m_leftEncoder.getDistance();
    }
    
    public double getRightEncoderDistance() {
        return m_rightEncoder.getDistance();
    }
    
    public void resetEncoders() {
        m_leftEncoder.reset();
        m_rightEncoder.reset();
    }
    
    public void setEncoderSetpoint(double inches) {
        m_leftEncoderController1.setSetpoint(inches);
        m_leftEncoderController2.setSetpoint(inches);
        m_rightEncoderController1.setSetpoint(inches);
        m_rightEncoderController2.setSetpoint(inches);
    }
    
    public void enableEncoderPid() {
        m_leftEncoderController1.enable();
        m_leftEncoderController2.enable();
        m_rightEncoderController1.enable();
        m_rightEncoderController2.enable();
    }
    
    public void disableEncoderPid() {
        m_leftEncoderController1.disable();
        m_leftEncoderController2.disable();
        m_rightEncoderController1.disable();
        m_rightEncoderController2.disable();
    }
    
    public double getLeftEncoderPIDValue() {
        return m_leftEncoderController1.get();
    }
    
    public double getRightEncoderPIDValue() {
        return m_rightEncoderController1.get();
    }
    
    public void setEncoderPIDOutputRange(double minimumOutput, double maximumOutput) {
        m_leftEncoderController1.setOutputRange(minimumOutput, maximumOutput);
        m_leftEncoderController2.setOutputRange(minimumOutput, maximumOutput);
        m_rightEncoderController1.setOutputRange(minimumOutput, maximumOutput);
        m_rightEncoderController2.setOutputRange(minimumOutput, maximumOutput);
    }
    
    public boolean driveForward(double inches, double tolerance) {
        
        if (!m_isDrivingForward) {
            resetEncoders();
            setEncoderSetpoint(inches);
            enableEncoderPid();
            m_isDrivingForward = true;
        }
        
        if (m_isDrivingForward) {
            boolean leftOnTarget = Math.abs(inches - getLeftEncoderDistance()) < tolerance;
            boolean rightOnTarget = Math.abs(inches - getRightEncoderDistance()) < tolerance;
            if (leftOnTarget || rightOnTarget) {
                disableEncoderPid();
                m_isDrivingForward = false;
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * 
     * @param setpoint In Inches
     * @param tolerance
     * @param finalTolerance - A tolerance distance in inches to stop adjusting at based on the gyro error
     * @param p
     * @return 
     */
    public boolean driveStraight(double setpoint, double tolerance, double finalTolerance) {
        if (!m_isDrivingStraight)  {
            resetEncoders();
            setEncoderSetpoint(setpoint);
            enableEncoderPid();
            
            m_gyro.reset();
            m_isDrivingStraight = true;
        }
        
        if (m_isDrivingStraight) {
            double angleError = m_gyro.getAngle();
            double leftDistance = m_leftEncoder.getDistance();
            double rightDistance = m_rightEncoder.getDistance();
            
            double adjustment = (angleError * Constants.GYRO_P);
          
            if (Math.abs(adjustment) > 0.25) {
                adjustment = (adjustment < 0) ? -0.25 : 0.25;
            }
            
            // NOTE: FINAL TOLERANCE NEEDS TO BE THE SAME SIGN AS SETPOINT
            if (leftDistance < setpoint - finalTolerance || rightDistance < setpoint - finalTolerance) {
                adjustment = 0;
            }

            double leftSpeed = m_leftDrive1.get() + adjustment;
            double rightSpeed = m_rightDrive1.get() - adjustment;
            
            if (leftSpeed > 1.0) {
                leftSpeed = 1.0;
            } else if (leftSpeed < -1.0) {
                leftSpeed = -1.0;
            }

            if (rightSpeed > 1.0) {
                rightSpeed = 1.0;
            } else if (rightSpeed < -1.0) {
                rightSpeed = -1.0;
            }
            
            setSpeed(leftSpeed, rightSpeed);
            
            if (setpoint > 0) {
                if (leftDistance >= setpoint || rightDistance >= setpoint) {
                    disableEncoderPid();
                    m_isDrivingStraight = false;
                    return true;
                }
            } else {
                if (leftDistance <= setpoint || rightDistance <= setpoint ) {
                    disableEncoderPid();
                    m_isDrivingStraight = false;
                    return true;
                }
            }
        }
        
        return false;
    }
    
    void resetGyro() {
        m_gyro.reset();
    }
    
    double getGyroAngle() {
        return m_gyro.getAngle();
    }
}