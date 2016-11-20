//
//  MainInterfaceViewController.swift
//  EzPassStore
//
//  Created by Alexander Ou on 10/15/16.
//  Copyright © 2016 HashMappers. All rights reserved.
//

import UIKit


class MainInterfaceViewController: UIViewController, UITableViewDataSource, UITableViewDelegate{

    //parse result
    var parse_response = [String]()
    
    @IBOutlet weak var loginUserLabel: UILabel!
    @IBOutlet weak var tableView: UITableView!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        print("CONNECTING FROM VIEWDIDLOAD MAIN********************************************")

        let user = DataContainerSingleton.sharedDataContainer.userString! as String
        let pass = DataContainerSingleton.sharedDataContainer.passString! as String
        
        //set loginuserlabel from username passed in from viewcontroller
        loginUserLabel.text = user;
        // Do any additional setup after loading the view.
        tableView.register(myCell.self, forCellReuseIdentifier: "cellId")

        //server response
        //var serverResp = 0
        
        /*****Send data to db to verify login*****/
        var request = URLRequest(url: URL(string: "http://localhost/~Aou/read_accounts_mysql_ios.php")!)
        request.httpMethod = "POST"
        /***** NOT SURE HOW ITS GETTING USER...CHECK LATER ***/
        let postString = "arg_usr="+user+"&arg_pwd="+pass
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
            //responsePhp = responseString!
            //serverResp = 1
            
        }
        task.resume()
        
        print("************************************")
        print(parse_response)

    }
    
    override func viewDidAppear(_ animated: Bool) {
         print("CONNECTING FROM VIEWDIDLOAD APPEAR********************************************")
        
        let user = DataContainerSingleton.sharedDataContainer.userString! as String
        let pass = DataContainerSingleton.sharedDataContainer.passString! as String
        
        //set loginuserlabel from username passed in from viewcontroller
        loginUserLabel.text = user;
        
        /*****Send data to db to verify login*****/
        var request = URLRequest(url: URL(string: "http://localhost/~Aou/read_accounts_mysql_ios.php")!)
        request.httpMethod = "POST"
        /***** NOT SURE HOW ITS GETTING USER...CHECK LATER ***/
        let postString = "arg_usr="+user+"&arg_pwd="+pass
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
            //responsePhp = responseString!
            //serverResp = 1
            
        }
        task.resume()
        
        print("************************************")
        print(parse_response)
        
        
    }
    
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return parse_response.count;
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "cellId", for: indexPath) as! myCell
        cell.rowLabel.text = parse_response[indexPath.row]
        cell.viewController = self
        
        return cell
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        
        
        let cell = tableView.cellForRow(at: tableView.indexPathForSelectedRow!) as! myCell
        
        
        DataContainerSingleton.sharedDataContainer.cellText = cell.rowLabel.text! as String
        
        print("cell label main: "+DataContainerSingleton.sharedDataContainer.cellText!)

        
        self.performSegue(withIdentifier: "showInfoViewController", sender: self)
    }
    
    //update the account info
    func updateCell(cell: UITableViewCell)
    {
       //segue to add view controller
        self.performSegue(withIdentifier: "showAddViewController", sender: self)
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
        parse_response.append("New Account")
        let insertIndexPath = NSIndexPath(row: parse_response.count - 1, section: 0)
        tableView.insertRows(at: [insertIndexPath as IndexPath], with: .automatic)
    }
    
    @IBAction func settingButton(_ sender: AnyObject) {
    }

    @IBAction func signoutButton(_ sender: AnyObject) {
    }
    
    func parseOutput(response: String) -> Array<String>{
        var strArray = response.components(separatedBy: "|")
        _ = strArray.removeLast()
        return strArray
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

