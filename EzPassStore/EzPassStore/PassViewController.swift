//
//  PassViewController.swift
//  EzPassStore
//
//  Created by Alexander Ou on 10/29/16.
//  Copyright © 2016 HashMappers. All rights reserved.
//

import UIKit

class PassViewController: UIViewController {

    @IBOutlet weak var generatedPass: UILabel!
    @IBOutlet weak var sliderLabel: UILabel!
    @IBOutlet weak var sliderOutlet: UISlider!
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
        if segue.identifier == "showAddViewController" {
            _ = segue.destination as! AddViewController
            
        }
        
    }
    
    //conditionals to making the segue
    override func shouldPerformSegue(withIdentifier identifier: String, sender: Any?) -> Bool {
        if(identifier == "showAddViewController") {
            //if all fields are filled, return true
            return true;
        }
        return false
    }
    
    @IBAction func slider(_ sender: AnyObject) {
        sliderLabel.text = String(Int(sliderOutlet.value))
    }
    @IBAction func cancelButton(_ sender: AnyObject) {
    }
    
    @IBAction func okayButton(_ sender: AnyObject) {
    }
    
    @IBAction func generateButton(_ sender: AnyObject) {
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
