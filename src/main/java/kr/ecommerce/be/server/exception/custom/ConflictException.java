package kr.ecommerce.be.server.exception.custom;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}