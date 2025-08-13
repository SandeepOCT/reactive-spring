package com.example.demo.reactive.classic.modal;

public record User(
    Integer id,
    String firstName,
    String lastName,
    String maidenName,
    Integer age,
    String gender,
    String email,
    String phone,
    String username,
    String password,
    String birthDate,
    String image,
    String bloodGroup,
    Double height,
    Double weight,
    String eyeColor,
    Hair hair,
    String ip,
    Address address,
    String macAddress,
    String university,
    Bank bank,
    Company company,
    String ein,
    String ssn,
    String userAgent,
    Crypto crypto,
    String role) {

  public record Hair(String color, String type) {}

  public record Address(
      String address,
      String city,
      String state,
      String stateCode,
      String postalCode,
      Coordinates coordinates,
      String country) {}

  public record Coordinates(Double lat, Double lng) {}

  public record Bank(
      String cardExpire, String cardNumber, String cardType, String currency, String iban) {}

  public record Company(String department, String name, String title, Address address) {}

  public record Crypto(String coin, String wallet, String network) {}

  public String getFullName() {
    return String.format("%s %s", firstName, lastName);
  }
}
