import random

# Special chars ascii values

pound = 35  # '#'
excl = 33  # '!'
at = 64  # '@'
dollar = 36  # '$'
perc = 37  # '%'
carrot = 94  # '^'
amper = 38  # '&'
ast = 42  # '*'

# Array definitions (global)

specCharacters = [pound, excl, at, perc, carrot, amper, ast, dollar]
specCharArr = []
upperCaseArr = []
numberArr = []
lowerCaseArr = []


# generates arrays of ascii values for corresponding sets of characters
def genAsciiArrays():
    for i in range(97, 123):
        lowerCaseArr.append(chr(i))  # 'a'-'z'
    for i in range(65, 91):
        upperCaseArr.append(chr(i))  # 'A' - 'Z'
    for i in range(48, 58):
        numberArr.append(chr(i))  # '1'-'9'
    for i in range(0, len(specCharacters)):
        specCharArr.append(chr(specCharacters[i]))  # '#!@%^&*$'


genAsciiArrays()


# Converts array(s) of ascii values to their corresponding character values
# Builds array of characters based on boxes checked on registration screen. (0 = not checked, 1 = checked)
def genArrAll(specChars, upperCase, numbers):
    main_arr = []
    for i in range(0, len(lowerCaseArr)):
        main_arr.append(lowerCaseArr[i])
    if upperCase == 1:
        for i in range(0, len(upperCaseArr)):
            main_arr.append(upperCaseArr[i])
    if specChars == 1:
        for i in range(0, len(specCharArr)):
            main_arr.append(specCharArr[i])
    if numbers == 1:
        for i in range(0, len(numberArr)):
            main_arr.append(numberArr[i])
    return main_arr


# Generates a random password based on user input
# Checks to see if at least one of each desired character is in the password, otherwise it generates a new password

def genPass(length, specChars, upperCase, numbers):
    (upper_count, num_count, lower_count, spec_count) = (0, 0, 0, 0)  # Initializes counters to 0
    total_count = 1 + specChars + upperCase + numbers  # Initializes desired count i.e. the count we want to check before returning
    ans = ""
    main_arr = genArrAll(specChars, upperCase, numbers)  # generates arrays based on user input
    while (True):
        if (len(ans) == length):  # If we have a fully generated password, check to see if we want to return it
            if (
                        upper_count + num_count + lower_count + spec_count) != total_count:  # Check character counters, if true, reset password and counters and re-loop
                ans = ""
                (upper_count, num_count, lower_count, spec_count) = (0, 0, 0, 0)
                continue
            else:
                return ans  # Otherwise return
        rand_num = random.randint(0, 10000000000000000000000000000000000000000000000) % len(
            main_arr)  # number between 0 and main_arr length-1
        character = main_arr[rand_num]  # Random character in the array
        ans = ans + character

        # Logic to check which condition is satisfied, then set counter flag to 1
        if (character in lowerCaseArr):
            lower_count = 1
        elif character in upperCaseArr:
            upper_count = 1
        elif character in numberArr:
            num_count = 1
        elif character in specCharArr:
            spec_count = 1


print(genPass(20, 1, 1, 1))
