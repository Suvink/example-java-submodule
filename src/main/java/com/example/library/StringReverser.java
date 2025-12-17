package com.example.library;

import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

/**
 * Custom mediator class for reversing strings in Apache Synapse.
 * This class is designed to be used in WSO2 integration scenarios.
 */
public class StringReverser extends AbstractMediator {
    
    private String input;
    
    /**
     * Mediates the message by reversing the input string and storing it
     * in the message context as 'reversedString'.
     * 
     * @param messageContext the message context
     * @return true if mediation is successful
     */
    public boolean mediate(MessageContext messageContext) {
        if (input == null || input.isEmpty()) {
            messageContext.setProperty("reversedString", "");
            return true;
        }
        
        // Reverse the input string using StringBuilder
        String reversed = new StringBuilder(input).reverse().toString();
        
        // Set the reversed string in the message context
        messageContext.setProperty("reversedString", reversed);
        
        return true;
    }
    
    /**
     * Setter for the input property.
     * This will be called by Synapse when the mediator is configured.
     * 
     * @param input the string to be reversed
     */
    public void setInput(String input) {
        this.input = input;
    }
    
    /**
     * Getter for the input property.
     * 
     * @return the input string
     */
    public String getInput() {
        return input;
    }
}
