//
//  InfoViewController.swift
//  Secuure
//
//  Created by Alexander Ou on 11/16/16.
//  Copyright © 2016 HashMappers. All rights reserved.
//

import UIKit

class InfoViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()

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
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
