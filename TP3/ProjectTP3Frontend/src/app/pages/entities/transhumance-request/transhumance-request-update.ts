import { Component, OnInit } from '@angular/core';
import { UntypedFormBuilder as FormBuilder, Validators } from '@angular/forms';
import { NavController, Platform, ToastController } from '@ionic/angular';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { TranshumanceRequest } from './transhumance-request.model';
import { TranshumanceRequestService } from './transhumance-request.service';
import { Beekeeper, BeekeeperService } from '../beekeeper';
import { Apiary, ApiaryService } from '../apiary';
import { Hive, HiveService } from '../hive';
import { NetworkCheckerService } from '../../../services/network/network-checker.service';

@Component({
  selector: 'page-transhumance-request-update',
  templateUrl: 'transhumance-request-update.html',
})
export class TranshumanceRequestUpdatePage implements OnInit {
  transhumanceRequest: TranshumanceRequest;
  beekeepers: Beekeeper[];
  apiaries: Apiary[];
  hives: Hive[];
  requestDate: string;
  isSaving = false;
  isNew = true;
  isReadyToSave: boolean;

  form = this.formBuilder.group({
    id: [null, []],
    beekeeperId: [null, []],
    apiaryId: [null, [Validators.required]],
    destinationApiaryId: [null, [Validators.required]],
    hives: [null, [Validators.required]],
    state: [null, []]
  });

  constructor(
    protected activatedRoute: ActivatedRoute,
    protected navController: NavController,
    protected formBuilder: FormBuilder,
    public platform: Platform,
    protected toastCtrl: ToastController,
    private beekeeperService: BeekeeperService,
    private apiaryService: ApiaryService,
    private hiveService: HiveService,
    private transhumanceRequestService: TranshumanceRequestService,
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
    this.beekeeperService.query().subscribe(
      data => {
        this.beekeepers = data.body;
      },
      error => this.onError(error)
    );
    this.apiaryService.query({ 'beekeeperId.equals': localStorage.getItem("beekeeperId") }).subscribe(
      data => {
        this.apiaries = data.body;
      },
      error => this.onError(error)
    );
    this.loadHivesForApiary();
    this.form.valueChanges.subscribe(v => {
      this.isReadyToSave = this.form.valid;
    });
    this.activatedRoute.data.subscribe(response => {
      this.transhumanceRequest = response.data;
      this.isNew = this.transhumanceRequest.id === null || this.transhumanceRequest.id === undefined;
      this.updateForm(this.transhumanceRequest);
    });
  }

  loadHivesForApiary() {
    const apiaryId = this.form.get('apiaryId').value;
    if (apiaryId) {
      this.hiveService.query({ 'apiaryId.equals': apiaryId }).subscribe(
        data => {
          this.hives = data.body;
        },
        error => this.onError(error)
      );
    } else {
      this.hives = [];
    }
  }


  updateForm(transhumanceRequest: TranshumanceRequest) {
    this.form.patchValue({
      id: transhumanceRequest.id,
      requestDate: this.isNew ? new Date().toISOString() : transhumanceRequest.requestDate,
      state: transhumanceRequest.state,
      beekeeperId: transhumanceRequest.beekeeperId,
      apiaryId: transhumanceRequest.apiaryId,
      destinationApiaryId: transhumanceRequest.destinationApiaryId,
      hives: transhumanceRequest.hives,
    });
    this.loadHivesForApiary();
  }

  onApiaryChange(event: any) {
    this.loadHivesForApiary();
  }

  save() {
    this.isSaving = true;
    const transhumanceRequest = this.createFromForm();
    if (!this.isNew) {
      this.subscribeToSaveResponse(this.transhumanceRequestService.update(transhumanceRequest));
    } else {
      this.subscribeToSaveResponse(this.transhumanceRequestService.create(transhumanceRequest));
    }
  }

  async onSaveSuccess(response) {
    let action = 'updated';
    if (response.status === 201) {
      action = 'created';
    }
    this.isSaving = false;
    const toast = await this.toastCtrl.create({
      message: `TranshumanceRequest ${action} successfully.`,
      duration: 2000,
      position: 'middle',
    });
    await toast.present();
    await this.navController.navigateBack('/tabs/entities/transhumance-request');
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
  compareApiary(first: Apiary, second: Apiary): boolean {
    return first && first.id && second && second.id ? first.id === second.id : first === second;
  }

  trackApiaryById(index: number, item: Apiary) {
    return item.id;
  }
  compareHive(first: Hive, second: Hive): boolean {
    return first && first.id && second && second.id ? first.id === second.id : first === second;
  }

  trackHiveById(index: number, item: Hive) {
    return item.id;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<TranshumanceRequest>>) {
    result.subscribe(
      (res: HttpResponse<TranshumanceRequest>) => this.onSaveSuccess(res),
      (res: HttpErrorResponse) => this.onError(res.error)
    );
  }

  private createFromForm(): TranshumanceRequest {
    return {
      ...new TranshumanceRequest(),
      id: this.form.get(['id']).value,
      beekeeperId: { 'beekeeperId.equals': localStorage.getItem("beekeeperId") }['beekeeperId.equals'] as unknown as number,
      apiaryId: this.form.get(['destinationApiaryId']).value,
      hives: this.form.get(['hives']).value,
    };
  }
}
