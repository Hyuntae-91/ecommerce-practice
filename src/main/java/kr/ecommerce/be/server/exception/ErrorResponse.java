package kr.ecommerce.be.server.exception;

public record ErrorResponse (
        int code,
        String message
) {}
