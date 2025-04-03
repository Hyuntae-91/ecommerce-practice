package kr.ecommerce.be.server.dto;

public record ErrorResponse (
        int code,
        String message
) {}
