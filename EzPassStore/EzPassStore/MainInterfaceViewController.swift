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
        accountArray += ["This", "Is", "A", "Test"]
    }
    
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return accountArray.count;
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "cellId", for: indexPath) as! myCell
        cell.rowLabel.text = accountArray[indexPath.row]
        cell.viewController = self
        
        return cell
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        self.performSegue(withIdentifier: "showInfoViewController", sender: self)
    }
    
    //update the account info
    func updateCell(cell: UITableViewCell)
    {
       //segue to add view controller
        self.performSegue(withIdentifier: "showAddViewController", sender: self)
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
        accountArray.append("New Account")
        let insertIndexPath = NSIndexPath(row: accountArray.count - 1, section: 0)
        tableView.insertRows(at: [insertIndexPath as IndexPath], with: .automatic)
    }
    
    @IBAction func settingButton(_ sender: AnyObject) {
    }

    @IBAction func signoutButton(_ sender: AnyObject) {
    }
    
}

class myCell: UITableViewCell
{
    var viewController: MainInterfaceViewController?
    
    //set up the view for our table cell
    override init(style: UITableViewCellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        setupViews()
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    // make label
    let rowLabel:UILabel = {
        let label = UILabel()
        label.text = "Test"
        label.translatesAutoresizingMaskIntoConstraints = false
        label.font = UIFont.boldSystemFont(ofSize: 10)
        return label
    }()
    
    let updateButton:UIButton = {
        let button = UIButton(type: .system)
        //make button smaller but doesnt work?
        button.frame = CGRect(x: 5, y: 5, width: 5, height: 5)
        button.setTitle("Edit", for: .normal)
        button.translatesAutoresizingMaskIntoConstraints = false
        return button
    }()
    
    //view func
    func setupViews()
    {
        addSubview(rowLabel)
        addSubview(updateButton)
        
        updateButton.addTarget(self, action: #selector(myCell.updateAction), for: .touchUpInside)
        
        addConstraints(NSLayoutConstraint.constraints(withVisualFormat: "H:|-16-[v0]-8-[v1(80)]-8-|", options: NSLayoutFormatOptions(), metrics: nil, views: ["v0": rowLabel, "v1": updateButton]))
        
        addConstraints(NSLayoutConstraint.constraints(withVisualFormat: "V:|[v0]|", options: NSLayoutFormatOptions(), metrics: nil, views: ["v0": rowLabel]))
        addConstraints(NSLayoutConstraint.constraints(withVisualFormat: "V:|[v0]|", options: NSLayoutFormatOptions(), metrics: nil, views: ["v0": updateButton]))
        
    }
    
    func updateAction ()
    {
            viewController?.updateCell(cell: self)
    }
    
    
}

