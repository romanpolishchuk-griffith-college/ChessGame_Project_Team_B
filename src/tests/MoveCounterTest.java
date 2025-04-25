package tests;

import griffith.MoveCounter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoveCounterTest {

    private MoveCounter counter;

    @BeforeEach
    public void setUp() {
        counter = new MoveCounter();
    }

    @Test
    void testInitialValueIsZero(){
        assertEquals(0, counter.getCount());
    }

    @Test
    void testIncrement() {
        counter.increment();
        assertEquals(1, counter.getCount());
    }

    @Test
    void testReset() {
        counter.increment();
        counter.increment();
        counter.reset();
        assertEquals(0, counter.getCount());
    }
}
