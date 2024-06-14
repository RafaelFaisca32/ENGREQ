import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { NavController, Platform, ToastController } from '@ionic/angular';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { Zone } from './zone.model';
import { ZoneService } from './zone.service';
import { Apiary, ApiaryService } from '../apiary';

@Component({
  selector: 'page-zone-update',
  templateUrl: 'zone-update.html',
})
export class ZoneUpdatePage implements OnInit {
  zone: Zone;
  apiaries: Apiary[];
  isSaving = false;
  isNew = true;
  isReadyToSave: boolean;

  form = this.formBuilder.group({
    id: [null, []],
    name: [null, [Validators.required]],
    number: [null, [Validators.required]],
    coordX: [null, [Validators.required]],
    coordY: [null, [Validators.required]],
    coordZ: [null, [Validators.required]],
    controlledZone: ['false', [Validators.required]],
  });

  constructor(
    protected activatedRoute: ActivatedRoute,
    protected navController: NavController,
    protected formBuilder: FormBuilder,
    public platform: Platform,
    protected toastCtrl: ToastController,
    private apiaryService: ApiaryService,
    private zoneService: ZoneService
  ) {
    // Watch the form for changes, and
    this.form.valueChanges.subscribe(v => {
      this.isReadyToSave = this.form.valid;
    });
  }

  ngOnInit() {
    this.apiaryService.query().subscribe(
      data => {
        this.apiaries = data.body;
      },
      error => this.onError(error)
    );
    this.activatedRoute.data.subscribe(response => {
      this.zone = response.data;
      this.isNew = this.zone.id === null || this.zone.id === undefined;
      this.updateForm(this.zone);
    });
  }

  updateForm(zone: Zone) {
    this.form.patchValue({
      id: zone.id,
      name: zone.name,
      number: zone.number,
      coordX: zone.coordX,
      coordY: zone.coordY,
      coordZ: zone.coordZ,
      controlledZone: zone.controlledZone,
    });
  }

  save() {
    this.isSaving = true;
    const zone = this.createFromForm();
    if (!this.isNew) {
      this.subscribeToSaveResponse(this.zoneService.update(zone));
    } else {
      this.subscribeToSaveResponse(this.zoneService.create(zone));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<Zone>>) {
    result.subscribe(
      (res: HttpResponse<Zone>) => this.onSaveSuccess(res),
      (res: HttpErrorResponse) => this.onError(res.error)
    );
  }

  async onSaveSuccess(response) {
    let action = 'updated';
    if (response.status === 201) {
      action = 'created';
    }
    this.isSaving = false;
    const toast = await this.toastCtrl.create({ message: `Zone ${action} successfully.`, duration: 2000, position: 'middle' });
    await toast.present();
    await this.navController.navigateBack('/tabs/entities/zone');
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

  private createFromForm(): Zone {
    return {
      ...new Zone(),
      id: this.form.get(['id']).value,
      name: this.form.get(['name']).value,
      number: this.form.get(['number']).value,
      coordX: this.form.get(['coordX']).value,
      coordY: this.form.get(['coordY']).value,
      coordZ: this.form.get(['coordZ']).value,
      controlledZone: this.form.get(['controlledZone']).value,
    };
  }

  compareApiary(first: Apiary, second: Apiary): boolean {
    return first && first.id && second && second.id ? first.id === second.id : first === second;
  }

  trackApiaryById(index: number, item: Apiary) {
    return item.id;
  }
}
