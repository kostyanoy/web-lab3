package hibernate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HibernateUtilsTest {

    @Test
    void getSessionFactory() {
        var s = HibernateUtils.getSessionFactory().openSession();
        s.close();

        assertTrue(true);
    }
}