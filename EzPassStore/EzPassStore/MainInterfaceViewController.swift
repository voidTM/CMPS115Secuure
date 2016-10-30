//
//  MainInterfaceViewController.swift
//  EzPassStore
//
//  Created by Alexander Ou on 10/15/16.
//  Copyright © 2016 HashMappers. All rights reserved.
//

import UIKit

class MainInterfaceViewController: UIViewController {

    //data passing from viewcontroller to mainintview
    @IBOutlet weak var loginUserLabel: UILabel!
    var username:String = ""
    
    override func viewDidLoad() {
        super.viewDidLoad()
        //set loginuserlabel from username passed in from viewcontroller
        loginUserLabel.text = username;
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    //segue from signupview to emailverifyviews
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "showSettingViewController" {
            _ = segue.destination as! SettingViewController
            
        }
        if segue.identifier == "showViewController" {
            _ = segue.destination as! ViewController
        }
        if segue.identifier == "showAddViewController" {
            _ = segue.destination as! AddViewController
        }
    }
    
    //conditionals to making the segue
    override func shouldPerformSegue(withIdentifier identifier: String, sender: Any?) -> Bool {
        if(identifier == "showSettingViewController") {
            //if all fields are filled, return true
            return true;
        }
        if(identifier == "showViewController") {
            return true;
        }
        if(identifier == "showAddViewController") {
            return true;
        }
        
        return false
    }

    @IBAction func addAccountButton(_ sender: AnyObject) {
    }
    
    @IBAction func settingButton(_ sender: AnyObject) {
    }

    @IBAction func signoutButton(_ sender: AnyObject) {
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
