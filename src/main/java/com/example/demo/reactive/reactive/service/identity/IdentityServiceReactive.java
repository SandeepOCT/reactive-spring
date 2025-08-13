package com.example.demo.reactive.reactive.service.identity;

import com.example.demo.reactive.reactive.modal.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IdentityServiceReactive {

  /**
   * Finds identities per page.
   *
   * @return a number of identities.
   */
  Mono<Integer> findIdentitiesCount();

  /**
   * Finds identities per page.
   *
   * @param page the page number to retrieve.
   * @return a list of identities for the specified page.
   */
  Flux<User> findIdentitiesPerPage(Integer page, Integer totalPages);

  /**
   * Finds all identities.
   *
   * @return a list of all identities.
   */
  Flux<User> findIdentities();
}
