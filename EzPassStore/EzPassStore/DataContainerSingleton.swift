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
    static let website = "websiteString"
    static let addUser = "addUserString"
}

/**
 :Class:   DataContainerSingleton
 This class is used to save app state data and share it between classes.
 It observes the system UIApplicationDidEnterBackgroundNotification and saves its properties to NSUserDefaults before
 entering the background.
 
*/

class DataContainerSingleton
{
    static let sharedDataContainer = DataContainerSingleton()
    
    //------------------------------------------------------------
    //Properties to share across app
    var userString: String?
    var passString: String?
    var cellText: String?
    var website: String?
    var addUser: String?
    //------------------------------------------------------------
    
    var goToBackgroundObserver: AnyObject?
    
    init()
    {
        //-----------------------------------------------------------------------------
        //sets default string value
        userString = ""
        passString = ""
        cellText = ""
        website = ""
        addUser = ""
        //-----------------------------------------------------------------------------
        
        //Add an obsever for the UIApplicationDidEnterBackgroundNotification.
        //When the app goes to the background, the code block saves properties to NSUserDefaults.
        goToBackgroundObserver = NotificationCenter.default.addObserver(
            forName: NSNotification.Name.UIApplicationDidEnterBackground,
            object: nil,
            queue: nil)
        {
            (note: Notification) -> Void in
            let defaults = UserDefaults.standard
            //-----------------------------------------------------------------------------
            //saves properties
            defaults.set( self.userString, forKey: DefaultsKeys.userString)
            defaults.set( self.passString, forKey: DefaultsKeys.passString)
            defaults.set( self.cellText, forKey: DefaultsKeys.cellText)
             defaults.set( self.website, forKey: DefaultsKeys.website)
             defaults.set( self.addUser, forKey: DefaultsKeys.addUser)
            //-----------------------------------------------------------------------------
            
            //save
            defaults.synchronize()
        }
    }
}
