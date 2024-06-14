import { Component, OnInit } from '@angular/core';
import { NavController, ToastController, Platform, IonItemSliding } from '@ionic/angular';
import { ActivatedRoute, NavigationExtras, Router  } from "@angular/router";
import { filter, map } from 'rxjs/operators';
import { HttpResponse } from '@angular/common/http';
import { Crest, CrestState } from './crest.model';
import { CrestService } from './crest.service';
import { NetworkCheckerService } from '../../../services/network/network-checker.service';

@Component({
  selector: 'page-crest',
  templateUrl: 'crest.html',
})
export class CrestPage implements OnInit {
  crests: Crest[];
  apiaryId: string;
  // todo: add pagination

  constructor(
    private navController: NavController,
    private crestService: CrestService,
    private toastCtrl: ToastController,
    public plt: Platform,
    private route: ActivatedRoute,
    private router: Router,
    private network: NetworkCheckerService
  ) {
    this.crests = [];
    this.route.queryParams.subscribe(params => {
      this.apiaryId = params.apiaryId
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
    this.crestService
      .queryByUser()
      .pipe(
        filter((res: HttpResponse<Crest[]>) => res.ok),
        map((res: HttpResponse<Crest[]>) => res.body)
      )
      .subscribe(
        (response: Crest[]) => {
          this.crests = response;
          this.crests.forEach(function(el){
            if(el.state){
              el.state = CrestState[el.state]
            }
          })
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

  trackId(index: number, item: Crest) {
    return item.id;
  }

  async new() {
    await this.navController.navigateForward('/tabs/entities/crest/new');
  }

  async edit(item: IonItemSliding, crest: Crest) {
    await this.navController.navigateForward('/tabs/entities/crest/' + crest.id + '/edit');
    await item.close();
  }

  async delete(crest) {
    this.crestService.delete(crest.id).subscribe(
      async () => {
        const toast = await this.toastCtrl.create({ message: 'Crest deleted successfully.', duration: 3000, position: 'middle' });
        await toast.present();
        await this.loadAll();
      },
      error => console.error(error)
    );
  }

  async view(crest: Crest) {
    await this.navController.navigateForward('/tabs/entities/crest/' + crest.id + '/view');
  }
}
