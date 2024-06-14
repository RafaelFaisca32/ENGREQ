import { Component, OnInit } from '@angular/core';
import { Inspection } from './inspection.model';
import { InspectionService } from './inspection.service';
import { NavController, AlertController } from '@ionic/angular';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'page-inspection-detail',
  templateUrl: 'inspection-detail.html',
})
export class InspectionDetailPage implements OnInit {
  inspection: Inspection = {};

  constructor(
    private navController: NavController,
    private inspectionService: InspectionService,
    private activatedRoute: ActivatedRoute,
    private alertController: AlertController
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(response => {
      this.inspection = response.data;
    });
  }

  open(item: Inspection) {
    this.navController.navigateForward('/tabs/entities/inspection/' + item.id + '/edit');
  }

  async deleteModal(item: Inspection) {
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
            this.inspectionService.delete(item.id).subscribe(() => {
              this.navController.navigateForward('/tabs/entities/inspection');
            });
          },
        },
      ],
    });
    await alert.present();
  }
}
