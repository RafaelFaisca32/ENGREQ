import { Component, OnInit } from '@angular/core';
import {Inspection, InspectionState} from './inspection.model';
import { InspectionService } from './inspection.service';
import { NavController, AlertController } from '@ionic/angular';
import { ActivatedRoute } from '@angular/router';
import {HiveService} from "../hive";
import { NetworkCheckerService } from '../../../services/network/network-checker.service';

@Component({
  selector: 'page-inspection-detail',
  templateUrl: 'inspection-detail.html',
})
export class InspectionDetailPage implements OnInit {
  inspection: Inspection = {};
  isAnulled: Boolean = false;
  colmeiaLabel: string;
  isFromApiary: boolean;

  constructor(
    private navController: NavController,
    private inspectionService: InspectionService,
    private hiveService: HiveService,
    private activatedRoute: ActivatedRoute,
    private alertController: AlertController,
    private network: NetworkCheckerService
  ) {
  }

  ngOnInit(): void {
    //this.network.openCheckNetwork();
    this.network.logNetworkState();
    this.activatedRoute.data.subscribe(response => {
      this.inspection = response.data;
      if (this.inspection.type){
        this.colmeiaLabel = "Colmeia";
        this.isFromApiary = false;
      } else {
        this.colmeiaLabel = "Apiário";
        this.isFromApiary = true;
      }
      if (this.inspection.type) {
        this.inspection.hiveId = this.inspection.hive.id;
      } else {
        this.hiveService.query({ 'id.equals': this.inspection.hive.id }).subscribe(
          data => {
            this.inspection.hiveId = data.body[0].apiary.id;
          },
          error => {}
        );
      }
      if (this.inspection.state.toString() == 'ANNULLED') {
        this.isAnulled = true;
      }
    });
  }

  open(item: Inspection) {
    this.navController.navigateForward('/tabs/entities/inspection/' + item.id + '/edit');
  }

  async deleteModal(item: Inspection) {
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
            item.state = InspectionState.ANNULLED;
            this.inspectionService.update(item).subscribe(() => {
              this.navController.navigateForward('/tabs/entities/inspection');
            });
          },
        },
      ],
    });
    await alert.present();
  }
}
