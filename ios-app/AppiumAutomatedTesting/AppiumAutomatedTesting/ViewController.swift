//
//  ViewController.swift
//  AppiumAutomatedTesting
//
//  Created by RICHEZ Thibaut on 5/6/21.
//

import UIKit

class ViewController: UIViewController {    
    @IBOutlet private weak var label: UILabel!

    @IBAction private func buttonTapped(_ sender: UIButton) {
        self.label.text = "CLICKED"
    }
}

