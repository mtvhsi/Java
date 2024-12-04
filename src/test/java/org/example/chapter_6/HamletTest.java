package org.example.chapter_6;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class HamletTest {
    private Hamlet hamlet;
    @Before
    public void setUp() {
        hamlet = new Hamlet("Петровка", 100, 5000.0);
    }

    @Test
    public void testShowInfo() {
        hamlet.showInfo();
    }

    @Test
    public void testElectMayor() {
        hamlet.electMayor("Иван");
    }

    @Test
    public void testModifyPopulation_Increase() {
        hamlet.modifyPopulation(50);
        assertEquals(150, hamlet.getPopulation());
    }

    @Test
    public void testModifyPopulation_Decrease() {
        hamlet.modifyPopulation(-20);
        assertEquals(80, hamlet.getPopulation());
    }

    @Test
    public void testModifyPopulation_NegativeResult() {
        hamlet.modifyPopulation(-200);
        assertEquals(100, hamlet.getPopulation());
    }

    @Test
    public void testEditBudget() {
        hamlet.editBudget(6000.0);
        assertEquals(6000.0, hamlet.getBudget(), 0.001);
    }

    @Test
    public void testIntroduceLaw() {
        hamlet.introduceLaw("Закон о благоустройстве");
    }
}
