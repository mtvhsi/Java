package org.example.chapter_6;

// Интерфейс для представления населенных пунктов
public interface Settlements {
    void showInfo();
    void electMayor(String newMayor);
    void modifyPopulation(int change);
    void editBudget(double newBudget);
    void changeStatus(boolean quarantine);
    void introduceLaw(String law);
}
