package com.example.demo.reactive;

import static com.example.demo.reactive.Constants.*;

public class Util {

  public static Integer calculateTotalPages(Integer totalCount) {
    Integer pages = (totalCount + PAGE_SIZE - 1) / PAGE_SIZE;

    return pages * LOAD;
  }

  public static Integer calculateSkipNumber(Integer pageNumber, Integer totalPages) {
    return (pageNumber % totalPages) * PAGE_SIZE;
  }

  public static <T> void delay(T t) {
    try {
      Thread.sleep(DELAY);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt(); // Restore interrupted status
    }
  }
}
