import { Component, OnInit } from '@angular/core';
import { NavController, ToastController, Platform, IonItemSliding } from '@ionic/angular';
import { filter, map } from 'rxjs/operators';
import { HttpResponse } from '@angular/common/http';
import { Apiary } from './apiary.model';
import { ApiaryService } from './apiary.service';
import { NavigationExtras, Router, ActivatedRoute } from "@angular/router";
import { HiveService } from "../hive/hive.service";
import { Hive } from "../hive";
import { NetworkCheckerService } from '../../../services/network/network-checker.service';

@Component({
  selector: 'page-apiary',
  templateUrl: 'apiary.html',
})
export class ApiaryPage implements OnInit {
  apiaries: Apiary[];
  from: String;

  // todo: add pagination

  constructor(
    private navController: NavController,
    private apiaryService: ApiaryService,
    private hiveService: HiveService,
    private toastCtrl: ToastController,
    public plt: Platform,
    private route: ActivatedRoute,
    private router: Router,
    private network: NetworkCheckerService
  ) {
    this.apiaries = [];

    this.route.queryParams.subscribe(params => {
      this.from = params.from
      if (this.router.getCurrentNavigation().extras.state) {
        //this.from = this.router.getCurrentNavigation().extras.state.from;
        //console.log(this.from)
      }
    });
  }

  ngOnInit(){
    //this.network.openCheckNetwork();
    this.network.logNetworkState();
  }

  async ionViewWillEnter() {
    await this.loadAll();
  }

  async loadAll(refresher?) {
    let req;
    let beekeeperId = localStorage.getItem("beekeeperId")

    switch(this.from){
      case 'createCrest':
      case 'createUnfolding':
      case 'createInspection':
        req = {  'state.equals' : 'APPROVED'  };
        if(!!beekeeperId){
          req = {  'state.equals' : 'APPROVED',
            'beekeeperId.equals' : beekeeperId  };
        }

        break;
      default:
        if(!!beekeeperId){
          req = {  'beekeeperId.equals' : beekeeperId };
        }
    }
    this.apiaryService
      .query(req)
      .pipe(
        filter((res: HttpResponse<Apiary[]>) => res.ok),
        map((res: HttpResponse<Apiary[]>) => res.body)
      )
      .subscribe(
        (response: Apiary[]) => {
          this.apiaries = [];

          let apiariesAux = response;

          if (this.from == "createUnfolding" || this.from == "createCrest" || this.from == "createInspection"){
            for(let i=0;i<apiariesAux.length;i++){
              this.hiveService
                .query({ 'apiaryId.equals' : apiariesAux[i].id})
                .pipe(
                  filter((res: HttpResponse<Hive[]>) => res.ok),
                  map((res: HttpResponse<Hive[]>) => res.body)
                )
                .subscribe(
                  (responseHives: Hive[]) => {
                    if (responseHives.length>0){
                      this.apiaries.push(apiariesAux[i]);
                    }
                  },
                )
            }
          } else {
            this.apiaries = apiariesAux;
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

  trackId(index: number, item: Apiary) {
    return item.id;
  }

  async new() {
    await this.navController.navigateForward('/tabs/entities/apiary/new');
  }

  async edit(item: IonItemSliding, apiary: Apiary) {
    await this.navController.navigateForward('/tabs/entities/apiary/' + apiary.id + '/edit');
    await item.close();
  }

  async delete(apiary) {
    this.apiaryService.delete(apiary.id).subscribe(
      async () => {
        const toast = await this.toastCtrl.create({ message: 'Apiary deleted successfully.', duration: 3000, position: 'middle' });
        await toast.present();
        await this.loadAll();
      },
      error => console.error(error)
    );
  }

  async view(apiary: Apiary) {
    let navigationExtras : NavigationExtras;
    switch(this.from){
      case 'createCrest':
        navigationExtras = { state : { apiaryId: apiary.id }, queryParams : { apiaryId: apiary.id  } }
        await this.navController.navigateForward('/tabs/entities/crest/new' , navigationExtras);
        break;
      case 'createUnfolding':
        navigationExtras  = { state : { apiaryId: apiary.id }, queryParams : { apiaryId: apiary.id, from: 'createUnfolding' } }
        await this.navController.navigateForward('/tabs/entities/unfolding/new' , navigationExtras);
        break;
      case 'createInspection':
        navigationExtras  = { state : { apiaryId: apiary.id }, queryParams : { apiaryId: apiary.id, from: 'createUnfolding' } }
        await this.navController.navigateForward('/tabs/entities/inspection/new' , navigationExtras);
        break;
      default:
        await this.navController.navigateForward('/tabs/entities/apiary/' + apiary.id + '/view');
    }
  }
  async isToCreate(){
    return this.from == "" || this.from == null || this.from == undefined || this.from == "new";
  }
}
