//
//  SignupViewController.swift
//  EzPassStore
//
//  Created by Alexander Ou on 10/15/16.
//  Copyright © 2016 HashMappers. All rights reserved.
//

import UIKit

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
        
        //init view properties
        
        self.view.backgroundColor = UIColor(patternImage: UIImage(named:"secuurebackground.jpg")!)
        
        UIGraphicsBeginImageContext(self.view.frame.size)
        UIImage(named: "secuurebackground.jpg")?.draw(in: self.view.bounds)
        
        let image: UIImage = UIGraphicsGetImageFromCurrentImageContext()!
        
        UIGraphicsEndImageContext()
        
        self.view.backgroundColor = UIColor(patternImage: image)
        
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
            if(!fieldIsEmpty()) {
                if(password.text == cpassword.text) {
                    return true;
                }else{
                    return false;
                }
            }
        }
        if(identifier == "showViewController") {
            return true;
        }
       
        return false
    }
    
    @IBAction func signUp(_ sender: AnyObject) {
        //are all the fields filled? if so initialize
        
        if(!fieldIsEmpty()) {
            if(password.text == cpassword.text) {
            /**** pass to db ******/
                var request = URLRequest(url: URL(string: "http://localhost/~Aou/register_mysql_ios.php")!)
                request.httpMethod = "POST"
                let postString = "arg_usr="+email.text!+"&arg_pwd="+password.text!+"&arg_fname="+firstName.text!+"&arg_lname="+lastName.text!
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
                    print("responseString: \(responseString)")
                }
                task.resume()
            //credentials are not valid, UIAlert pop up
            }else {
                //uialert for unmatching password
                let signupAlertController = UIAlertController(title: "Unmatching password", message: "Please enter matching passwords", preferredStyle: UIAlertControllerStyle.alert)
                let okAction = UIAlertAction(title: "Try Again", style: UIAlertActionStyle.default, handler: nil)
                signupAlertController.addAction(okAction)
                self.present(signupAlertController, animated: true, completion: nil)
            }
            
        //if fields arent filled, prep UIAlert
        }else{
            let signupAlertController = UIAlertController(title: "Sign up failed", message: "Please enter all fields", preferredStyle: UIAlertControllerStyle.alert)
            let okAction = UIAlertAction(title: "Try Again", style: UIAlertActionStyle.default, handler: nil)
            signupAlertController.addAction(okAction)
            self.present(signupAlertController, animated: true, completion: nil)
            
        }
    }
    //return true if fields are empty
    func fieldIsEmpty() -> Bool {
        if(!(password.text?.isEmpty)! && !(cpassword.text?.isEmpty)! && !(firstName.text?.isEmpty)! && !(lastName.text?.isEmpty)! && !(email.text?.isEmpty)!) {
            return false;
        }
        return true;
    }
  

}
