- ✓ Базовый автомобиль обладает следующими свойствами:
  маркой (строка), моделью (строка), годом выпуска (int), мощностью в лошадиных силах (int), ускорением (int),
  подвеской (int) и долговечностью (int).
- ✓ Каждый отдельный тип автомобиля дополняет эти свойства.
- ✓ Вот типы:
- ✓ • PerformanceCar – гоночный автомобиль. Имеет дополнения addOns (массив строк, по умолчанию – пустой)Увеличенная
  мощность двигателя на 50%. Уменьшенная подвеска на 25%.
- ✓ • ShowCar – спортивная машина. Looking cool there, bro.Включает поле stars (int). (по умолчанию – 0), поле для
  оценки популярности автомобиля.

- ✓ Race Гонка имеет следующие свойства: длина (int), маршрут (строка), призовой фонд (int) и участники (коллекция
  автомобилей),
- ✓ • CasualRace – обычная гонка.
- ✓ • DragRace – гонка за самый мощный двигатель. Идеальное переключение передач — залог победы.
- ✓ • DriftRace – дрифтовая гонка.
- ✓ Garage
- ✓ • Garage – место, где остаются все автомобили, когда они не участвуют в гонках. Гараж также предоставляет
  возможность
  модифицировать припаркованный автомобиль. Включает parkedCars (массив объектов типа Car).
- ✓ Каждый из представленных классов должен включать:
- ✓ • Конструктор пустой и с параметрами;
- ✓ • Переопределенный метод toString();
- ✓ • Геттеры и сеттеры для полей.
- ✓ Обратить внимание, что поля требуется сделать private;
- ✓ • У классов переопределены методы equals() и hashcode().
- ✓ Работу с классами проверить в методе main класса App.
- Дополнительно:
- Следующие доработки для исходной программы сохранить в ветке githomeworks/homework09 отдельным коммитом/коммитами:
-
    1. ✓ Дополнить модель предметной области. Создать 2 дополнительных специальных класса для гонок:
- ✓ TimeLimitRace Класс имеет дополнительное поле goldTime (int).
- ✓ CircuitRaceКласс имеет дополнительное поле laps (int).
- ✓ Оба новых класса имеют дополнительный параметр, помимо наследуемых у класса Race.
- ✓ Параметр вводится в качестве последнего параметра при открытии одного из этих типов гонок.
-
    2. ✓ Реализовать ввод параметров задачи из файла.
-
    3. Реализовать вывод результата задачи в файл.