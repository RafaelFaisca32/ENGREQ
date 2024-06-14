import { Component, OnInit } from '@angular/core';
import { NavController, ToastController, Platform, IonItemSliding } from '@ionic/angular';
import { filter, map } from 'rxjs/operators';
import { HttpResponse } from '@angular/common/http';
import { Inspection } from './inspection.model';
import { InspectionService } from './inspection.service';
import { NetworkCheckerService } from '../../../services/network/network-checker.service';

@Component({
  selector: 'page-inspection',
  templateUrl: 'inspection.html',
})
export class InspectionPage implements OnInit {
  inspections: Inspection[];

  // todo: add pagination

  constructor(
    private navController: NavController,
    private inspectionService: InspectionService,
    private toastCtrl: ToastController,
    public plt: Platform,
    private network: NetworkCheckerService
  ) {
    this.inspections = [];
  }

  ngOnInit(){
    //this.network.openCheckNetwork();
    this.network.logNetworkState();
  }

  async ionViewWillEnter() {
    await this.loadAll();
  }

  async loadAll(refresher?) {
    this.inspectionService
      .query()
      .pipe(
        filter((res: HttpResponse<Inspection[]>) => res.ok),
        map((res: HttpResponse<Inspection[]>) => res.body)
      )
      .subscribe(
        (response: Inspection[]) => {
          this.inspections = response;
          if (typeof refresher !== 'undefined') {
            setTimeout(() => {
              refresher.target.complete();
            }, 750);
          }
        },
        async error => {
          console.error(error);
          const toast = await this.toastCtrl.create({ message: 'Failed to load data', duration: 2000, position: 'middle' });
          await toast.present();
        }
      );
  }

  trackId(index: number, item: Inspection) {
    return item.id;
  }

  async new() {
    await this.navController.navigateForward('/tabs/entities/inspection/new');
  }

  async edit(item: IonItemSliding, inspection: Inspection) {
    await this.navController.navigateForward('/tabs/entities/inspection/' + inspection.id + '/edit');
    await item.close();
  }

  async delete(inspection) {
    this.inspectionService.delete(inspection.id).subscribe(
      async () => {
        const toast = await this.toastCtrl.create({ message: 'Inspection deleted successfully.', duration: 3000, position: 'middle' });
        await toast.present();
        await this.loadAll();
      },
      error => console.error(error)
    );
  }

  async view(inspection: Inspection) {
    await this.navController.navigateForward('/tabs/entities/inspection/' + inspection.id + '/view');
  }
}
