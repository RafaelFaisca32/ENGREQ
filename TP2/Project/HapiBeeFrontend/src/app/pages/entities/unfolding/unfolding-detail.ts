import { Component, OnInit } from '@angular/core';
import { Unfolding } from './unfolding.model';
import { UnfoldingService } from './unfolding.service';
import { NavController, AlertController } from '@ionic/angular';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'page-unfolding-detail',
  templateUrl: 'unfolding-detail.html',
})
export class UnfoldingDetailPage implements OnInit {
  unfolding: Unfolding = {};

  constructor(
    private navController: NavController,
    private unfoldingService: UnfoldingService,
    private activatedRoute: ActivatedRoute,
    private alertController: AlertController
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(response => {
      this.unfolding = response.data;
      this.unfolding.hiveId = this.unfolding.hive.id;
    });
  }

  open(item: Unfolding) {
    this.navController.navigateForward('/tabs/entities/unfolding/' + item.id + '/edit');
  }

  async deleteModal(item: Unfolding) {
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
            this.unfoldingService.delete(item.id).subscribe(() => {
              this.navController.navigateForward('/tabs/entities/unfolding');
            });
          },
        },
      ],
    });
    await alert.present();
  }
}
