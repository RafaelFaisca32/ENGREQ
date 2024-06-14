import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { NavController, Platform, ToastController } from '@ionic/angular';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, NavigationExtras, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Unfolding } from './unfolding.model';
import { UnfoldingService } from './unfolding.service';
import { Hive, HiveService } from '../hive';

@Component({
  selector: 'page-unfolding-update',
  templateUrl: 'unfolding-update.html',
})
export class UnfoldingUpdatePage implements OnInit {
  unfolding: Unfolding;
  hives: Hive[];
  date: string;
  isSaving = false;
  isNew = true;
  isReadyToSave: boolean;
  from: String;
  apiaryId: string;
  hive: Hive;

  form = this.formBuilder.group({
    id: [null, []],
    observations: [null, []],
    date: [null, [Validators.required]],
    hiveId: [null, []],
    newHiveId: [null, []],
    newHiveCode: [null, []],
  });

  constructor(
    protected activatedRoute: ActivatedRoute,
    protected navController: NavController,
    protected formBuilder: FormBuilder,
    public platform: Platform,
    protected toastCtrl: ToastController,
    private hiveService: HiveService,
    private unfoldingService: UnfoldingService,
    private route: ActivatedRoute,
    private router: Router
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
    this.hiveService.query({ 'apiaryId.equals': this.apiaryId }).subscribe(
      data => {
        this.hives = data.body;
      },
      error => this.onError(error)
    );
    this.activatedRoute.data.subscribe(response => {
      this.unfolding = response.data;
      this.isNew = this.unfolding.id === null || this.unfolding.id === undefined;
      this.updateForm(this.unfolding);
    });
  }

  updateForm(unfolding: Unfolding) {
    this.form.patchValue({
      id: unfolding.id,
      observations: unfolding.observations,
      date: this.isNew ? new Date().toISOString() : unfolding.date,
      hiveId: unfolding.hiveId,
    });
  }

  save() {
    this.isSaving = true;
    const unfolding = this.createFromForm();
    if (!this.isNew) {
      this.subscribeToSaveResponse(this.unfoldingService.update(unfolding));
    } else {
      this.subscribeToSaveResponse(this.unfoldingService.create(unfolding));
    }
  }

  protected subscribeToSaveResponseHive(result: Observable<HttpResponse<Hive>>) {
    result.subscribe(
      (res: HttpResponse<Hive>) => this.onSaveSuccess(res),
      (res: HttpErrorResponse) => this.onError(res.error)
    );
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<Unfolding>>) {
    result.subscribe(
      (res: HttpResponse<Unfolding>) => {
        const hive = this.createFromFormHive();
        this.subscribeToSaveResponseHive(this.hiveService.create(hive));
      },
      (res: HttpErrorResponse) => this.onError(res.error)
    );
  }

  async onSaveSuccess(response) {
    let action = 'updated';
    if (response.status === 201) {
      action = 'created';
    }
    this.isSaving = false;
    const toast = await this.toastCtrl.create({ message: `Unfolding ${action} successfully.`, duration: 2000, position: 'middle' });
    await toast.present();
    await this.navController.navigateBack('/tabs/entities/unfolding');
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

  private createFromForm(): Unfolding {
    return {
      ...new Unfolding(),
      id: this.form.get(['id']).value,
      observations: this.form.get(['observations']).value,
      date: new Date(this.form.get(['date']).value),
      hiveId: this.form.get(['hiveId']).value,
    };
  }

  private createFromFormHive(): Hive {
    return {
      ...new Hive(),
      id: this.form.get(['newHiveId']).value,
      code: this.form.get(['newHiveCode']).value,
      apiaryId: parseInt(this.apiaryId),
    };
  }

  compareHive(first: Hive, second: Hive): boolean {
    return first && first.id && second && second.id ? first.id === second.id : first === second;
  }

  trackHiveById(index: number, item: Hive) {
    return item.id;
  }
}
