#main_gui for secuure
#developed by Isaak Cherdak

import tkinter
import storage
from tkinter import messagebox

fscreen_en = False

userAccounts = {} # temporary users until backend is sorted out
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

def quit_prgrm(): # when you hit escape
    global root
    root.destroy()

root = tkinter.Tk() # set root to be toplevel window
root.title("Welcome to Secuure")

natwidth = root.winfo_screenwidth() # get native resolutions
natheight = root.winfo_screenheight()

root.geometry(("%dx%d") % (natwidth / 2,natheight / 2)) # start with a window
                                                        # 1/4 the size of the
                                                        # screen

root.attributes("-fullscreen", fscreen_en) # default to non-fullscreen

root.bind('<F11>', toggle_fscreen)
root.bind('<Key>', mapKeyToFunc) # for most keystrokes, one function will map

loginButton = tkinter.Button(root, text ="log in", width = 10, height = 3,
        command = login)

userentry = tkinter.Entry(root, bd = 5)
passentry = tkinter.Entry(root, bd = 5, show = '*') # password is hidden

loginButton.pack(side=tkinter.BOTTOM) # button on bottom
userentry.pack(side=tkinter.TOP) # entry on top
passentry.pack(side=tkinter.TOP) # ""

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
