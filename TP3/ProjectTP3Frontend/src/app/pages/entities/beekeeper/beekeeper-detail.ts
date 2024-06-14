import { Component, OnInit } from '@angular/core';
import { Beekeeper } from './beekeeper.model';
import { BeekeeperService } from './beekeeper.service';
import { NavController, AlertController } from '@ionic/angular';
import { ActivatedRoute } from '@angular/router';
import { NetworkCheckerService } from '../../../services/network/network-checker.service';

@Component({
  selector: 'page-beekeeper-detail',
  templateUrl: 'beekeeper-detail.html',
})
export class BeekeeperDetailPage implements OnInit {
  beekeeper: Beekeeper = {};

  constructor(
    private navController: NavController,
    private beekeeperService: BeekeeperService,
    private activatedRoute: ActivatedRoute,
    private alertController: AlertController,
    private network: NetworkCheckerService
  ) {}

  ngOnInit(): void {
    //this.network.openCheckNetwork();
    this.network.logNetworkState();
    this.activatedRoute.data.subscribe(response => {
      this.beekeeper = response.data;
    });
  }

  open(item: Beekeeper) {
    this.navController.navigateForward('/tabs/entities/beekeeper/' + item.id + '/edit');
  }

  async deleteModal(item: Beekeeper) {
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
            this.beekeeperService.delete(item.id).subscribe(() => {
              this.navController.navigateForward('/tabs/entities/beekeeper');
            });
          },
        },
      ],
    });
    await alert.present();
  }
}
