package org.example.chapter_6;

//////деревня
class Village implements Settlements {
    private String name;
    private int population;
    private double budget;
    private String mayor;
    public Village(String name, int population, double budget, String mayor) {
        this.name = name;
        this.population = population;
        this.budget = budget;
        this.mayor = mayor;
    }

    @Override
    public void showInfo() {
        System.out.println("Основная информация о деревне:");
        System.out.println("Название: " + name);
        System.out.println("Население: " + population);
        System.out.println("Бюджет: " + budget);
        System.out.println("Действующий мэр: " + mayor);
    }

    @Override
    public void electMayor(String newMayor) {
        this.mayor = newMayor;
        System.out.println("Новый мэр деревни: " + mayor);
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
        System.out.println("Новый бюджет: " + budget);
    }
    @Override
    public void changeStatus(boolean quarantine) {
        System.out.println("Деревня не имеет статуса карантина.");
    }

    @Override
    public void introduceLaw(String law) {
        System.out.println("Введен новый закон для деревни: " + law);
    }




    ///////геттеры и сеттеры
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

    public String getMayor() {
        return mayor;
    }
    public void setMayor(String mayor) {
        this.mayor = mayor;
    }
}
