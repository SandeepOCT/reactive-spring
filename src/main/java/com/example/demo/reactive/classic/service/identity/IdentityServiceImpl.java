package com.example.demo.reactive.classic.service.identity;

import static com.example.demo.reactive.Constants.PAGE_SIZE;
import static com.example.demo.reactive.Util.calculateSkipNumber;
import static com.example.demo.reactive.Util.calculateTotalPages;

import com.example.demo.reactive.classic.modal.User;
import com.example.demo.reactive.classic.service.identity.modal.IdentityResponse;
import com.example.demo.reactive.classic.service.identity.modal.PageInfo;
import java.util.List;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class IdentityServiceImpl implements IdentityService {

  private final WebClient webClient;

  public IdentityServiceImpl(WebClient.Builder webClientBuilder) {
    this.webClient = webClientBuilder.baseUrl("https://dummyjson.com").build();
  }

  @Override
  public Integer findIdentitiesCount() {
    return webClient
        .get()
        .uri("/users")
        .retrieve()
        .bodyToMono(PageInfo.class)
        .map(PageInfo::total)
        .block();
  }

  @Override
  public List<User> findIdentitiesPerPage(Integer page, Integer totalPages) {
    log.info("Fetching page {} of {}", page, totalPages);

    return webClient
        .get()
        .uri(
            // Build the URI with pagination parameters
            uriBuilder ->
                uriBuilder
                    .path("/users")
                    .queryParam("skip", calculateSkipNumber(page, totalPages))
                    .queryParam("limit", PAGE_SIZE)
                    .build())
        .retrieve()
        .bodyToMono(IdentityResponse.class)
        .map(IdentityResponse::users)
        .block();
  }

  @Override
  public List<User> findIdentities() {
    Integer totalCount = findIdentitiesCount();
    int totalPages = calculateTotalPages(totalCount); // Calculate total pages

    return Stream.iterate(0, page -> page < totalPages, page -> page + 1)
        .parallel()
        .flatMap(page -> findIdentitiesPerPage(page, totalPages).stream())
        .toList();
  }
}
