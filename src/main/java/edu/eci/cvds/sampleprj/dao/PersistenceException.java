package edu.eci.cvds.sampleprj.dao;

public class PersistenceException extends Exception{
    private static final long serialVersionUID = 1L;

    public PersistenceException(String message, Exception e) {
        super(message+e.toString());
    }
}
