package Tracker;/*
Класс Tracker.Tracker.Item описывает модель заявления.
Поле id - это уникальный номер заявления.
Поле name - это содержит название заявления.
создаем их приватные и создаем на них аксессоры.
 */

public class Item {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
