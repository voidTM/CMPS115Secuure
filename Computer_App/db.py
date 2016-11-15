import mysql.connector
import difflib
#initalizes database if table doesn't already exist
def addToUserTable(user, pw):
    (conn, cursor) = createCon('cs115','insecuurity')
    print(user, pw)
    try:
        create = ("""CREATE USER %s IDENTIFIED BY %s""")
        cursor.execute(create, user, pw)
        conn.commit()
        permissions = ("""GRANT SELECT,DELETE,INSERT,UPDATE on data to %s identified by %s""")
        cursor.execute(permissions, user, pw)
        conn.commit()
    except mysql.connector.Error as e:
        print ("User already exists")
        conn.close()
        return
    cursor.execute("""CREATE TABLE IF NOT EXISTS users(
            id  INTEGER ,
            username  text,
            first_name  text,
            last_name  text
            )""")
    conn.close()


#gets highest ID number in the USER table
def getUserIdNum():
    (conn, cursor) = createCon('cs115','insecuurity')
    cursor.execute("""SELECT ID from users""")
    max_id = 0
    for id in cursor:
        if id[0] > max_id:
            max_id = id[0]
    conn.close()
    return max_id + 1

def getDataIdNum():
    (conn, cursor) = createCon('cs115','insecuurity')
    cursor.execute("""SELECT userid from data""")
    max_id = 0
    for id in cursor:
        if id[0] > max_id:
            max_id = id[0]
    conn.close()
    return max_id + 1

def createPassTable():
    (conn, cursor) = createCon('cs115','insecuurity')
    cursor.execute("""CREATE TABLE IF NOT EXISTS data(
                userid integer,
                username text,
                website text,
                password text,
                notes text
                )""")
    conn.close()
#inserts into the "accounts" table.
#Thing to note: table to be inserted into CANNOT be a variable (must be hardcoded)
def insertToUserTable(fname,lname, user):
    (conn, cursor) = createCon('cs115','insecuurity')
    query = ("""SELECT username FROM users""")       #
    cursor.execute(query)                           #
    for u in cursor:                                # This piece checks if an account exists already
        print(u[0], user)
        if u[0].lower() == user.lower():           #
             print("Account name already exists")    #
             return                                  #
    max_id = getUserIdNum()
    cursor.execute("""INSERT IGNORE INTO users values (%s, %s, %s, %s)""", (max_id, user, fname, lname))
    conn.commit()
    conn.close()


#Iterates over the database and looks for login and login_pw, if they match, returns true, otherwise false.
def verMasterLogin(login, login_pw):
    (conn, cursor) = createCon('cs115', 'insecuurity')
    query = ("""SELECT user,password FROM users""") #query to select all user/pw from table
    cursor.execute(query)

    for u, p in cursor:
        if u.lower() == login.lower() and login_pw.lower() == login_pw.lower(): #if they match return true
            conn.close()
            return True
    conn.close()
    return False

#Creates connection to local MySQL database
# ***************REPLACES verMasterLogin******************
#  Passwords for accounts are no longer stored on the db
def createCon(user, pw):
    try:
        conn = mysql.connector.connect(user=user, password=pw, host = '98.234.141.183', database='secuure') #isaak: 98.234.141.183
        cursor = conn.cursor()
        return (conn, cursor)
    except mysql.connector.Error as e:
        print("Incorrect user/pw or connection error")
        exit()
        return
    # note the above host name is my local one: external IP is 98.234.141.183



#Adds a username and password for a specific website given by the user
def addPassForWebsite(username, pw, website, notes):
    (conn, cursor) = createCon('cs115', 'insecuurity')
    query = ("""SELECT account, website FROM data """)
    cursor.execute(query)
    for u, w in cursor:
        if u.lower() == username.lower() and website.lower() == w.lower():
            print("Duplicate entry in table, please try again")
            conn.close
            return
    cursor.execute("""INSERT IGNORE INTO data values (%s, %s, %s, %s, %s)""", (getDataIdNum(), username, website, pw, notes))
    conn.commit()
    conn.close()

#Prints passwords for a specified user
def getPasswordsForUser(accountName):
    (conn, cursor) = createCon('cs115','insecuurity')
    query = ("""SELECT account, password, website, notes FROM DATA """)
    cursor.execute(query)
    data = []
    for a, p, w, n in cursor:
        if accountName.lower() == a.lower():
            print(a, p, w, n)
    conn.close()
    return data


#Removes entry from the table, all values have to match
def removeEntry(username, pw, website, notes):
    (conn, cursor) = createCon('cs115', 'insecuurity')
    query = """DELETE FROM DATA WHERE
                account=%s && website=%s && password=%s && notes=%s
                """

    cursor.execute(query, (username, website, pw, notes))
    conn.commit()
    conn.close()


#####################
#      Testing      #
#####################

addToUserTable("josccking","root")
#createPassTable()
insertToUserTable('John', 'King', 'joscddkingking')
#print("Before printing\n")
addPassForWebsite("sup yossssssss", "mypass3!!!21test", "gmail", "last")
addPassForWebsite("sup yos", "mypass321test", "gmail", "last")
getPasswordsForUser("jking")
print("After printing\n")
#removeEntry("sup yos", "mypass321test", "gmail", "last")
getPasswordsForUser("jking")






