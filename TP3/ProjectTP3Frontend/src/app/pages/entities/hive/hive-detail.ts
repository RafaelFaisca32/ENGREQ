import { Component, OnInit } from '@angular/core';
import { Hive } from './hive.model';
import { HiveService } from './hive.service';
import { NavController, AlertController } from '@ionic/angular';
import {ActivatedRoute, NavigationExtras, Router} from '@angular/router';
import { NetworkCheckerService } from '../../../services/network/network-checker.service';

@Component({
  selector: 'page-hive-detail',
  templateUrl: 'hive-detail.html',
})
export class HiveDetailPage implements OnInit {
  hive: Hive = {};
  apiaryId: string;
  fromApiary: boolean;

  constructor(
    private navController: NavController,
    private hiveService: HiveService,
    private route: ActivatedRoute,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private alertController: AlertController,
    private network: NetworkCheckerService
  ) {
    this.route.queryParams.subscribe(params => {
      this.apiaryId = params.apiaryId;
      if (this.router.getCurrentNavigation().extras.state) {
        //this.from = this.router.getCurrentNavigation().extras.state.from;
        //console.log(this.from)
      }
    });
  }

  ngOnInit(): void {
    //this.network.openCheckNetwork();
    this.network.logNetworkState();
    this.activatedRoute.data.subscribe(response => {
      this.hive = response.data;
      this.hive.apiaryId = this.hive.apiary.id;
    });

    if (this.apiaryId){
      this.fromApiary = true;
    } else {
      this.fromApiary = false;
    }
  }

  open(item: Hive) {
    let navigationExtras : NavigationExtras  = { state : { apiaryId: this.hive.apiaryId }, queryParams : { apiaryId: this.hive.apiaryId  } }
    this.navController.navigateForward('/tabs/entities/hive/' + item.id + '/edit', navigationExtras);
  }

  async deleteModal(item: Hive) {
    const alert = await this.alertController.create({
      header: 'Confirm the deletion?',
      buttons: [
        {
          text: 'Cancel',
          role: 'cancel',
          cssClass: 'secondary',
        },
        {
          text: 'Delete',
          handler: () => {
            this.hiveService.delete(item.id).subscribe(() => {
              this.navController.navigateForward('/tabs/entities/hive');
            });
          },
        },
      ],
    });
    await alert.present();
  }
}
