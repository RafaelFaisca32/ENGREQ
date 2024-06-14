import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { NavController, Platform, ToastController } from '@ionic/angular';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, NavigationExtras } from '@angular/router';
import { Observable } from 'rxjs';
import { Apiary, ApiaryState } from './apiary.model';
import { ApiaryService } from './apiary.service';
import { Zone, ZoneService } from '../zone';
import { Beekeeper, BeekeeperService } from '../beekeeper';
import {filter, map} from "rxjs/operators";
import {Hive} from "../hive";

@Component({
  selector: 'page-apiary-update',
  templateUrl: 'apiary-update.html',
})
export class ApiaryUpdatePage implements OnInit {
  apiary: Apiary;
  zones: Zone[];
  beekeepers: Beekeeper[];
  isSaving = false;
  isNew = true;
  isReadyToSave: boolean;

  form = this.formBuilder.group({
    id: [null, []],
    name: [null, [Validators.required]],
    state: [null, []],
    number: [null, [Validators.required]],
    intensive: ['false', [Validators.required]],
    transhumance: ['false', [Validators.required]],
    zoneId: [null, [Validators.required]],
    beekeeperId: [null, []],
  });

  constructor(
    protected activatedRoute: ActivatedRoute,
    protected navController: NavController,
    protected formBuilder: FormBuilder,
    public platform: Platform,
    protected toastCtrl: ToastController,
    private zoneService: ZoneService,
    private beekeeperService: BeekeeperService,
    private apiaryService: ApiaryService
  ) {
    // Watch the form for changes, and
    this.form.valueChanges.subscribe(v => {
      this.isReadyToSave = this.form.valid;
    });
  }

  ngOnInit() {
    this.zoneService.query({ filter: 'apiary-is-null' }).subscribe(
      data => {
        this.zones = [];
        if (!this.apiary.zoneId) {
          for (let i=0;i<data.body.length;i++){
            this.apiaryService.query({'zoneId.equals':data.body[i].id}).pipe(
              filter((res: HttpResponse<Apiary[]>) => res.ok),
              map((res: HttpResponse<Apiary[]>) => res.body)
            ).subscribe(
              apiaryData => {
                if (apiaryData.length == 0){
                  this.zones.push(data.body[i]);
                }
              }
            )
          }
        } else {
          this.zoneService.find(this.apiary.zoneId).subscribe(
            (subData: HttpResponse<Zone>) => {
              this.zones = [subData.body].concat(subData.body);
            },
            error => this.onError(error)
          );
        }
      },
      error => this.onError(error)
    );
    this.beekeeperService.query().subscribe(
      data => {
        this.beekeepers = data.body;
      },
      error => this.onError(error)
    );
    this.activatedRoute.data.subscribe(response => {
      this.apiary = response.data;
      this.isNew = this.apiary.id === null || this.apiary.id === undefined;
      this.updateForm(this.apiary);
    });
  }

  updateForm(apiary: Apiary) {
    this.form.patchValue({
      id: apiary.id,
      name: apiary.name,
      state: apiary.state,
      number: apiary.number,
      intensive: apiary.intensive,
      transhumance: apiary.transhumance,
      zoneId: apiary.zoneId,
      beekeeperId: apiary.beekeeperId,
    });
  }

  save() {
    this.isSaving = true;
    const apiary = this.createFromForm();
    apiary.beekeeperId = parseInt(localStorage.getItem("beekeeperId"));
    if (!this.isNew) {
      this.subscribeToSaveResponse(this.apiaryService.update(apiary));
    } else {
      this.subscribeToSaveResponse(this.apiaryService.create(apiary));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<Apiary>>) {
    result.subscribe(
      (res: HttpResponse<Apiary>) => this.onSaveSuccess(res),
      (res: HttpErrorResponse) => this.onError(res.error)
    );
  }

  async onSaveSuccess(response) {
    let action = 'updated';
    if (response.status === 201) {
      action = 'created';
    }
    this.isSaving = false;
    const toast = await this.toastCtrl.create({ message: `Apiary ${action} successfully.`, duration: 2000, position: 'middle' });
    await toast.present();
    if(action = 'created'){
      let navigationExtras : NavigationExtras  = { state : { apiaryId: response.body.id }, queryParams : { apiaryId: response.body.id  } }
      await this.navController.navigateForward('/tabs/entities/hive/new', navigationExtras);
    } else {
      await this.navController.navigateBack('/tabs/entities/apiary');
    }
  }

  previousState() {
    window.history.back();
  }

  async onError(error) {
    this.isSaving = false;
    console.error(error);
    const toast = await this.toastCtrl.create({ message: 'Failed to load data', duration: 2000, position: 'middle' });
    await toast.present();
  }

  private createFromForm(): Apiary {
    return {
      ...new Apiary(),
      id: this.form.get(['id']).value,
      name: this.form.get(['name']).value,
      state: ApiaryState["PENDING"],
      number: this.form.get(['number']).value,
      intensive: this.form.get(['intensive']).value,
      transhumance: this.form.get(['transhumance']).value,
      zoneId: this.form.get(['zoneId']).value,
      beekeeperId: this.form.get(['beekeeperId']).value,
    };
  }

  compareZone(first: Zone, second: Zone): boolean {
    return first && first.id && second && second.id ? first.id === second.id : first === second;
  }

  trackZoneById(index: number, item: Zone) {
    return item.id;
  }
  compareBeekeeper(first: Beekeeper, second: Beekeeper): boolean {
    return first && first.id && second && second.id ? first.id === second.id : first === second;
  }

  trackBeekeeperById(index: number, item: Beekeeper) {
    return item.id;
  }
}
