package com.Kelly.TrackByDaylight;

public class RequestNotFoundException extends RuntimeException{
    public RequestNotFoundException (String message){
        super(message);
    }
}
