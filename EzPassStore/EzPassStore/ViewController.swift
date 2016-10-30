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

class ViewController: UIViewController, UITextFieldDelegate {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        self.usernameText.delegate = self
        self.passwordText.delegate = self
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    //hide keyboard when user touch outside keyboard
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        self.view.endEditing(true)
    }
    //hide keyboard when user press return
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        usernameText.resignFirstResponder()
        passwordText.resignFirstResponder()
        return true
    }
    
    //segue from viewcontroller to mainint view with data
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "showMainIntViewController" {
            let MainInterfaceViewController = segue.destination as! MainInterfaceViewController
            MainInterfaceViewController.username = user
            
        }
    }

    //conditionals to making the segue
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

    //sign up button
    @IBAction func signupButton(_ sender: AnyObject) {
            //warningLabel.text = "Signing up ... "
    }
    
    //login button, sets username and password accordingly
    @IBAction func loginButton(_ sender: AnyObject) {
        if(!(usernameText.text?.isEmpty)! && !(passwordText.text?.isEmpty)!) {
            user = usernameText.text!;
            pass = passwordText.text!;
            warningLabel.text = "Logging in...";
        }else{
            let signupAlertController = UIAlertController(title: "Login Failed", message: "Incorrect Username or Password", preferredStyle: UIAlertControllerStyle.alert)
            let okAction = UIAlertAction(title: "Try Again", style: UIAlertActionStyle.default, handler: nil)
            signupAlertController.addAction(okAction)
            self.present(signupAlertController, animated: true, completion: nil)

        }
    }
}

