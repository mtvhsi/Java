package org.example.chapter_6;

//////хутор
class Hamlet implements Settlements {
    private String name;
    private int population;
    private double budget;

    public Hamlet(String name, int population, double budget) {
        this.name = name;
        this.population = population;
        this.budget = budget;
    }

    @Override
    public void showInfo() {
        System.out.println("Основная информация о хуторе:");
        System.out.println("Название: " + name);
        System.out.println("население: " + population);
        System.out.println("Бюджет: " + budget);
    }

    @Override
    public void electMayor(String newMayor) {
        System.out.println("Хутор не имеет мэра.");
    }

    @Override
    public void modifyPopulation(int change) {
        if (this.population + change >= 0) {
            this.population += change;
            System.out.println("Население: " + population);
        } else {
            System.out.println("Население не может быть отрицательным.");
        }
    }


    @Override
    public void editBudget(double newBudget) {
        this.budget = newBudget;
        System.out.println("Новый бюджет:" + budget);
    }

    @Override
    public void changeStatus(boolean quarantine) {
        System.out.println("Хутор не имеет статуса карантина ");
    }

    @Override
    public void introduceLaw(String law) {
        System.out.println("Введен новый закон для хутора: " + law);
    }



    //геттеры и сеттеры
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }
}
