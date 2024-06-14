import {Component, OnInit} from '@angular/core';
import {UntypedFormBuilder as FormBuilder, Validators} from '@angular/forms';
import {NavController, Platform, ToastController} from '@ionic/angular';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';
import {Observable} from 'rxjs';
import {Unfolding, UnfoldingState} from './unfolding.model';
import {UnfoldingService} from './unfolding.service';
import {Hive, HiveService} from '../hive';
import {Frame, FrameService} from '../frame';
import { NetworkCheckerService } from '../../../services/network/network-checker.service';

@Component({
  selector: 'page-unfolding-update',
  templateUrl: 'unfolding-update.html',
})
export class UnfoldingUpdatePage implements OnInit {
  unfolding: Unfolding;
  hives: Hive[];
  framesOldHive: Frame[];
  date: string;
  isSaving = false;
  isNew = true;
  isReadyToSave: boolean;
  from: String;
  apiaryId: string;
  hive: Hive;
  frames: Frame[];
  newFramesOldHive: Frame[];
  newFramesNewHive: Frame[];

  form = this.formBuilder.group({
    id: [null, []],
    observations: [null, []],
    date: [null, [Validators.required]],
    state: [null, []],
    createdHiveId: [null, []],
    hiveId: [null, []],
    removedFramesOldHives: [null, []],
    insertedFramesOldHives: [null, []],
    insertedFramesNewHives: [null, []],
    newHiveId: [null, []],
    newHiveCode: [null, []],
    frames: [null, []],
    newFramesOldHive: [null, []],
    newFramesNewHive: [null, []],
  });

  constructor(
    protected activatedRoute: ActivatedRoute,
    protected navController: NavController,
    protected formBuilder: FormBuilder,
    public platform: Platform,
    protected toastCtrl: ToastController,
    private hiveService: HiveService,
    private frameService: FrameService,
    private unfoldingService: UnfoldingService,
    private route: ActivatedRoute,
    private router: Router,
    private network: NetworkCheckerService
  ) {
    // Watch the form for changes, and
    this.form.valueChanges.subscribe(v => {
      this.isReadyToSave = this.form.valid;
    });

    this.route.queryParams.subscribe(params => {
      this.from = params.from
      this.apiaryId = params.apiaryId
      if (this.router.getCurrentNavigation().extras.state) {
        //this.from = this.router.getCurrentNavigation().extras.state.from;
        //console.log(this.from)
      }
    });
  }

  ngOnInit() {
    //this.network.openCheckNetwork();
    this.network.logNetworkState();
    this.frames = [];
    this.newFramesNewHive = [];
    this.newFramesOldHive = [];
    this.hives = [];
    this.hiveService.query({'apiaryId.equals': this.apiaryId}).subscribe(
      data => {
        let hivesAux = data.body;
        hivesAux.forEach(h => {
          this.frameService.query({'hiveId.equals': h.id }).subscribe(
            dataAux => {
              if (dataAux.body.length > 0){
                this.hives.push(h);
              }
            },
            error => this.onError(error)
          )
        })
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
                this.newFramesNewHive.push(f);
                this.newFramesOldHive.push(f);
              }
            },
            error => this.onError(error)
          )
        )
      },
      error => this.onError(error)
    );
    this.activatedRoute.data.subscribe(response => {
      this.unfolding = response.data;
      this.isNew = this.unfolding.id === null || this.unfolding.id === undefined;
      this.updateForm(this.unfolding);
      if (!this.isNew) {
        this.hiveSelection();
      }
    });
  }

  updateForm(unfolding: Unfolding) {
    this.form.patchValue({
      id: unfolding.id,
      observations: unfolding.observations,
      date: this.isNew ? new Date().toISOString() : unfolding.date,
      state: unfolding.state,
      createdHiveId: unfolding.createdHiveId,
      hiveId: unfolding.hiveId,
      removedFramesOldHives: unfolding.removedFramesOldHives,
      insertedFramesOldHives: unfolding.insertedFramesOldHives,
      insertedFramesNewHives: unfolding.insertedFramesNewHives,
    });
  }

  save() {
    this.isSaving = true;
    const unfolding = this.createFromForm();
    const hiveCreated = this.createFromFormHive();
    if (unfolding.hiveId && unfolding.insertedFramesNewHives.length>0 && unfolding.removedFramesOldHives.length>0 && unfolding.insertedFramesOldHives.length>0 && hiveCreated.code) {
      if (!this.isNew) {
        this.subscribeToSaveResponse(this.unfoldingService.update(unfolding));
      } else {
        this.hiveService.create(hiveCreated).subscribe(
          (res: HttpResponse<Hive>) => {
            unfolding.state = UnfoldingState.REGISTERED;
            unfolding.createdHiveId = res.body.id;
            this.subscribeToSaveResponse(this.unfoldingService.create(unfolding));
          },
          (res: HttpErrorResponse) => this.onError(res.error)
        );
      }
    } else {
      alert("Faltam preencher dados!");
    }
  }

  private createFromFormHive(): Hive {
    return {
      ...new Hive(),
      id: this.form.get(['newHiveId']).value,
      code: this.form.get(['newHiveCode']).value,
      apiaryId: parseInt(this.apiaryId),
      frames: this.form.get(['insertedFramesNewHives']).value.concat(this.form.get(['removedFramesOldHives']).value),
    };
  }

  async onSaveSuccess(response) {
    let action = 'updated';
    if (response.status === 201) {
      action = 'created';
    }
    this.isSaving = false;
    const toast = await this.toastCtrl.create({ message: `Desdobramento ${action} com sucesso.`, duration: 2000, position: 'middle' });
    await toast.present();
    await this.navController.navigateBack('/tabs/entities/unfolding');
  }

  previousState() {
    window.history.back();
  }

  async onError(error) {
    this.isSaving = false;
    console.error(error);
    const toast = await this.toastCtrl.create({ message: 'Erro a carregar a informação', duration: 2000, position: 'middle' });
    await toast.present();
  }

  loadAvailableFrames() {
    this.newFramesOldHive = [].concat(this.frames);
    this.newFramesNewHive = [].concat(this.frames);

    if (this.form.get(['insertedFramesNewHives']).value)
      this.form.get(['insertedFramesNewHives']).value.forEach(f => this.newFramesOldHive.splice(this.newFramesOldHive.indexOf(f),1));

    if (this.form.get(['insertedFramesOldHives']).value)
      this.form.get(['insertedFramesOldHives']).value.forEach(f => this.newFramesNewHive.splice(this.newFramesNewHive.indexOf(f),1));
  }

  compareHive(first: Hive, second: Hive): boolean {
    return first && first.id && second && second.id ? first.id === second.id : first === second;
  }

  trackHiveById(index: number, item: Hive) {
    return item.id;
  }
  compareFrame(first: Frame, second: Frame): boolean {
    return first && first.id && second && second.id ? first.id === second.id : first === second;
  }

  trackFrameById(index: number, item: Frame) {
    return item.id;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<Unfolding>>) {
    result.subscribe(
      (res: HttpResponse<Unfolding>) => {
        this.onSaveSuccess(res)
      },
      (res: HttpErrorResponse) => this.onError(res.error)
    );
  }

  hiveSelection() {
    if (this.form.get(['hiveId']).value) {
      this.hiveService.query({'id.equals': this.form.get(['hiveId']).value}).subscribe(
        data => {
          this.hive = data.body[0];
          this.framesOldHive = this.hive.frames;
        },
        error => this.onError(error)
      );
    }
  }

  private createFromForm(): Unfolding {
    return {
      ...new Unfolding(),
      id: this.form.get(['id']).value,
      observations: this.form.get(['observations']).value,
      date: new Date(this.form.get(['date']).value),
      state: this.form.get(['state']).value,
      createdHiveId: this.form.get(['createdHiveId']).value,
      hiveId: this.form.get(['hiveId']).value,
      removedFramesOldHives: this.form.get(['removedFramesOldHives']).value,
      insertedFramesOldHives: this.form.get(['insertedFramesOldHives']).value,
      insertedFramesNewHives: this.form.get(['insertedFramesNewHives']).value,
    };
  }
}
