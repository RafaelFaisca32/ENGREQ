import { Component, OnInit } from '@angular/core';
import { UntypedFormBuilder as FormBuilder, Validators } from '@angular/forms';
import { NavController, Platform, ToastController } from '@ionic/angular';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ApiaryInformation } from './apiary-information.model';
import { ApiaryInformationService } from './apiary-information.service';
import { AnnualInventoryDeclaration, AnnualInventoryDeclarationService } from '../annual-inventory-declaration';

@Component({
  selector: 'page-apiary-information-update',
  templateUrl: 'apiary-information-update.html',
})
export class ApiaryInformationUpdatePage implements OnInit {
  apiaryInformation: ApiaryInformation;
  annualInventoryDeclarations: AnnualInventoryDeclaration[];
  isSaving = false;
  isNew = true;
  isReadyToSave: boolean;

  form = this.formBuilder.group({
    id: [null, []],
    zoneNumber: [null, [Validators.required]],
    zoneName: [null, [Validators.required]],
    numberHives: [null, [Validators.required]],
    intensive: ['false', [Validators.required]],
    transhumance: ['false', [Validators.required]],
    coordX: [null, [Validators.required]],
    coordY: [null, [Validators.required]],
    coordZ: [null, [Validators.required]],
    numberFrames: [null, [Validators.required]],
    annualInventoryDeclarationId: [null, []],
  });

  constructor(
    protected activatedRoute: ActivatedRoute,
    protected navController: NavController,
    protected formBuilder: FormBuilder,
    public platform: Platform,
    protected toastCtrl: ToastController,
    private annualInventoryDeclarationService: AnnualInventoryDeclarationService,
    private apiaryInformationService: ApiaryInformationService
  ) {
    // Watch the form for changes, and
    this.form.valueChanges.subscribe(v => {
      this.isReadyToSave = this.form.valid;
    });
  }

  ngOnInit() {
    this.annualInventoryDeclarationService.query().subscribe(
      data => {
        this.annualInventoryDeclarations = data.body;
      },
      error => this.onError(error)
    );
    this.activatedRoute.data.subscribe(response => {
      this.apiaryInformation = response.data;
      this.isNew = this.apiaryInformation.id === null || this.apiaryInformation.id === undefined;
      this.updateForm(this.apiaryInformation);
    });
  }

  updateForm(apiaryInformation: ApiaryInformation) {
    this.form.patchValue({
      id: apiaryInformation.id,
      zoneNumber: apiaryInformation.zoneNumber,
      zoneName: apiaryInformation.zoneName,
      numberHives: apiaryInformation.numberHives,
      intensive: apiaryInformation.intensive,
      transhumance: apiaryInformation.transhumance,
      coordX: apiaryInformation.coordX,
      coordY: apiaryInformation.coordY,
      coordZ: apiaryInformation.coordZ,
      numberFrames: apiaryInformation.numberFrames,
      annualInventoryDeclarationId: apiaryInformation.annualInventoryDeclarationId,
    });
  }

  save() {
    this.isSaving = true;
    const apiaryInformation = this.createFromForm();
    if (!this.isNew) {
      this.subscribeToSaveResponse(this.apiaryInformationService.update(apiaryInformation));
    } else {
      this.subscribeToSaveResponse(this.apiaryInformationService.create(apiaryInformation));
    }
  }

  async onSaveSuccess(response) {
    let action = 'updated';
    if (response.status === 201) {
      action = 'created';
    }
    this.isSaving = false;
    const toast = await this.toastCtrl.create({ message: `ApiaryInformation ${action} successfully.`, duration: 2000, position: 'middle' });
    await toast.present();
    await this.navController.navigateBack('/tabs/entities/apiary-information');
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

  compareAnnualInventoryDeclaration(first: AnnualInventoryDeclaration, second: AnnualInventoryDeclaration): boolean {
    return first && first.id && second && second.id ? first.id === second.id : first === second;
  }

  trackAnnualInventoryDeclarationById(index: number, item: AnnualInventoryDeclaration) {
    return item.id;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ApiaryInformation>>) {
    result.subscribe(
      (res: HttpResponse<ApiaryInformation>) => this.onSaveSuccess(res),
      (res: HttpErrorResponse) => this.onError(res.error)
    );
  }

  private createFromForm(): ApiaryInformation {
    return {
      ...new ApiaryInformation(),
      id: this.form.get(['id']).value,
      zoneNumber: this.form.get(['zoneNumber']).value,
      zoneName: this.form.get(['zoneName']).value,
      numberHives: this.form.get(['numberHives']).value,
      intensive: this.form.get(['intensive']).value,
      transhumance: this.form.get(['transhumance']).value,
      coordX: this.form.get(['coordX']).value,
      coordY: this.form.get(['coordY']).value,
      coordZ: this.form.get(['coordZ']).value,
      numberFrames: this.form.get(['numberFrames']).value,
      annualInventoryDeclarationId: this.form.get(['annualInventoryDeclarationId']).value,
    };
  }
}
