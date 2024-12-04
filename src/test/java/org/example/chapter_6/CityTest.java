package org.example.chapter_6;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CityTest {
    private City city;

    @Before
    public void setUp() {
        city = new City("Россия", 1703, 5000000, 600.0, "Санкт-Петербург", 1000000.0, "Александр", "Русский");
    }



    @Test
    public void testShowInfo() {


        city.showInfo();
    }

    @Test
    public void testElectMayor() {
        city.electMayor("Елена");
        assertEquals("Елена", city.getMayor());
    }

    @Test
    public void testModifyPopulation_Increase() {
        city.modifyPopulation(50000);
        assertEquals(5050000, city.getPopulation());
    }

    @Test
    public void testModifyPopulation_Decrease() {
        city.modifyPopulation(-100000);
        assertEquals(4900000, city.getPopulation());
    }

    @Test
    public void testModifyPopulation_NegativeResult() {
        city.modifyPopulation(-6000000);
        assertEquals(5000000, city.getPopulation());
    }

    @Test
    public void testEditBudget() {
        city.editBudget(1200000.0);
        assertEquals(1200000.0, city.getBudget(), 0.001);
    }

    @Test
    public void testChangeStatus() {
        city.changeStatus(true);
        assertTrue(city.isQuarantineStatus());

        city.changeStatus(false);
        assertFalse(city.isQuarantineStatus());
    }

    @Test
    public void testIntroduceLaw() {
        city.introduceLaw("Закон о защите окружающей среды");

    }
}
