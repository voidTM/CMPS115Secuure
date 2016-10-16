#main_gui for secuure
#developed by Isaak Cherdak


import tkinter
from tkinter import messagebox


fscreen_en = False


def loginButton():
    global userentry
    global passentry
    userstr = userentry.get()
    if (userstr == ""):
        userstr = "<EMPTY>"
    passstr = passentry.get()
    if (passstr == ""):
        passstr = "<EMPTY>"
    messagebox.showinfo("account_info", "Your username is %s\n" %
            (userstr) + "Your password is %s\nHave a nice day\n"
            % (passstr))

def loginKey(event):
    if (event.char != '\n' and event.char != '\r'):
        #print (str(ord(event.char)) + "\n")
        return
    global userentry
    global passentry
    userstr = userentry.get()
    if (userstr == ""):
        userstr = "<EMPTY>"
    passstr = passentry.get()
    if (passstr == ""):
        passstr = "<EMPTY>"
    messagebox.showinfo("account_info", "Your username is %s\n" %
            (userstr) + "Your password is %s\nHave a nice day\n"
            % (passstr))

def toggle_fscreen(event):
    global fscreen_en
    fscreen_en = not fscreen_en
    root.attributes("-fullscreen", fscreen_en)




root = tkinter.Tk()

natwidth = root.winfo_screenwidth()
natheight = root.winfo_screenheight()

root.geometry(("%dx%d") % (natwidth / 2,natheight / 2))

root.attributes("-fullscreen", fscreen_en)

root.bind('<F11>', toggle_fscreen)
root.bind('<Key>', loginKey)

B = tkinter.Button(root, text ="log in", width = 10, height = 3,
        command = loginButton)
userentry = tkinter.Entry(root, bd = 5)
passentry = tkinter.Entry(root, bd = 5, show = '*')

B.pack(side=tkinter.BOTTOM)
userentry.pack(side=tkinter.TOP)
passentry.pack(side=tkinter.TOP)
root.mainloop()
