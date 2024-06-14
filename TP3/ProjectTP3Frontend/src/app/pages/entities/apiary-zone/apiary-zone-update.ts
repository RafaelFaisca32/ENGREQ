import { Component, OnInit } from '@angular/core';
import { UntypedFormBuilder as FormBuilder, Validators } from '@angular/forms';
import { NavController, Platform, ToastController } from '@ionic/angular';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ApiaryZone } from './apiary-zone.model';
import { ApiaryZoneService } from './apiary-zone.service';
import { NetworkCheckerService } from '../../../services/network/network-checker.service';

@Component({
  selector: 'page-apiary-zone-update',
  templateUrl: 'apiary-zone-update.html',
})
export class ApiaryZoneUpdatePage implements OnInit {
  apiaryZone: ApiaryZone;
  isSaving = false;
  isNew = true;
  isReadyToSave: boolean;

  form = this.formBuilder.group({
    id: [null, []],
    name: [null, [Validators.required]],
    controlledZone: ['false', [Validators.required]],
    state: [null, [Validators.required]],
  });

  constructor(
    protected activatedRoute: ActivatedRoute,
    protected navController: NavController,
    protected formBuilder: FormBuilder,
    public platform: Platform,
    protected toastCtrl: ToastController,
    private apiaryZoneService: ApiaryZoneService,
    private network: NetworkCheckerService
  ) {
    // Watch the form for changes, and
    this.form.valueChanges.subscribe(v => {
      this.isReadyToSave = this.form.valid;
    });
  }

  ngOnInit() {

    //this.network.openCheckNetwork();
    this.network.logNetworkState();

    this.activatedRoute.data.subscribe(response => {
      this.apiaryZone = response.data;
      this.isNew = this.apiaryZone.id === null || this.apiaryZone.id === undefined;
      this.updateForm(this.apiaryZone);
    });
  }

  updateForm(apiaryZone: ApiaryZone) {
    this.form.patchValue({
      id: apiaryZone.id,
      name: apiaryZone.name,
      controlledZone: apiaryZone.controlledZone,
      state: apiaryZone.state,
    });
  }

  save() {
    this.isSaving = true;
    const apiaryZone = this.createFromForm();
    if (!this.isNew) {
      this.subscribeToSaveResponse(this.apiaryZoneService.update(apiaryZone));
    } else {
      this.subscribeToSaveResponse(this.apiaryZoneService.create(apiaryZone));
    }
  }

  async onSaveSuccess(response) {
    let action = 'updated';
    if (response.status === 201) {
      action = 'created';
    }
    this.isSaving = false;
    const toast = await this.toastCtrl.create({ message: `ApiaryZone ${action} successfully.`, duration: 2000, position: 'middle' });
    await toast.present();
    await this.navController.navigateBack('/tabs/entities/apiary-zone');
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ApiaryZone>>) {
    result.subscribe(
      (res: HttpResponse<ApiaryZone>) => this.onSaveSuccess(res),
      (res: HttpErrorResponse) => this.onError(res.error)
    );
  }

  private createFromForm(): ApiaryZone {
    return {
      ...new ApiaryZone(),
      id: this.form.get(['id']).value,
      name: this.form.get(['name']).value,
      controlledZone: this.form.get(['controlledZone']).value,
      state: this.form.get(['state']).value,
    };
  }
}
