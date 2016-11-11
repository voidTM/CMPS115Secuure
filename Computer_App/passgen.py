import random

# Special chars ascii values

pound=35 #  '#'
excl=33 #   '!'
at=64 #     '@'
dollar=36 # '$'
perc=37 #   '%'
carrot=94 # '^'
amper=38 #  '&'
ast=42 #    '*'

# Array definitions

specCharArr = [pound, excl, at, perc, carrot, amper, ast]
upperCaseArr = []
numberArr = []
lowerCaseArr =[]
randCharArr = [] # Primary array

def genArrays():
    for i in range(65, 90):
        lowerCaseArr.push(i)
    for i in range(97, 122):
        upperCaseArr.push(i)
    for i in range(48, 57):
        numberArr.push(i)


def genArrAll(specCharArr, specChars, upperCase, numbers):
    for i in range(0, len(lowerCaseArr)):
        randCharArr.push(chr(lowerCaseArr[i]))
    if upperCase == 1:
        for i in range(0, len(upperCaseArr)):
            randCharArr.push(chr(upperCaseArr[i]))
    if specCharArr == 1:
        for i in range(0, len(specCharArr)):
            randCharArr.push(chr(specCharArr[i]))
    if numbers == 1:
        for i in range(0, len(numberArr)):
            randCharArr.push(chr(numberArr[i]))
    return randCharArr

def genPass(length, specChars, upperCase, numbers):
