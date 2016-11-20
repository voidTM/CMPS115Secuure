//
//  InfoViewController.swift
//  Secuure
//
//  Created by Alexander Ou on 11/16/16.
//  Copyright © 2016 HashMappers. All rights reserved.
//

import UIKit

class InfoViewController: UIViewController {

    
    @IBOutlet weak var displayWebsite: UILabel!
    
    @IBOutlet weak var displayUsername: UILabel!
    
    @IBOutlet weak var displayPassword: UILabel!
    
    @IBOutlet weak var displayNotes: UILabel!
    
    var parse_response = [String]()

    override func viewDidLoad() {
        super.viewDidLoad()
        
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


        // Do any additional setup after loading the view.
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func deleteAccount(_ sender: Any) {
        //delete the account from database
        //segue back to mainint
        self.performSegue(withIdentifier: "showMainIntViewController", sender: self)
        
    }

    @IBAction func backToMainInt(_ sender: Any) {
        //segue back to main
         self.performSegue(withIdentifier: "showMainIntViewController", sender: self)
    }
    
    func parseOutput(response: String) -> Array<String>{
        let strArray = response.components(separatedBy: "|")
        return strArray
    }
    
    func parseUnderscore(response: String) -> Array<String>{
        let strArray = response.components(separatedBy: "_")
        return strArray
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
