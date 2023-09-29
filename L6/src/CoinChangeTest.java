import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoinChangeTest {

    @Test
    void minChange() {
        assertEquals(0, CoinChange.minChange(0));
        assertEquals(1, CoinChange.minChange(1));
        assertEquals(1, CoinChange.minChange(5));
        assertEquals(2, CoinChange.minChange(6));
        assertEquals(3, CoinChange.minChange(7));
        assertEquals(1, CoinChange.minChange(10));
        assertEquals(2, CoinChange.minChange(11));
        assertEquals(2, CoinChange.minChange(15));
        assertEquals(2, CoinChange.minChange(20));
        assertEquals(1, CoinChange.minChange(25));
    }
}