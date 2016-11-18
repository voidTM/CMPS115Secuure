#!/usr/bin/python3

#main_gui for secuure
#developed by Isaak Cherdak

import tkinter
from tkinter import messagebox
from db import *

class Secuure_GUI:

        def __init__(self):

                self.bcolor = '#9ACAEE'
                #createMasterTable("accounts")
                createPassTable()
                self.fscreen_en = False
                self.userAccounts = {}# temporary users until backend is sorted out
                self.userAccounts["Admin"] = "Password"
        
                self.root = tkinter.Tk() # set root to be toplevel window
                self.root.title("Welcome to Secuure")
                self.root.configure(background = self.bcolor)
                
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
                user_label = tkinter.Label(self.root, text = 'Username',
                        background = self.bcolor)
                self.passentry = tkinter.Entry(self.root, bd = 5, show = '*') # password is hidden
                pass_label = tkinter.Label(self.root, text = 'Password',
                        background = self.bcolor)
                reg_button = tkinter.Button(self.root, text = "Register", command = self.register_account)
                
                blank_label = tkinter.Label(self.root, text = '', background =
                        self.bcolor)
                
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
            login_valid = verMasterLogin(user_str, pass_str)

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
        
            #messagebox.showinfo("Account information", info_str)

            print(info_str)

            if (login_valid):
                self.list_account_info(user_str)
        
        def toggle_fscreen(self, event):
            self.fscreen_en = not self.fscreen_en
            self.root.attributes("-fullscreen", self.fscreen_en)
        
        def quit_prgrm(self): # when you hit escape
            self.root.destroy()
        
        def map_reg_key(self, event): # only called through register_account()
            if (len(event.char) != 1):
                return
            if (ord(event.char) == 27):
                self.reg_win.destroy()
            return
        
        def map_list_account_info_key(self, event):
            self.window_info.destroy()

        def list_account_info(self, user_str):
            self.window_info = tkinter.Toplevel()
            self.window_info.configure(background = self.bcolor)
            self.window_info.geometry(("%dx%d") % (self.natwidth / 2,self.natheight / 2)) # start with a window
            self.window_info.title("Account Information for '%s'" % (user_str))
            self.window_info.bind('<Key>', self.map_list_account_info_key)

            #get accounts
            list_accounts = {}
            list_accounts["Admin"] = "Password"
            label_usernames = []
            label_passwords = []

            """ new """
            """"""
            data = getPasswordsForUser(user_str)
            for info in data:
                label_usernames.append(tkinter.Label(self.window_info, text =
                    info[1] + " : "))
                label_passwords.append(tkinter.Label(self.window_info, text =
                    info[2]))
            """"""

            """ old """
            """
            for key in list_accounts:
                label_usernames.append(tkinter.Label(self.window_info, text =
                    key + " : ", background = self.bcolor))
                label_passwords.append(tkinter.Label(self.window_info, text =
                    list_accounts[key], background = self.bcolor))
            """

            for index in range(0, len(label_usernames)):
                label_user = label_usernames[index]
                label_pass = label_passwords[index]
                label_user.grid(row = index, column = 0)
                label_pass.grid(row = index, column = 1)

            # need to do this for every button (add account, remove account, etc)
            blank_labels = [] # how to artificially make space
            num_spaces = 10
            space_start = 2
            for i in range(0, num_spaces):
                blank_labels.append(tkinter.Label(self.window_info, text = ' ',
                    background = self.bcolor))
                blank_labels[i].grid(row  = 0, column = space_start + i)

            button_add = tkinter.Button(self.window_info, text = "Add Account",
                    command = self.add_account_window)
            button_remove = tkinter.Button(self.window_info,
            text = "Remove Account", command = self.remove_account_window)
            
            button_add.grid(row = 0, column = space_start + num_spaces)
            button_remove.grid(row = 1, column = space_start + num_spaces)

        def submit_account_registration(self):
            if (self.field_confpass.get() != self.field_pass.get()):
                messagebox.showerror("Account Registration Error", "Passwords" +
                        " do not match")
                return
            
            user_str = self.field_username.get()
            pass_str = self.field_pass.get()

            
            if (user_str in self.userAccounts.keys()):
                print ("Replacing Password: '%s' with '%s' for user '%s'" %
                        (self.userAccounts[user_str], pass_str, user_str))
                return
            
            else:
                print ("Adding user '%s' with password '%s'" % (user_str, pass_str))
                insertToUserTable("fname", "lname", user_str, pass_str)

            self.userAccounts[user_str] = pass_str # remove after backend
                                                   #integration

            

        def register_account(self):
            self.reg_win = tkinter.Toplevel(width = self.natwidth / 2, height = self.natheight / 2)
            self.reg_win.configure(background = self.bcolor)
            self.reg_win.geometry(("%dx%d") % (self.natwidth / 2,self.natheight / 2)) # start with a window
            self.reg_win.title("Register Account")
            self.reg_win.bind('<Key>', self.map_reg_key)

            label_blank = tkinter.Label(self.reg_win, text = '', background =
                    self.bcolor)

            button_submit = tkinter.Button(self.reg_win, text = "Submit",
                    width = 10, height = 3, command =
                    self.submit_account_registration)

            field_fname = tkinter.Entry(self.reg_win, bd = 5)
            field_lname = tkinter.Entry(self.reg_win, bd = 5)
            self.field_username = tkinter.Entry(self.reg_win, bd = 5)
            self.field_pass = tkinter.Entry(self.reg_win, bd = 5, show = '*')
            self.field_confpass = tkinter.Entry(self.reg_win, bd = 5, show = '*')
            
            label_fname = tkinter.Label(self.reg_win, text = "First Name",
                    background = self.bcolor)
            label_lname = tkinter.Label(self.reg_win, text = "Last Name",
                    background = self.bcolor)
            label_username = tkinter.Label(self.reg_win, text = "User Name",
                    background = self.bcolor)
            label_pass = tkinter.Label(self.reg_win, text = "Password",
                    background = self.bcolor)
            label_confpass = tkinter.Label(self.reg_win,
                    text = "Confirm Password", background = self.bcolor)
            
            field_fname.grid(row = 0, column = 1) # trying to organize
            field_lname.grid(row = 1, column = 1)
            self.field_username.grid(row = 2, column = 1)
            self.field_pass.grid(row = 3, column = 1)
            self.field_confpass.grid(row = 4, column = 1)
        
            label_fname.grid(row = 0, column = 0)
            label_lname.grid(row = 1, column = 0)
            label_username.grid(row = 2, column = 0)
            label_pass.grid(row = 3, column = 0)
            label_confpass.grid(row = 4, column = 0)

            label_blank.grid(row = 5, column = 1)

            button_submit.grid(row = 6, column = 1)

        def add_account_window(self):
            window_add_acc = tkinter.Toplevel()
            window_add_acc.configure(background = self.bcolor)
            window_add_acc.geometry(("%dx%d") % (self.natwidth / 2,self.natheight / 2)) # start with a window
            window_add_acc.title("Add Account")

        def remove_account_window(self):
            window_rem_acc = tkinter.Toplevel()
            window_rem_acc.configure(background = self.bcolor)
            window_rem_acc.geometry(("%dx%d") % (self.natwidth / 2,self.natheight / 2)) # start with a window
            window_rem_acc.title("Remove Account")

        def donothing(self):
            return
            self.root
            filewin = tkinter.Toplevel(self.root)
            button = tkinter.Button(filewin, text="Do nothing button")
            button.pack()

        # more stuff here
