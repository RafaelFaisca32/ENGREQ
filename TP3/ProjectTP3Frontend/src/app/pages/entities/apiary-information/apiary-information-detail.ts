import { Component, OnInit } from '@angular/core';
import { ApiaryInformation } from './apiary-information.model';
import { ApiaryInformationService } from './apiary-information.service';
import { NavController, AlertController } from '@ionic/angular';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'page-apiary-information-detail',
  templateUrl: 'apiary-information-detail.html',
})
export class ApiaryInformationDetailPage implements OnInit {
  apiaryInformation: ApiaryInformation = {};

  constructor(
    private navController: NavController,
    private apiaryInformationService: ApiaryInformationService,
    private activatedRoute: ActivatedRoute,
    private alertController: AlertController
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(response => {
      this.apiaryInformation = response.data;
    });
  }

  open(item: ApiaryInformation) {
    this.navController.navigateForward('/tabs/entities/apiary-information/' + item.id + '/edit');
  }

  async deleteModal(item: ApiaryInformation) {
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
            this.apiaryInformationService.delete(item.id).subscribe(() => {
              this.navController.navigateForward('/tabs/entities/apiary-information');
            });
          },
        },
      ],
    });
    await alert.present();
  }
}
