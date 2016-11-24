//
//  PassViewController.swift
//  EzPassStore
//
//  Created by Alexander Ou on 10/29/16.
//  Copyright © 2016 HashMappers. All rights reserved.
//

import UIKit

class PassViewController: UIViewController {

    var transferGenPass = false
    
    @IBOutlet weak var symbolSwitch: UISwitch!
    @IBOutlet weak var numSwitch: UISwitch!
    @IBOutlet weak var capSwitch: UISwitch!
    @IBOutlet weak var generatedPass: UILabel!
    @IBOutlet weak var sliderLabel: UILabel!
    @IBOutlet weak var sliderOutlet: UISlider!
    override func viewDidLoad() {
        super.viewDidLoad()

        //init properties
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
    
    //segue from signupview to emailverifyviews and pass data
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if(transferGenPass) {
            let destination = segue.destination as! AddViewController
            destination.genPass = generatedPass.text!
           
        }
        
    }

    
    @IBAction func slider(_ sender: AnyObject) {
        sliderLabel.text = String(Int(sliderOutlet.value))
    }
    @IBAction func cancelButton(_ sender: AnyObject) {
    }
    
    @IBAction func okayButton(_ sender: AnyObject) {
        transferGenPass = true
    }
    
    @IBAction func generateButton(_ sender: AnyObject) {
        
        let genPass = randomString(length: Int(sliderOutlet.value), flagletter: capSwitch.isOn, flagnum: numSwitch.isOn, flagspecial: symbolSwitch.isOn)
        print(genPass)
        generatedPass.text = genPass
    }

    //pass generator function
    func randomString(length: Int, flagletter: Bool, flagnum: Bool, flagspecial: Bool) -> String {
        
        var letters : NSString = ""
        
        if(flagletter && !flagnum && !flagspecial) {
            letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
        }
        else if(!flagletter && flagnum && !flagspecial) {
            letters = "abcdefghijklmnopqrstuvwxyz0123456789"
        }
        else if(!flagletter && !flagnum && flagspecial) {
            letters = "abcdefghijklmnopqrstuvwxyz!@#$%^*()"
        }
        else if(flagletter && flagnum && !flagspecial) {
            letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
        }
        else if(flagletter && !flagnum && flagspecial) {
            letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^*()"
        }
        else if(!flagletter && flagnum && flagspecial) {
            letters = "abcdefghijklmnopqrstuvwxyz0123456789!@#$%^*()"
        }
        else if(flagletter && flagnum && flagspecial) {
            letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^*()"
        }else if(!flagletter && !flagnum && !flagspecial) {
            letters = "abcdefghijklmnopqrstuvwxyz"
        }
        
        let len = UInt32(letters.length)
        
        var randomString = ""
        
        for _ in 0 ..< length {
            let rand = arc4random_uniform(len)
            var nextChar = letters.character(at: Int(rand))
            randomString += NSString(characters: &nextChar, length: 1) as String
        }
        
        return randomString
    }

}
