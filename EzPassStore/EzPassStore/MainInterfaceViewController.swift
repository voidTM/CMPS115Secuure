//
//  MainInterfaceViewController.swift
//  EzPassStore
//
//  Created by Alexander Ou on 10/15/16.
//  Copyright © 2016 HashMappers. All rights reserved.
//

import UIKit

class MainInterfaceViewController: UIViewController{

    //data passing from viewcontroller to mainintview
    @IBOutlet weak var loginUserLabel: UILabel!
    var username:String = ""
    var tableView: UITableView!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        //set loginuserlabel from username passed in from viewcontroller
        loginUserLabel.text = username;
//        let tableView = UITableView(frame: view.bounds)
//        view.addSubview(tableView)
//        self.tableView = tableView
//        tableView.dataSource = self
//        tableView.delegate = self
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
    
//    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
//        return 5;
//    }
//    
//    
//    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
//        let cell = tableView.dequeueReusableCell(withIdentifier: "cell", for: indexPath)
//        
//        let button : UIButton = UIButton(type:UIButtonType.custom) as UIButton
//        
//        button.frame = CGRect(origin: CGPoint(x: 40,y :60), size: CGSize(width: 100, height: 24))
//        let cellHeight: CGFloat = 44.0
//        button.center = CGPoint(x: view.bounds.width / 2.0, y: cellHeight / 2.0)
//        button.backgroundColor = UIColor.red
//        button.addTarget(self, action:Selector(("buttonClicked:")), for: UIControlEvents.touchUpInside)
//        button.setTitle("Click Me !", for: UIControlState.normal)
//        cell.addSubview(button)
//        return cell
//    }
}

//extension MainInterfaceViewController: UITableViewDataSource, UITableViewDelegate {
//    
//    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
//        tableView.deselectRow(at: indexPath, animated: true)
//    }
//    
//}
