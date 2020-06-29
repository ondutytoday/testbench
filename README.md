#BACKEND СТАЖИРОВКА

###Запуск программы.
Для запуска программы необходимо в папке с файлом "task.jar"
запустить интерпретатор командной строки и набрать команду 
```
java -jar task.jar
```
###Руководство пользователя.
1. **Отключение проверки SSL-сертификата (при необходимости).** <br>
При необходимости можно отключить проверку SSL-сертификата набрав 
команду "**_yes_**" (регистр учитывается).<br>
При вводе любого другого сообщения проверка остается включенной.
2. **Ввод адреса сайта.** <br>
Необходимо ввести адрес сайта, включая протокол.<br>
Происходит парсинг сайта с выводом результатов 
в консоль, а также выводом пути к файлу с сохраненными данными.<br>
_Примеры для тестирования_:<br>
https://www.simbirsoft.com/ <br>
https://4pda.ru/forum/index.php?act=idx <br>
https://yandex.ru/ <br>
https://www.multitran.com/c/m.exe?a=1 <br>
3. **Выход из приложения.** <br>
Для выхода из приложения необходимо набрать команду "**_exit_**" (регистр не учитывается).

###О работе программы.

- При проверке работоспособности на одном из компьютеров была 
обнаружена проблема с проверкой SSL-сертификатов, 
поэтому при возникновении подобной проблемы проверку можно 
отключить, но лучше, конечно же, этого не делать.
- При парсинге учитывались только те разделители слов, которые были указаны в задании.
- При парсинге страницы, слова переводятся в верхний регистр,
выводятся в алфавитном порядке в том виде, как это было отображено в задании (через дефис, с новой строки).
- После завершения парсинга в консоль выводится путь к файлу, где сохранена страница.
- Также имеется логгирование (файл logs.log)
- При написании приложения была предпринята попытка использования многоуровневой 
архитектуры, паттерна проектирования MVC, а также обеспечения расширяемости решения.
