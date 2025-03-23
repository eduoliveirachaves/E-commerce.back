package com.edu.ecommerce.utils;

public class Response {
    
    private final String message;
    
    private final Object data;
    
    public Response(String message, Object data) {
        this.message = message;
        this.data = data;
    }
    
    public Response(String message) {
        this(message, null);
    }
    
    @Override
    public String toString() {
        return "Response{" +
                "message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
    
}
