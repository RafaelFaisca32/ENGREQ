import {Component, OnInit} from '@angular/core';
import {Unfolding, UnfoldingState} from './unfolding.model';
import {UnfoldingService} from './unfolding.service';
import {AlertController, NavController} from '@ionic/angular';
import {ActivatedRoute} from '@angular/router';
import { NetworkCheckerService } from '../../../services/network/network-checker.service';

@Component({
  selector: 'page-unfolding-detail',
  templateUrl: 'unfolding-detail.html',
})
export class UnfoldingDetailPage implements OnInit {
  unfolding: Unfolding = {};
  isAnulled: Boolean = false;

  constructor(
    private navController: NavController,
    private unfoldingService: UnfoldingService,
    private activatedRoute: ActivatedRoute,
    private alertController: AlertController,
    private network: NetworkCheckerService
  ) {}

  ngOnInit(): void {
    //this.network.openCheckNetwork();
    this.network.logNetworkState();
    this.activatedRoute.data.subscribe(response => {
      this.unfolding = response.data;
      this.unfolding.hiveId = this.unfolding.hive.id;
      this.unfolding.createdHiveId = this.unfolding.createdHive.id;
      if (this.unfolding.state.toString() == 'ANNULLED') {
        this.isAnulled = true;
      }
    });
  }

  open(item: Unfolding) {
    this.navController.navigateForward('/tabs/entities/unfolding/' + item.id + '/edit');
  }

  async deleteModal(item: Unfolding) {
    const alert = await this.alertController.create({
      header: 'Confirma a anulação?',
      buttons: [
        {
          text: 'Cancelar',
          role: 'cancel',
          cssClass: 'secondary',
        },
        {
          text: 'Anular',
          handler: () => {
            item.state = UnfoldingState.ANNULLED;
            this.unfoldingService.update(item).subscribe(() => {
              this.navController.navigateForward('/tabs/entities/unfolding');
            });
          },
        },
      ],
    });
    await alert.present();
  }
}
