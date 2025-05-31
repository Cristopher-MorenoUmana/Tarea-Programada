package util;

import java.io.Serializable;
import java.util.HashMap;

public class Response implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private char success; //S(si) N(no)
    private String message;
    private String internalMessage;
    private HashMap<String, Object> data;
    
    public Response(){
        this.data = new HashMap<>();
    }
    
    public Response(char pSucces, String pMessage, String pInternalMessage){
        
        this.success = pSucces;
        this.message = pMessage;
        this.internalMessage = pInternalMessage;
        this.data = new HashMap<>();
    }
    
    public Response(char pSucces, String pMessage, String pInternalMessage, String pName, Object pValue) {
        this.success = pSucces;
        this.message = pMessage;
        this.internalMessage = pInternalMessage;
        this.data = new HashMap<>();
        this.data.put(pName, pValue);
    }
    
    public char getSuccess(){
        return this.success;
    }
    public void setSucces(char pSuccess){
        this.success = pSuccess;
    }
    
    public String getMessage(){
        return this.message;
    }
    public void setMessage(String pMessage){
        this.message = pMessage;
    }
    
    public String getInternalMessage(){
        return this.internalMessage;
    }
    public void setInternalMessage(String pInternalMessage){
        this.internalMessage = pInternalMessage;
    }
    
    public Object getData(String pNombre){
        return this.data.get(pNombre);
    }
    
    public void setData(String pNombre, Object pData){
        this.data.put(pNombre, pData);
    }
}