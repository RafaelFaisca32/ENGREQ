import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { NavController, Platform, ToastController } from '@ionic/angular';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { Crest, CrestState } from './crest.model';
import { CrestService } from './crest.service';
import { Hive, HiveService } from '../hive';
import { NavigationExtras } from "@angular/router";
import { Router } from "@angular/router";

@Component({
  selector: 'page-crest-update',
  templateUrl: 'crest-update.html',
})
export class CrestUpdatePage implements OnInit {
  crest: Crest;
  hives: Hive[];
  initialDateDecantation: string;
  finalDateDecantation: string;
  isSaving = false;
  isNew = true;
  isFinalized = false;
  isReadyToSave: boolean;

  apiaryId: string;

  form = this.formBuilder.group({
    id: [null, []],
    combFrameQuantity: [null, [Validators.required]],
    waxWeight: [null, [Validators.required]],
    timeWastedCentrifuge: [null, [Validators.required]],
    initialDateDecantation: [null, [Validators.required]],
    finalDateDecantation: [null, []],
    producedHoneyQuantity: [null, []],
    state: [null, [Validators.required]],
    hiveId: [null, [Validators.required]],
  });

  constructor(
    protected activatedRoute: ActivatedRoute,
    protected navController: NavController,
    protected formBuilder: FormBuilder,
    public platform: Platform,
    protected toastCtrl: ToastController,
    private hiveService: HiveService,
    private crestService: CrestService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    // Watch the form for changes, and
    this.form.valueChanges.subscribe(v => {
      this.isReadyToSave = this.form.valid;
    });

    this.route.queryParams.subscribe(params => {
      this.apiaryId = params.apiaryId
      if (this.router.getCurrentNavigation().extras.state) {
        //this.from = this.router.getCurrentNavigation().extras.state.from;
        //console.log(this.from)
      }
    });
  }

  ngOnInit() {
    let req = {};
    if (this.apiaryId ){
      req = {'apiaryId.equals' : this.apiaryId}
    }

    this.hiveService.query(req).subscribe(
      data => {
        this.hives = data.body;
      },
      error => this.onError(error)
    );
    this.activatedRoute.data.subscribe(response => {
      this.crest = response.data;
      if(response.data.hive){
        this.crest.hiveId = response.data.hive.id
      }

      this.isNew = this.crest.id === null || this.crest.id === undefined;
      this.isFinalized = this.crest.state === CrestState.FINALIZED;

      this.form.get('state').valueChanges.subscribe((value) => {
        const finalDateDecantation = this.form.get('finalDateDecantation');
        const producedHoneyQuantity = this.form.get('producedHoneyQuantity');

        if (value === 'FINALIZED') {
          finalDateDecantation.setValidators([Validators.required]);
          producedHoneyQuantity.setValidators([Validators.required]);
        } else {
          finalDateDecantation.clearValidators();
          producedHoneyQuantity.clearValidators();
        }

        finalDateDecantation.updateValueAndValidity();
        producedHoneyQuantity.updateValueAndValidity();
      });

      this.updateForm(this.crest);
    });
  }

  updateForm(crest: Crest) {
    this.form.patchValue({
      id: crest.id,
      combFrameQuantity: crest.combFrameQuantity,
      waxWeight: crest.waxWeight,
      timeWastedCentrifuge: crest.timeWastedCentrifuge,
      initialDateDecantation: this.isNew ? new Date().toISOString() : crest.initialDateDecantation,
      finalDateDecantation: this.isNew ? new Date().toISOString() : crest.finalDateDecantation,
      producedHoneyQuantity: crest.producedHoneyQuantity,
      state: crest.state,
      hiveId: crest.hiveId,
    });
  }

  save() {
    this.isSaving = true;
    const crest = this.createFromForm();
    if (!this.isNew) {
      this.subscribeToSaveResponse(this.crestService.update(crest));
    } else {
      this.subscribeToSaveResponse(this.crestService.create(crest));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<Crest>>) {
    result.subscribe(
      (res: HttpResponse<Crest>) => this.onSaveSuccess(res),
      (res: HttpErrorResponse) => this.onError(res.error)
    );
  }

  async onSaveSuccess(response) {
    let action = 'updated';
    if (response.status === 201) {
      action = 'created';
    }
    this.isSaving = false;
    const toast = await this.toastCtrl.create({ message: `Crest ${action} successfully.`, duration: 2000, position: 'middle' });
    await toast.present();
    await this.navController.navigateBack('/tabs/entities/crest');
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

  private createFromForm(): Crest {
    return {
      ...new Crest(),
      id: this.form.get(['id']).value,
      combFrameQuantity: this.form.get(['combFrameQuantity']).value,
      waxWeight: this.form.get(['waxWeight']).value,
      timeWastedCentrifuge: this.form.get(['timeWastedCentrifuge']).value,
      initialDateDecantation: new Date(this.form.get(['initialDateDecantation']).value),
      finalDateDecantation: new Date(this.form.get(['finalDateDecantation']).value),
      producedHoneyQuantity: this.form.get(['producedHoneyQuantity']).value,
      state: this.form.get(['state']).value,
      hiveId: this.form.get(['hiveId']).value,
    };
  }

  compareHive(first: Hive, second: Hive): boolean {
    return first && first.id && second && second.id ? first.id === second.id : first === second;
  }

  trackHiveById(index: number, item: Hive) {
    return item.id;
  }
}
