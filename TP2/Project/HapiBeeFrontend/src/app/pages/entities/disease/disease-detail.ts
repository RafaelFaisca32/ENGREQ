import { Component, OnInit } from '@angular/core';
import { Disease } from './disease.model';
import { DiseaseService } from './disease.service';
import { NavController, AlertController } from '@ionic/angular';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'page-disease-detail',
  templateUrl: 'disease-detail.html',
})
export class DiseaseDetailPage implements OnInit {
  disease: Disease = {};

  constructor(
    private navController: NavController,
    private diseaseService: DiseaseService,
    private activatedRoute: ActivatedRoute,
    private alertController: AlertController
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(response => {
      this.disease = response.data;
    });
  }

  open(item: Disease) {
    this.navController.navigateForward('/tabs/entities/disease/' + item.id + '/edit');
  }

  async deleteModal(item: Disease) {
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
            this.diseaseService.delete(item.id).subscribe(() => {
              this.navController.navigateForward('/tabs/entities/disease');
            });
          },
        },
      ],
    });
    await alert.present();
  }
}
