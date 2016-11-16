//
//  MainInterfaceViewController.swift
//  EzPassStore
//
//  Created by Alexander Ou on 10/15/16.
//  Copyright © 2016 HashMappers. All rights reserved.
//

import UIKit

class MainInterfaceViewController: UIViewController, UITableViewDataSource, UITableViewDelegate{

    var username:String = ""
    var accountArray: [String] = []
    

    @IBOutlet weak var loginUserLabel: UILabel!
    @IBOutlet weak var tableView: UITableView!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        //set loginuserlabel from username passed in from viewcontroller
        loginUserLabel.text = username;
        // Do any additional setup after loading the view.
        tableView.register(myCell.self, forCellReuseIdentifier: "cellId")
    }
    
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 5;
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        return tableView.dequeueReusableCell(withIdentifier: "cellId", for: indexPath)
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
    
}

class myCell: UITableViewCell
{
    override init(style: UITableViewCellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        setupViews()
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    func setupViews()
    {
        
    }
    
    
}

