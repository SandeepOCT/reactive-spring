package com.example.demo.reactive.grpc;

import com.example.demo.reactive.reactive.modal.User;

/**
 * Mapper utility to convert between domain User and gRPC UserResponse.
 */
public class UserMapper {

  public static UserResponse toUserResponse(User user) {
    UserResponse.Builder builder = UserResponse.newBuilder()
        .setId(user.id() != null ? user.id() : 0)
        .setFirstName(user.firstName() != null ? user.firstName() : "")
        .setLastName(user.lastName() != null ? user.lastName() : "")
        .setMaidenName(user.maidenName() != null ? user.maidenName() : "")
        .setAge(user.age() != null ? user.age() : 0)
        .setGender(user.gender() != null ? user.gender() : "")
        .setEmail(user.email() != null ? user.email() : "")
        .setPhone(user.phone() != null ? user.phone() : "")
        .setUsername(user.username() != null ? user.username() : "")
        .setBirthDate(user.birthDate() != null ? user.birthDate() : "")
        .setImage(user.image() != null ? user.image() : "")
        .setBloodGroup(user.bloodGroup() != null ? user.bloodGroup() : "")
        .setHeight(user.height() != null ? user.height() : 0.0)
        .setWeight(user.weight() != null ? user.weight() : 0.0)
        .setEyeColor(user.eyeColor() != null ? user.eyeColor() : "")
        .setIp(user.ip() != null ? user.ip() : "")
        .setMacAddress(user.macAddress() != null ? user.macAddress() : "")
        .setUniversity(user.university() != null ? user.university() : "")
        .setEin(user.ein() != null ? user.ein() : "")
        .setSsn(user.ssn() != null ? user.ssn() : "")
        .setUserAgent(user.userAgent() != null ? user.userAgent() : "")
        .setRole(user.role() != null ? user.role() : "");

    // Map hair
    if (user.hair() != null) {
      Hair hair = Hair.newBuilder()
          .setColor(user.hair().color() != null ? user.hair().color() : "")
          .setType(user.hair().type() != null ? user.hair().type() : "")
          .build();
      builder.setHair(hair);
    }

    // Map address
    if (user.address() != null) {
      Address.Builder addressBuilder = Address.newBuilder()
          .setAddress(user.address().address() != null ? user.address().address() : "")
          .setCity(user.address().city() != null ? user.address().city() : "")
          .setState(user.address().state() != null ? user.address().state() : "")
          .setStateCode(user.address().stateCode() != null ? user.address().stateCode() : "")
          .setPostalCode(user.address().postalCode() != null ? user.address().postalCode() : "")
          .setCountry(user.address().country() != null ? user.address().country() : "");

      if (user.address().coordinates() != null) {
        Coordinates coordinates = Coordinates.newBuilder()
            .setLat(user.address().coordinates().lat() != null ? user.address().coordinates().lat() : 0.0)
            .setLng(user.address().coordinates().lng() != null ? user.address().coordinates().lng() : 0.0)
            .build();
        addressBuilder.setCoordinates(coordinates);
      }

      builder.setAddress(addressBuilder.build());
    }

    // Map bank
    if (user.bank() != null) {
      Bank bank = Bank.newBuilder()
          .setCardExpire(user.bank().cardExpire() != null ? user.bank().cardExpire() : "")
          .setCardNumber(user.bank().cardNumber() != null ? user.bank().cardNumber() : "")
          .setCardType(user.bank().cardType() != null ? user.bank().cardType() : "")
          .setCurrency(user.bank().currency() != null ? user.bank().currency() : "")
          .setIban(user.bank().iban() != null ? user.bank().iban() : "")
          .build();
      builder.setBank(bank);
    }

    // Map company
    if (user.company() != null) {
      Company.Builder companyBuilder = Company.newBuilder()
          .setDepartment(user.company().department() != null ? user.company().department() : "")
          .setName(user.company().name() != null ? user.company().name() : "")
          .setTitle(user.company().title() != null ? user.company().title() : "");

      if (user.company().address() != null) {
        Address.Builder companyAddressBuilder = Address.newBuilder()
            .setAddress(user.company().address().address() != null ? user.company().address().address() : "")
            .setCity(user.company().address().city() != null ? user.company().address().city() : "")
            .setState(user.company().address().state() != null ? user.company().address().state() : "")
            .setStateCode(user.company().address().stateCode() != null ? user.company().address().stateCode() : "")
            .setPostalCode(user.company().address().postalCode() != null ? user.company().address().postalCode() : "")
            .setCountry(user.company().address().country() != null ? user.company().address().country() : "");

        if (user.company().address().coordinates() != null) {
          Coordinates coordinates = Coordinates.newBuilder()
              .setLat(user.company().address().coordinates().lat() != null ? user.company().address().coordinates().lat() : 0.0)
              .setLng(user.company().address().coordinates().lng() != null ? user.company().address().coordinates().lng() : 0.0)
              .build();
          companyAddressBuilder.setCoordinates(coordinates);
        }

        companyBuilder.setAddress(companyAddressBuilder.build());
      }

      builder.setCompany(companyBuilder.build());
    }

    // Map crypto
    if (user.crypto() != null) {
      Crypto crypto = Crypto.newBuilder()
          .setCoin(user.crypto().coin() != null ? user.crypto().coin() : "")
          .setWallet(user.crypto().wallet() != null ? user.crypto().wallet() : "")
          .setNetwork(user.crypto().network() != null ? user.crypto().network() : "")
          .build();
      builder.setCrypto(crypto);
    }

    return builder.build();
  }
}