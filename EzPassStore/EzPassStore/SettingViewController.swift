//
//  SettingViewController.swift
//  EzPassStore
//
//  Created by Alexander Ou on 10/29/16.
//  Copyright © 2016 HashMappers. All rights reserved.
//

import UIKit

class SettingViewController: UIViewController {

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
        if segue.identifier == "showMainIntViewController" {
            _ = segue.destination as! MainInterfaceViewController
        }
    }
    
    //conditionals to making the segue
    override func shouldPerformSegue(withIdentifier identifier: String, sender: Any?) -> Bool {
        if(identifier == "showMainIntViewController") {
            //if all fields are filled, return true
            return true;
        }
        if(identifier == "showMainIntViewController") {
            return true;
        }
        
        return false
    }
    
    //save changes
    @IBAction func saveButton(_ sender: AnyObject) {
    }
    //discard changes
    @IBAction func discardButton(_ sender: AnyObject) {
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
