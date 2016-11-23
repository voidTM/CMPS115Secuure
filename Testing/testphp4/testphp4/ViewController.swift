//
//  ViewController.swift
//  testphp4
//
//  Created by skwan on 10/21/16.
//  Copyright © 2016 skwan. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBAction func buttonPressed(_ sender: UIButton) {
        var parse_response = [String]()
//        var request = URLRequest(url: URL(string: "http://localhost/~Steven/register_mysql_ios.php")!)
//        var request = URLRequest(url: URL(string: "http://localhost/~Steven/delete_mysql.php")!)
//        var request = URLRequest(url: URL(string: "http://localhost/~Steven/edit_mysql.php")!)
//        var request = URLRequest(url: URL(string: "http://localhost/~Steven/insert_mysql_ios.php")!)
 //         var request = URLRequest(url: URL(string: "http://localhost/~Steven/login_mysql_ios.php")!)
        var request = URLRequest(url: URL(string: "http://localhost/~Steven/read_accounts_mysql_ios.php")!)
//          var request = URLRequest(url: URL(string: "http://98.234.141.183:8080/read_mysql_ios.php")!)
//        var request = URLRequest(url: URL(string: "http://localhost/~Steven/register_mysql.php")!)
        
        request.httpMethod = "POST"
        let postString = "arg_usr=ALKJSDLKJFLSDKJFLKJSDF&arg_pwd=admin" // login_mysql.php / read_accounts_mysql.php
//        let postString = "arg_usr=&arg_pwd=&arg_del_acc=&arg_del_ws=" // delete_mysql.php
//        let postString = "arg_usr=&arg_pwd=&arg_edit_acc=&arg_edit_ws=&arg_edit_pwd=&arg_edit_note=" // edit_mysql.php
//        let postString = "arg_usr=ALKJSDLKJFLSDKJFLKJSDF&arg_pwd=admin&arg_add_acc=Account&arg_add_ws=website&arg_add_pwd=password&arg_add_note=Note" // insert_mysql.php
//        let postString = "arg_usr=&arg_pwd=&arg_read_acc=&arg_read_ws=" // read_mysql.php
 //       let postString = "arg_usr=ALKJSDLKJFLSDKJFLKJSDF&arg_pwd=admin&arg_fname=Alex&arg_lname=Ou" // register_mysql.php
        
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
            parse_response = self.parseOutput(response: responseString!)
            print(parse_response)
        }
        task.resume()
    }
    
    func parseOutput(response: String) -> Array<String>{
        var strArray = response.components(separatedBy: " ")
        _ = strArray.removeLast()
        return strArray
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    @IBAction func passwordGen(_ sender: UIButton) {
        let genPass = randomString(length:20, flagletter: true, flagnum: false, flagspecial: true)
        print(genPass)
    }
    
    func randomString(length: Int, flagletter: Bool, flagnum: Bool, flagspecial: Bool) -> String {
        
        var letters : NSString = ""
        
        if(flagletter && !flagnum && !flagspecial) {
            letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
        }
        else if(!flagletter && flagnum && !flagspecial) {
            letters = "abcdefghijklmnopqrstuvwxyz0123456789"
        }
        else if(!flagletter && !flagnum && flagspecial) {
            letters = "abcdefghijklmnopqrstuvwxyz!@#$%^*()"
        }
        else if(flagletter && flagnum && !flagspecial) {
            letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
        }
        else if(flagletter && !flagnum && flagspecial) {
            letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^*()"
        }
        else if(!flagletter && flagnum && flagspecial) {
            letters = "abcdefghijklmnopqrstuvwxyz0123456789!@#$%^*()"
        }
        else if(flagletter && flagnum && flagspecial) {
            letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^*()"
        }
        else if(!flagletter && !flagnum && !flagspecial) {
            letters = "abcdefghijklmnopqrstuvwxyz"
        }
        
        let len = UInt32(letters.length)
        
        var randomString = ""
        
        for _ in 0 ..< length {
            let rand = arc4random_uniform(len)
            var nextChar = letters.character(at: Int(rand))
            randomString += NSString(characters: &nextChar, length: 1) as String
        }
        
        return randomString
    }

}

