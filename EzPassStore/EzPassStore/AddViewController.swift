//
//  AddViewController.swift
//  EzPassStore
//
//  Created by Alexander Ou on 10/29/16.
//  Copyright © 2016 HashMappers. All rights reserved.
//

import UIKit

class AddViewController: UIViewController, UITextFieldDelegate {
    
    var user:String = ""
    var pass:String = ""

    @IBOutlet weak var AccountName: UITextField!
    @IBOutlet weak var UserName: UITextField!
    @IBOutlet weak var Password: UITextField!
    @IBOutlet weak var confirmPassword: UITextField!
    @IBOutlet weak var AdditionalNote: UITextView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
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
    
    @IBAction func backButton(_ sender: Any) {
    }
    
    @IBAction func addAccount(_ sender: Any) {
        if(!fieldIsEmpty()) {
            /***** CONNECT TO DB AND SEND DATA *****/
            var request = URLRequest(url: URL(string: "http://localhost/~Aou/insert_mysql_ios.php")!)
            request.httpMethod = "POST"
            let userPassString = "arg_usr="+user+"&arg_pwd="+pass
            let nameAccountString = "&arg_add_acc="+UserName.text!+"&arg_add_ws="+AccountName.text!
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
            }
            task.resume()
        }
        
    }
    
    
    //return true if fields are empty
    func fieldIsEmpty() -> Bool {
        if(!(AccountName.text?.isEmpty)! && !(UserName.text?.isEmpty)! && !(Password.text?.isEmpty)! && !(confirmPassword.text?.isEmpty)! && !(AdditionalNote.text?.isEmpty)!) {
            return false;
        }
        return true;
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
