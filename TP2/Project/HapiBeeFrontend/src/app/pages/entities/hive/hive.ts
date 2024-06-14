import { Component } from '@angular/core';
import { NavController, ToastController, Platform, IonItemSliding } from '@ionic/angular';
import { filter, map } from 'rxjs/operators';
import { HttpResponse } from '@angular/common/http';
import { Hive } from './hive.model';
import { HiveService } from './hive.service';
import {ActivatedRoute, NavigationExtras, Router} from "@angular/router";

@Component({
  selector: 'page-hive',
  templateUrl: 'hive.html',
})
export class HivePage {
  hives: Hive[];
  from: String;
  apiaryId: string;

  // todo: add pagination

  constructor(
    private navController: NavController,
    private hiveService: HiveService,
    private toastCtrl: ToastController,
    public plt: Platform,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.hives = [];

    this.route.queryParams.subscribe(params => {
      this.from = params.from
      this.apiaryId = params.apiaryId
      if (this.router.getCurrentNavigation().extras.state) {
        //this.from = this.router.getCurrentNavigation().extras.state.from;
        //console.log(this.from)
      }
    });
  }

  async ionViewWillEnter() {
    await this.loadAll();
  }

  async loadAll(refresher?) {
    let queryParams = {};
    if(!!this.apiaryId){
      queryParams =  { 'apiaryId.equals': this.apiaryId };
    }
    this.hiveService
      .query(queryParams)
      .pipe(
        filter((res: HttpResponse<Hive[]>) => res.ok),
        map((res: HttpResponse<Hive[]>) => res.body)
      )
      .subscribe(
        (response: Hive[]) => {
          this.hives = response;
          for (let i =0;i<this.hives.length;i++){
            this.hives[i].apiaryId = this.hives[i].apiary.id;
          }
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

  trackId(index: number, item: Hive) {
    return item.id;
  }

  async new() {
    await this.navController.navigateForward('/tabs/entities/hive/new');
  }

  async edit(item: IonItemSliding, hive: Hive) {
    await this.navController.navigateForward('/tabs/entities/hive/' + hive.id + '/edit');
    await item.close();
  }

  async delete(hive) {
    this.hiveService.delete(hive.id).subscribe(
      async () => {
        const toast = await this.toastCtrl.create({ message: 'Hive deleted successfully.', duration: 3000, position: 'middle' });
        await toast.present();
        await this.loadAll();
      },
      error => console.error(error)
    );
  }

  async view(hive: Hive) {
    await this.navController.navigateForward('/tabs/entities/hive/' + hive.id + '/view');
  }
}
