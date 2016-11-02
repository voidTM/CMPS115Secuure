//
//  ViewController.swift
//  EzPassStore
//
//  Created by Alexander Ou on 10/9/16.
//  Copyright © 2016 HashMappers. All rights reserved.
//

import UIKit
import Foundation

// global var to hold username and pass
var user = "";
var pass = "";
var login_result = false;

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
                if(login_result == true){
                    return true
                }
                else {
                    return false
                }
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
            
            /*****Send data to db to verify login*****/
            var request = URLRequest(url: URL(string: "http://localhost/~Steven/login_mysql.php")!)
            request.httpMethod = "POST"
            let postString = "arg_usr="+usernameText.text!+"&arg_pwd="+passwordText.text!
            request.httpBody = postString.data(using: .utf8)
            let task = URLSession.shared.dataTask(with: request) { data, response, error in
                guard let data = data, error == nil else {                                                 // check for fundamental networking error
                    print("error: \(error)")
                    return
                }
                
                if let httpStatus = response as? HTTPURLResponse, httpStatus.statusCode != 200 {           // check for http errors
                    print("statusCode should be 200, but is \(httpStatus.statusCode)")
                    print("response: \(response)")
                }
                
                let responseString = String(data: data, encoding: .utf8)
                let emess = "Connection failed"
                let range = responseString?.range(of:emess)
                if (range != nil) {
                    login_result = true;
                }
                else {
                    login_result = false;
                }
            }
            task.resume()

            warningLabel.text = "Logging in...";
        }else{
            let signupAlertController = UIAlertController(title: "Login Failed", message: "Incorrect Username or Password", preferredStyle: UIAlertControllerStyle.alert)
            let okAction = UIAlertAction(title: "Try Again", style: UIAlertActionStyle.default, handler: nil)
            signupAlertController.addAction(okAction)
            self.present(signupAlertController, animated: true, completion: nil)

        }
    }
}

