package com.example.demo.reactive.reactive.service.identity;

import static com.example.demo.reactive.Constants.PAGE_SIZE;
import static com.example.demo.reactive.Util.calculateSkipNumber;
import static com.example.demo.reactive.Util.calculateTotalPages;

import com.example.demo.reactive.reactive.modal.User;
import com.example.demo.reactive.reactive.service.identity.modal.IdentityResponse;
import com.example.demo.reactive.reactive.service.identity.modal.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@Slf4j
public class IdentityServiceReactiveImpl implements IdentityServiceReactive {

  private final WebClient webClient;

  public IdentityServiceReactiveImpl(WebClient.Builder webClientBuilder) {
    this.webClient = webClientBuilder.baseUrl("https://dummyjson.com").build();
  }

  @Override
  public Mono<Integer> findIdentitiesCount() {
    return webClient.get().uri("/users").retrieve().bodyToMono(PageInfo.class).map(PageInfo::total);
  }

  @Override
  public Flux<User> findIdentitiesPerPage(Integer page, Integer totalPages) {
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
        .flatMapMany(users -> Flux.fromStream(users.stream()));
  }

  @Override
  public Flux<User> findIdentities() {
    Mono<Integer> totalCountMono = findIdentitiesCount();

    // Wait for the total count to be available
    return totalCountMono.flatMapMany(
        totalCount -> {
          int totalPages = calculateTotalPages(totalCount); // Calculate total pages

          return Flux.range(0, totalPages)
              .parallel(5) // Process pages in parallel
              .runOn(Schedulers.boundedElastic()) // Use bounded elastic scheduler for blocking I/O
              .flatMap(page -> findIdentitiesPerPage(page, totalPages));
        });
  }
}
