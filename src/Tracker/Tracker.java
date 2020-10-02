package Tracker;/*
Создаем класс Трекер,поле ids используется для генерации нового ключа. В нашем примере мы используем последовательность.
То есть каждый вызов метод add будет добавлять в поле ids единицу. Так мы сможем обеспечить уникальность поле id в Tracker.Tracker.Item.
Аналогичный подход используется в базах данных.

Поле private Tracker.Tracker.Item[] items = new Tracker.Tracker.Item[100] содержит возможное количество заявлений.
Оно у нас ограничено сотней позиций.

В классе Tracker.Tracker должны быть методы:
добавление заявок - public Tracker.Tracker.Item add(Tracker.Tracker.Item item);
получение списка всех заявок - public Tracker.Tracker.Item[] findAll();
получение списка по имени - public Tracker.Tracker.Item[] findByName(String key);
получение заявки по id - public Tracker.Tracker.Item findById(int id);
 */


import java.util.Arrays;

public class Tracker {
    private final Item[] items = new Item[100];
    private int ids = 1;
    private int size = 0;

/*
Метод public Tracker.Tracker.Item add(Tracker.Tracker.Item item) добавляет заявку, переданную в аргументах в массив заявок items.
В методе add нужно проставить уникальный ключ в объект Tracker.Tracker.Item item. Это нужно сделать через метод setId.
           item.setId(ids++);
Поле ids используется для генерации нового ключа. В нашем примере мы используем последовательность.
То есть каждый вызов метод add будет добавлять в поле ids единицу. Так мы сможем обеспечить уникальность поле id в Tracker.Tracker.Item.
Аналогичный подход используется в базах данных.
 */

    public Item add(Item item) {
        item.setId(ids++);
        items[size++] = item;
        return item;
    }

/*
Получение списка всех заявок.
Метод public Tracker.Tracker.Item[] findAll() возвращает копию массива items без null элементов (без пустых ячеек).
Перебирать все 100 элементов не нужно. У нас есть поле size.
Это поле и есть размер нового массива.
Чтобы получить новый массив, нужно использовать метод Arrays.copyOf. Этот метод принимает два параметра:
массив элементов и новый размер.
 */
    public Item[] findAll() {
        return Arrays.copyOf(items, size);
    }

/*
Получение списка по имени.
Метод public Tracker.Tracker.Item[] findByName(String key) проверяет в цикле все элементы массива items,
сравнивая name (используя метод getName класса Tracker.Tracker.Item) с аргументом метода String key.
Элементы, у которых совпадает name, копирует в результирующий массив и возвращает его.
Алгоритм этого метода аналогичен методу findAll.
 */

    public Item[]findByName(String key) {
        Item[] copyItems = new Item[size];
        int copySize = 0;
        for (int index = 0; index < size; index++) {
            Item item = this.items[index];
            if (item.getName().equals(key)) {
                copyItems[copySize++] = item;
            }
        }
        return Arrays.copyOf(copyItems, copySize);
    }



/*
Получение заявки по id.
Метод public Tracker.Tracker.Item findById(int id) проверяет в цикле все элементы массива items, сравнивая id с аргументом int id
и возвращает найденный Tracker.Tracker.Item. Если Tracker.Tracker.Item не найден - возвращает null.
 */
    public Item findById(int id) {
        // находим индекс
        int index = indexOf(id);
        //если индекс найден возвращаем item, иначе null
        return index != - 1 ? items[index] : null;
        // таким образом избавились от копирования кода и получили удобный метод для поиска индекса заявки по id.
    }

/*
Создадим метод который будет возвращать index по id.
Метод объявлен как private, потому что он используется только внутри системы.
 */

    private int indexOf(int id) {
        int rsl = -1;
        for (int index = 0; index < size; index++) {
            if (items[index].getId() == id) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }

/*
Метод indexOf нужно использовать в методе replace.

1. Найти индекс ячейки по id.
2. Проставить id с item. При замене нужно сохранять старый id.
3. Записать в ячейку с найденным индекс объект item. Это входящий параметр.
4. Вернуть true, если замена произведена или false, если index по id не найден.
 */
    public boolean replace(int id, Item item) {
        int index = indexOf(id);
        boolean rsl = index != -1;
        if (index != -1) {
            items[index] = item;
            item.setId(id);
            rsl = true;
        }
        return rsl;
    }

/*
Метод удаления заявки
 */

    public boolean delete(int id) {
        int index = indexOf(id);
        boolean rsl = index != -1;
        if (index != -1) {
            int start = index + 1;
            int distPos = index;
            int length = size - index;
            System.arraycopy(items, start, items, distPos, length);
            items[size - 1] = null;
            size--;
            return true;
        }
        return rsl;
    }
}
