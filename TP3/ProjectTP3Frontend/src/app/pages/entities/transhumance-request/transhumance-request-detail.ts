import { Component, OnInit } from '@angular/core';
import { TranshumanceRequest } from './transhumance-request.model';
import { TranshumanceRequestService } from './transhumance-request.service';
import { NavController, AlertController } from '@ionic/angular';
import { ActivatedRoute } from '@angular/router';
import { NetworkCheckerService } from '../../../services/network/network-checker.service';

@Component({
  selector: 'page-transhumance-request-detail',
  templateUrl: 'transhumance-request-detail.html',
})
export class TranshumanceRequestDetailPage implements OnInit {
  transhumanceRequest: TranshumanceRequest = {};

  constructor(
    private navController: NavController,
    private transhumanceRequestService: TranshumanceRequestService,
    private activatedRoute: ActivatedRoute,
    private alertController: AlertController,
    private network: NetworkCheckerService
  ) {}

  ngOnInit(): void {
    //this.network.openCheckNetwork();
    this.network.logNetworkState();
    this.activatedRoute.data.subscribe(response => {
      this.transhumanceRequest = response.data;
    });
  }

  open(item: TranshumanceRequest) {
    this.navController.navigateForward('/tabs/entities/transhumance-request/' + item.id + '/edit');
  }

  async deleteModal(item: TranshumanceRequest) {
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
            this.transhumanceRequestService.delete(item.id).subscribe(() => {
              this.navController.navigateForward('/tabs/entities/transhumance-request');
            });
          },
        },
      ],
    });
    await alert.present();
  }
}
