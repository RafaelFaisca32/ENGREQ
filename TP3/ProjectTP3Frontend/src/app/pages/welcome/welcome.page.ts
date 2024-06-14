import { Component, OnInit } from '@angular/core';
import { NavController } from '@ionic/angular';
import { AccountService } from '../../services/auth/account.service';
import { NetworkCheckerService } from '../../services/network/network-checker.service';


@Component({
  selector: 'app-welcome',
  templateUrl: 'welcome.page.html',
  styleUrls: ['welcome.page.scss'],
})
export class WelcomePage implements OnInit {
  constructor(private accountService: AccountService, private navController: NavController, private network: NetworkCheckerService
    ) {}

  ngOnInit() {
    //this.network.openCheckNetwork();
    this.network.logNetworkState();
    if(this.network.onlineIndicator){
      localStorage.setItem("offlineUser","false");
      this.accountService.identity().then(account => {
        if (account) {
          this.navController.navigateRoot('/tabs');
        }
      });
    }
  }

  goToOffline(){
    if(!this.network.onlineIndicator){
      localStorage.setItem("offlineUser","true");
      this.navController.navigateRoot('/tabs');
    } else {
      this.navController.navigateRoot('/login');
    }
  }
}
