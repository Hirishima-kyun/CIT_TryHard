import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

public class MyArrayListTest {
    MyArrayList list;

    @BeforeEach
    public void setUp() {
        list = new MyArrayList();
    }

    @Test
    public void testAdd() {
        assertDoesNotThrow(() -> {
            list.add(1);
            list.add(2);
            list.add(3);
        });
        assertEquals(3, list.size());
        assertEquals(1, list.get(1));
        assertEquals(2, list.get(2));
        assertEquals(3, list.get(3));
    }
    @Test
    public void testFullOnAdd() {
        assertDoesNotThrow(() -> {
            list.add(1);
            list.add(2);
            list.add(3);
            list.add(4);
            list.add(5);
        });
        assertThrows(ArrayFullException.class, () -> list.add(6));
    }

    @Test
    public void testAddAt1() {
        assertDoesNotThrow(() -> {
            list.add(1);
            list.add(2);
            list.addAt(3,2);
            list.addAt(4,2);
        });
        assertEquals(4, list.size());
        assertEquals(1, list.get(1));
        assertEquals(4, list.get(2));
        assertEquals(3, list.get(3));
        assertEquals(2, list.get(4));
    }

    @Test
    public void testAddAt2() {
        assertDoesNotThrow(() -> {
            list.add(1);
            list.add(2);
            list.addAt(3,1);
            list.addAt(4,4);
        });
        assertEquals(4, list.size());
        assertEquals(3, list.get(1));
        assertEquals(1, list.get(2));
        assertEquals(2, list.get(3));
        assertEquals(4, list.get(4));
    }

    @Test
    public void testAddAt3() {
        assertDoesNotThrow(() -> {
            list.addAt(3,1);
            list.addAt(4,2);
            list.add(1);
            list.add(2);
        });
        assertEquals(4, list.size());
        assertEquals(3, list.get(1));
        assertEquals(4, list.get(2));
        assertEquals(1, list.get(3));
        assertEquals(2, list.get(4));
    }

    @Test
    public void testFullOnAddAt() {
        assertDoesNotThrow(() -> {
            list.add(1);
            list.add(2);
            list.addAt(3,1);
            list.addAt(4,2);
            list.addAt(5,1);
        });
        assertThrows(ArrayFullException.class, () -> list.addAt(6, 3));
    }

    @Test
    public void testRemove1() {
        assertDoesNotThrow(() -> {
            list.add(1);
            list.add(2);
            list.add(3);
        });
        assertTrue(list.remove(1));
        assertFalse(list.remove(1));
        assertEquals(2, list.size());
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    public void testRemove2() {
        assertDoesNotThrow(() -> {
            list.add(1);
            list.add(2);
            list.add(3);
            list.add(4);
            list.add(5);
        });
        assertTrue(list.remove(3));
        assertDoesNotThrow(() -> {
            list.add(6);
        });
        assertEquals(5, list.size());
        assertEquals(1, list.get(1));
        assertEquals(2, list.get(2));
        assertEquals(4, list.get(3));
        assertEquals(5, list.get(4));
        assertEquals(6, list.get(5));
    }
    @Test
    public void testRemove3() {
        assertDoesNotThrow(() -> {
            list.add(1);
            list.add(2);
            list.add(3);
            list.add(4);
            list.add(5);
        });
        assertTrue(list.remove(3));
        assertDoesNotThrow(() -> {
            list.addAt(6, 5);
        });
        assertEquals(5, list.size());
        assertEquals(1, list.get(1));
        assertEquals(2, list.get(2));
        assertEquals(4, list.get(3));
        assertEquals(5, list.get(4));
        assertEquals(6, list.get(5));
    }
    @Test
    public void testRemoveAt1() {
        assertDoesNotThrow(() -> {
            list.add(1);
            list.add(2);
            list.add(3);
        });
        assertEquals(1, list.removeAt(1));
        assertEquals(2, list.removeAt(1));
        assertEquals(1, list.size());
        assertEquals(3, list.get(1));
        assertThrows(InvalidPositionException.class, () -> list.get(2));
    }

    @Test
    public void testRemoveAt2() {
        assertDoesNotThrow(() -> {
            list.add(1);
            list.add(2);
            list.add(3);
            list.add(4);
            list.add(5);
        });
        assertEquals(3, list.removeAt(3));
        assertDoesNotThrow(() -> {
            list.add(6);
        });
        assertEquals(5, list.size());
        assertEquals(1, list.get(1));
        assertEquals(2, list.get(2));
        assertEquals(4, list.get(3));
        assertEquals(5, list.get(4));
        assertEquals(6, list.get(5));
    }
    @Test
    public void testRemoveAt3() {
        assertDoesNotThrow(() -> {
            list.add(1);
            list.add(2);
            list.add(3);
            list.add(4);
            list.add(5);
        });
        assertEquals(3, list.removeAt(3));
        assertDoesNotThrow(() -> {
            list.addAt(6, 5);
        });
        assertEquals(5, list.size());
        assertEquals(1, list.get(1));
        assertEquals(2, list.get(2));
        assertEquals(4, list.get(3));
        assertEquals(5, list.get(4));
        assertEquals(6, list.get(5));
    }

    @Test
    public void testContains() {
        assertDoesNotThrow(() -> {
            list.add(1);
            list.add(2);
            list.add(3);
        });
        assertTrue(list.contains(1));
        assertTrue(list.remove(1));
        assertFalse(list.contains(1));
    }

    @Test
    public void testIsEmpty() {
        assertTrue(list.isEmpty());
        assertDoesNotThrow(() -> {
            list.add(1);
            list.add(2);
            list.add(3);
        });
        assertFalse(list.isEmpty());
        assertDoesNotThrow(() -> {
            list.removeAt(1);
            list.removeAt(2);
            list.removeAt(1);
        });
        assertTrue(list.isEmpty());
    }

    @Test
    public void testSet1() {
        assertDoesNotThrow(() -> {
            list.add(1);
            list.add(2);
            list.add(3);
        });
        assertEquals(2, list.set(2, 5));
        assertEquals(5, list.set(2, 7));
    }

    @Test
    public void testSet2() {
        assertDoesNotThrow(() -> {
            list.add(1);
            list.add(2);
            list.add(3);
        });
        assertThrows(InvalidPositionException.class, () -> list.set(4, 4));
    }

    @Test
    public void testInvalidPos1() {
        assertDoesNotThrow(() -> {
            list.add(8);
            list.add(9);
        });
        assertThrows(InvalidPositionException.class, () -> list.addAt(7, 4));
        assertThrows(InvalidPositionException.class, () -> list.addAt(7, 7));
    }

    @Test
    public void testInvalidPos2() {
        assertDoesNotThrow(() -> {
            list.add(8);
            list.add(9);
        });
        assertThrows(InvalidPositionException.class, () -> list.addAt(7, 0));
        assertThrows(InvalidPositionException.class, () -> list.addAt(7, -1));
    }

    @Test
    public void testInvalidPos3() {
        assertDoesNotThrow(() -> {
            list.add(8);
            list.add(9);
        });
        assertThrows(InvalidPositionException.class, () -> list.removeAt(0));
        assertThrows(InvalidPositionException.class, () -> list.removeAt(3));
    }

    @Test
    public void testInvalidPos4() {
        assertDoesNotThrow(() -> {
            list.add(8);
            list.add(9);
        });
        assertThrows(InvalidPositionException.class, () -> list.get(0));
        assertThrows(InvalidPositionException.class, () -> list.get(3));
    }

    @Test
    public void testArrayFullIsChecked() {
        assertTrue(Exception.class.isAssignableFrom(ArrayFullException.class));
        assertFalse(RuntimeException.class.isAssignableFrom(ArrayFullException.class));
    }

    @Test
    public void testInvalidPosIsUnchecked() {
        assertTrue(Exception.class.isAssignableFrom(InvalidPositionException.class));
        assertTrue(RuntimeException.class.isAssignableFrom(InvalidPositionException.class));
    }

    @Test
    public void testFieldsPrivate() {
        Field[] fields = MyArrayList.class.getDeclaredFields();
        for (Field f : fields) {
            int modifiers = f.getModifiers();
            assertTrue(Modifier.isPrivate(modifiers));
        }
    }
}
