from Driver import Driver


def list_of_stolen_cars():
    file = open('cars_01.dat', 'rb')
    byte = file.read()
    str = byte.decode('utf-8')
    lines = str.split('\n')
    for line in lines:
        items = line.split()
        if items[3] == "y":
            print(line)
    file.close()


def was_the_car_stolen(number):
    file = open('cars_01.dat', 'rb')
    byte = file.read()
    str = byte.decode('utf-8')
    lines = str.split('\n')
    for line in lines:
        items = line.split()
        if items[0] == number:
            if items[3] == 'n':
                print("This car is not stolen")
            else:
                print("This car is stolen")
            break
    file.close()


f = open('cars.txt', 'r')
file = open('cars_01.dat', 'wb')
byte = f.read().encode('utf-8')
f.close()
file.write(byte)
file.close()
file = open('cars_01.dat', 'rb')
byte = file.read()
str = byte.decode('utf-8')
lines = str.split('\n')
drivers = []
print("Все записи файла:")
for line in lines:
    data = line.split()
    if data[3] == "y":
        flag = True
    else:
        flag = False
    driver = Driver(data[0], data[1], data[2], flag)
    drivers.append(driver)
    if driver.isStolen is True:
        print(driver.id + " " + driver.kind + " " + driver.name + " y")
    else:
        print(driver.id + " " + driver.kind + " " + driver.name + " n")
print()
print("Список автомобилей, числящихся в угоне:")
list_of_stolen_cars()
print()
print("Установить факт угона автомобиля с номером 0")
was_the_car_stolen("0")
