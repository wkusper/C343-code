import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
@Test
    void multiply() {
    assertEquals(10, Calculator.multiply(2, 5));
    assertEquals(-4, Calculator.multiply(-2, 2));
    assertEquals(0, Calculator.multiply(1000000, 0));
}
}