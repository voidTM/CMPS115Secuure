//
//  ViewController.swift
//  EzPassStore
//
//  Created by Alexander Ou on 10/9/16.
//  Copyright © 2016 HashMappers. All rights reserved.
//

import UIKit

// global var to hold username and pass
var user = "";
var pass = "";

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "showMainIntViewController" {
            let MainInterfaceViewController = segue.destination as! MainInterfaceViewController
            MainInterfaceViewController.username = user
            
        }
    }

    
    override func shouldPerformSegue(withIdentifier identifier: String, sender: Any?) -> Bool {
        if(identifier == "showSignupViewController") {
          return true
        }
        if(identifier == "showMainIntViewController") {
            if(!(usernameText.text?.isEmpty)! && !(passwordText.text?.isEmpty)!) {
                
                return true
            }
        }
        return false
    }

    // username and password textfield input
    @IBOutlet weak var warningLabel: UILabel!
    @IBOutlet weak var usernameText: UITextField!
    @IBOutlet weak var passwordText: UITextField!

    @IBAction func signupButton(_ sender: AnyObject) {
            //warningLabel.text = "Signing up ... "
    }
    
    @IBAction func loginButton(_ sender: AnyObject) {
        if(!(usernameText.text?.isEmpty)! && !(passwordText.text?.isEmpty)!) {
            user = usernameText.text!;
            pass = passwordText.text!;
            warningLabel.text = "Logging in...";
        }else{
            warningLabel.text = "Please enter your username and password!"
            print("warninglabel seg");
        }
    }
}

