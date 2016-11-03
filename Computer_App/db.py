import mysql.connector


#initalizes database if table doesn't already exist
def createMasterTable(table):
    conn = createCon()
    cursor = conn.cursor()
    cursor.execute("""CREATE TABLE IF NOT EXISTS %s(
            fname  text,
            lname  text,
            user  text,
            password  text
            )""" %table)
    conn.close()

#inserts into the "accounts" table.
#Thing to note: table to be inserted into CANNOT be a variable (must be hardcoded)
def insertToUserTable(fname,lname, user, pw):
    conn = createCon()
    cursor = conn.cursor()
    cursor.execute("""INSERT IGNORE INTO accounts values (%s, %s, %s, %s)""", (fname, lname, user, pw))
    conn.commit()
    conn.close()


#Iterates over the database and looks for login and login_pw, if they match, returns true, otherwise false.
def verMasterLogin(login, login_pw):
    conn = createCon()
    cursor = conn.cursor()
    query = ("""SELECT user,password FROM accounts""") #query to select all user/pw from table
    cursor.execute(query)
    for u, p in cursor:
        if u.lower() == login.lower() and login_pw.lower() == login_pw.lower(): #if they match return true
            conn.close()
            return True
    conn.close()
    return False



#Creates connection to local MySQL database
def createCon():
    conn = mysql.connector.connect(user='root', password='passmanager',
                                     host='localhost', database = 'secuure_db')
    return conn
#####################
#      Testing      #
#####################

createMasterTable("accounts")
insertToUserTable("John", "King", "jking", "test")
print(verMasterLogin("jking", "test"))
#query = ("SELECT user, password FROM accounts")
#
#c.execute(query)







