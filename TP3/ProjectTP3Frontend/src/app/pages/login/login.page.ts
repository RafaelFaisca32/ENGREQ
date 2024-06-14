import { Component, OnInit } from '@angular/core';
import { NavController, ToastController } from '@ionic/angular';
import { TranslateService } from '@ngx-translate/core';
import { LoginService } from '../../services/login/login.service';
import { BeekeeperService } from "../entities/beekeeper";
import { NavigationExtras} from "@angular/router";
import { NetworkCheckerService } from '../../services/network/network-checker.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {
  // The account fields for the login form.
  account: { username: string; password: string; rememberMe: boolean } = {
    username: '',
    password: '',
    rememberMe: false,
  };

  // Our translated text strings
  private loginErrorString: string;

  constructor(
    public translateService: TranslateService,
    public loginService: LoginService,
    public beekeeperService: BeekeeperService,
    public toastController: ToastController,
    public navController: NavController,
    private network: NetworkCheckerService

  ) {}

  ngOnInit() {
    //this.network.openCheckNetwork();
    this.network.logNetworkState();

    this.translateService.get('LOGIN_ERROR').subscribe(value => {
      this.loginErrorString = value;
    });
    if(!this.network.onlineIndicator){
      this.doOfflineLogin();
    }
  }

  doOfflineLogin(){
    localStorage.setItem("offlineUser","true");
    this.navController.navigateRoot('/tabs');
  }

  doLogin() {
    this.loginService.login(this.account).then(
      () => {
        this.beekeeperService.query({'userId.equals': localStorage.getItem('userId')}).subscribe(
          data => {
            let beekeepers = data.body;
            if (beekeepers.length > 0){
              this.navController.navigateRoot('/tabs');
            } else {
              let navigationExtras : NavigationExtras;
              navigationExtras = { state : { from: 'newUser' }, queryParams : { from: 'newUser' } }
              this.navController.navigateRoot('/tabs/entities/beekeeper/new', navigationExtras);
            }
          });
      },
      async err => {
        // Unable to log in
        this.account.password = '';
        const toast = await this.toastController.create({
          message: this.loginErrorString,
          duration: 3000,
          position: 'top',
        });
        toast.present();
      }
    );
  }
}
