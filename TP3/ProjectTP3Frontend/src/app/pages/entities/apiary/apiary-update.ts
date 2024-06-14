import { Component, OnInit } from '@angular/core';
import { UntypedFormBuilder as FormBuilder, Validators } from '@angular/forms';
import { NavController, Platform, ToastController } from '@ionic/angular';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, NavigationExtras } from '@angular/router';
import { Observable } from 'rxjs';
import { Apiary, ApiaryState } from './apiary.model';
import { ApiaryService } from './apiary.service';
import { Beekeeper, BeekeeperService } from '../beekeeper';
import { ApiaryZone, ApiaryZoneService } from '../apiary-zone';
import { NetworkCheckerService } from '../../../services/network/network-checker.service';
import { LocalStorageService } from '../../../services/localStorage/local-storage.service';

@Component({
  selector: 'page-apiary-update',
  templateUrl: 'apiary-update.html',
})
export class ApiaryUpdatePage implements OnInit {
  apiary: Apiary;
  beekeepers: Beekeeper[];
  apiaryZones: ApiaryZone[];
  isSaving = false;
  isNew = true;
  isReadyToSave: boolean;

  form = this.formBuilder.group({
    id: [null, []],
    name: [null, [Validators.required]],
    state: [null, []],
    coordX: [null, [Validators.required]],
    coordY: [null, [Validators.required]],
    coordZ: [null, [Validators.required]],
    number: [null, [Validators.required]],
    intensive: ['false', [Validators.required]],
    transhumance: ['false', [Validators.required]],
    beekeeperId: [null, []],
    apiaryZoneId: [null, [Validators.required]],
  });

  constructor(
    protected activatedRoute: ActivatedRoute,
    protected navController: NavController,
    protected formBuilder: FormBuilder,
    public platform: Platform,
    protected toastCtrl: ToastController,
    private beekeeperService: BeekeeperService,
    private apiaryZoneService: ApiaryZoneService,
    private apiaryService: ApiaryService,
    private network: NetworkCheckerService,
    private localStorage: LocalStorageService
  ) {
    // Watch the form for changes, and
    this.form.valueChanges.subscribe(v => {
      this.isReadyToSave = this.form.valid;
    });
  }

  ngOnInit() {
    //this.network.openCheckNetwork();
    this.network.logNetworkState();

    this.beekeeperService.query().subscribe(
      data => {
        this.beekeepers = data.body;
      },
      error => this.onError(error)
    );
    this.apiaryZoneService.query().subscribe(
      data => {
        this.apiaryZones = data.body;
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
      coordX: apiary.coordX,
      coordY: apiary.coordY,
      coordZ: apiary.coordZ,
      number: apiary.number,
      intensive: apiary.intensive,
      transhumance: apiary.transhumance,
      beekeeperId: apiary.beekeeperId,
      apiaryZoneId: apiary.apiaryZoneId,
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

  async onSaveSuccess(response) {
    let action = 'updated';
    if (response.status === 201) {
      action = 'created';
    }
    this.isSaving = false;
    const toast = await this.toastCtrl.create({ message: `Apiary ${action} successfully.`, duration: 2000, position: 'middle' });
    await toast.present();
    let navigationExtras : NavigationExtras  = { state : { apiaryId: response.body.id }, queryParams : { apiaryId: response.body.id  } }
    if(action == 'created'){
      await this.navController.navigateForward('/tabs/entities/hive/new', navigationExtras);
    } else {
      await this.navController.navigateBack('/tabs/entities/hive', navigationExtras);
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

  compareBeekeeper(first: Beekeeper, second: Beekeeper): boolean {
    return first && first.id && second && second.id ? first.id === second.id : first === second;
  }

  trackBeekeeperById(index: number, item: Beekeeper) {
    return item.id;
  }
  compareApiaryZone(first: ApiaryZone, second: ApiaryZone): boolean {
    return first && first.id && second && second.id ? first.id === second.id : first === second;
  }

  trackApiaryZoneById(index: number, item: ApiaryZone) {
    return item.id;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<Apiary>>) {
    result.subscribe(
      (res: HttpResponse<Apiary>) => this.onSaveSuccess(res),
      (res: HttpErrorResponse) => this.onError(res.error)
    );
  }

  private createFromForm(): Apiary {
    return {
      ...new Apiary(),
      id: this.form.get(['id']).value,
      name: this.form.get(['name']).value,
      state: ApiaryState["PENDING"],
      coordX: this.form.get(['coordX']).value,
      coordY: this.form.get(['coordY']).value,
      coordZ: this.form.get(['coordZ']).value,
      number: this.form.get(['number']).value,
      intensive: this.form.get(['intensive']).value,
      transhumance: this.form.get(['transhumance']).value,
      beekeeperId: this.form.get(['beekeeperId']).value,
      apiaryZoneId: this.form.get(['apiaryZoneId']).value,
    };
  }
}
