import { Component, OnInit } from '@angular/core';
import { Crest } from './crest.model';
import { Hive } from '../hive/hive.model';
import { CrestState } from './crest.model';
import { CrestService } from './crest.service';
import { NavController, AlertController } from '@ionic/angular';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'page-crest-detail',
  templateUrl: 'crest-detail.html',
})
export class CrestDetailPage implements OnInit {
  crest: Crest = {};
  hiveDisplayed: Hive;
  isFinalized = false;

  constructor(
    private navController: NavController,
    private crestService: CrestService,
    private activatedRoute: ActivatedRoute,
    private alertController: AlertController
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(response => {
      if(response.data.hive){
        this.hiveDisplayed = response.data.hive
      }

      this.crest = response.data;

      if(this.crest.state){
        this.crest.state = CrestState[this.crest.state]
      }

      this.isFinalized = this.crest.state === CrestState.FINALIZED;
    });
  }

  open(item: Crest) {
    this.navController.navigateForward('/tabs/entities/crest/' + item.id + '/edit');
  }

  async deleteModal(item: Crest) {
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
            this.crestService.delete(item.id).subscribe(() => {
              this.navController.navigateForward('/tabs/entities/crest');
            });
          },
        },
      ],
    });
    await alert.present();
  }
}
