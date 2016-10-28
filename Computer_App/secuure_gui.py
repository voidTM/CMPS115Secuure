#!/usr/bin/python3

#main_gui for secuure
#developed by Isaak Cherdak


import tkinter
from storage import *
from tkinter import messagebox

class Secuure_GUI:

	def __init__(self):

		self.fscreen_en = False
		self.userAccounts = {}# temporary users until backend is sorted out
		self.userAccounts["Admin"] = "Password"
	
		self.root = tkinter.Tk() # set root to be toplevel window
		self.root.title("Welcome to Secuure")
		
		self.natwidth = self.root.winfo_screenwidth() # get native resolutions
		self.natheight = self.root.winfo_screenheight()
		
		self.root.geometry(("%dx%d") % (self.natwidth / 2,self.natheight / 2)) # start with a window
		                                                        # 1/4 the size of the
		                                                        # screen
		
		frame_main = tkinter.Frame(self.root)
		
		self.root.attributes("-fullscreen", self.fscreen_en) # default to non-fullscreen
		
		self.root.bind('<F11>', self.toggle_fscreen)
		self.root.bind('<Key>', self.mapKeyToFunc) # for most keystrokes, one function will map
		
		login_Button = tkinter.Button(self.root, text ="log in", width = 10, height = 3,
		        command = self.login)
	
		self.userentry = tkinter.Entry(self.root, bd = 5)
		user_label = tkinter.Label(self.root, text = 'Username')
		self.passentry = tkinter.Entry(self.root, bd = 5, show = '*') # password is hidden
		pass_label = tkinter.Label(self.root, text = 'Password')
		reg_button = tkinter.Button(self.root, text = "Register", command = self.register_account)
		
		blank_label = tkinter.Label(self.root, text = '')
		
		reg_button.grid(row = 4, column = 1)
		blank_label.grid(row = 3, column = 100) # filling space
		login_Button.grid(row = 2, column = 1)
		self.userentry.grid(row = 0, column = 1) # these four need to be sized appropriately
		user_label.grid(row = 0, column = 0)
		self.passentry.grid(row = 1, column = 1)
		pass_label.grid(row = 1, column = 0)
		
		menubar = tkinter.Menu(self.root) # make menubar
		filemenu = tkinter.Menu(menubar, tearoff=0)
		filemenu.add_command(label="New", command=self.donothing)
		filemenu.add_command(label="Open", command=self.donothing)
		filemenu.add_command(label="Save", command=self.donothing)
		filemenu.add_command(label="Save as...", command=self.donothing)
		filemenu.add_command(label="Close", command=self.donothing)
		
		filemenu.add_separator()
			
		filemenu.add_command(label="Exit", command=self.root.quit)
		menubar.add_cascade(label="File", menu=filemenu)
		editmenu = tkinter.Menu(menubar, tearoff=0)
		editmenu.add_command(label="Undo", command=self.donothing)
	
		editmenu.add_separator()
		
		editmenu.add_command(label="Cut", command=self.donothing)
		editmenu.add_command(label="Copy", command=self.donothing)
		editmenu.add_command(label="Paste", command=self.donothing)
		editmenu.add_command(label="Delete", command=self.donothing)
		editmenu.add_command(label="Select All", command=self.donothing)
		
		menubar.add_cascade(label="Edit", menu=editmenu)
		helpmenu = tkinter.Menu(menubar, tearoff=0)
		helpmenu.add_command(label="Help Index", command=self.donothing)
		helpmenu.add_command(label="About...", command=self.donothing)
		menubar.add_cascade(label="Help", menu=helpmenu)
		
		self.root.config(menu=menubar) # set menubar to main window
		
		self.root.mainloop() # start the interface
	
	def mapKeyToFunc(self, event):
    		if (len(event.char) != 1): # ie: shift is length 0
    		    return
    		if (event.char == '\n' or event.char == '\r'):
    		    self.login()
    		elif (ord(event.char) == 27): # 27 is ascii val of escape
    		    self.quit_prgrm()
    		return # without a return, every key results in an error
	
	def login(self): # hit enter or login button
	    user_str = self.userentry.get()
	    pass_str = self.passentry.get()
	    
	    #Call to login function in storage.py
	    login_valid = verLogin(user_str, pass_str, self.userAccounts)

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
	    self.root.attributes("-fullscreen", fscreen_en)
	
	def quit_prgrm(self): # when you hit escape
	    self.root.destroy()
	
	def map_reg_key(self, event): # only called through register_account()
	    if (len(event.char) != 1):
	        return
	    if (ord(event.char) == 27):
	        self.reg_win.destroy()
	    return
	
	def register_account(self):
	    self.reg_win = tkinter.Toplevel(width = self.natwidth / 2, height = self.natheight / 2)
	    self.reg_win.title("Register Account")
	    self.reg_win.bind('<Key>', self.map_reg_key)

	
	def donothing(self):
	    return
	    self.root
	    filewin = tkinter.Toplevel(self.root)
	    button = tkinter.Button(filewin, text="Do nothing button")
	    button.pack()

	# more stuff here
