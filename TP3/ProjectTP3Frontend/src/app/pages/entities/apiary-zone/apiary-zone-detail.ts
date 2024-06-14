import { Component, OnInit } from '@angular/core';
import { ApiaryZone } from './apiary-zone.model';
import { ApiaryZoneService } from './apiary-zone.service';
import { NavController, AlertController } from '@ionic/angular';
import { ActivatedRoute } from '@angular/router';
import { NetworkCheckerService } from '../../../services/network/network-checker.service';

@Component({
  selector: 'page-apiary-zone-detail',
  templateUrl: 'apiary-zone-detail.html',
})
export class ApiaryZoneDetailPage implements OnInit {
  apiaryZone: ApiaryZone = {};

  constructor(
    private navController: NavController,
    private apiaryZoneService: ApiaryZoneService,
    private activatedRoute: ActivatedRoute,
    private alertController: AlertController,
    private network: NetworkCheckerService
  ) {}

  ngOnInit(): void {
    //this.network.openCheckNetwork();
    this.network.logNetworkState();

    this.activatedRoute.data.subscribe(response => {
      this.apiaryZone = response.data;
    });
  }

  open(item: ApiaryZone) {
    this.navController.navigateForward('/tabs/entities/apiary-zone/' + item.id + '/edit');
  }

  async deleteModal(item: ApiaryZone) {
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
            this.apiaryZoneService.delete(item.id).subscribe(() => {
              this.navController.navigateForward('/tabs/entities/apiary-zone');
            });
          },
        },
      ],
    });
    await alert.present();
  }
}
