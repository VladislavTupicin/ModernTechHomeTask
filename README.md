# консольное приложение электронной коммерции 

| | |
|---|---|
| **Дисциплина** | Современные технологии программирования |
| **Срок сдачи** | 10 апреля 2026 г., 23:59  |
| **Состав команды** | до 4 студентов |
| **Среда разработки** | IntelliJ IDEA Community Edition |
| **Интерфейс** | только терминал (консоль); без GUI, без регистрации и входа |
| **Сдача** |Ссылка : [URL] |

---

## Цель работы

Спроектировать и реализовать **консольное** приложение магазина, демонстрирующее объектно-ориентированный дизайн на современном Java. Типы данных реализуются **с нуля**. В методе `main` создаются объекты и показываются **несколько сценариев оплаты** с использованием паттерна **«Стратегия»** для провайдеров платежей.

---

## Результаты сдачи

1. **ERD** сущности, атрибуты, связи (cardinality). Инструменты: draw.io, Lucidchart, PlantUML. Формат **PNG** или **PDF**.
2. **Исходный код**  полный проект; запуск из IntelliJ или командой `java` с указанием classpath.
3. **README.md**   описание, запуск в IntelliJ, **ФИО и роли** участников, ключевые решения, **скриншот** работы в консоли.

---

## Функциональные требования (консоль)

| Функция | Описание |
|--------|----------|
| Product catalog | List products with **category**, **price**, **stock** |
| Shopping cart | Add / remove lines; **total with VAT** |
| Order | Create order **from cart**; show order details |
| Payments | Pay through **Ozon** or **Wildberries** with a **PaymentMethod** |
| Order status | Show **status changes** (e.g. after checkout / updates) |


---

## Обязательные средства языка Java

| Средство | Где применять |
|----------|----------------|
| **Classes** | `Customer`, `Order`, `ShoppingCart` |
| **Records** | `Product`, `CartItem`, `OrderItem` |
| **Interface** | `Payment` (marketplace / provider) |
| **Sealed interface** | `PaymentMethod`  |
| **Enums** | `OrderStatus`, `ProductCategory`, `PaymentStatus` |
| **Collections** | `ArrayList` and `HashMap` used meaningfully (catalog,

---

## Структура пакетов (обязательно)

Корень: `com.moderntech.ecommerce`

```text
com/moderntech/ecommerce/
├── main/
│   └── ECommerceApp.java
├── models/
│   ├── Product.java              (record)
│   ├── Customer.java             (класс)
│   ├── ShoppingCart.java         (класс)
│   ├── Order.java                (класс)
│   ├── CartItem.java             (record)
│   └── OrderItem.java            (record)
├── payment/
│   ├── Payment.java
│   ├── PaymentMethod.java        (sealed interface + permits)
│   ├── CreditCardPayment.java
│   ├── DigitalWalletPayment.java
│   ├── CashOnDelivery.java
│   ├── OzonPayment.java
│   ├── WildberriesPayment.java
│   └── PaymentStatus.java        (enum)
└── enums/
    ├── OrderStatus.java
    └── ProductCategory.java
```

---

## Спецификация 


**Перечисления**

- `OrderStatus`: PENDING, CONFIRMED, PROCESSING, SHIPPED, DELIVERED, CANCELLED  
- `ProductCategory`: SMARTPHONE, LAPTOP, TABLET, ACCESSORY, CAMERA  
- `PaymentStatus`: PENDING, SUCCESS, FAILED, REFUNDED, PROCESSING  
---

## Требования к `ECommerceApp.main`

1. Создать каталог **4–5** товаров и вывести его.  
2. Создать **покупателя** (без экрана входа).  
3. Создать **корзину**, добавить товары, показать корзину и **итог с НДС**.  
4. Выполнить **оформление заказа** (`checkout`): вывести идентификатор заказа, строки, сумму; изменить **статус заказа** не менее одного раза (видно в выводе).  
5. Выполнить **три** платёжных сценария с выводом деталей:  
   - Ozon + банковская карта  
   - Wildberries + электронный кошелёк  
   - Ozon + наложенный платёж  
6. Вывести **итоговую сводку** по заказу в консоль.

---

## Чек-лист студента

- [ ] ERD подготовлена и отражена в материалах сдачи  
- [ ] Все требуемые типы и пакеты присутствуют  
- [ ] Использованы records, sealed `PaymentMethod`, перечисления, `ArrayList`, `HashMap`  
- [ ] Реализованы `Payment`, `OzonPayment`, `WildberriesPayment` (Стратегия)  
- [ ] Проект компилируется и запускается; в README есть **скриншот**  
- [ ] Указаны **группа**, **команда**, **ФИО**, **порядковые номера** в письме и README  
- [ ] Сдано **до дедлайна**  

---

## Порядок сдачи


проект + ERD (PNG/PDF) + README со скриншотом.  


https://disk.yandex.ru/i/VEScPdsj6J7pmQ

```text
Группа: [название]
Команда: [название]

Студенты:
1. [ФИО] — порядковый номер в группе: [XX]
2. [ФИО] — порядковый номер в группе: [XX]
3. [ФИО] — порядковый номер в группе: [XX]
4. [ФИО] — порядковый номер в группе: [XX]

Ссылка : [URL]
```

---

## Критерии оценивания (100 баллов)

| Критерий | Доля |
|----------|------|
| Качество ERD | 10 % |
| Принципы ООП | 20 % |
| Использование требуемых средств Java | 25 % |
| Паттерн «Стратегия» | 15 % |
| Качество кода | 15 % |
| Функциональная полнота | 15 % |

---

Удачи в работе.
