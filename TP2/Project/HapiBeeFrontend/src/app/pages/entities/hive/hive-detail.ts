import { Component, OnInit } from '@angular/core';
import { Hive } from './hive.model';
import { HiveService } from './hive.service';
import { NavController, AlertController } from '@ionic/angular';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'page-hive-detail',
  templateUrl: 'hive-detail.html',
})
export class HiveDetailPage implements OnInit {
  hive: Hive = {};

  constructor(
    private navController: NavController,
    private hiveService: HiveService,
    private activatedRoute: ActivatedRoute,
    private alertController: AlertController
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(response => {
      this.hive = response.data;
      this.hive.apiaryId = this.hive.apiary.id;
    });
  }

  open(item: Hive) {
    this.navController.navigateForward('/tabs/entities/hive/' + item.id + '/edit');
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
