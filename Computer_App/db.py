import mysql.connector


#initalizes database if accounts exists
def createDB(cursor, table):
    cursor.execute("""CREATE TABLE IF NOT EXISTS %s(
            user  text,
            password  text)""" %table)

#inserts into the "accounts" table.
#Thing to note: table to be inserted into CANNOT be a variable (must be hardcoded)
def insertToUserTable(cursor, user, pw):
    cursor.execute("""INSERT IGNORE INTO accounts values (%s, %s)""", (user, pw))


def verMasterLogin(cursor, login, login_pw):
    query = ("SELECT user, password FROM accounts")
    cursor.execute(query)
    for (u, p) in c:
        if u.lower() == login.lower() and login_pw.lower() == login_pw.lower():
            return True
    return False

#Creates connection to local MySQL database
connection = mysql.connector.connect(user='root', password='passmanager',
                                     host='localhost', database = 'secuure_db')
#####################
#      Testing      #
#####################

c = connection.cursor()

createDB(c, "accounts")
insertToUserTable(c, "John", "test")
print(verMasterLogin(c, "John", "test"))
#query = ("SELECT user, password FROM accounts")
#
#c.execute(query)




connection.close()



