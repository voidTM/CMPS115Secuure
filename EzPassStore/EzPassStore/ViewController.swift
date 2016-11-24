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
var responsePhp = "";


class ViewController: UIViewController, UITextFieldDelegate {
    
    @IBOutlet weak var loginButton: UIButton!
    @IBOutlet weak var signupButton: UIButton!
    override func viewDidLoad() {
        super.viewDidLoad()
        
        //init properties
        self.view.backgroundColor = UIColor(patternImage: UIImage(named:"secuurebackground.jpg")!)

        UIGraphicsBeginImageContext(self.view.frame.size)
        UIImage(named: "secuurebackground.jpg")?.draw(in: self.view.bounds)
        
        let image: UIImage = UIGraphicsGetImageFromCurrentImageContext()!
        
        UIGraphicsEndImageContext()
        
        self.view.backgroundColor = UIColor(patternImage: image)
        
            
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
    
    //ui alert pop up for wrong credentials
    func invalidLogin() {
        let signupAlertController = UIAlertController(title: "Login Failed", message: "Incorrect Username or Password", preferredStyle: UIAlertControllerStyle.alert)
        let okAction = UIAlertAction(title: "Try Again", style: UIAlertActionStyle.default, handler: nil)
        signupAlertController.addAction(okAction)
        self.present(signupAlertController, animated: true, completion: nil)
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
        var serverResp = 0
        if(!(usernameText.text?.isEmpty)! && !(passwordText.text?.isEmpty)!) {

            
            
            DataContainerSingleton.sharedDataContainer.userString = usernameText.text!
            DataContainerSingleton.sharedDataContainer.passString = passwordText.text!
            
            let user = DataContainerSingleton.sharedDataContainer.userString! as String
            let pass = DataContainerSingleton.sharedDataContainer.passString! as String
            
            
            /*****Send data to db to verify login*****/
            var request = URLRequest(url: URL(string: "http://localhost/~Aou/login_mysql_ios.php")!)
            request.httpMethod = "POST"
            let postString = "arg_usr="+user+"&arg_pwd="+pass
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
                responsePhp = responseString!
                serverResp = 1
            }
            task.resume()
            //wait for server response
            while(serverResp != 1){
                //50 Milliseconds
                usleep(50000)
            }
            if(authenticate()) {
                self.performSegue(withIdentifier: "showMainIntViewController", sender: self)
            }else{
                invalidLogin()
            }
        }else{
            invalidLogin()
        }
    }
    
    //func to check if server authentication is successful
    func authenticate() -> Bool {
        let emess = "{\"login\":true}"
        let range = responsePhp.range(of:emess)
        if (range != nil) {
            return true
        }else{
            return false
        }
    }
}

