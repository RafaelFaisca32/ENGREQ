import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { NavController, Platform, ToastController } from '@ionic/angular';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';
import { Observable } from 'rxjs';
import { Inspection } from './inspection.model';
import { InspectionService } from './inspection.service';
import { Hive, HiveService } from '../hive';
import { Disease, DiseaseService } from '../disease';

@Component({
  selector: 'page-inspection-update',
  templateUrl: 'inspection-update.html',
})
export class InspectionUpdatePage implements OnInit {
  inspection: Inspection;
  hives: Hive[];
  diseases: Disease[];
  dateDp: any;
  isSaving = false;
  isNew = true;
  isReadyToSave: boolean;
  from: String;
  apiaryId: string;

  form = this.formBuilder.group({
    id: [null, []],
    date: [null, [Validators.required]],
    note: [null, []],
    hiveId: [null, []],
    diseases: [null, []],
  });

  constructor(
    protected activatedRoute: ActivatedRoute,
    protected navController: NavController,
    protected formBuilder: FormBuilder,
    public platform: Platform,
    protected toastCtrl: ToastController,
    private hiveService: HiveService,
    private diseaseService: DiseaseService,
    private inspectionService: InspectionService,
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
    this.diseaseService.query().subscribe(
      data => {
        this.diseases = data.body;
      },
      error => this.onError(error)
    );
    this.activatedRoute.data.subscribe(response => {
      this.inspection = response.data;
      this.isNew = this.inspection.id === null || this.inspection.id === undefined;
      this.updateForm(this.inspection);
    });
  }

  updateForm(inspection: Inspection) {
    this.form.patchValue({
      id: inspection.id,
      date: this.isNew ? new Date().toISOString().split('T')[0] : inspection.date,
      note: inspection.note,
      hiveId: inspection.hiveId,
      diseases: inspection.diseases,
    });
  }

  save() {
    this.isSaving = true;
    const inspection = this.createFromForm();
    inspection.date = new Date(inspection.date).toISOString().split('T')[0];
    if (!this.isNew) {
      this.subscribeToSaveResponse(this.inspectionService.update(inspection));
    } else {
      this.subscribeToSaveResponse(this.inspectionService.create(inspection));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<Inspection>>) {
    result.subscribe(
      (res: HttpResponse<Inspection>) => this.onSaveSuccess(res),
      (res: HttpErrorResponse) => this.onError(res.error)
    );
  }

  async onSaveSuccess(response) {
    let action = 'updated';
    if (response.status === 201) {
      action = 'created';
    }
    this.isSaving = false;
    const toast = await this.toastCtrl.create({ message: `Inspection ${action} successfully.`, duration: 2000, position: 'middle' });
    await toast.present();
    await this.navController.navigateBack('/tabs/entities/inspection');
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

  private createFromForm(): Inspection {
    return {
      ...new Inspection(),
      id: this.form.get(['id']).value,
      date: this.form.get(['date']).value,
      note: this.form.get(['note']).value,
      hiveId: this.form.get(['hiveId']).value,
      diseases: this.form.get(['diseases']).value,
    };
  }

  compareHive(first: Hive, second: Hive): boolean {
    return first && first.id && second && second.id ? first.id === second.id : first === second;
  }

  trackHiveById(index: number, item: Hive) {
    return item.id;
  }
  compareDisease(first: Disease, second: Disease): boolean {
    return first && first.id && second && second.id ? first.id === second.id : first === second;
  }

  trackDiseaseById(index: number, item: Disease) {
    return item.id;
  }
}
