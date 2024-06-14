import { Component, OnInit } from '@angular/core';
import { UntypedFormBuilder as FormBuilder, Validators } from '@angular/forms';
import { NavController, Platform, ToastController } from '@ionic/angular';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { Frame } from './frame.model';
import { FrameService } from './frame.service';
import { Hive, HiveService } from '../hive';
import { Crest, CrestService } from '../crest';
import { Unfolding, UnfoldingService } from '../unfolding';
import { NetworkCheckerService } from '../../../services/network/network-checker.service';
import { LocalStorageService } from '../../../services/localStorage/local-storage.service';

@Component({
  selector: 'page-frame-update',
  templateUrl: 'frame-update.html',
})
export class FrameUpdatePage implements OnInit {
  frame: Frame;
  hives: Hive[];
  crests: Crest[];
  unfoldings: Unfolding[];
  isSaving = false;
  isNew = true;
  isReadyToSave: boolean;

  form = this.formBuilder.group({
    id: [null, []],
    code: [null, []],
  });

  constructor(
    protected activatedRoute: ActivatedRoute,
    protected navController: NavController,
    protected formBuilder: FormBuilder,
    public platform: Platform,
    protected toastCtrl: ToastController,
    private hiveService: HiveService,
    private crestService: CrestService,
    private unfoldingService: UnfoldingService,
    private frameService: FrameService,
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

    this.localStorage.getData("dataToSave").then(data => {
      console.log(data)
    })

    if(this.network.onlineIndicator){
    this.hiveService.query().subscribe(
      data => {
        this.hives = data.body;
      },
      error => this.onError(error)
    );
    this.crestService.query().subscribe(
      data => {
        this.crests = data.body;
      },
      error => this.onError(error)
    );
    this.unfoldingService.query().subscribe(
      data => {
        this.unfoldings = data.body;
      },
      error => this.onError(error)

    );
  }


    this.activatedRoute.data.subscribe(response => {
      this.frame = response.data;
      this.isNew = this.frame.id === null || this.frame.id === undefined;
      this.updateForm(this.frame);
    });
  }

  updateForm(frame: Frame) {
    this.form.patchValue({
      id: frame.id,
      code: frame.code,
    });
  }

  save() {
    this.isSaving = true;
    const frame = this.createFromForm();


    if(this.network.onlineIndicator){
      if (!this.isNew) {
        this.subscribeToSaveResponse(this.frameService.update(frame));
      } else {
        this.subscribeToSaveResponse(this.frameService.create(frame));
      }
    } else {

      const frameToSave = { frame: frame, isSaving: this.isSaving }
      this.localStorage.getData("dataToSave").then(data => {
        data.frames.push(frameToSave);
        this.localStorage.setData("dataToSave",data);
        this.offlineSuccess()
      })


    }



  }

  async offlineSuccess(){
    let action = "updated";
    if(this.isSaving){
      action = "created"
    }
    this.isSaving = false;
    const toast = await this.toastCtrl.create({ message: `Frame will be ${action} when online.`, duration: 2000, position: 'middle' });
    await toast.present();
    await this.navController.navigateBack('/tabs/entities/frame');
  }

  async onSaveSuccess(response) {
    let action = 'updated';
    if (response.status === 201) {
      action = 'created';
    }
    this.isSaving = false;
    const toast = await this.toastCtrl.create({ message: `Frame ${action} successfully.`, duration: 2000, position: 'middle' });
    await toast.present();
    await this.navController.navigateBack('/tabs/entities/frame');
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

  compareHive(first: Hive, second: Hive): boolean {
    return first && first.id && second && second.id ? first.id === second.id : first === second;
  }

  trackHiveById(index: number, item: Hive) {
    return item.id;
  }
  compareCrest(first: Crest, second: Crest): boolean {
    return first && first.id && second && second.id ? first.id === second.id : first === second;
  }

  trackCrestById(index: number, item: Crest) {
    return item.id;
  }
  compareUnfolding(first: Unfolding, second: Unfolding): boolean {
    return first && first.id && second && second.id ? first.id === second.id : first === second;
  }

  trackUnfoldingById(index: number, item: Unfolding) {
    return item.id;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<Frame>>) {
    result.subscribe(
      (res: HttpResponse<Frame>) => this.onSaveSuccess(res),
      (res: HttpErrorResponse) => this.onError(res.error)
    );
  }

  private createFromForm(): Frame {
    return {
      ...new Frame(),
      id: this.form.get(['id']).value,
      code: this.form.get(['code']).value,
    };
  }
}
