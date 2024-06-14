import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { NavController, Platform, ToastController } from '@ionic/angular';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Beekeeper } from './beekeeper.model';
import { BeekeeperService } from './beekeeper.service';
import { User } from '../../../services/user/user.model';
import { UserService } from '../../../services/user/user.service';
import {filter, map} from "rxjs/operators";

@Component({
  selector: 'page-beekeeper-update',
  templateUrl: 'beekeeper-update.html',
})
export class BeekeeperUpdatePage implements OnInit {
  beekeeper: Beekeeper;
  users: User[];
  isSaving = false;
  isNew = true;
  isReadyToSave: boolean;
  from: String;

  form = this.formBuilder.group({
    id: [null, []],
    beekeeperCompleteName: [null, [Validators.required]],
    beekeeperNumber: [null, [Validators.required]],
    entityZoneResidence: [null, [Validators.required]],
    nif: [null, [Validators.required]],
    address: [null, [Validators.required]],
    postalCode: [null, [Validators.required]],
    phoneNumber: [null, [Validators.required]],
    faxNumber: [null, [Validators.required]],
    userId: [null, []],
  });

  constructor(
    protected activatedRoute: ActivatedRoute,
    protected navController: NavController,
    protected formBuilder: FormBuilder,
    public platform: Platform,
    protected toastCtrl: ToastController,
    private userService: UserService,
    private beekeeperService: BeekeeperService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    // Watch the form for changes, and
    this.form.valueChanges.subscribe(v => {
      this.isReadyToSave = this.form.valid;
    });

    this.route.queryParams.subscribe(params => {
      this.from = params.from
      if (this.router.getCurrentNavigation().extras.state) {
        //this.from = this.router.getCurrentNavigation().extras.state.from;
        //console.log(this.from)
      }
    });
  }

  ngOnInit() {
    this.userService.findAll().subscribe(
      data => {
        this.users = [];
        for (let i=0;i<data.length;i++){
          this.beekeeperService.query({'userId.equals':data[i].id}).pipe(
            filter((res: HttpResponse<Beekeeper[]>) => res.ok),
            map((res: HttpResponse<Beekeeper[]>) => res.body)
          )
          .subscribe(
            (response: Beekeeper[]) => {
              if (response.length == 0){
                this.users.push(data[i]);
              }
            });
        }
      },
      error => this.onError(error)
    );
    this.activatedRoute.data.subscribe(response => {
      this.beekeeper = response.data;
      this.isNew = this.beekeeper.id === null || this.beekeeper.id === undefined;
      this.updateForm(this.beekeeper);
    });
  }

  updateForm(beekeeper: Beekeeper) {
    this.form.patchValue({
      id: beekeeper.id,
      beekeeperCompleteName: beekeeper.beekeeperCompleteName,
      beekeeperNumber: beekeeper.beekeeperNumber,
      entityZoneResidence: beekeeper.entityZoneResidence,
      nif: beekeeper.nif,
      address: beekeeper.address,
      postalCode: beekeeper.postalCode,
      phoneNumber: beekeeper.phoneNumber,
      faxNumber: beekeeper.faxNumber,
      userId: beekeeper.userId,
    });
  }

  save() {
    this.isSaving = true;
    const beekeeper = this.createFromForm();
    if (this.from == 'newUser' && localStorage.getItem('userId') != ""){
      beekeeper.userId = parseInt(localStorage.getItem('userId'));
    }
    if (!this.isNew) {
      this.subscribeToSaveResponse(this.beekeeperService.update(beekeeper));
    } else {
      this.subscribeToSaveResponse(this.beekeeperService.create(beekeeper));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<Beekeeper>>) {
    result.subscribe(
      (res: HttpResponse<Beekeeper>) => {
        localStorage.setItem('beekeeperId', res.body.id.toString());
        return this.onSaveSuccess(res);
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
    const toast = await this.toastCtrl.create({ message: `Beekeeper ${action} successfully.`, duration: 2000, position: 'middle' });
    await toast.present();
    await this.navController.navigateBack('/tabs/home');
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

  private createFromForm(): Beekeeper {
    return {
      ...new Beekeeper(),
      id: this.form.get(['id']).value,
      beekeeperCompleteName: this.form.get(['beekeeperCompleteName']).value,
      beekeeperNumber: this.form.get(['beekeeperNumber']).value,
      entityZoneResidence: this.form.get(['entityZoneResidence']).value,
      nif: this.form.get(['nif']).value,
      address: this.form.get(['address']).value,
      postalCode: this.form.get(['postalCode']).value,
      phoneNumber: this.form.get(['phoneNumber']).value,
      faxNumber: this.form.get(['faxNumber']).value,
      userId: this.form.get(['userId']).value,
    };
  }

  compareUser(first: User, second: User): boolean {
    return first && first.id && second && second.id ? first.id === second.id : first === second;
  }

  trackUserById(index: number, item: User) {
    return item.id;
  }
}
