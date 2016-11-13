import mysql.connector
import difflib
#initalizes database if table doesn't already exist
def createMasterTable(table):
    (conn, cursor) = createCon()

    cursor.execute("""CREATE TABLE IF NOT EXISTS %s(
            fname  text,
            lname  text,
            user  text,
            password  text
            )""" %table)
    conn.close()

def createPassTable():
    (conn, cursor) = createCon()
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
    (conn, cursor) = createCon()
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
    (conn, cursor) = createCon()
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
    conn = mysql.connector.connect(user='cs115', password='insecuurity',
                                     host='192.168.0.107',database='secuure')
    # note the above host name is my local one: external IP is 98.234.141.183

    cursor = conn.cursor()
    return (conn, cursor)

#Adds a username and password for a specific website given by the user
def addPass(user, username, pw, website, notes):
    (conn, cursor) = createCon()
    query = ("""SELECT username, website FROM data """)
    cursor.execute(query)
    for u, w in cursor:
        if u.lower() == username.lower() and website.lower() == w.lower():
            print("Duplicate entry in table, please try again")
            conn.close
            return
    cursor.execute("""INSERT IGNORE INTO data values (%s, %s, %s, %s, %s)""", (user, username, pw, website, notes))
    conn.commit()
    conn.close()

#Prints passwords for a specified user
def getPasswordsForUser(accountName):
    (conn, cursor) = createCon()
    query = ("""SELECT account, username, password, website, notes FROM DATA """)
    cursor.execute(query)
    data = []
    for a, u, p, w, n in cursor:
        if accountName.lower() == a.lower():
            temp = []
            temp.extend((a, u, p, w, n))
            data.append(temp)
            print(temp)
    conn.close()
    return data

def removeEntry(accountName, username, pw, website, notes):
    (conn, cursor) = createCon()
    query = """DELETE FROM DATA WHERE
                account=%s && username=%s && password=%s && website=%s && notes=%s
                """
    cursor.execute(query, (accountName, username, pw, website, notes))
    conn.commit()
    conn.close()


#####################
#      Testing      #
#####################

createMasterTable("accounts")
createPassTable()
insertToUserTable("John", "King", "jking", "test")
print("Before printing\n")
#addPass("jking", "sup yossssssss", "mypass3!!!21test", "gmail", "last")
#addPass("jking", "sup yos", "mypass321test", "gmail", "last")
#getPasswordsForUser("jking")
print("After printing\n")
#removeEntry("jking", "sup yos", "mypass321test", "gmail", "last")
#getPasswordsForUser("jking")

#c.execute(query)







