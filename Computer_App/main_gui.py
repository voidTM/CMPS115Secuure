#main_gui for secuure
#developed by Isaak Cherdak

import tkinter
from storage import *
from tkinter import messagebox

fscreen_en = False

userAccounts = {}# temporary users until backend is sorted out
userAccounts["Admin"] = "Password"

def mapKeyToFunc(event):
    if (len(event.char) != 1): # ie: shift is length 0
        return
    if (event.char == '\n' or event.char == '\r'):
        login()
    elif (ord(event.char) == 27): # 27 is ascii val of escape
        quit_prgrm()
    return # without a return, every key results in an error

def login(): # hit enter or login button
    global userentry
    global passentry
    user_str = userentry.get()

    pass_str = passentry.get()
    #Call to login function in storage.py
    login_valid = verLogin(user_str, pass_str, userAccounts)

    if (len(user_str) == 0):
        user_str = "<EMPTY>"
    if (len(pass_str) == 0):
        pass_str = "<EMPTY>"

    info_str = "Your username is %s\n" % \
        (user_str) + "Your password is %s\n" % (pass_str)
    
    if (login_valid):
        info_str += "This is a valid combination"
    else:
        info_str += "This is not a valid combination"

    messagebox.showinfo("Account information", info_str)

def toggle_fscreen(event):
    global fscreen_en
    fscreen_en = not fscreen_en
    root.attributes("-fullscreen", fscreen_en)

def quit_prgrm(): # when you hit escape
    global root
    root.destroy()

def map_reg_key(event): # only called through register_account()
    if (len(event.char) != 1):
        return
    if (ord(event.char) == 27):
        self.reg_win.destroy()
    return

def register_account():
    return # for now
    self.reg_win = tkinter.Toplevel(width = natwidth / 2, height = natheight / 2)
    self.reg_win.title("Register Account")
    self.reg_win.bind('<Key>', map_reg_key)


root = tkinter.Tk() # set root to be toplevel window
root.title("Welcome to Secuure")

natwidth = root.winfo_screenwidth() # get native resolutions
natheight = root.winfo_screenheight()

root.geometry(("%dx%d") % (natwidth / 2,natheight / 2)) # start with a window
                                                        # 1/4 the size of the
                                                        # screen

frame_main = tkinter.Frame(root)

root.attributes("-fullscreen", fscreen_en) # default to non-fullscreen

root.bind('<F11>', toggle_fscreen)
root.bind('<Key>', mapKeyToFunc) # for most keystrokes, one function will map

login_Button = tkinter.Button(root, text ="log in", width = 10, height = 3,
        command = login)


userentry = tkinter.Entry(root, bd = 5)
user_label = tkinter.Label(root, text = 'Username')
passentry = tkinter.Entry(root, bd = 5, show = '*') # password is hidden
pass_label = tkinter.Label(root, text = 'Password')
reg_button = tkinter.Button(root, text = "Register", command = register_account)

blank_label = tkinter.Label(root, text = '')

reg_button.grid(row = 4, column = 1)
blank_label.grid(row = 3, column = 100) # filling space
login_Button.grid(row = 2, column = 1)
userentry.grid(row = 0, column = 1) # these four need to be sized appropriately
user_label.grid(row = 0, column = 0)
passentry.grid(row = 1, column = 1)
pass_label.grid(row = 1, column = 0)

def donothing():
    return
    global root
    filewin = tkinter.Toplevel(root)
    button = tkinter.Button(filewin, text="Do nothing button")
    button.pack()

menubar = tkinter.Menu(root)
filemenu = tkinter.Menu(menubar, tearoff=0)
filemenu.add_command(label="New", command=donothing)
filemenu.add_command(label="Open", command=donothing)
filemenu.add_command(label="Save", command=donothing)
filemenu.add_command(label="Save as...", command=donothing)
filemenu.add_command(label="Close", command=donothing)

filemenu.add_separator()

filemenu.add_command(label="Exit", command=root.quit)
menubar.add_cascade(label="File", menu=filemenu)
editmenu = tkinter.Menu(menubar, tearoff=0)
editmenu.add_command(label="Undo", command=donothing)

editmenu.add_separator()

editmenu.add_command(label="Cut", command=donothing)
editmenu.add_command(label="Copy", command=donothing)
editmenu.add_command(label="Paste", command=donothing)
editmenu.add_command(label="Delete", command=donothing)
editmenu.add_command(label="Select All", command=donothing)

menubar.add_cascade(label="Edit", menu=editmenu)
helpmenu = tkinter.Menu(menubar, tearoff=0)
helpmenu.add_command(label="Help Index", command=donothing)
helpmenu.add_command(label="About...", command=donothing)
menubar.add_cascade(label="Help", menu=helpmenu)

root.config(menu=menubar)

root.mainloop() # start the interface




# more stuff here
