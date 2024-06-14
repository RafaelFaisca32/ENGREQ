"use strict";
(self["webpackChunkapp"] = self["webpackChunkapp"] || []).push([["src_app_pages_entities_hive_hive_module_ts"],{

/***/ 26100:
/*!****************************************************!*\
  !*** ./src/app/pages/entities/hive/hive-update.ts ***!
  \****************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "HiveUpdatePage": () => (/* binding */ HiveUpdatePage)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _hive_update_html_ngResource__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./hive-update.html?ngResource */ 38026);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/forms */ 90587);
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! @ionic/angular */ 93819);
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! @angular/router */ 52816);
/* harmony import */ var _hive_model__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./hive.model */ 82144);
/* harmony import */ var _hive_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./hive.service */ 54232);
/* harmony import */ var _apiary__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../apiary */ 50180);
/* harmony import */ var _transhumance_request__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../transhumance-request */ 79524);











let HiveUpdatePage = class HiveUpdatePage {
    constructor(activatedRoute, navController, formBuilder, platform, toastCtrl, apiaryService, transhumanceRequestService, hiveService, alertController) {
        this.activatedRoute = activatedRoute;
        this.navController = navController;
        this.formBuilder = formBuilder;
        this.platform = platform;
        this.toastCtrl = toastCtrl;
        this.apiaryService = apiaryService;
        this.transhumanceRequestService = transhumanceRequestService;
        this.hiveService = hiveService;
        this.alertController = alertController;
        this.isSaving = false;
        this.isNew = true;
        this.form = this.formBuilder.group({
            id: [null, []],
            code: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_5__.Validators.required]],
            apiaryId: [null, []],
        });
        // Watch the form for changes, and
        this.form.valueChanges.subscribe(v => {
            this.isReadyToSave = this.form.valid;
        });
    }
    presentAlert() {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_6__.__awaiter)(this, void 0, void 0, function* () {
            const alert = yield this.alertController.create({
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
            yield alert.present();
        });
    }
    ngOnInit() {
        this.apiaryService.query().subscribe(data => {
            this.apiaries = data.body;
        }, error => this.onError(error));
        this.transhumanceRequestService.query().subscribe(data => {
            this.transhumanceRequests = data.body;
        }, error => this.onError(error));
        this.activatedRoute.data.subscribe(response => {
            this.hive = response.data;
            this.activatedRoute.queryParams.subscribe((params) => {
                this.hive.apiaryId = +params.apiaryId;
            });
            this.isNew = this.hive.id === null || this.hive.id === undefined;
            this.updateForm(this.hive);
        });
    }
    updateForm(hive) {
        this.form.patchValue({
            id: hive.id,
            code: hive.code,
            apiaryId: hive.apiaryId,
        });
    }
    save() {
        this.isSaving = true;
        const hive = this.createFromForm();
        if (!this.isNew) {
            this.subscribeToSaveResponse(this.hiveService.update(hive));
        }
        else {
            this.subscribeToSaveResponse(this.hiveService.create(hive));
        }
    }
    subscribeToSaveResponse(result) {
        result.subscribe((res) => this.onSaveSuccess(res), (res) => this.onError(res.error));
    }
    onSaveSuccess(response) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_6__.__awaiter)(this, void 0, void 0, function* () {
            let action = 'updated';
            if (response.status === 201) {
                action = 'created';
            }
            this.isSaving = false;
            const toast = yield this.toastCtrl.create({ message: `Hive ${action} successfully.`, duration: 2000, position: 'middle' });
            yield toast.present();
            yield this.navController.navigateBack('/tabs/entities/apiary');
        });
    }
    previousState() {
        window.history.back();
    }
    onError(error) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_6__.__awaiter)(this, void 0, void 0, function* () {
            this.isSaving = false;
            console.error(error);
            const toast = yield this.toastCtrl.create({ message: 'Failed to load data', duration: 2000, position: 'middle' });
            yield toast.present();
        });
    }
    createFromForm() {
        return Object.assign(Object.assign({}, new _hive_model__WEBPACK_IMPORTED_MODULE_1__.Hive()), { id: this.form.get(['id']).value, code: this.form.get(['code']).value, apiaryId: this.form.get(['apiaryId']).value });
    }
    compareApiary(first, second) {
        return first && first.id && second && second.id ? first.id === second.id : first === second;
    }
    trackApiaryById(index, item) {
        return item.id;
    }
    compareTranshumanceRequest(first, second) {
        return first && first.id && second && second.id ? first.id === second.id : first === second;
    }
    trackTranshumanceRequestById(index, item) {
        return item.id;
    }
};
HiveUpdatePage.ctorParameters = () => [
    { type: _angular_router__WEBPACK_IMPORTED_MODULE_7__.ActivatedRoute },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_8__.NavController },
    { type: _angular_forms__WEBPACK_IMPORTED_MODULE_5__.FormBuilder },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_8__.Platform },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_8__.ToastController },
    { type: _apiary__WEBPACK_IMPORTED_MODULE_3__.ApiaryService },
    { type: _transhumance_request__WEBPACK_IMPORTED_MODULE_4__.TranshumanceRequestService },
    { type: _hive_service__WEBPACK_IMPORTED_MODULE_2__.HiveService },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_8__.AlertController }
];
HiveUpdatePage = (0,tslib__WEBPACK_IMPORTED_MODULE_6__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_9__.Component)({
        selector: 'page-hive-update',
        template: _hive_update_html_ngResource__WEBPACK_IMPORTED_MODULE_0__,
    })
], HiveUpdatePage);



/***/ }),

/***/ 55873:
/*!****************************************************!*\
  !*** ./src/app/pages/entities/hive/hive.module.ts ***!
  \****************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "HivePageModule": () => (/* binding */ HivePageModule),
/* harmony export */   "HiveResolve": () => (/* binding */ HiveResolve)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _ngx_translate_core__WEBPACK_IMPORTED_MODULE_12__ = __webpack_require__(/*! @ngx-translate/core */ 87514);
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! @ionic/angular */ 93819);
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_11__ = __webpack_require__(/*! @angular/common */ 36362);
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_13__ = __webpack_require__(/*! @angular/router */ 52816);
/* harmony import */ var _services_auth_user_route_access_service__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../../../services/auth/user-route-access.service */ 51284);
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(/*! @angular/forms */ 90587);
/* harmony import */ var rxjs__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! rxjs */ 64139);
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! rxjs/operators */ 59151);
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! rxjs/operators */ 86942);
/* harmony import */ var _hive__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./hive */ 83044);
/* harmony import */ var _hive_update__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./hive-update */ 26100);
/* harmony import */ var ___WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! . */ 45734);













let HiveResolve = class HiveResolve {
    constructor(service) {
        this.service = service;
    }
    resolve(route, state) {
        const id = route.params.id ? route.params.id : null;
        if (id) {
            return this.service.find(id).pipe((0,rxjs_operators__WEBPACK_IMPORTED_MODULE_4__.filter)((response) => response.ok), (0,rxjs_operators__WEBPACK_IMPORTED_MODULE_5__.map)((hive) => hive.body));
        }
        return (0,rxjs__WEBPACK_IMPORTED_MODULE_6__.of)(new ___WEBPACK_IMPORTED_MODULE_3__.Hive());
    }
};
HiveResolve.ctorParameters = () => [
    { type: ___WEBPACK_IMPORTED_MODULE_3__.HiveService }
];
HiveResolve = (0,tslib__WEBPACK_IMPORTED_MODULE_7__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_8__.Injectable)({ providedIn: 'root' })
], HiveResolve);

const routes = [
    {
        path: '',
        component: _hive__WEBPACK_IMPORTED_MODULE_1__.HivePage,
        data: {
            authorities: ['ROLE_USER'],
        },
        canActivate: [_services_auth_user_route_access_service__WEBPACK_IMPORTED_MODULE_0__.UserRouteAccessService],
    },
    {
        path: 'new',
        component: _hive_update__WEBPACK_IMPORTED_MODULE_2__.HiveUpdatePage,
        resolve: {
            data: HiveResolve,
        },
        data: {
            authorities: ['ROLE_USER'],
        },
        canActivate: [_services_auth_user_route_access_service__WEBPACK_IMPORTED_MODULE_0__.UserRouteAccessService],
    },
    {
        path: ':id/view',
        component: ___WEBPACK_IMPORTED_MODULE_3__.HiveDetailPage,
        resolve: {
            data: HiveResolve,
        },
        data: {
            authorities: ['ROLE_USER'],
        },
        canActivate: [_services_auth_user_route_access_service__WEBPACK_IMPORTED_MODULE_0__.UserRouteAccessService],
    },
    {
        path: ':id/edit',
        component: _hive_update__WEBPACK_IMPORTED_MODULE_2__.HiveUpdatePage,
        resolve: {
            data: HiveResolve,
        },
        data: {
            authorities: ['ROLE_USER'],
        },
        canActivate: [_services_auth_user_route_access_service__WEBPACK_IMPORTED_MODULE_0__.UserRouteAccessService],
    },
];
let HivePageModule = class HivePageModule {
};
HivePageModule = (0,tslib__WEBPACK_IMPORTED_MODULE_7__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_8__.NgModule)({
        declarations: [_hive__WEBPACK_IMPORTED_MODULE_1__.HivePage, _hive_update__WEBPACK_IMPORTED_MODULE_2__.HiveUpdatePage, ___WEBPACK_IMPORTED_MODULE_3__.HiveDetailPage],
        imports: [_ionic_angular__WEBPACK_IMPORTED_MODULE_9__.IonicModule, _angular_forms__WEBPACK_IMPORTED_MODULE_10__.FormsModule, _angular_forms__WEBPACK_IMPORTED_MODULE_10__.ReactiveFormsModule, _angular_common__WEBPACK_IMPORTED_MODULE_11__.CommonModule, _ngx_translate_core__WEBPACK_IMPORTED_MODULE_12__.TranslateModule, _angular_router__WEBPACK_IMPORTED_MODULE_13__.RouterModule.forChild(routes)],
    })
], HivePageModule);



/***/ }),

/***/ 38026:
/*!*****************************************************************!*\
  !*** ./src/app/pages/entities/hive/hive-update.html?ngResource ***!
  \*****************************************************************/
/***/ ((module) => {

module.exports = "<ion-header>\r\n  <ion-toolbar>\r\n    <ion-buttons slot=\"start\">\r\n      <ion-back-button></ion-back-button>\r\n    </ion-buttons>\r\n    <ion-title>Criar/Atualizar colmeia</ion-title>\r\n\r\n    <ion-buttons slot=\"end\">\r\n      <ion-button id=\"present-alert\" [disabled]=\"!isReadyToSave\" (click)=\"presentAlert()\" color=\"primary\">\r\n        <span *ngIf=\"platform.is('ios')\">{{'DONE_BUTTON' | translate}}</span>\r\n        <ion-icon name=\"checkmark\" *ngIf=\"!platform.is('ios')\"></ion-icon>\r\n      </ion-button>\r\n    </ion-buttons>\r\n  </ion-toolbar>\r\n</ion-header>\r\n\r\n<ion-content class=\"ion-padding\">\r\n  <form *ngIf=\"form\" name=\"form\" [formGroup]=\"form\" (ngSubmit)=\"save()\">\r\n    <ion-list>\r\n      <ion-item [hidden]=\"!form.id\">\r\n        <ion-label>ID</ion-label>\r\n        <ion-input type=\"hidden\" id=\"id\" formControlName=\"id\" readonly></ion-input>\r\n      </ion-item>\r\n      <ion-item>\r\n        <ion-label position=\"floating\">Code</ion-label>\r\n        <ion-input type=\"text\" name=\"code\" formControlName=\"code\"></ion-input>\r\n      </ion-item>\r\n    </ion-list>\r\n  </form>\r\n</ion-content>\r\n";

/***/ })

}]);
//# sourceMappingURL=src_app_pages_entities_hive_hive_module_ts.js.map