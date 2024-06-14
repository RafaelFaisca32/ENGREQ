import { Component, OnInit } from '@angular/core';
import { Zone } from './zone.model';
import { ZoneService } from './zone.service';
import { NavController, AlertController } from '@ionic/angular';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'page-zone-detail',
  templateUrl: 'zone-detail.html',
})
export class ZoneDetailPage implements OnInit {
  zone: Zone = {};

  constructor(
    private navController: NavController,
    private zoneService: ZoneService,
    private activatedRoute: ActivatedRoute,
    private alertController: AlertController
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(response => {
      this.zone = response.data;
    });
  }

  open(item: Zone) {
    this.navController.navigateForward('/tabs/entities/zone/' + item.id + '/edit');
  }

  async deleteModal(item: Zone) {
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
            this.zoneService.delete(item.id).subscribe(() => {
              this.navController.navigateForward('/tabs/entities/zone');
            });
          },
        },
      ],
    });
    await alert.present();
  }
}
