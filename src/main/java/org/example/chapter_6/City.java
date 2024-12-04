package org.example.chapter_6;

// город
class City implements Settlements {
    private String country;
    private int yearFounded;
    private int population;
    private double area; // в квадратных километрах
    private String location;
    private double budget;
    private String mayor;
    private String language;
    private boolean quarantineStatus;
    public City(String country, int yearFounded, int population, double area, String location, double budget, String mayor, String language) {
        this.country = country;
        this.yearFounded = yearFounded;
        this.population = population;
        this.area = area;
        this.location = location;
        this.budget = budget;
        this.mayor = mayor;
        this.language = language;
        this.quarantineStatus = false;
    }



    @Override
    public void showInfo() {
        System.out.println("Основная информация о городе:");
        System.out.println("Страна:" + country);
        System.out.println("Год основания:  " + yearFounded);
        System.out.println("население: " + population);
        System.out.println("Площадь:" + area + " км²");
        System.out.println("Расположение: " + location);
        System.out.println("Бюджет: " + budget);
        System.out.println("Действующий мэр: " + mayor);
        System.out.println("Язык общения: " + language);
        System.out.println("Статус карантина: " + (quarantineStatus ? "На карантине" : "Не на карантине"));
    }

    @Override
    public void electMayor(String newMayor) {
        this.mayor = newMayor;
        System.out.println("Новый мэр города: " + mayor);
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
        this.quarantineStatus = quarantine;
        System.out.println("Статус города изменен на: " + (quarantineStatus ? "На карантине" : "Не на карантине"));
    }
    @Override
    public void introduceLaw(String law) {
        System.out.println("Введен новый закон: " + law);
    }

    //геттеры и сеттеры
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public int getYearFounded() {
        return yearFounded;
    }

    public void setYearFounded(int yearFounded) {
        this.yearFounded = yearFounded;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isQuarantineStatus() {
        return quarantineStatus;
    }

    public void setQuarantineStatus(boolean quarantineStatus) {
        this.quarantineStatus = quarantineStatus;
    }
}
