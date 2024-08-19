Формулировка задания:
1. ✓ Предусмотреть функциональный интерфейс:
   interface ByCondition {
   boolean isOk(int number);
   }
   В функциональном интерфейсе обязательно проставить аннотацию.
2. ✓ Создать класс Sequence для последовательности со следующим
   методом:
   public static int[] filter(int[] array, ByCondition condition) {
   ...
   }
   Данный метод возвращает массив, который содержит элементы,
   удовлетворяющие логическому выражению в condition.
3. ✓ В main в качестве condition подставить:
- проверку на четность элемента
- проверку, является ли сумма цифр элемента четным числом.


   Дополнительная задача:
1. ✓ Изучите структуру Optional.
2. ✓ Реализуйте generic-класс Pair, похожий на Optional, но содержащий
   пару элементов разных типов и не запрещающий элементам принимать
   значение null.
3. ✓ Реализуйте методы getFirst(), getSecond(), equals() и hashCode(), а также
   статический фабричный метод of(). Конструктор должен быть закрытым
   (private).
4. ✓ С корректно реализованным классом Pair должен компилироваться и
   успешно работать следующий код:
   Pair<Integer, String> pair = Pair.of(1, "hello");
   Integer i = pair.getFirst(); // 1
   String s = pair.getSecond(); // "hello"
   Pair<Integer, String> pair2 = Pair.of(1, "hello");
   boolean mustBeTrue = pair.equals(pair2); // true!
   boolean mustAlsoBeTrue = pair.hashCode() == pair2.hashCode(); // true!