//
//  DataContainerSingleton.swift
//  Secuure
//
//  Created by Alexander Ou on 11/20/16.
//  Copyright © 2016 HashMappers. All rights reserved.
//

import Foundation
import UIKit


/**
 This struct defines the keys used to save the data container singleton's properties to NSUserDefaults.
 This is the "Swift way" to define string constants.
 */
struct DefaultsKeys
{
    static let userString  = "userString"
    static let passString  = "passString"
    static let cellText  = "cellText"
}

/**
 :Class:   DataContainerSingleton
 This class is used to save app state data and share it between classes.
 It observes the system UIApplicationDidEnterBackgroundNotification and saves its properties to NSUserDefaults before
 entering the background.
 
 Use the syntax `DataContainerSingleton.sharedDataContainer` to reference the shared data container singleton
 */

class DataContainerSingleton
{
    static let sharedDataContainer = DataContainerSingleton()
    
    //------------------------------------------------------------
    //Add properties here that you want to share accross your app
    var userString: String?
    var passString: String?
    var cellText: String?
    //------------------------------------------------------------
    
    var goToBackgroundObserver: AnyObject?
    
    init()
    {
        //-----------------------------------------------------------------------------
        //This code reads the singleton's properties from NSUserDefaults.
        //edit this code to load your custom properties
        userString = ""
        passString = ""
        cellText = ""
        //-----------------------------------------------------------------------------
        
        //Add an obsever for the UIApplicationDidEnterBackgroundNotification.
        //When the app goes to the background, the code block saves our properties to NSUserDefaults.
        goToBackgroundObserver = NotificationCenter.default.addObserver(
            forName: NSNotification.Name.UIApplicationDidEnterBackground,
            object: nil,
            queue: nil)
        {
            (note: Notification) -> Void in
            let defaults = UserDefaults.standard
            //-----------------------------------------------------------------------------
            //This code saves the singleton's properties to NSUserDefaults.
            //edit this code to save your custom properties
            defaults.set( self.userString, forKey: DefaultsKeys.userString)
            defaults.set( self.passString, forKey: DefaultsKeys.passString)
            defaults.set( self.cellText, forKey: DefaultsKeys.cellText)
            //-----------------------------------------------------------------------------
            
            //Tell NSUserDefaults to save to disk now.
            defaults.synchronize()
        }
    }
}
