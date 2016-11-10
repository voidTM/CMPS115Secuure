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

def createPassTable():
    conn = createCon()
    cursor = conn.cursor()
    cursor.execute("""CREATE TABLE IF NOT EXISTS data(
                account text,
                username text,
                password text,
                website text,
                notes text
                )""")
    conn.close()
#inserts into the "accounts" table.
#Thing to note: table to be inserted into CANNOT be a variable (must be hardcoded)
def insertToUserTable(fname,lname, user, pw):
    conn = createCon()
    cursor = conn.cursor()

    query = ("""SELECT user FROM accounts""")       #
    cursor.execute(query)                           #
    for u in cursor:                                # This piece checks if an account exists already
        print(u[0], user)
        if u[0].lower() == user.lower():           #
             print("Account name already exists")    #
             return                                  #

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
    conn = mysql.connector.connect(user='root', password='root',
                                     host='localhost',database='secuuredb')
    return conn

#Adds a username and password for a specific website given by the user
def addPass(user, username, pw, website, notes):
    print(user, username, pw, website, notes)
    conn = createCon()
    cursor = conn.cursor()
    query = ("""SELECT username, website FROM data """)
    cursor.execute(query)
    for u, w in cursor:
        if u.lower() == user.lower() and website.lower == w.lower:
            print("Duplicate entry in table, please try again")
            conn.close
            return
    cursor.execute("""INSERT IGNORE INTO data values (%s, %s, %s, %s, %s)""", (user, username, pw, website, notes))
    conn.commit()
    conn.close()


#####################
#      Testing      #
#####################

createMasterTable("accounts")
createPassTable()
insertToUserTable("John", "King", "joscking", "test")
addPass("joscking", "jking", "mypass321test", "gmail", "last")

#c.execute(query)







