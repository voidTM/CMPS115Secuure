//
//  EmailVerifyViewController.swift
//  EzPassStore
//
//  Created by Alexander Ou on 10/18/16.
//  Copyright © 2016 HashMappers. All rights reserved.
//

import UIKit

class EmailVerifyViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        
        //init view properties
        self.view.backgroundColor = UIColor(patternImage: UIImage(named:"secuurebackground.jpg")!)
        
        UIGraphicsBeginImageContext(self.view.frame.size)
        UIImage(named: "secuurebackground.jpg")?.draw(in: self.view.bounds)
        
        let image: UIImage = UIGraphicsGetImageFromCurrentImageContext()!
        
        UIGraphicsEndImageContext()
        
        self.view.backgroundColor = UIColor(patternImage: image)

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    //segue from email verification view to login view
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "showViewController" {
            _ = segue.destination as! ViewController
            
        }
    }
}
