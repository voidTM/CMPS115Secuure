import mysql.connector


#initalizes database if accounts exists
def createDB(cursor, table):
    cursor.execute("""drop table if exists accounts""")

    cursor.execute("""CREATE TABLE %s(
            user  text,
            password  text)""" %table)

#inserts into the "accounts" table.
#Thing to note: table to be inserted into CANNOT be a variable (must be hardcoded)
def insertToUserTable(cursor, user, pw):
    cursor.execute("""INSERT INTO accounts values (%s, %s)""", (user, pw))



#Creates connection to local MySQL database
connection = mysql.connector.connect(user='root', password='passmanager',
                                     host='localhost', database = 'secuure_db')
####################
#      Testing     #
####################

c = connection.cursor()

createDB(c, "accounts")
insertToUserTable(c, "John", "test")

query = ("SELECT user, password FROM accounts")

c.execute(query)

for (u, p) in c:
    print ("user: ", u, "password: ", p)

connection.commit()


connection.close()



