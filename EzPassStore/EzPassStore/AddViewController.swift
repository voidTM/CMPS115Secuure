//
//  AddViewController.swift
//  EzPassStore
//
//  Created by Alexander Ou on 10/29/16.
//  Copyright © 2016 HashMappers. All rights reserved.
//

import UIKit

class AddViewController: UIViewController, UITextFieldDelegate ,UITextViewDelegate{
    
    
    var genPass = ""
    var parse_response = [String]()
    var isEdit = true
    
    @IBOutlet weak var WebsiteName: UITextField!
    @IBOutlet weak var UserName: UITextField!
    @IBOutlet weak var Password: UITextField!
    @IBOutlet weak var confirmPassword: UITextField!
    @IBOutlet weak var AdditionalNote: UITextView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        //init view properties
        self.view.backgroundColor = UIColor(patternImage: UIImage(named:"secuurebackground.jpg")!)
        
        UIGraphicsBeginImageContext(self.view.frame.size)
        UIImage(named: "secuurebackground.jpg")?.draw(in: self.view.bounds)
        
        let image: UIImage = UIGraphicsGetImageFromCurrentImageContext()!
        
        UIGraphicsEndImageContext()
        
        self.view.backgroundColor = UIColor(patternImage: image)
        
        self.WebsiteName.delegate = self
        self.UserName.delegate = self
        self.Password.delegate = self
        self.confirmPassword.delegate = self
        self.AdditionalNote.delegate = self
        
        //set the website and username after returning from password gen
        if(DataContainerSingleton.sharedDataContainer.website != ""){
            WebsiteName.text = DataContainerSingleton.sharedDataContainer.website
            UserName.text = DataContainerSingleton.sharedDataContainer.addUser
            
            DataContainerSingleton.sharedDataContainer.website = ""
            DataContainerSingleton.sharedDataContainer.addUser = ""
        }
        
        if(genPass != "") {
            self.Password.text = genPass
            self.confirmPassword.text = genPass
        }
        
        //check if is in edit mode
        if(DataContainerSingleton.sharedDataContainer.cellText == "New Account") {
            isEdit = false
        }

        //if in edit mode
        if(isEdit) {
            
            //call server to get account info from DB and display it. DO NOT allow users to edit website or username
            var accWebArray = [String]()
            
            print("CELL TEXT IS******"+DataContainerSingleton.sharedDataContainer.cellText!)
            
            accWebArray = parseUnderscore(response: DataContainerSingleton.sharedDataContainer.cellText!)
            
            let user = DataContainerSingleton.sharedDataContainer.userString! as String
            let pass = DataContainerSingleton.sharedDataContainer.passString! as String
            
            
            WebsiteName.text = accWebArray[1]
            UserName.text = accWebArray[0]
            WebsiteName.isUserInteractionEnabled = false
            UserName.isUserInteractionEnabled = false
            
            
            var serverResp = 0
            /*****Send data to db to verify login*****/
            var request = URLRequest(url: URL(string: "http://localhost/~Aou/read_mysql_ios.php")!)
            request.httpMethod = "POST"
            /***** NOT SURE HOW ITS GETTING USER...CHECK LATER ***/
            let postString = "arg_usr="+user+"&arg_pwd="+pass+"&arg_read_acc="+UserName.text!+"&arg_read_ws="+WebsiteName.text!
            print(WebsiteName.text!+"*******"+UserName.text!)
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
                //print("************************************")
                print("responseString: \(responseString)")
                self.parse_response = self.parseOutput(response: responseString!)
                print(self.parse_response)
                
                print("RUNNNINGGGGGGGGGGG")
                if(self.genPass == "") {
                    self.Password.text = self.parse_response[2]
                    self.confirmPassword.text = self.parse_response[2]
                }
                DispatchQueue.main.async {
                    self.AdditionalNote.text = self.parse_response[3]
                }
                
                responsePhp = responseString!
                serverResp = 1
            }
            task.resume()
            //wait for response
            while(serverResp != 1){
                //50 Milliseconds
                usleep(50000)
            }

        
        }
        // Do any additional setup after loading the view.
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
        WebsiteName.resignFirstResponder()
        UserName.resignFirstResponder()
        Password.resignFirstResponder()
        confirmPassword.resignFirstResponder()
        AdditionalNote.resignFirstResponder()
        return true
    }

    
    //segue from signupview to emailverifyviews
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "showMainIntViewController" {
            _ = segue.destination as! MainInterfaceViewController
            
        }
        if segue.identifier == "showPassViewController" {
            _ = segue.destination as! PassViewController
        }
    
    }
    
    //conditionals to making the segue
    override func shouldPerformSegue(withIdentifier identifier: String, sender: Any?) -> Bool {
        if(identifier == "showMainIntViewController") {
            //if all fields are filled, return true
            return true;
        }
        if(identifier == "showPassViewController") {
            return true;
        }
        return false
    }
    
    //save website and username when segue to password generator. Fields are used when returning to this view after generating the password
    @IBAction func genPass(_ sender: Any) {
        DataContainerSingleton.sharedDataContainer.website = WebsiteName.text!
        DataContainerSingleton.sharedDataContainer.addUser = UserName.text!
    }
    @IBAction func backButton(_ sender: Any) {
    }
    
    @IBAction func addAccount(_ sender: Any) {
        if(!fieldIsEmpty()) {
            
            
            //call server and add account to DB with the neccesary info
            let user = DataContainerSingleton.sharedDataContainer.userString! as String
            let pass = DataContainerSingleton.sharedDataContainer.passString! as String
            
            
            if(Password.text != confirmPassword.text) {
                invalidLogin()
            //call edit script if its in edit mode
            }else if(isEdit) {
                
                var serverResp = 0
                /*****Send data to db to verify login*****/
                var request = URLRequest(url: URL(string: "http://localhost/~Aou/edit_mysql_ios.php")!)
                request.httpMethod = "POST"
                
                let userPassString = "arg_usr="+user+"&arg_pwd="+pass
                let UserNameWebString = "&arg_edit_acc="+UserName.text!+"&arg_edit_ws="+WebsiteName.text!
                let passNotesString = "&arg_edit_pwd="+Password.text!+"&arg_edit_note="+AdditionalNote.text!
                
                let postString = userPassString + UserNameWebString + passNotesString
                
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
                    //print("************************************")
                    print("responseString: \(responseString)")
                    serverResp = 1
                }
                task.resume()
                //wait for response
                while(serverResp != 1){
                    //50 Milliseconds
                    usleep(50000)
                }
            //call the add script if its in add mode
            }else{
            var serverResp = 0
            /***** CONNECT TO DB AND SEND DATA *****/
            var request = URLRequest(url: URL(string: "http://localhost/~Aou/insert_mysql_ios.php")!)
            request.httpMethod = "POST"
            let userPassString = "arg_usr="+user+"&arg_pwd="+pass
            let nameAccountString = "&arg_add_acc="+UserName.text!+"&arg_add_ws="+WebsiteName.text!
            let passNoteString = "&arg_add_pwd="+Password.text!+"&arg_add_note="+AdditionalNote.text!
            let postString = userPassString+nameAccountString+passNoteString
            
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
            while(serverResp != 1){
                //50 Milliseconds
                usleep(50000)
            }
        }
        }
    }
    
    
    //return true if fields are empty
    func fieldIsEmpty() -> Bool {
        if(!(WebsiteName.text?.isEmpty)! && !(UserName.text?.isEmpty)! && !(Password.text?.isEmpty)! && !(confirmPassword.text?.isEmpty)!) {
            return false;
        }
        return true;
    }
    
    //func to parse server response
    func parseUnderscore(response: String) -> Array<String>{
        let strArray = response.components(separatedBy: "_")
        return strArray
    }
    
    //UIAlert pop up if credentials are wrong
    func invalidLogin() {
        let signupAlertController = UIAlertController(title: "Process Failed", message: "Passwords do not match", preferredStyle: UIAlertControllerStyle.alert)
        let okAction = UIAlertAction(title: "Try Again", style: UIAlertActionStyle.default, handler: nil)
        signupAlertController.addAction(okAction)
        self.present(signupAlertController, animated: true, completion: nil)
    }
    
    //func to parse server response
    func parseOutput(response: String) -> Array<String>{
        let strArray = response.components(separatedBy: "|")
        return strArray
    }
    


}
