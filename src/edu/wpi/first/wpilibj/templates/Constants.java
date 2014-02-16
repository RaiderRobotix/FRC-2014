

package edu.wpi.first.wpilibj.templates;


public class Constants {
    // port numbers in computer/digtal/analog for object initialization
    final static int LEFT_JOYSTICK_PORT = 1;
    final static int RIGHT_JOYSTICK_PORT = 2;
    final static int OPERATOR_JOYSTICK_PORT = 3;
    
    final static double JOYSTICK_DEADBAND = 0.2;
    
    // Left Drive PWM Constants
    final static int LEFT_DRIVE_PWM1 = 1;
    final static int LEFT_DRIVE_PWM2 = 2;
    
    // Right Drive PWM Constants
    final static int RIGHT_DRIVE_PWM1 = 4;
    final static int RIGHT_DRIVE_PWM2 = 5;
    
    // Brake PWM Constants
    final static int LEFT_BRAKE_PWM = 7;
    final static int RIGHT_BRAKE_PWM = 8;
    
    // Pickup Talon Constants
    final static int LEFT_PICKUP_PWM= 9;
    final static int RIGHT_PICKUP_PWM=10;
   
    // CATCHER SOLENOID
    final static int PRESSURE_SWITCH_PWM = 1;
    final static int COMPRESSOR_RELAY = 1;
    
    final static int LEFT_SOLENOID_OUT = 3;
    final static int LEFT_SOLENOID_IN = 4;
    final static int RIGHT_SOLENOID_OUT = 1;
    final static int RIGHT_SOLENOID_IN = 2
            ;
    final static int PICKUP_SOLENOID_OUT = 5;
    final static int PICKUP_SOLENOID_IN = 6;
    
    final static int KICKER_SOLENOID = 7;
    
    // ULTRASONIC SENSOR
    final static int CATCHER_SONIC = 1;
    
    // CAMERA LIGHT
    final static int CAMERA_LIGHT = 2;
    
    // ENCODERS
    final static int LEFT_ENCODER_A = 11;
    final static int LEFT_ENCODER_B = 12;
    final static int RIGHT_ENCODER_A = 13;
    final static int RIGHT_ENCODER_B = 14;
    
    final static double TIRE_CIRCUMFERENCE = 27.75; // in inches
    final static double COUNTS_PER_REVOLUTION = 128.0;
    final static double INCHES_PER_COUNT = TIRE_CIRCUMFERENCE / COUNTS_PER_REVOLUTION;
 
    final static double LEFT_ENCODER_P = 0.011;
    final static double LEFT_ENCODER_I = 0.0;
    final static double LEFT_ENCODER_D = 0.0;
    
    final static double RIGHT_ENCODER_P = 0.010;
    final static double RIGHT_ENCODER_I = 0.0;
    final static double RIGHT_ENCODER_D = 0.0;
}