//
//  ViewController.swift
//  iosApp
//
//  Created by user on 24.05.2022.
//

import UIKit
import SampleKMM

class ViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet var table: UITableView!
    
    private var viewModel: MainViewModel!
    
    private var lauchesResult = [RocketLaunchEntitys]()
    
    private var isLoading: Bool = true

    override func viewDidLoad() {
        super.viewDidLoad()
        let eventsDispatcher = EventsDispatcher<MainViewModelEventListener>(listener: self)
        viewModel = MainViewModel(eventsDispatcher: eventsDispatcher)
        
        table.register(RocketTableViewCell.nib(), forCellReuseIdentifier: RocketTableViewCell.identifier)
        table.delegate = self
        table.dataSource = self
        table.rowHeight = UITableView.automaticDimension
        table.refreshControl = UIRefreshControl()
        table.refreshControl?.addTarget(self, action: #selector(callPullToRefresh), for: .valueChanged)
    }
    
    @objc func callPullToRefresh(){
        viewModel.getLaunches()
    }

    override func didMove(toParent parent: UIViewController?) {
        if(parent == nil) { viewModel.onCleared() }
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return lauchesResult.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: RocketTableViewCell.identifier, for: indexPath) as! RocketTableViewCell
        let launch = lauchesResult[indexPath.row]
        cell.configure(with: launch)
        return cell
    }
}

extension ViewController: MainViewModelEventListener {
    
    func hideLoading() {
        table.refreshControl?.endRefreshing()
    }
    
    func showLoading() {
        table.refreshControl?.programaticallyBeginRefreshing(in: table)
    }
    
    func onSuccess(result: [RocketLaunchEntitys]) {
        lauchesResult.append(contentsOf: result)
        table.reloadData()
    }
    
    func onError(error: String) {
        print("Error: \(error)")
    }
}

extension UIRefreshControl {
    func programaticallyBeginRefreshing(in tableView: UITableView) {
        beginRefreshing()
        let offsetPoint = CGPoint.init(x: 0, y: -frame.size.height)
        tableView.setContentOffset(offsetPoint, animated: true)
    }
}
