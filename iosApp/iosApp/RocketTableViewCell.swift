//
//  RocketTableViewCell.swift
//  iosApp
//
//  Created by user on 24.05.2022.
//

import UIKit
import SampleKMM

class RocketTableViewCell: UITableViewCell {
    
    static let identifier = "RocketTableViewCell"
    
    static func nib() -> UINib {
        return UINib(nibName: identifier, bundle: nil)
    }
    
    public func configure(with model: RocketLaunchEntitys) {
        self.name.text = "Launch name: \(model.missionName)"
        if model.launchSuccess as! Bool {
            self.status.text = "Successful"
            self.status.textColor = UIColor.green
        } else {
            self.status.text = "Unsuccessful"
            self.status.textColor = UIColor.red
        }
        self.year.text = "Launch year: \(model.launchYear)"
        self.detail.text = "Launch details: \(model.details ?? "value")"
    }
    
    @IBOutlet var name: UILabel!
    @IBOutlet var status: UILabel!
    @IBOutlet var year: UILabel!
    @IBOutlet var detail: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
}
