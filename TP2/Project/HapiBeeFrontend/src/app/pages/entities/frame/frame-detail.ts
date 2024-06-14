import { Component, OnInit } from '@angular/core';
import { Frame } from './frame.model';
import { FrameService } from './frame.service';
import { NavController, AlertController } from '@ionic/angular';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'page-frame-detail',
  templateUrl: 'frame-detail.html',
})
export class FrameDetailPage implements OnInit {
  frame: Frame = {};

  constructor(
    private navController: NavController,
    private frameService: FrameService,
    private activatedRoute: ActivatedRoute,
    private alertController: AlertController
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(response => {
      this.frame = response.data;
    });
  }

  open(item: Frame) {
    this.navController.navigateForward('/tabs/entities/frame/' + item.id + '/edit');
  }

  async deleteModal(item: Frame) {
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
            this.frameService.delete(item.id).subscribe(() => {
              this.navController.navigateForward('/tabs/entities/frame');
            });
          },
        },
      ],
    });
    await alert.present();
  }
}
