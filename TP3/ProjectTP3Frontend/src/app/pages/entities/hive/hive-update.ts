import { Component, OnInit } from '@angular/core';
import { UntypedFormBuilder as FormBuilder, Validators } from '@angular/forms';
import { NavController, Platform, ToastController } from '@ionic/angular';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { Hive } from './hive.model';
import { HiveService } from './hive.service';
import { Apiary, ApiaryService } from '../apiary';
import { Frame, FrameService } from '../frame';
import { Unfolding, UnfoldingService } from '../unfolding';
import { TranshumanceRequest, TranshumanceRequestService } from '../transhumance-request';
import { AlertController } from '@ionic/angular';
import { NetworkCheckerService } from '../../../services/network/network-checker.service';

@Component({
  selector: 'page-hive-update',
  templateUrl: 'hive-update.html',
})
export class HiveUpdatePage implements OnInit {
  hive: Hive;
  apiaries: Apiary[];
  frames: Frame[];
  unfoldings: Unfolding[];
  transhumanceRequests: TranshumanceRequest[];
  isSaving = false;
  isNew = true;
  isReadyToSave: boolean;

  form = this.formBuilder.group({
    id: [null, []],
    code: [null, [Validators.required]],
    apiaryId: [null, []],
    frames: [null, []],
  });

  constructor(
    protected activatedRoute: ActivatedRoute,
    protected navController: NavController,
    protected formBuilder: FormBuilder,
    public platform: Platform,
    protected toastCtrl: ToastController,
    private apiaryService: ApiaryService,
    private frameService: FrameService,
    private unfoldingService: UnfoldingService,
    private transhumanceRequestService: TranshumanceRequestService,
    private hiveService: HiveService,
    private alertController: AlertController,
    private network: NetworkCheckerService
  ) {
    // Watch the form for changes, and
    this.form.valueChanges.subscribe(v => {
      this.isReadyToSave = this.form.valid;
    });
  }

  async presentAlert() {
    const alert = await this.alertController.create({
      header: 'Aviso',
      message: 'Prentende adicionar mais uma colmeia',
      buttons: [
        {
            text: 'Não',
            role: 'cancel',
            cssClass: 'secondary',
            handler: () => {
                this.save();
            }
        },
        {
            text: 'Sim',
            handler: () => {
                this.save();
                window.location.reload();
            }
        }
    ]
    });

    await alert.present();
  }

  ngOnInit() {
    this.frames = [];
    //this.network.openCheckNetwork();
    this.network.logNetworkState();

    this.apiaryService.query().subscribe(
      data => {
        this.apiaries = data.body;
      },
      error => this.onError(error)
    );
    this.frameService.query( { size: 100 }).subscribe(
      data => {
        let listFrames = data.body;
        listFrames.forEach(f =>
          this.hiveService.query({'frameId.equals': f.id }).subscribe(
            dataAux => {
              if (dataAux.body.length == 0){
                this.frames.push(f);
              }
            },
            error => this.onError(error)
          )
        )
      },
      error => this.onError(error)
    );
    this.unfoldingService.query().subscribe(
      data => {
        this.unfoldings = data.body;
      },
      error => this.onError(error)
    );
    this.transhumanceRequestService.query().subscribe(
      data => {
        this.transhumanceRequests = data.body;
      },
      error => this.onError(error)
    );
    this.activatedRoute.data.subscribe(response => {
      this.hive = response.data;
      this.activatedRoute.queryParams.subscribe(
        (params: {apiaryId: string}) => {
          this.hive.apiaryId = +params.apiaryId;
      });
      this.isNew = this.hive.id === null || this.hive.id === undefined;
      this.updateForm(this.hive);
    });
  }

  updateForm(hive: Hive) {
    this.form.patchValue({
      id: hive.id,
      code: hive.code,
      apiaryId: hive.apiaryId,
      frames: hive.frames,
    });
  }

  save() {
    this.isSaving = true;
    const hive = this.createFromForm();
    if (!this.isNew) {
      this.subscribeToSaveResponse(this.hiveService.update(hive));
    } else {
      this.subscribeToSaveResponse(this.hiveService.create(hive));
    }
  }

  async onSaveSuccess(response) {
    let action = 'updated';
    if (response.status === 201) {
      action = 'created';
    }
    this.isSaving = false;
    const toast = await this.toastCtrl.create({ message: `Hive ${action} successfully.`, duration: 2000, position: 'middle' });
    await toast.present();
    await this.navController.navigateBack('/tabs/entities/apiary');
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

  compareApiary(first: Apiary, second: Apiary): boolean {
    return first && first.id && second && second.id ? first.id === second.id : first === second;
  }

  trackApiaryById(index: number, item: Apiary) {
    return item.id;
  }
  compareFrame(first: Frame, second: Frame): boolean {
    return first && first.id && second && second.id ? first.id === second.id : first === second;
  }

  trackFrameById(index: number, item: Frame) {
    return item.id;
  }
  compareUnfolding(first: Unfolding, second: Unfolding): boolean {
    return first && first.id && second && second.id ? first.id === second.id : first === second;
  }

  trackUnfoldingById(index: number, item: Unfolding) {
    return item.id;
  }
  compareTranshumanceRequest(first: TranshumanceRequest, second: TranshumanceRequest): boolean {
    return first && first.id && second && second.id ? first.id === second.id : first === second;
  }

  trackTranshumanceRequestById(index: number, item: TranshumanceRequest) {
    return item.id;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<Hive>>) {
    result.subscribe(
      (res: HttpResponse<Hive>) => this.onSaveSuccess(res),
      (res: HttpErrorResponse) => this.onError(res.error)
    );
  }

  private createFromForm(): Hive {
    return {
      ...new Hive(),
      id: this.form.get(['id']).value,
      code: this.form.get(['code']).value,
      apiaryId: this.form.get(['apiaryId']).value,
      frames: this.form.get(['frames']).value,
    };
  }
}
