package Tracker;

public class StartUI {

    public static void main(String[] args) {
        Tracker tracker = new Tracker();
        tracker.findAll();
        System.out.println(tracker.findById(1));
    }
}
