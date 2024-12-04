package org.example.chapter_6;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class VillageTest {
    private Village village;
    @Before
    public void setUp() {
        village = new Village("Село Лужники", 150, 10000.0, "Иван Петров");
    }

    @Test
    public void testShowInfo() {
        village.showInfo();
    }
    @Test
    public void testElectMayor() {
        village.electMayor("Мария Иванова");
        assertEquals("Мария Иванова", village.getMayor());
    }
    @Test
    public void testModifyPopulation_Increase() {
        village.modifyPopulation(30);
        assertEquals(180, village.getPopulation());
    }




    @Test
    public void testModifyPopulation_Decrease() {
        village.modifyPopulation(-20);
        assertEquals(130, village.getPopulation());
    }
    @Test
    public void testModifyPopulation_NegativeResult() {
        village.modifyPopulation(-200);
        assertEquals(150, village.getPopulation());
    }
    @Test
    public void testEditBudget() {
        village.editBudget(12000.0);
        assertEquals(12000.0, village.getBudget(), 0.001);
    }
    @Test
    public void testIntroduceLaw() {
        village.introduceLaw("Закон о поддержке фермеров");
    }
    @Test
    public void testChangeStatus() {
        village.changeStatus(true);
    }
}
