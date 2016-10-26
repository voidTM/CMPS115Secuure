import mysql.connector

#Creates connection to local MySQL database
connection = mysql.connector.connect(user='root', password='passmanager',
                                     host='localhost', database = 'secuure_db')

c = connection.cursor()

c.execute("""drop table if exists accounts""")

c.execute(""" CREATE TABLE accounts(
        user  text,
        password  text)""")

c.execute("""INSERT INTO accounts values ("John", "Test")""")

query = ("SELECT user, password FROM accounts")

c.execute(query)

for (u, p) in c:
    print ("user: ", u, "password: ", p)

connection.commit()


connection.close()



