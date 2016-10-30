//
//  AddViewController.swift
//  EzPassStore
//
//  Created by Alexander Ou on 10/29/16.
//  Copyright © 2016 HashMappers. All rights reserved.
//

import UIKit

class AddViewController: UIViewController, UITextFieldDelegate {

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
    
    
    @IBOutlet weak var addAccountButton: UIButton!

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
