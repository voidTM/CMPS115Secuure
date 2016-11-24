//
//  InfoViewController.swift
//  Secuure
//
//  Created by Alexander Ou on 11/16/16.
//  Copyright © 2016 HashMappers. All rights reserved.
//

import UIKit
import QuartzCore

class InfoViewController: UIViewController {

    
    @IBOutlet weak var displayWebsite: UILabel!
    
    @IBOutlet weak var displayUsername: UILabel!
    
    @IBOutlet weak var displayPassword: UILabel!
    
    @IBOutlet weak var displayNotes: UILabel!
    
    var parse_response = [String]()
    var newAccount = false

    override func viewDidLoad() {
        super.viewDidLoad()
        
        //set background image and init var properties
        self.view.backgroundColor = UIColor(patternImage: UIImage(named:"secuurebackground.jpg")!)
        
        UIGraphicsBeginImageContext(self.view.frame.size)
        UIImage(named: "secuurebackground.jpg")?.draw(in: self.view.bounds)
        
        let image: UIImage = UIGraphicsGetImageFromCurrentImageContext()!
        
        UIGraphicsEndImageContext()
        
        self.view.backgroundColor = UIColor(patternImage: image)
        
        displayWebsite.textColor = UIColor.white
        displayUsername.textColor = UIColor.white
        displayPassword.textColor = UIColor.white
        displayNotes.textColor = UIColor.white
    
        displayWebsite.layer.borderWidth = 1;
        displayWebsite.layer.borderColor = UIColor.white.cgColor
        
        displayUsername.layer.borderWidth = 1;
        displayUsername.layer.borderColor = UIColor.white.cgColor
        
        displayPassword.layer.borderWidth = 1;
        displayPassword.layer.borderColor = UIColor.white.cgColor
        
        displayNotes.layer.borderWidth = 1;
        displayNotes.layer.borderColor = UIColor.white.cgColor
        
        //check if its new account(add) or existing account(edit)
        if(DataContainerSingleton.sharedDataContainer.cellText == "New Account") {
            newAccount = true
        }
        
        //if new account, set website to New Account
        if(newAccount) {
            displayWebsite.text = "New Account"
        //else connect to web server and retrieve account info from DB
        }else if(!newAccount) {
        let user = DataContainerSingleton.sharedDataContainer.userString! as String
        let pass = DataContainerSingleton.sharedDataContainer.passString! as String
        let cellLabelArray = parseUnderscore(response: DataContainerSingleton.sharedDataContainer.cellText!)
        
        
        var serverResp = 0
        /*****Send data to db to verify login*****/
        var request = URLRequest(url: URL(string: "http://localhost/~Aou/read_mysql_ios.php")!)
        request.httpMethod = "POST"
        /***** NOT SURE HOW ITS GETTING USER...CHECK LATER ***/
        let postString = "arg_usr="+user+"&arg_pwd="+pass+"&arg_read_acc="+cellLabelArray[0]+"&arg_read_ws="+cellLabelArray[1]
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
            
            self.displayWebsite.text = self.parse_response[1]
            self.displayUsername.text = self.parse_response[0]
            self.displayPassword.text = self.parse_response[2]
            self.displayNotes.text = self.parse_response[3]
            
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
    
    @IBAction func deleteAccount(_ sender: Any) {
        //delete the account from database
        //segue back to mainint
        if(!newAccount) {
        
        let user = DataContainerSingleton.sharedDataContainer.userString! as String
        let pass = DataContainerSingleton.sharedDataContainer.passString! as String
        
        
        var serverResp = 0
        /*****Send data to db to verify login*****/
        var request = URLRequest(url: URL(string: "http://localhost/~Aou/delete_mysql_ios.php")!)
        request.httpMethod = "POST"
        /***** NOT SURE HOW ITS GETTING USER...CHECK LATER ***/
        let postString = "arg_usr="+user+"&arg_pwd="+pass+"&arg_del_acc="+parse_response[0]+"&arg_del_ws="+parse_response[1]
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
        self.performSegue(withIdentifier: "showMainIntViewController", sender: self)
        
    }

    @IBAction func backToMainInt(_ sender: Any) {
        //segue back to main
         self.performSegue(withIdentifier: "showMainIntViewController", sender: self)
    }
    
    //func to parse resposne from server
    func parseOutput(response: String) -> Array<String>{
        let strArray = response.components(separatedBy: "|")
        return strArray
    }
    
    //func to parse response from server
    func parseUnderscore(response: String) -> Array<String>{
        let strArray = response.components(separatedBy: "_")
        return strArray
    }

}
