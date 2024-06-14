import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { NavController, Platform, ToastController } from '@ionic/angular';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { Frame } from './frame.model';
import { FrameService } from './frame.service';
import { Hive, HiveService } from '../hive';

@Component({
  selector: 'page-frame-update',
  templateUrl: 'frame-update.html',
})
export class FrameUpdatePage implements OnInit {
  frame: Frame;
  hives: Hive[];
  isSaving = false;
  isNew = true;
  isReadyToSave: boolean;

  form = this.formBuilder.group({
    id: [null, []],
    code: [null, []],
    hiveId: [null, []],
  });

  constructor(
    protected activatedRoute: ActivatedRoute,
    protected navController: NavController,
    protected formBuilder: FormBuilder,
    public platform: Platform,
    protected toastCtrl: ToastController,
    private hiveService: HiveService,
    private frameService: FrameService
  ) {
    // Watch the form for changes, and
    this.form.valueChanges.subscribe(v => {
      this.isReadyToSave = this.form.valid;
    });
  }

  ngOnInit() {
    this.hiveService.query().subscribe(
      data => {
        this.hives = data.body;
      },
      error => this.onError(error)
    );
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
      hiveId: frame.hiveId,
    });
  }

  save() {
    this.isSaving = true;
    const frame = this.createFromForm();
    if (!this.isNew) {
      this.subscribeToSaveResponse(this.frameService.update(frame));
    } else {
      this.subscribeToSaveResponse(this.frameService.create(frame));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<Frame>>) {
    result.subscribe(
      (res: HttpResponse<Frame>) => this.onSaveSuccess(res),
      (res: HttpErrorResponse) => this.onError(res.error)
    );
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

  private createFromForm(): Frame {
    return {
      ...new Frame(),
      id: this.form.get(['id']).value,
      code: this.form.get(['code']).value,
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
