package com.cecilectomy.dmge.Screens;

/**
 * DeadMarsLibrary ScreenState Enumeration
 * 
 * @author Daniel Cecil
 */
public enum ScreenState {
    /*
     * Used to signal a screen is transitioning on.
     */
    TransitionOn,
    
    /*
     * Used to signal a screen is currently active.
     */
    Active,
    
    /*
     * Used to signal a screen is transitioning off.
     */
    TransitionOff,
    
    /*
     * Used to signal a screen is currently hidden.
     */
    Hidden,
}
