package ua.hillel.rest;

public class APIException extends Throwable {
    public APIException( String string) {
        super(string);
    }
}
