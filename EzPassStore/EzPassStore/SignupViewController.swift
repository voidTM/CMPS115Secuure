//
//  SignupViewController.swift
//  EzPassStore
//
//  Created by Alexander Ou on 10/15/16.
//  Copyright © 2016 HashMappers. All rights reserved.
//

import UIKit

var signupPass = ""
var signupCpass = ""
var signupFirstN = ""
var signupLastN = ""
var signupEmail = ""

class SignupViewController: UIViewController, UITextFieldDelegate {
    @IBOutlet weak var signupText: UILabel!
    @IBOutlet weak var firstName: UITextField!
    @IBOutlet weak var lastName: UITextField!
    @IBOutlet weak var password: UITextField!
    @IBOutlet weak var email: UITextField!
    @IBOutlet weak var cpassword: UITextField!
    //update the text on signup view
    var usernameUpdate:String = ""
    
    override func viewDidLoad() {
        super.viewDidLoad()
        //ignupText.text = username
        // Do any additional setup after loading the view.
        self.firstName.delegate = self
        self.lastName.delegate = self
        self.password.delegate = self
        self.email.delegate = self
        self.cpassword.delegate = self
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
        firstName.resignFirstResponder()
        lastName.resignFirstResponder()
        password.resignFirstResponder()
        email.resignFirstResponder()
        cpassword.resignFirstResponder()
        return true
    }
    
    //segue from signupview to emailverifyviews
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "showEmailVerifyViewController" {
            _ = segue.destination as! EmailVerifyViewController
            
        }
        if segue.identifier == "showViewController" {
            _ = segue.destination as! ViewController
        }
    }
    
    //conditionals to making the segue
    override func shouldPerformSegue(withIdentifier identifier: String, sender: Any?) -> Bool {
        if(identifier == "showEmailVerifyViewController") {
            //if all fields are filled, return true
            if(!(password.text?.isEmpty)! && !(cpassword.text?.isEmpty)! && !(firstName.text?.isEmpty)! && !(lastName.text?.isEmpty)! && !(email.text?.isEmpty)!) {
                    return true
            }
        }
        if(identifier == "showViewController") {
            return true;
        }
       
        return false
    }
    
    @IBAction func signUp(_ sender: AnyObject) {
        //are all the fields filled? if so initialize
        if(!(password.text?.isEmpty)! && !(cpassword.text?.isEmpty)! && !(firstName.text?.isEmpty)! && !(lastName.text?.isEmpty)! && !(email.text?.isEmpty)!) {
            signupPass = password.text!;
            signupCpass = cpassword.text!;
            signupFirstN = firstName.text!
            signupLastN = lastName.text!
            signupEmail = email.text!
        //if fields arent filled, prep UIAlert
        }else{
            let signupAlertController = UIAlertController(title: "Sign up failed", message: "Please enter all fields", preferredStyle: UIAlertControllerStyle.alert)
            let okAction = UIAlertAction(title: "Try Again", style: UIAlertActionStyle.default, handler: nil)
            signupAlertController.addAction(okAction)
            self.present(signupAlertController, animated: true, completion: nil)
            
        }
    }
    
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
