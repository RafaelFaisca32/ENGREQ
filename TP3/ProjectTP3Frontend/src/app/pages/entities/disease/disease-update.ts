import { Component, OnInit } from '@angular/core';
import { UntypedFormBuilder as FormBuilder, Validators } from '@angular/forms';
import { NavController, Platform, ToastController } from '@ionic/angular';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { Disease } from './disease.model';
import { DiseaseService } from './disease.service';
import { Inspection, InspectionService } from '../inspection';
import { NetworkCheckerService } from '../../../services/network/network-checker.service';

@Component({
  selector: 'page-disease-update',
  templateUrl: 'disease-update.html',
})
export class DiseaseUpdatePage implements OnInit {
  disease: Disease;
  inspections: Inspection[];
  isSaving = false;
  isNew = true;
  isReadyToSave: boolean;

  form = this.formBuilder.group({
    id: [null, []],
    name: [null, []],
    description: [null, []],
  });

  constructor(
    protected activatedRoute: ActivatedRoute,
    protected navController: NavController,
    protected formBuilder: FormBuilder,
    public platform: Platform,
    protected toastCtrl: ToastController,
    private inspectionService: InspectionService,
    private diseaseService: DiseaseService,
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
    this.inspectionService.query().subscribe(
      data => {
        this.inspections = data.body;
      },
      error => this.onError(error)
    );
    this.activatedRoute.data.subscribe(response => {
      this.disease = response.data;
      this.isNew = this.disease.id === null || this.disease.id === undefined;
      this.updateForm(this.disease);
    });
  }

  updateForm(disease: Disease) {
    this.form.patchValue({
      id: disease.id,
      name: disease.name,
      description: disease.description,
    });
  }

  save() {
    this.isSaving = true;
    const disease = this.createFromForm();
    if (!this.isNew) {
      this.subscribeToSaveResponse(this.diseaseService.update(disease));
    } else {
      this.subscribeToSaveResponse(this.diseaseService.create(disease));
    }
  }

  async onSaveSuccess(response) {
    let action = 'updated';
    if (response.status === 201) {
      action = 'created';
    }
    this.isSaving = false;
    const toast = await this.toastCtrl.create({ message: `Disease ${action} successfully.`, duration: 2000, position: 'middle' });
    await toast.present();
    await this.navController.navigateBack('/tabs/entities/disease');
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

  compareInspection(first: Inspection, second: Inspection): boolean {
    return first && first.id && second && second.id ? first.id === second.id : first === second;
  }

  trackInspectionById(index: number, item: Inspection) {
    return item.id;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<Disease>>) {
    result.subscribe(
      (res: HttpResponse<Disease>) => this.onSaveSuccess(res),
      (res: HttpErrorResponse) => this.onError(res.error)
    );
  }

  private createFromForm(): Disease {
    return {
      ...new Disease(),
      id: this.form.get(['id']).value,
      name: this.form.get(['name']).value,
      description: this.form.get(['description']).value,
    };
  }
}
