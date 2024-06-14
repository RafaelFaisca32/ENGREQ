import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { NavController, Platform, ToastController } from '@ionic/angular';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import {AnnualInventoryDeclaration, AnnualInventoyDeclarationState} from './annual-inventory-declaration.model';
import { AnnualInventoryDeclarationService } from './annual-inventory-declaration.service';
import {filter, map} from 'rxjs/operators';
import {Apiary, ApiaryService} from '../apiary';
import {Beekeeper, BeekeeperService} from '../beekeeper';
import {ApiaryInformation} from '../apiary-information';

@Component({
  // eslint-disable-next-line @angular-eslint/component-selector
  selector: 'page-annual-inventory-declaration-update',
  templateUrl: 'annual-inventory-declaration-update.html',
})
export class AnnualInventoryDeclarationUpdatePage implements OnInit {
  annualInventoryDeclaration: AnnualInventoryDeclaration;
  dateDp: any;
  revisionDateDp: any;
  isSaving = false;
  isNew = true;
  isReadyToSave: boolean;
  apiaries: Apiary[];
  beekeeper: Beekeeper;

  // eslint-disable-next-line @typescript-eslint/member-ordering
  form = this.formBuilder.group({
    id: [null, []],
    total: [null, [Validators.required]],
    beekeeperFaxNumber: [null, [Validators.required]],
    beekeeperCompleteName: [null, [Validators.required]],
    beekeeperNif: [null, [Validators.required]],
    date: [null, [Validators.required]],
    beekeeperAddress: [null, [Validators.required]],
    beekeeperPostalCode: [null, [Validators.required]],
    beekeeperPhoneNumber: [null, [Validators.required]],
    beekeeperEntityZoneResidence: [null, [Validators.required]],
    beekeeperNumber: [null, [Validators.required]],
    annualInventoryDeclarationState: [null, []],
    revisionDate: [null, []],
    revisionLocation: [null, []],
    revisorSignature: [null, []],
    revisorName: [null, []],
  });

  constructor(
    private beekeeperService: BeekeeperService,
    private apiaryService: ApiaryService,
    protected activatedRoute: ActivatedRoute,
    protected navController: NavController,
    protected formBuilder: FormBuilder,
    public platform: Platform,
    protected toastCtrl: ToastController,
    private annualInventoryDeclarationService: AnnualInventoryDeclarationService
  ) {
    // Watch the form for changes, and
    this.form.valueChanges.subscribe(v => {
      this.isReadyToSave = this.form.valid;
    });
  }

  ngOnInit() {
    let req;
    const beekeeperId = localStorage.getItem('beekeeperId');

    // eslint-disable-next-line prefer-const,@typescript-eslint/naming-convention
    req = {'Id.equals': beekeeperId};

    this.beekeeperService.query(req).subscribe(
      data => {
        this.beekeeper = data.body[0];
        let reqApiary;
        // eslint-disable-next-line prefer-const,@typescript-eslint/naming-convention
        reqApiary = {'beekeeperId.equals': beekeeperId};
        this.apiaryService.query(reqApiary).subscribe(
          dataApiary => {
            this.apiaries = dataApiary.body;
            let total = 10;
            const apiaryInformations: ApiaryInformation[] = [];
            for (const a of this.apiaries) {
              // eslint-disable-next-line max-len
              apiaryInformations.push(new ApiaryInformation(a.id,a.zoneId,a.name,10,a.intensive,a.transhumance,'x','y','z',10,'?',null));
            }
            console.log('Number total=' + total);
            // eslint-disable-next-line max-len
            this.annualInventoryDeclaration = new AnnualInventoryDeclaration(null,total,this.beekeeper.faxNumber,
              this.beekeeper.beekeeperCompleteName,this.beekeeper.nif,new Date(),this.beekeeper.address,this.beekeeper.postalCode,
              // eslint-disable-next-line max-len
              this.beekeeper.phoneNumber,this.beekeeper.entityZoneResidence.toString(),this.beekeeper.beekeeperNumber,AnnualInventoyDeclarationState.PENDING,
              null,null,null,null,apiaryInformations);

            this.activatedRoute.data.subscribe(response => {
              this.isNew = this.annualInventoryDeclaration.id === null || this.annualInventoryDeclaration.id === undefined;
              this.updateForm(this.annualInventoryDeclaration);
            });
          },
        );
      },
    );
  }

  performData() {


  }
  updateForm(annualInventoryDeclaration: AnnualInventoryDeclaration) {
    this.form.patchValue({
      id: annualInventoryDeclaration.id,
      total: annualInventoryDeclaration.total,
      beekeeperFaxNumber: annualInventoryDeclaration.beekeeperFaxNumber,
      beekeeperCompleteName: annualInventoryDeclaration.beekeeperCompleteName,
      beekeeperNif: annualInventoryDeclaration.beekeeperNif,
      date: this.isNew ? new Date().toISOString().split('T')[0] : annualInventoryDeclaration.date,
      beekeeperAddress: annualInventoryDeclaration.beekeeperAddress,
      beekeeperPostalCode: annualInventoryDeclaration.beekeeperPostalCode,
      beekeeperPhoneNumber: annualInventoryDeclaration.beekeeperPhoneNumber,
      beekeeperEntityZoneResidence: annualInventoryDeclaration.beekeeperEntityZoneResidence,
      beekeeperNumber: annualInventoryDeclaration.beekeeperNumber,
      annualInventoryDeclarationState: annualInventoryDeclaration.annualInventoryDeclarationState,
      revisionDate: this.isNew ? new Date().toISOString().split('T')[0] : annualInventoryDeclaration.revisionDate,
      revisionLocation: annualInventoryDeclaration.revisionLocation,
      revisorSignature: annualInventoryDeclaration.revisorSignature,
      revisorName: annualInventoryDeclaration.revisorName,
    });
  }

  save() {
    this.isSaving = true;
    const annualInventoryDeclaration = this.createFromForm();
    annualInventoryDeclaration.date = new Date(annualInventoryDeclaration.date).toISOString().split('T')[0];
    annualInventoryDeclaration.revisionDate = new Date(annualInventoryDeclaration.revisionDate).toISOString().split('T')[0];
    if (!this.isNew) {
      this.subscribeToSaveResponse(this.annualInventoryDeclarationService.update(annualInventoryDeclaration));
    } else {
      this.subscribeToSaveResponse(this.annualInventoryDeclarationService.create(annualInventoryDeclaration));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<AnnualInventoryDeclaration>>) {
    result.subscribe(
      (res: HttpResponse<AnnualInventoryDeclaration>) => this.onSaveSuccess(res),
      (res: HttpErrorResponse) => this.onError(res.error)
    );
  }

  // eslint-disable-next-line @typescript-eslint/member-ordering
  async onSaveSuccess(response) {
    let action = 'updated';
    if (response.status === 201) {
      action = 'created';
    }
    this.isSaving = false;
    const toast = await this.toastCtrl.create({
      message: `AnnualInventoryDeclaration ${action} successfully.`,
      duration: 2000,
      position: 'middle',
    });
    await toast.present();
    await this.navController.navigateBack('/tabs/entities/apiary-information');
  }

  // eslint-disable-next-line @typescript-eslint/member-ordering
  previousState() {
    window.history.back();
  }

  // eslint-disable-next-line @typescript-eslint/member-ordering
  async onError(error) {
    this.isSaving = false;
    console.error(error);
    const toast = await this.toastCtrl.create({ message: 'Failed to load data', duration: 2000, position: 'middle' });
    await toast.present();
  }

  private createFromForm(): AnnualInventoryDeclaration {
    return {
      ...new AnnualInventoryDeclaration(),
      id: this.form.get(['id']).value,
      total: this.form.get(['total']).value,
      beekeeperFaxNumber: this.form.get(['beekeeperFaxNumber']).value,
      beekeeperCompleteName: this.form.get(['beekeeperCompleteName']).value,
      beekeeperNif: this.form.get(['beekeeperNif']).value,
      date: this.form.get(['date']).value,
      beekeeperAddress: this.form.get(['beekeeperAddress']).value,
      beekeeperPostalCode: this.form.get(['beekeeperPostalCode']).value,
      beekeeperPhoneNumber: this.form.get(['beekeeperPhoneNumber']).value,
      beekeeperEntityZoneResidence: this.form.get(['beekeeperEntityZoneResidence']).value,
      beekeeperNumber: this.form.get(['beekeeperNumber']).value,
      annualInventoryDeclarationState: this.form.get(['annualInventoryDeclarationState']).value,
      revisionDate: this.form.get(['revisionDate']).value,
      revisionLocation: this.form.get(['revisionLocation']).value,
      revisorSignature: this.form.get(['revisorSignature']).value,
      revisorName: this.form.get(['revisorName']).value,
    };
  }
}
