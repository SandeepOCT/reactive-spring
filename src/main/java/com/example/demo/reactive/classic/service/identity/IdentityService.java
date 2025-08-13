package com.example.demo.reactive.classic.service.identity;

import com.example.demo.reactive.classic.modal.User;
import java.util.List;

public interface IdentityService {

  /**
   * Finds identities per page.
   *
   * @return a number of identities.
   */
  Integer findIdentitiesCount();

  /**
   * Finds identities per page.
   *
   * @param page the page number to retrieve.
   * @return a list of identities for the specified page.
   */
  List<User> findIdentitiesPerPage(Integer page, Integer totalPages);

  /**
   * Finds all identities.
   *
   * @return a list of all identities.
   */
  List<User> findIdentities();
}
