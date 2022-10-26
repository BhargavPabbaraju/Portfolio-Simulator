package models;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class UserImplTest {
  @Test
  public void testUserCreation() throws IOException {
    User user1 = new UserImpl("user3");
  }
}