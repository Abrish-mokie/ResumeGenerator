package com.example.pdfgenerator.User.DTO;

public record RequestUserDTO(
        String name,
        String title,
        String email,
        String phoneNumber,
        String address,
        String linkedIn,
        String website
) {
}
