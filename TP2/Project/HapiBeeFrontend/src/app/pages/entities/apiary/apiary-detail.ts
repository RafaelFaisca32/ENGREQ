import { Component, OnInit } from '@angular/core';
import { Apiary } from './apiary.model';
import { ApiaryService } from './apiary.service';
import { NavController, AlertController } from '@ionic/angular';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'page-apiary-detail',
  templateUrl: 'apiary-detail.html',
})
export class ApiaryDetailPage implements OnInit {
  apiary: Apiary = {};

  constructor(
    private navController: NavController,
    private apiaryService: ApiaryService,
    private activatedRoute: ActivatedRoute,
    private alertController: AlertController
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(response => {
      this.apiary = response.data;
    });
  }

  open(item: Apiary) {
    this.navController.navigateForward('/tabs/entities/apiary/' + item.id + '/edit');
  }

  async deleteModal(item: Apiary) {
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
            this.apiaryService.delete(item.id).subscribe(() => {
              this.navController.navigateForward('/tabs/entities/apiary');
            });
          },
        },
      ],
    });
    await alert.present();
  }
}
