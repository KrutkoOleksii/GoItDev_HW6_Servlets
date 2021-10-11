package ua.goit;

public class NoEntityException extends RuntimeException{
    public NoEntityException(String message) {
        super(message);
    }
}