Формулировка задания:

1. ✓ Создать классы Покупатель (Person) и Продукт (Product).
2. ✓ Характеристики Покупателя: имя, сумма денег и пакет с продуктами(массив объектов типа Продукт).
3. ✓ Имя не может быть пустой строкой.
4. ✓ Деньги не могут быть отрицательным числом.
5. ✓ Если Покупатель может позволить себе Продукт, то Продукт добавляется в пакет.
6. ✓ Если у Покупателя недостаточно денег, то добавление не происходит.
7. ✓ Характеристики Продукта: название и стоимость.
8. ✓ Название продукта не может быть пустой строкой, оно должно быть.
9. ✓ Стоимость продукта не может быть отрицательным числом.
10. ✓ Поля в классах должны быть private, доступ к полям осуществляется через геттеры и сеттеры или конструктор класса.
11. ✓ В классах переопределены методы toString(), equals(), hashcode().
12. ✓ Создать в классе App метод main и проверить работу приложения.
13. ✓ Данные Покупателей и Продукты вводятся с клавиатуры или задаются случайным образом.
14. ✓ Продукты в цикле выбираются покупателями по очереди и, пока не введено слово END, наполняется пакет.
15. ✓ Обработать следующие ситуации:
16. ✓ а. Если покупатель не может позволить себе продукт, то напечатайте соответствующее сообщение ("[Имя   человека] не
    может позволить себе [Название продукта]").
17. ✓ б. Если ничего не куплено, выведите имя человека, за которым следует "Ничего не куплено".
18. ✓ в. В случае неверного ввода (сообщение об исключении: "Деньги не могут быть отрицательными") или пустого имени: (
    сообщение об исключении: "Имя не может быть пустым").
