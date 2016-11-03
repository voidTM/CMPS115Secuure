import hashlib
import getpass


accounts = {}
passList = {}

#creates a user via a hashtable
def createUser():
    user = input("Please enter your desired username\n")
    password = input("Please enter your desired password:\n")
    confirm = input("Please confirm your password\n")
    while password != confirm:
        print ("Passwords do not match\n")
        print ("Please enter your desired password\n")
        password = getpass.getpass()
        print ("Please confirm your password\n")
        confirm = getpass.getpass()
    accounts[user] = password
    passList[user] = {}

#verifies login given user and pass of user
def verLogin(user, password, accounts):
    # user = input("Please enter your username\n")
    # password = input("Please enter your password\n")
    # i = 3
    # while i != 0:
    #     if password != accounts[user]:
    #         i -= 1
    #         print ("Incorrect login, please try again (you have", i, " attempts left)\n")
    #         password = input("Please enter your password:\n")
    #         continue
    #     return user
    # if i == 0:
    #     print ("You've exceeded your login attemps, goodbye.")
    #     exit()
    if user in accounts.keys() and accounts[user] == password:
        return True
    else:
        return False

def enterPass(user):
    use = input("What would you like to use the following password for?\n")
    password = input("Enter the password to be stored\n")
    passList[user] = {use, password}

def listPasswords(user):
    dict = passList[user]
    for use in dict:
        print ("Use:", use, "password:", dict[use], "\n")

# createUser()
# user = verLogin()
#
# print ("Welcome to your password manager")
# while True:
#     action = input("What would you like to do?\n"
#                     "1. Enter a password\n"
#                     "2. List your current passwords?\n"
#                     "3. Exit\n")
#
#     if action == "1":
#         enterPass(user)
#         continue
#     elif action == "2":
#         listPasswords(user)
#         continue
#     elif action == "3":
#         print ("Have a nice day!")
#         exit()
#     else:
#         print ("Sorry, the input wasn't recognized, please try again\n")
#         continue







