package com.victor;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TechnicalTestApplicationTests {

@Test
void testTrue() {
  assertThat(true).isTrue();
}

}
